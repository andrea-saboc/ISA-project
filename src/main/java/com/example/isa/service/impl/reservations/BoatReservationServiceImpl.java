package com.example.isa.service.impl.reservations;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import com.example.isa.enums.EntityType;
import com.example.isa.exception.BoatOwnerNotAvailable;
import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.service.impl.AdvertisersService;
import com.example.isa.service.impl.ClientService;
import com.example.isa.service.impl.DateCoverter;
import com.example.isa.repository.*;
import com.example.isa.service.impl.RecordIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.isa.dto.CustomReservationForClientDto;
import com.example.isa.dto.ReservationDto;
import com.example.isa.exception.EntityDeletedException;
import com.example.isa.exception.ImpossibleDueToPenaltyPoints;
import com.example.isa.exception.PeriodNoLongerAvailableException;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStartEndDateFormatter;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.ReservationService;

@Service
@Transactional(readOnly=true)
public class BoatReservationServiceImpl implements ReservationService{

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatAvailablePeriodRepository availablePeriodsRepo;
	@Autowired
	AdditionalServiceRepository additinalServicesRepo;
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	BoatReservationSuggestionServiceImpl boatReservationSuggestionService;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	MailService<String> mailService;
	@Autowired
	ClientService clientService;
	@Autowired
	CollectingBoatReservationsServiceImpl collectingBoatReservationsService;
	@Autowired
	CollectionMansionReservationsImpl collectionMansionReservations;
	@Autowired
	AdvertisersService advertisersService;
	@Autowired
	LoyaltyProgramRepository loyaltyProgramRepository;
	@Autowired
	RecordIncomeRepository recordIncomeRepository;
	@Autowired
	RecordIncomeService recordIncomeService;

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public int createReservationForClient(CustomReservationForClientDto dto) throws PeriodNoLongerAvailableException, ParseException{
    	
		ReservationDto res = new ReservationDto(dto);
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;

		Boat entity = boatRepo.findLockedById(res.getEntityId());
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
		Boat boat = boatRepo.findById(res.getEntityId()).orElse(new Boat());
		BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,boat.getId());
		BoatOwner boatOwner=boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();

		if(period == null) {
			return 5;
		}
		else {
			
			Client client = clientRepository.findByEmail(dto.email);
			if (client == null){
				return 2;
			}
			if(!clientHasAnActiveReservationAtEntity(client, boat)){
				return 7;
			}
			if(!clientService.clientAvailable(client, startDate, endDate)){
				return 4;
			}
			BoatReservation newBoatReservation = new BoatReservation(client, startDate,endDate, res.getNumberOfGuests(), dto.toResSearchDto(),
					boat);

			if(!period.getStartDate().equals(startDate)) {
				BoatAvailablePeriod periodBefore = new BoatAvailablePeriod(period.getStartDate(),startDate,period.getBoat());
				availablePeriodsRepo.save(periodBefore);
			}
			if(!period.getEndDate().equals(endDate)) {
				BoatAvailablePeriod periodAfter = new BoatAvailablePeriod(endDate,period.getEndDate(),period.getBoat());
				availablePeriodsRepo.save(periodAfter);
			}
			if(dto.isOwnerPresent && !advertisersService.boatOwnerAvailable(boat.getBoatOwner(), startDate, endDate)){
				return 8;
			}
			newBoatReservation.setOwnerPresent(dto.isOwnerPresent);
			availablePeriodsRepo.delete(period);
	        newBoatReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
	        newBoatReservation.setTotalPrice( dto.getPrice(boat) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res));
			mailService.notifyClientAboutCreatedReservation(newBoatReservation);
			BoatReservation newRes = boatReservationRepo.save(newBoatReservation);
			if(newRes == null){
				return 3;
			}

			newBoatReservation = setLoyalty(client, boatOwner, newBoatReservation, res);

			availablePeriodsRepo.delete(period);

	        newBoatReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
	        newBoatReservation.setTotalPrice( dto.getPrice(boat) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res));
			mailService.notifyClientAboutCreatedReservation(newBoatReservation);

			BoatReservation boatReservation = boatReservationRepo.save(newBoatReservation);
			recordIncomeService.saveBoatRecord(boatReservation.getId());
			return 1;
		}
    }

	private BoatReservation setLoyalty(Client client, BoatOwner boatOwner, BoatReservation newBoatReservation, ReservationDto res) {
		LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findById(1L).orElse(null);
		if (loyaltyProgram != null) {
			client.setLoyaltyPoints((int) (client.getLoyaltyPoints() + loyaltyProgram.client_reservation_score));
			boatOwner.setLoyaltyPoints((int) (boatOwner.getLoyaltyPoints() + loyaltyProgram.owner_reservation_score));
			boatOwnerRepository.save(boatOwner);
			clientRepository.save(client);
			if (client.getLoyaltyPoints() < loyaltyProgram.getSilver_points_min()) {
				newBoatReservation.setTotalPrice((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(), res)));
			}
			if (client.getLoyaltyPoints() >= loyaltyProgram.silver_points_min && client.getLoyaltyPoints() < loyaltyProgram.gold_points_min) {
				newBoatReservation.setTotalPrice(((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(), res)) * (100 - loyaltyProgram.client_discount_silver)) / 100);
			}
			if (client.getLoyaltyPoints() >= loyaltyProgram.gold_points_min) {
				newBoatReservation.setTotalPrice(((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(), res)) * (100 - loyaltyProgram.client_discount_gold)) / 100);
			}
		}
		return newBoatReservation;
	}

	private boolean clientHasAnActiveReservationAtEntity(Client client, AbstractEntity entity) {
		List<AbstractReservation> allClientsReservations = new ArrayList<>();
		if (entity.getEntityType() == EntityType.BOAT){
			allClientsReservations.addAll(collectingBoatReservationsService.getAllNotCancelledReservationsByBoatAndClient(client, entity.getId()));
		} else if(entity.getEntityType() == EntityType.AVENTURE){
		} else if (entity.getEntityType() == EntityType.MANSION){
			allClientsReservations.addAll(collectionMansionReservations.getAllNotCancelledReservationsByMansionAndClient(client, entity.getId()));
		}
		LocalDateTime now = LocalDateTime.now();
		for (AbstractReservation ar : allClientsReservations){
			if(DateCoverter.convertToLocalDateTimeViaInstant(ar.getStartDate()).isBefore(now)
					&& DateCoverter.convertToLocalDateTimeViaInstant(ar.getEndDate()).isAfter(now)){
				return true;
			}
		}
		return false;
	}


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public BoatReservation createReservation(ReservationDto res) throws ParseException, PeriodNoLongerAvailableException, EntityDeletedException, ImpossibleDueToPenaltyPoints, BoatOwnerNotAvailable {

		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
		LoyaltyProgram loyaltyProgram=loyaltyProgramRepository.findById(1L).get();


		BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
	    Boat boat = boatRepo.findByIdAndDeletedFalse(res.getEntityId());
		Boat entity = boatRepo.findLockedById(res.getEntityId());
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
	    Client client = clientRepository.findByEmail(authenticationService.getLoggedUser().getEmail());
		BoatOwner boatOwner=boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();
	    
	    if(period == null) {
	    	throw new PeriodNoLongerAvailableException();
	    }else if (boat == null) {
	    	throw new EntityDeletedException();	    	
	    }else if(client.getPenaltyPoints() > 3) {
	    	throw new ImpossibleDueToPenaltyPoints();
	    }
	    else {
	        BoatReservation newBoatReservation = new BoatReservation(authenticationService.getLoggedUser(), 
	        		startDate,endDate, res.getNumberOfGuests(), res.getPrice(),boat);
	        
	        if(!period.getStartDate().equals(startDate)) {
	        	BoatAvailablePeriod periodBefore = new BoatAvailablePeriod(period.getStartDate(),startDate,period.getBoat());
	        	availablePeriodsRepo.save(periodBefore);
	        }
	        if(!period.getEndDate().equals(endDate)) {
	        	BoatAvailablePeriod periodAfter = new BoatAvailablePeriod(endDate,period.getEndDate(),period.getBoat());
	        	availablePeriodsRepo.save(periodAfter);
	        }
			if(res.getOwnerPresent() && advertisersService.boatOwnerAvailable(boat.getBoatOwner(), startDate, endDate)){
				throw new BoatOwnerNotAvailable();
			}
	        newBoatReservation.setOwnerPresent(res.getOwnerPresent());
	        
	        availablePeriodsRepo.delete(period);	        

			client.setLoyaltyPoints((int) (client.getLoyaltyPoints()+loyaltyProgram.client_reservation_score));
			boatOwner.setLoyaltyPoints((int) (boatOwner.getLoyaltyPoints()+loyaltyProgram.owner_reservation_score));
			boatOwnerRepository.save(boatOwner);
			clientRepository.save(client);

	        newBoatReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
			if(client.getLoyaltyPoints()<loyaltyProgram.getSilver_points_min())
			{
				newBoatReservation.setTotalPrice((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res)));
			}
			if(client.getLoyaltyPoints()>=loyaltyProgram.silver_points_min && client.getLoyaltyPoints()<loyaltyProgram.gold_points_min)
			{
				newBoatReservation.setTotalPrice(((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res))*(100-loyaltyProgram.client_discount_silver))/100);
			}
			if(client.getLoyaltyPoints()>= loyaltyProgram.gold_points_min)
			{
				newBoatReservation.setTotalPrice(((res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res))*(100-loyaltyProgram.client_discount_gold))/100);
			}
		    return boatReservationRepo.save(newBoatReservation);
	    }
	}

	@Override
	public Set<AdditionalService> addAdditionalServices(List<AdditionalService> additionalServices) {
		Set<AdditionalService> services = new HashSet<AdditionalService>();		
        for(AdditionalService id1 : additionalServices) {
        	AdditionalService service = additinalServicesRepo.findById(id1.getId()).orElse(null);
			if(service!=null)
			services.add(service);
        }
        return services;
	}

	@Override
	public double accountAdditionalServices(Set<AdditionalService> additinalServices, ReservationDto res) {
		double additinalServicesPrice = 0;		
		for(AdditionalService s : additinalServices) {       	       	
			additinalServicesPrice += s.getPricePerDay() * res.getNumberOfDays();
			additinalServicesPrice += s.getPricePerHour() * res.getNumberOfHours();
        }		
		return additinalServicesPrice;
	}

	@Override
	@Transactional(readOnly=false)
	public Reservation cancelReservation(long resId) {
		
		BoatReservation res = boatReservationRepo.findById(resId);

		BoatAvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate(),res.getBoat().getId());
		BoatAvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate(),res.getBoat().getId());		
		BoatAvailablePeriod periodToAdd;
		
		if(periodBefore!=null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new BoatAvailablePeriod(periodBefore.getStartDate(),periodAfter.getEndDate(),res.getBoat());	
		}
		else if (periodBefore==null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new BoatAvailablePeriod(res.getStartDate(),periodAfter.getEndDate(),res.getBoat());
		}
		else if (periodBefore!=null && periodAfter==null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			periodToAdd = new BoatAvailablePeriod(periodBefore.getStartDate(),res.getEndDate(),res.getBoat());
		}
		else 
			periodToAdd = new BoatAvailablePeriod(res.getStartDate(),res.getEndDate(),res.getBoat());
		
		
		availablePeriodsRepo.save(periodToAdd);
		BoatReservation b = boatReservationRepo.findById(resId);
		b.setStatus(ReservationStatus.CANCELLED);
		boatReservationRepo.save(b);
		return null;
	}

	public boolean isThereAReservationAfterToday(Boat boat) {
		List<AbstractReservation> allReservations = collectingBoatReservationsService.getAllNotCancelledReservationsByBoat(boat);
		LocalDateTime today = LocalDateTime.now();
		for (AbstractReservation ar : allReservations){
			if ((DateCoverter.convertToLocalDateTimeViaInstant(ar.getStartDate()).isAfter((today))
					|| (DateCoverter.convertToLocalDateTimeViaInstant(ar.getStartDate()).isBefore(today) && DateCoverter.convertToLocalDateTimeViaInstant(ar.getEndDate()).isAfter(today))
			) && (ar.getStatus()== ReservationStatus.RESERVED || ar.getStatus() == ReservationStatus.ACTIVE)){
				return true;
			}
		}
		return false;
	}



}

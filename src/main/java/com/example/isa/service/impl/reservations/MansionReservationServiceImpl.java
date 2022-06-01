package com.example.isa.service.impl.reservations;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import com.example.isa.service.impl.ClientService;
import com.example.isa.service.impl.DateCoverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.isa.dto.CustomReservationForClientDto;
import com.example.isa.dto.ReservationDto;
import com.example.isa.exception.EntityDeletedException;
import com.example.isa.exception.ImpossibleDueToPenaltyPoints;
import com.example.isa.exception.PeriodNoLongerAvailableException;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.ReservationService;


@Service
@Transactional(readOnly=true)
public class MansionReservationServiceImpl implements ReservationService{

	@Autowired
	MansionRepository mansionRepo;
	@Autowired 
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	MansionDiscountReservationRepository mansionDiscountReservationRepository;

	@Autowired
	MansionAvailablePeriodRepository availablePeriodsRepo;
	@Autowired
	AdditionalServiceRepository additinalServicesRepo;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	MailService<String> mailService;
	@Autowired
	ClientService clientService;
	@Autowired
	CollectionMansionReservationsImpl collectionMansionReservationsService;



	public List<AbstractReservation> getAllMansionReservations(Mansion mansion){
		List<AbstractReservation> allReservations = new ArrayList<>(mansionReservationRepo.findAllByMansionAndStatusNot(mansion, ReservationStatus.CANCELLED));
		allReservations.addAll(mansionDiscountReservationRepository.findAllByMansionAndStatusNot(mansion, ReservationStatus.CANCELLED));
		return allReservations;
	}
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public MansionReservation createReservation(ReservationDto res)  throws PeriodNoLongerAvailableException, ParseException, EntityDeletedException, ImpossibleDueToPenaltyPoints {
				
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
		
		MansionAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
		Mansion mansion = mansionRepo.findByIdAndDeletedFalse(res.getEntityId());
		Client client = clientRepository.findByEmail(authenticationService.getLoggedUser().getEmail());
		
		if(period == null) {
			throw new PeriodNoLongerAvailableException();
		}
		else if(mansion == null) {			
			throw new EntityDeletedException();
		}
		else if(client.getPenaltyPoints() > 3) {
			throw new ImpossibleDueToPenaltyPoints();
		}
		else {
			
			 MansionReservation newMansionReservation = new MansionReservation(authenticationService.getLoggedUser(),
					 startDate, endDate, res.getNumberOfGuests(),res.getPrice(), mansion);
		        

		        if(!period.getStartDate().equals(startDate)) {
		        	MansionAvailablePeriod periodBefore = new MansionAvailablePeriod(period.getStartDate(),startDate,period.getMansion());
		        	availablePeriodsRepo.save(periodBefore);
		        }
		        if(!period.getEndDate().equals(endDate)) {
		        	MansionAvailablePeriod periodAfter = new MansionAvailablePeriod(endDate,period.getEndDate(),period.getMansion());
		        	availablePeriodsRepo.save(periodAfter);
		        }
		        
		        
		        newMansionReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
		        newMansionReservation.setTotalPrice(res.getPrice() + accountAdditionalServices(newMansionReservation.getAdditionalServices(),res));
		        
		        availablePeriodsRepo.delete(period);
			    return mansionReservationRepo.save(newMansionReservation);			
		}     		
		
	}


	@Override
	public Set<AdditionalService> addAdditionalServices(List<AdditionalService> additionalServices) {

		Set<AdditionalService> services = new HashSet<AdditionalService>();		
        for(AdditionalService id1 : additionalServices) {
        	AdditionalService service = additinalServicesRepo.findById(id1.getId()).orElse(null);
			services.add(service);
        }
        return services;
	}


	@Override
	public double accountAdditionalServices(Set<AdditionalService> additinalServices, ReservationDto res) {
		double additinalServicesPrice = 0;
		
		int numberOfWeeks = res.getNumberOfDays() / 7;
		int numberOfDays = res.getNumberOfDays() - 7*numberOfWeeks;		
		for(AdditionalService s : additinalServices) {   			
			//dodati i za per week i additinal service
			additinalServicesPrice += s.getPricePerDay() * numberOfDays;
        }		
		return additinalServicesPrice;		
	}


	@Override
	@Transactional(readOnly=false)
	public Reservation cancelReservation(long resId) {
		
		MansionReservation res = mansionReservationRepo.findById(resId);

		MansionAvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate(),res.getMansion().getId());
		MansionAvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate(),res.getMansion().getId());	

		MansionAvailablePeriod periodToAdd;
		
		if(periodBefore!=null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new MansionAvailablePeriod(periodBefore.getStartDate(),periodAfter.getEndDate(),res.getMansion());	
		}
		else if (periodBefore==null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new MansionAvailablePeriod(res.getStartDate(),periodAfter.getEndDate(),res.getMansion());
		}
		else if (periodBefore!=null && periodAfter==null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			periodToAdd = new MansionAvailablePeriod(periodBefore.getStartDate(),res.getEndDate(),res.getMansion());
		}
		else 
			periodToAdd = new MansionAvailablePeriod(res.getStartDate(),res.getEndDate(),res.getMansion());
		
		availablePeriodsRepo.save(periodToAdd);
		//MansionReservation m = mansionReservationRepo.findById(resId);
		res.setStatus(ReservationStatus.CANCELLED);
		mansionReservationRepo.save(res);
		return null;
	}


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public int createReservationForClient(CustomReservationForClientDto dto) throws PeriodNoLongerAvailableException, ParseException{
		ReservationDto res = new ReservationDto(dto);
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;

		MansionAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate, res.getEntityId());
		Mansion mansion = mansionRepo.findByIdAndDeletedFalse(res.getEntityId());
		if(period == null) {
			return 5;
			//throw new PeriodNoLongerAvailableException();
		}
		else {

			Client client = clientRepository.findByEmailAndBlockedFalseAndDeletedFalse(dto.email);
			if (client == null){
				return 2;
			}
			if(!clientService.clientAvailable(client, startDate, endDate)){
				return 4;
			}
			MansionReservation newMansionReservation = new MansionReservation(client, startDate, endDate, res.getNumberOfGuests(), dto.toResSearchDto(), mansion);
			if(!period.getStartDate().equals(startDate)) {
				MansionAvailablePeriod periodBefore = new MansionAvailablePeriod(period.getStartDate(),startDate,period.getMansion());
				availablePeriodsRepo.save(periodBefore);
			}
			if(!period.getEndDate().equals(endDate)) {
				MansionAvailablePeriod periodAfter = new MansionAvailablePeriod(endDate,period.getEndDate(),period.getMansion());
				availablePeriodsRepo.save(periodAfter);
			}


			availablePeriodsRepo.delete(period);
			newMansionReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
			newMansionReservation.setTotalPrice( dto.getPrice(mansion) + accountAdditionalServices(newMansionReservation.getAdditionalServices(),res));
			mailService.notifyClientAboutCreatedReservation(newMansionReservation);
			MansionReservation newRes = mansionReservationRepo.save(newMansionReservation);
			if(newRes == null){
				return 3;
			}
			return 1;
		}
	}


    public boolean isThereAReservationAfterToday(Mansion mansion) {
		List<AbstractReservation> allReservations = collectionMansionReservationsService.getAllNotCancelledReservationsByMansion(mansion);
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

package com.example.isa.service.impl.reservations;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.isa.mail.MailService;
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
import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.Client;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStartEndDateFormatter;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientRepository;
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
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public BoatReservation createReservationForClient(CustomReservationForClientDto dto) throws PeriodNoLongerAvailableException, ParseException{
    	
		ReservationDto res = new ReservationDto(dto);
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;

		BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
		Boat boat = boatRepo.findById(res.getEntityId()).orElse(new Boat());
		
		if(period == null) {
			throw new PeriodNoLongerAvailableException();
		}
		else {
			
			Client client = clientRepository.findByEmail(dto.email);			
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
			
		
			availablePeriodsRepo.delete(period);
	        newBoatReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
	        newBoatReservation.setTotalPrice( dto.getPrice(boat) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res));
			//mailService.notifyClientAboutCreatedReservation(newBoatReservation);
			return boatReservationRepo.save(newBoatReservation);
		}
    }


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public BoatReservation createReservation(ReservationDto res) throws ParseException, PeriodNoLongerAvailableException, EntityDeletedException, ImpossibleDueToPenaltyPoints  {

		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
		    
	    BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
	    Boat boat = boatRepo.findByIdAndDeletedFalse(res.getEntityId());
	    Client client = clientRepository.findByEmail(authenticationService.getLoggedUser().getEmail());
	    
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
	        
	        
	        availablePeriodsRepo.delete(period);	        
	        newBoatReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
	        newBoatReservation.setTotalPrice(res.getPrice(newBoatReservation.getBoat()) + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res));	            
		    return boatReservationRepo.save(newBoatReservation);
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



}

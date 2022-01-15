package com.example.isa.service.impl.reservations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.MakeBoatReservationForClientDTO;
import com.example.isa.dto.ReservationDTO;
import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStartEndDateFormatter;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.ReservationService;

@Service
//@Transactional(readOnly=true)
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
	
	
	//ANDREA 
	
    public List<BoatReservation> getLoggedUserReservations() {
		User user = authenticationService.getLoggedUser();
		BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
		List<Boat> ownersBoats = boatRepo.findBoatByBoatOwner(boatOwner);
		List<BoatReservation> boatReservations = new ArrayList<>();
		for ( Boat boat : ownersBoats) {
			boatReservations.addAll(boatReservationRepo.findAllByBoat(boat));
		}
		return boatReservations;
    }

	public List<BoatReservation> getBoatReservationsByBoat(Long boatId) {
		Boat boat = boatRepo.findById(boatId).get();
		List<BoatReservation> boatReservations = boatReservationRepo.findAllByBoat(boat);
		return boatReservations;
	}

    public BoatReservation createBoatReservationForClient(MakeBoatReservationForClientDTO dto) throws ParseException, PeriodNoLongerAvailableException{
		ReservationDTO res = convertMakeBoatReservationForClientDTO2Reservation(dto);
		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;

		BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());

		if(period == null) {
			throw new PeriodNoLongerAvailableException();
		}
		else {
			Client client = clientRepository.findByEmail(dto.email);
			Boat boat = boatRepo.findById(res.getEntityId()).orElse(new Boat());
			/*
			BoatReservation newBoatReservation = new BoatReservation(client, startDate,endDate, res.getNumberOfGuests(),
					boatReservationSuggestionService.calculateReservationPrice(res.getNumberOfDays(), res.getNumberOfHours(), boat),
					boat);*/

			if(!period.getStartDate().equals(startDate)) {
				BoatAvailablePeriod periodBefore = new BoatAvailablePeriod(period.getStartDate(),startDate,period.getBoat());
				availablePeriodsRepo.save(periodBefore);
			}
			if(!period.getEndDate().equals(endDate)) {
				BoatAvailablePeriod periodAfter = new BoatAvailablePeriod(endDate,period.getEndDate(),period.getBoat());
				availablePeriodsRepo.save(periodAfter);
			}
				
			/*
			Set<AdditionalService> services = new HashSet<AdditionalService>();
			for(long id : res.getAdditionalServices()) {
				AdditionalService service = additinalServicesRepo.findById(id).orElse(null);
				if(service!=null){
					services.add(service);
					newBoatReservation.setTotalPrice( newBoatReservation.getTotalPrice()
							+calculateAdditionalServicesPrice(newBoatReservation,res,service));
				}

			}
			*/
			availablePeriodsRepo.delete(period);
			//newBoatReservation.setAdditionalServices(services);

			//return boatReservationRepo.save(newBoatReservation);
			return null;
		}
    }

	private ReservationDTO convertMakeBoatReservationForClientDTO2Reservation(MakeBoatReservationForClientDTO dto) {
		ReservationDTO reservation = new ReservationDTO();
		reservation.setAdditionalServices(dto.additionalServiceSet);
		reservation.setEntityId(dto.boatId);
		reservation.startDateTime = dto.startDate;
		reservation.setNumberOfDays(dto.days);
		reservation.setNumberOfHours(dto.hours);
		reservation.setNumberOfGuests(dto.numberOfGuests);
		reservation.setPrice(0);
		return reservation;
	}

	
	
	
	//SERVICE IMPLEMENTATION
	
	@Override
	public Reservation createReservation(ReservationDTO res) throws ParseException, PeriodNoLongerAvailableException  {

		ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
		Date startDate = formatter.startDate;
		Date endDate = formatter.endDate;
	
	    
	    BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
	    
	    if(period == null) {
	    	throw new PeriodNoLongerAvailableException();
	    }
	    else {
	        BoatReservation newBoatReservation = new BoatReservation(authenticationService.getLoggedUser(), startDate,endDate, res.getNumberOfGuests(), res.getPrice(),
					boatRepo.findById(res.getEntityId()).orElse(new Boat()));
	        
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
	        newBoatReservation.setTotalPrice(res.getPrice() + accountAdditionalServices(newBoatReservation.getAdditionalServices(),res));	            
		    return boatReservationRepo.save(newBoatReservation);
	    }
	}

	@Override
	public Set<AdditionalService> addAdditionalServices(List<Long> additionalServices) {
		Set<AdditionalService> services = new HashSet<AdditionalService>();		
        for(long id : additionalServices) {       	
        	AdditionalService service = additinalServicesRepo.findById(id).orElse(null);			
			services.add(service);
        }
        return services;
	}

	@Override
	public double accountAdditionalServices(Set<AdditionalService> additinalServices, ReservationDTO res) {
		double additinalServicesPrice = 0;		
		for(AdditionalService s : additinalServices) {       	       	
			additinalServicesPrice += s.getPricePerDay() * res.getNumberOfDays();
			additinalServicesPrice += s.getPricePerHour() * res.getNumberOfHours();
        }		
		return additinalServicesPrice;
	}

	@Override
	public Reservation cancelReservation(long resId) {
		
		BoatReservation res = boatReservationRepo.findById(resId);

		BoatAvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate());
		BoatAvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate());		
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
		b.setCancelled(true);
		boatReservationRepo.save(b);
		return null;
	}

	@Override
	public List<Reservation> GetReservationHistory() {
		Date today = new Date();
		List<Reservation> res = new ArrayList<Reservation>();
		for(BoatReservation m: boatReservationRepo.findAllByUser(authenticationService.getLoggedUser())) {
			if(m.getEndDate().before(today) && !m.isCancelled())
				res.add(m);
		}
		System.out.println("KOLIKO IMA BOATS "+res.size());
		return res;
	}

}

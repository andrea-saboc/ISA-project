package com.example.isa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReservationDTO;
import com.example.isa.model.AdditionalService;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;

@Service
public class BoatReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatAvailablePeriodRepository availablePeriodsRepo;
	@Autowired
	AdditionalServiceRepository additinalServicesRepo;
	
	public Iterable<BoatReservation> GetBoatReservationHistory(){
		return boatReservationRepo.findAllByUser(getLoggedUser());
	}

	public BoatReservation createBoatReservation(ReservationDTO res) {
		
		String sDate = res.getStartDate()+" "+res.getStartTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			Date startDate=formatter.parse(sDate);			
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(startDate);
	        cal.add(Calendar.DAY_OF_MONTH, res.getNumberOfDays());
	        cal.add(Calendar.HOUR, res.getNumberOfHours()); 
	        Date endDate = cal.getTime();
	                
	        System.out.println("Adding days to start date: "+endDate);
	        
	        BoatReservation newBoatReservation = new BoatReservation(getLoggedUser(), startDate,endDate, res.getNumberOfGuests(), res.getPrice(),
					boatRepo.findById(res.getEntityId()).orElse(new Boat()));
	        
	        BoatAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, startDate);
	         
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
	        	newBoatReservation.addService(additinalServicesRepo.findById(id).orElse(null));
	        }
	        
	        
	        */
	        availablePeriodsRepo.delete(period);
		    return boatReservationRepo.save(newBoatReservation);
		    
			} catch (ParseException e) {
			System.out.println("PUČE!");
			e.printStackTrace();
			}
				
		return null;
	}
	

	
	public User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	
	public BoatReservation cancelBoatReservation(long resId) {
		
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
		boatReservationRepo.deleteById(resId);
		return null;
	}
	
}

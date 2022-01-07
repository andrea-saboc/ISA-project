package com.example.isa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.BoatReservationDTO;
import com.example.isa.model.AvailablePeriod;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.repository.UserRepository;

@Service
public class BoatReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatAvailablePeriodRepository availablePeriodsRepo;
	
	public Iterable<BoatReservation> GetBoatReservationHistory(User u){
		return boatReservationRepo.findAllByUser(u);
	}
	
	public Iterable<MansionReservation> GetMansionReservationHistory(User u){
		return null;
	}
	
	public List<Reservation> GetUserReservations(User u){
		
		List<Reservation> ret = new ArrayList<Reservation>();
		ret.addAll(boatReservationRepo.findAllByUser(u));
		
		return ret;
	}
	
	public BoatReservation createBoatReservation(BoatReservationDTO res) {
		
		String sDate = res.getStartDate()+" "+res.getStartTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			Date startDate=formatter.parse(sDate);			
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(startDate);
	        cal.add(Calendar.DAY_OF_MONTH, res.getNumberOfDays()); 
	        Date endDate = cal.getTime();
	                
	        System.out.println("Adding days to start date: "+endDate);
	        
	        BoatReservation newBoatReservation = new BoatReservation(getLoggedUser(), startDate,endDate, res.getNumberOfGuests(), res.getPrice(),
					boatRepo.findById(res.getBoatId()).orElse(new Boat()));
	        
	        AvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, startDate);
	         
	        if(!period.getStartDate().equals(startDate)) {
	        	AvailablePeriod periodBefore = new AvailablePeriod(period.getStartDate(),startDate,period.getBoat());
	        	availablePeriodsRepo.save(periodBefore);
	        }
	        if(!period.getEndDate().equals(endDate)) {
	        	AvailablePeriod periodAfter = new AvailablePeriod(endDate,period.getEndDate(),period.getBoat());
	        	availablePeriodsRepo.save(periodAfter);
	        }
	        
	        
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

		AvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate());
		AvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate());		
		AvailablePeriod periodToAdd;
		
		if(periodBefore!=null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new AvailablePeriod(periodBefore.getStartDate(),periodAfter.getEndDate(),res.getBoat());	
		}
		else if (periodBefore==null && periodAfter!=null) {
			availablePeriodsRepo.deleteById(periodAfter.getId());
			periodToAdd = new AvailablePeriod(res.getStartDate(),periodAfter.getEndDate(),res.getBoat());
		}
		else if (periodBefore!=null && periodAfter==null) {
			availablePeriodsRepo.deleteById(periodBefore.getId());
			periodToAdd = new AvailablePeriod(periodBefore.getStartDate(),res.getEndDate(),res.getBoat());
		}
		else 
			periodToAdd = new AvailablePeriod(res.getStartDate(),res.getEndDate(),res.getBoat());
		
		
		availablePeriodsRepo.save(periodToAdd);
		//availablePeriodsRepo.deleteById(availablePeriodsRepo.getPeriodOfInterest(res.getStartDate(), res.getEndDate()).getId());		
		boatReservationRepo.deleteById(resId);
		return null;
	}
	
}

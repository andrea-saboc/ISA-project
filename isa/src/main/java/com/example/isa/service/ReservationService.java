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
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.repository.UserRepository;

@Service
public class ReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired 
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	BoatRepository boatRepo;
	
	public Iterable<BoatReservation> GetBoatReservationHistory(User u){
		return boatReservationRepo.findAllByUser(u);
	}
	
	public Iterable<MansionReservation> GetMansionReservationHistory(User u){
		return mansionReservationRepo.findAllByUser(u);
	}
	
	public List<Reservation> GetUserReservations(User u){
		
		List<Reservation> ret = new ArrayList<Reservation>();
		ret.addAll(boatReservationRepo.findAllByUser(u));
		
		return ret;
	}
	
	public BoatReservation createBoatReservation(BoatReservationDTO res) {
		
		String sDate = res.getStartDate()+" "+res.getStartTime();
		System.out.println(sDate);
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			Date startDate=formatter.parse(sDate);
			
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(startDate);
	 
	        // add 1 days to current day
	        cal.add(Calendar.DAY_OF_MONTH, res.getNumberOfDays()); 
	        Date endDate = cal.getTime();
	                
	        System.out.println("Adding days to start date: "+endDate);
	        

		    return boatReservationRepo.save(new BoatReservation(getLoggedUser(), startDate,endDate, res.getNumberOfGuests(), res.getPrice(),
					boatRepo.findById(res.getBoatId()).orElse(new Boat())));
		    
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
	
}

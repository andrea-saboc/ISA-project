package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.isa.model.Client;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ActiveReservationDto;
import com.example.isa.service.AuthenticationService;

@Service
public class CollectingActiveReservationsService {

	@Autowired
	BoatReservationRepository boatReservationRepository;
	@Autowired
	AdventureReservationRepository adventureReservationRepository;
	@Autowired
	MansionReservationRepository mansionReservationRepository;
	@Autowired
	AdventureDiscountReservationRepository adventureDiscountReservationRepository;
	@Autowired
	BoatDiscountReservationRepository boatDiscountReservationRepository;
	@Autowired
	MansionDiscountReservationRepository mansionDiscountReservationRepository;
	@Autowired
	BoatReservationRepository boatRepo;

	@Autowired
	MansionRepository mansionRepository;

	@Autowired
	AuthenticationService authenticationService;
	
	
	public List<ActiveReservationDto> getActiveReservations() {
		System.out.println("enteres dervice");
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		ret.addAll(getBoatReservations());
		ret.addAll(getMansionReservations());
		ret.addAll(getMansionDiscountReservations());
		ret.addAll(getBoatDiscountReservations());
		return ret;
	}
	
	public List<ActiveReservationDto>getBoatReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		System.out.println("USer koji trazi trans " + authenticationService.getLoggedUser().getName() );
		
		for(BoatReservation r: boatReservationRepository.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.ACTIVE)) {
			ActiveReservationDto res = new ActiveReservationDto(r);
			res.setAllowedCancelation(isCancellationAllowed(r.getStartDate()));
			ret.add(res); 
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getMansionReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		for(MansionReservation r: mansionReservationRepository.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.ACTIVE)) {
			ActiveReservationDto res = new ActiveReservationDto(r);
			res.setAllowedCancelation(isCancellationAllowed(r.getStartDate()));
			ret.add(res); 
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getMansionDiscountReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		for(MansionDiscountReservation r: mansionDiscountReservationRepository.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.RESERVED)) {
			ActiveReservationDto res = new ActiveReservationDto(r);
			res.setAllowedCancelation(isCancellationAllowed(r.getStartDate()));
			ret.add(res); 
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getBoatDiscountReservations(){
		
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		
		for(BoatDiscountReservation r: boatDiscountReservationRepository.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.RESERVED)) {
			ActiveReservationDto res = new ActiveReservationDto(r);
			res.setAllowedCancelation(isCancellationAllowed(r.getStartDate()));
			ret.add(res); 
		}
		return ret;
	}
	
	public boolean isCancellationAllowed(Date reservationStart ) {
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(reservationStart);
        cal.add(Calendar.DAY_OF_MONTH, -3); 
        
        Date reservationDate = cal.getTime();       
		Date todayDate = new Date();
		return todayDate.before(reservationDate);
	}

	public List<AbstractReservation> getAllReservationsByClient(Client client) {
		List<AbstractReservation> allReservationsByClient = new ArrayList<>(boatReservationRepository.findAllByUserAndStatusNot(client,ReservationStatus.CANCELLED));
		allReservationsByClient.addAll(mansionReservationRepository.findAllByUserAndStatusNot(client, ReservationStatus.CANCELLED));
		allReservationsByClient.addAll(adventureReservationRepository.findAllByUserAndStatusNot(client, ReservationStatus.CANCELLED));
		allReservationsByClient.addAll(boatDiscountReservationRepository.findAllByUserAndStatusNot(client, ReservationStatus.CANCELLED));
		allReservationsByClient.addAll(mansionDiscountReservationRepository.findAllByUserAndStatusNot(client, ReservationStatus.CANCELLED));
		allReservationsByClient.addAll(adventureDiscountReservationRepository.findAllByUserAndStatusNot(client, ReservationStatus.CANCELLED));
		return allReservationsByClient;
	}
}

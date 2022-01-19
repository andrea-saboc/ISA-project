package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.service.AuthenticationService;

@Service
public class CollectingBoatReservationsServiceImpl {
	
	
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	BoatReservationRepository boatReservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatOwnerRepository boatOwnerRepo;

	public List<Reservation> GetReservationHistory() {
		Date today = new Date();
		List<Reservation> res = new ArrayList<Reservation>();
		
		for(BoatReservation m: boatReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.ACTIVE)) {
			if(m.getEndDate().before(today))
				res.add(m);
		}
		
		return res;
	}
	
	

    public List<BoatReservation> getLoggedUserReservations() {

		BoatOwner boatOwner = boatOwnerRepo.findById(authenticationService.getLoggedUser().getId()).get();		
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
}
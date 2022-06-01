package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.isa.model.reservations.AbstractReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatDiscountReservationRepository;
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
	@Autowired
	BoatDiscountReservationRepository boatDiscountReservationRepo;

	public List<BoatReservation> GetReservationHistory() {
		
		List<BoatReservation> ret = new ArrayList<BoatReservation>();
		ret.addAll(GetDiscountReservationHistory());
		ret.addAll(boatReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.CLOSED));
		return ret;
	}
	
	public List<BoatReservation> GetDiscountReservationHistory(){
		
		List<BoatReservation> ret = new ArrayList<BoatReservation>();
		for(BoatDiscountReservation r: 
			boatDiscountReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(), ReservationStatus.CLOSED)) {
			
			ret.add(new BoatReservation(r.getUser(), r.getStartDate(),r.getEndDate(),r.getNumberOfGuests(), r.getPriceWithDiscount(),
			r.getBoat()));
		}
		return ret;
	}

    public List<BoatReservation> getOwnerReservations() {

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
		List<BoatReservation> boatReservations = boatReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED);
		return boatReservations;
	}

	public List<AbstractReservation> getAllNotCancelledReservationsByBoat(Boat boat){
		List<AbstractReservation> allMansionReservations = new ArrayList<>(boatReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
		allMansionReservations.addAll(boatDiscountReservationRepo.findAllByBoatAndStatusNot(boat, ReservationStatus.CANCELLED));
		return allMansionReservations;
	}

	public boolean overlapsWithActiveReservations(Date startDate, Date endDate, Boat boat) {
		List<AbstractReservation> allReservations = getAllNotCancelledReservationsByBoat(boat);
		for (AbstractReservation ar : allReservations){
			if (ar.getStartDate().before(endDate) && startDate.before(ar.getEndDate())){
				return true;
			}
		}
		return false;
	}
}

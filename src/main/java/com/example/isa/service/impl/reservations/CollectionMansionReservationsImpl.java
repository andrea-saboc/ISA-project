package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.Mansion;
import com.example.isa.model.MansionOwner;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.MansionOwnerRepository;
import com.example.isa.repository.MansionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.repository.MansionDiscountReservationRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.AuthenticationService;


@Service
public class CollectionMansionReservationsImpl {
	
	
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	MansionDiscountReservationRepository discountReservationRepo;
	@Autowired
	MansionOwnerRepository mansionOwnerRepository;
	@Autowired
	MansionRepository mansionRepository;

	public List<MansionReservation> GetReservationHistory() {
		
		List<MansionReservation> ret = new ArrayList<MansionReservation>();
		ret.addAll(GetDiscountReservationHistory());
		ret.addAll(mansionReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.CLOSED));
		return ret;
	}
	
	public List<MansionReservation> GetDiscountReservationHistory(){
		
		List<MansionReservation> ret = new ArrayList<MansionReservation>();
		for(MansionDiscountReservation r: 
			discountReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(), ReservationStatus.CLOSED)) {
			
			ret.add(new MansionReservation(r.getUser(), r.getStartDate(),r.getEndDate(),r.getNumberOfGuests(), r.getPriceWithDiscount(),
			r.getMansion()));
		}
		return ret;
	}


    public List<MansionReservation> getOwnerReservation() {
		MansionOwner mansionOwner = mansionOwnerRepository.findById(authenticationService.getLoggedUser().getId()).get();
		List<Mansion> ownersMansions = mansionRepository.findAllByMansionOwnerAndDeletedFalse(mansionOwner);

		List<MansionReservation> mansionReservations= new ArrayList<>();
		for ( Mansion mansion: ownersMansions) {
			mansionReservations.addAll(mansionReservationRepo.findAllByMansion(mansion));
		}
		return mansionReservations;
    }

    public List<MansionReservation> getAllMansionReservationsByMansion(Long boatId) {
		Mansion mansion = mansionRepository.findById(boatId).get();
		List<MansionReservation> mansionReservations = mansionReservationRepo.findAllByMansionAndStatusNot(mansion, ReservationStatus.CANCELLED);
		return mansionReservations;
    }

	public List<AbstractReservation> getAllNotCancelledReservationsByMansion(Mansion mansion){
		List<AbstractReservation> allMansionReservations = new ArrayList<>(mansionReservationRepo.findAllByMansionAndStatusNot(mansion, ReservationStatus.CANCELLED));
		allMansionReservations.addAll(discountReservationRepo.findAllByMansionAndStatusNot(mansion, ReservationStatus.CANCELLED));
		return allMansionReservations;
	}

    public boolean overlapsWithActiveReservations(Date startDate, Date endDate, Mansion mansion) {
		List<AbstractReservation> allReservations = getAllNotCancelledReservationsByMansion(mansion);
		for (AbstractReservation ar : allReservations){
			if (ar.getStartDate().before(endDate) && startDate.before(ar.getEndDate())){
				return true;
			}
		}
		return false;
    }
}

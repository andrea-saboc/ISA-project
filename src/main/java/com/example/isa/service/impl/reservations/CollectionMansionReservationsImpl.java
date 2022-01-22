package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.ReservationStatus;
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


}

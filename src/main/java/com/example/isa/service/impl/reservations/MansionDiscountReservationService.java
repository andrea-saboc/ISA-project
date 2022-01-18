package com.example.isa.service.impl.reservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.MansionDiscountReservationRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;

public class MansionDiscountReservationService implements DiscountReservationService{
	
	@Autowired
	MansionDiscountReservationRepository reservationRepo;
	@Autowired
	MansionRepository mansionRepo;
	@Autowired
	AuthenticationService authenticationService;
	

	@Override
	public List<DiscountReservation> getDiscountReservations(long id) {

    	Mansion mansion = mansionRepo.findById(id);
		return reservationRepo.findAllByMansion(mansion);
	}

	@Override
	public List<DiscountReservation> getReservedDiscountReservations(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiscountReservation makeReservationOnDiscount(long resId) throws PeriodNoLongerAvailableException {
		
    	MansionDiscountReservation res = reservationRepo.findById(resId).orElse(new MansionDiscountReservation());
    	if(res == null) throw new PeriodNoLongerAvailableException();
    	else {
    	res.setStatus(ReservationStatus.RESERVED);
    	res.setUser(authenticationService.getLoggedUser());
    	return reservationRepo.save(res);
    	}
	}
}

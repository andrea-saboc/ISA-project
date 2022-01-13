package com.example.isa.service.reservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatRepository;

@Service
public class BoatDiscountReservationService {
	
	@Autowired
	BoatDiscountReservationRepository reservationRepo;
	@Autowired
	BoatRepository boatRepo;
	
    public List<BoatDiscountReservation> getBoatDiscountReservations(long id) {

    	Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndReservedFalse(boat);
    }
    
    public BoatDiscountReservation makeBoatReservationOnDiscount(long resId) throws PeriodNoLongerAvailableException {
 
    	BoatDiscountReservation res = reservationRepo.findByIdAndReservedFalse(resId);
    	if(res == null) throw new PeriodNoLongerAvailableException();
    	else {
	    	res.setReserved(true);
	    	res.setUser(getLoggedUser());
	    	return reservationRepo.save(res);
    	}
    }
	
    public User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return user;
    }
}

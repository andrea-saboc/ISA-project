package com.example.isa.service.impl.reservations;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.AddNewDiscountReservationBoatDTO;
import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.Boat;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;

@Service
public class BoatDiscountReservationService implements DiscountReservationService{
	
	@Autowired
	BoatDiscountReservationRepository reservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	AuthenticationService authenticationService;
	

	
	@Override
	public List<DiscountReservation> getDiscountReservations(long id) {
    	Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndReservedFalse(boat);
	}

	@Override
	public List<BoatDiscountReservation> getReservedDiscountReservations(long id) {
		Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndReservedTrue(boat);
	}

	@Override
	public DiscountReservation makeReservationOnDiscount(long resId) throws PeriodNoLongerAvailableException {
    	BoatDiscountReservation res = reservationRepo.findByIdAndReservedFalse(resId);
    	if(res == null) throw new PeriodNoLongerAvailableException();
    	else {
	    	res.setReserved(true);
	    	res.setUser(authenticationService.getLoggedUser());
	    	return reservationRepo.save(res);
    	}
	}
	
    
    
    
	public BoatDiscountReservation createBoatDiscountReservation(AddNewDiscountReservationBoatDTO dto) {
		BoatDiscountReservation boatDiscountReservation = new BoatDiscountReservation();
		Boat boat = boatRepo.findById(dto.boatId).get();
		boatDiscountReservation.setBoat(boat);
		boatDiscountReservation.setReserved(false);
		boatDiscountReservation.setCancelled(false);
		boatDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
		boatDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
		boatDiscountReservation.setStartDate(dto.startDate);
		boatDiscountReservation.setEndDate(getEndDate(dto));
		boatDiscountReservation.setType("BOAT");
		reservationRepo.save(boatDiscountReservation);
		return boatDiscountReservation;
	}

	public Date getEndDate(AddNewDiscountReservationBoatDTO reservation) {
		Date endDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(reservation.startDate);
		cal.add(Calendar.DAY_OF_MONTH, reservation.days);
		cal.add(Calendar.HOUR, reservation.hours);
		endDate = cal.getTime();
		return endDate;
	}

}

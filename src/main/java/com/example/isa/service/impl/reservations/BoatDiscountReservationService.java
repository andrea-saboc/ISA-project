package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatOwnerRepository;
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
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	

	
	@Override
	public List<DiscountReservation> getAvailableDiscountReservations(long id) {
    	Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndStatus(boat,ReservationStatus.ACTIVE);
	}

	@Override
	public List<DiscountReservation> getReservedDiscountReservations(long id) {
		Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndStatus(boat,ReservationStatus.RESERVED);
	}
	
	@Override
	public DiscountReservation cancelDiscountReservation(long resId) {		
		BoatDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.RESERVED);
		res.setStatus(ReservationStatus.ACTIVE);
		return reservationRepo.save(res);
	}

	@Override
	public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException{
    	BoatDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.ACTIVE);
    	if(res == null) throw new OfferNotAvailableException();
    	else {
	    	res.setStatus(ReservationStatus.RESERVED);
	    	res.setUser(authenticationService.getLoggedUser());
	    	return reservationRepo.save(res);
    	}
	}
	
	public List<BoatDiscountReservation> getLoggedUserReservation(){
		User user = authenticationService.getLoggedUser();
		BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
		List<Boat> ownersBoats = boatRepo.findBoatByBoatOwner(boatOwner);
		List<BoatDiscountReservation> boatReservations = new ArrayList<>();
		for ( Boat boat : ownersBoats) {
			boatReservations.addAll(reservationRepo.findAllByBoat(boat));
		}
		return boatReservations;
	}
	
	public Date getEndDate(NewDiscountReservationDto reservation) {
		Date endDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(reservation.startDate);
		cal.add(Calendar.DAY_OF_MONTH, reservation.days);
		cal.add(Calendar.HOUR, reservation.hours);
		endDate = cal.getTime();
		return endDate;
	}

	@Override
	public DiscountReservation createDiscountReservation(NewDiscountReservationDto dto) {
		BoatDiscountReservation boatDiscountReservation = new BoatDiscountReservation();
		Boat boat = boatRepo.findById(dto.boatId).get();
		boatDiscountReservation.setBoat(boat);
		boatDiscountReservation.setStatus(ReservationStatus.ACTIVE);
		boatDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
		boatDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
		boatDiscountReservation.setValidUntil(dto.validUntil);
		boatDiscountReservation.setStartDate(dto.startDate);
		boatDiscountReservation.setEndDate(getEndDate(dto));
		boatDiscountReservation.setType("BOAT");
		boatDiscountReservation.setPriceWithoutDiscount(dto.getPrice(boat));
		boatDiscountReservation.calculatePercentageOfDiscount();
		reservationRepo.save(boatDiscountReservation);
		return boatDiscountReservation;
	}



}

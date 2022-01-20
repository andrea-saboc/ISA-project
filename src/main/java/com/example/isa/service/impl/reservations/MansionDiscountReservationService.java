package com.example.isa.service.impl.reservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.Mansion;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.MansionDiscountReservationRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;

@Service
public class MansionDiscountReservationService implements DiscountReservationService{
	
	@Autowired
	MansionDiscountReservationRepository reservationRepo;
	@Autowired
	MansionRepository mansionRepo;
	@Autowired
	AuthenticationService authenticationService;
	

	@Override
	public List<DiscountReservation> getAvailableDiscountReservations(long id) {
    	Mansion mansion = mansionRepo.findById(id);
		return reservationRepo.findAllByMansionAndStatus(mansion,ReservationStatus.ACTIVE);
	}

	@Override
	public List<DiscountReservation> getReservedDiscountReservations(long id) {
    	Mansion mansion = mansionRepo.findById(id);
		return reservationRepo.findAllByMansionAndStatus(mansion,ReservationStatus.RESERVED);
	}

	@Override
	public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException {
		
    	MansionDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.ACTIVE);
    	if(res == null) throw new OfferNotAvailableException();
    	else {
    	res.setStatus(ReservationStatus.RESERVED);
    	res.setUser(authenticationService.getLoggedUser());
    	return reservationRepo.save(res);
    	}
	}

	@Override
	public DiscountReservation createDiscountReservation(NewDiscountReservationDto dto) {
		// TODO Auto-generated method stub
		return null;
	}


}

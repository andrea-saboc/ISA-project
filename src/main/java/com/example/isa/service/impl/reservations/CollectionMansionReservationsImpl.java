package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.AuthenticationService;


@Service
public class CollectionMansionReservationsImpl {
	
	
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	MansionReservationRepository mansionReservationRepo;

	public List<Reservation> GetReservationHistory() {
		Date today = new Date();
		List<Reservation> res = new ArrayList<Reservation>();
		
		for(MansionReservation m: mansionReservationRepo.findAllByUserAndStatus(authenticationService.getLoggedUser(),ReservationStatus.ACTIVE)) {
			if(m.getEndDate().before(today))
				res.add(m);
		}
		
		return res;
	}


}

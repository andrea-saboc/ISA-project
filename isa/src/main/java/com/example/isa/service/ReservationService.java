package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ActiveReservationDTO;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatReservationRepository;

@Service
public class ReservationService {

	@Autowired
	BoatReservationRepository boatResRepo;
	@Autowired
	BoatReservationRepository boatRepo;
	
	public User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	public List<ActiveReservationDTO> getActiveReservations() {
		List<ActiveReservationDTO> ret = new ArrayList<ActiveReservationDTO>();
		ret.addAll(getBoatReservations());
		
		return ret;
	}
	
	public List<ActiveReservationDTO>getBoatReservations(){
		List<ActiveReservationDTO> ret = new ArrayList<ActiveReservationDTO>();
		for(BoatReservation r: boatResRepo.findAllByUser(getLoggedUser())) {
			
			ret.add(new ActiveReservationDTO(r.getId(), r.getType(), r.getStartDate().toString(), r.getEndDate().toString(),
					r.getNumberOfGuests(),null, r.getTotalPrice(),r.getBoat().getName(),
					r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress(),
					r.getBoat().getPromoDescription(), isCancellationAllowed(r)));
		}
		return ret;
	}
	
	public boolean isCancellationAllowed(Reservation r) {
		
		return true;
	}

}

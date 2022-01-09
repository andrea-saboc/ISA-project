package com.example.isa.service.reservations;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ActiveReservationDTO;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.MansionReservationRepository;

@Service
public class ReservationService {

	@Autowired
	BoatReservationRepository boatResRepo;
	@Autowired
	BoatReservationRepository boatRepo;
	@Autowired
	MansionReservationRepository mansionResRepo;
	@Autowired
	BoatReservationRepository mansionRepo;
	
	public User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	public List<ActiveReservationDTO> getActiveReservations() {
		List<ActiveReservationDTO> ret = new ArrayList<ActiveReservationDTO>();
		ret.addAll(getBoatReservations());
		ret.addAll(getMansionReservations());
		return ret;
	}
	
	public List<ActiveReservationDTO>getBoatReservations(){
		List<ActiveReservationDTO> ret = new ArrayList<ActiveReservationDTO>();
		for(BoatReservation r: boatResRepo.findAllByUser(getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			ret.add(new ActiveReservationDTO(r.getId(), r.getType(),formatter.format(r.getStartDate()),formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getTotalPrice(),r.getBoat().getName(),
					r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress(),
					r.getBoat().getPromoDescription(), isCancellationAllowed(r)));
		}
		return ret;
	}
	
	public List<ActiveReservationDTO>getMansionReservations(){
		List<ActiveReservationDTO> ret = new ArrayList<ActiveReservationDTO>();
		for(MansionReservation r: mansionResRepo.findAllByUser(getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			ret.add(new ActiveReservationDTO(r.getId(), r.getType(), formatter.format(r.getStartDate()) ,formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getTotalPrice(),r.getMansion().getName(),
					r.getMansion().getAddress().getCountry() + ", "+r.getMansion().getAddress().getCity()+", "+r.getMansion().getAddress().getAddress(),
					r.getMansion().getPromoDescription(), isCancellationAllowed(r)));
		}
		return ret;
	}
	
	public boolean isCancellationAllowed(Reservation r) {
		
		Date reservationStart = r.getStartDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(reservationStart);
        cal.add(Calendar.DAY_OF_MONTH, -3); 
        
        Date reservationDate = cal.getTime();       
		Date todayDate = new Date();
		return todayDate.before(reservationDate);
	}

}

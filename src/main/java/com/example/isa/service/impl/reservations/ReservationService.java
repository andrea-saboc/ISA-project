package com.example.isa.service.impl.reservations;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ActiveReservationDto;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.MansionDiscountReservationRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.AuthenticationService;

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
	@Autowired
	BoatDiscountReservationRepository boatDiscountResRepo;
	@Autowired
	MansionDiscountReservationRepository mansionDiscountResRepo;
	@Autowired
	AuthenticationService authenticationService;
	
	
	public List<ActiveReservationDto> getActiveReservations() {
		System.out.println("enteres dervice");
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		ret.addAll(getBoatReservations());
		ret.addAll(getMansionReservations());
		ret.addAll(getMansionDiscountReservations());
		ret.addAll(getBoatDiscountReservations());
		return ret;
	}
	
	public List<ActiveReservationDto>getBoatReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		for(BoatReservation r: boatResRepo.findAllByUserAndCancelledFalse(authenticationService.getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			if(r.getEndDate().after(new Date()))
			ret.add(new ActiveReservationDto(r.getId(), r.getType(),formatter.format(r.getStartDate()),formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getTotalPrice(),r.getBoat().getName(),
					r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress(),
					r.getBoat().getPromoDescription(), isCancellationAllowed(r.getStartDate())));
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getMansionReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		for(MansionReservation r: mansionResRepo.findAllByUserAndCancelledFalse(authenticationService.getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			if(r.getEndDate().after(new Date()))
			ret.add(new ActiveReservationDto(r.getId(), r.getType(), formatter.format(r.getStartDate()) ,formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getTotalPrice(),r.getMansion().getName(),
					r.getMansion().getAddress().getCountry() + ", "+r.getMansion().getAddress().getCity()+", "+r.getMansion().getAddress().getAddress(),
					r.getMansion().getPromoDescription(), isCancellationAllowed(r.getStartDate())));
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getMansionDiscountReservations(){
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		for(MansionDiscountReservation r: mansionDiscountResRepo.findAllByUserAndCancelledFalseAndReservedTrue(authenticationService.getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			if(r.getEndDate().after(new Date()))
			ret.add(new ActiveReservationDto(r.getId(), r.getType(), formatter.format(r.getStartDate()) ,formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getPriceWithDiscount(),r.getMansion().getName(),
					r.getMansion().getAddress().getCountry() + ", "+r.getMansion().getAddress().getCity()+", "+r.getMansion().getAddress().getAddress(),
					r.getMansion().getPromoDescription(), isCancellationAllowed(r.getStartDate())));
		}
		return ret;
	}
	
	public List<ActiveReservationDto>getBoatDiscountReservations(){
		
		List<ActiveReservationDto> ret = new ArrayList<ActiveReservationDto>();
		
		for(BoatDiscountReservation r: boatDiscountResRepo.findAllByUserAndCancelledFalseAndReservedTrue(authenticationService.getLoggedUser())) {
			
			Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			//if(r.getEndDate().after(new Date()))
			ret.add(new ActiveReservationDto(r.getId(), r.getType(), formatter.format(r.getStartDate()) ,formatter.format(r.getEndDate()),
					r.getNumberOfGuests(),null, r.getPriceWithDiscount(),r.getBoat().getName(),
					r.getBoat().getAddress().getCountry() + ", "+r.getBoat().getAddress().getCity()+", "+r.getBoat().getAddress().getAddress(),
					r.getBoat().getPromoDescription(), isCancellationAllowed(r.getStartDate())));
		}
		return ret;
	}
	
	public boolean isCancellationAllowed(Date reservationStart ) {
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(reservationStart);
        cal.add(Calendar.DAY_OF_MONTH, -3); 
        
        Date reservationDate = cal.getTime();       
		Date todayDate = new Date();
		return todayDate.before(reservationDate);
	}

}

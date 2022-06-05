package com.example.isa.constants;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.isa.dto.ReservationDto;
import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;

public class ReservationConstants {


	public static final Boat BOAT = new Boat("Milicija", "Promo Milicije",0,0);
	
	public static final User CLIENT = new User() {{this.setId(1L);}};
	
	public static final BoatDiscountReservation BOAT_DISCOUNT_RESERVATION = new BoatDiscountReservation() {{this.setId(1L); this.setStatus(ReservationStatus.ACTIVE);
	this.setStartDate(new Date());this.setEndDate(new Date());}};
	
	public static final MansionDiscountReservation MANSION_DISCOUNT_RESERVATION = new MansionDiscountReservation() {{this.setId(1L);}};

	public static final double EXPECTED_PRICE = 50.00;
	
	
	
	public ReservationDto RESERVATION_PARAMS = new ReservationDto(5, 0);
	
	private static final AdditionalService a1 = new AdditionalService("Wifi", 10, 0, BOAT);
	
	
	public Set<AdditionalService> BOAT_ADDITIONAL_SERVICES = new HashSet<>(1) {{
		add(a1);
	}};
		
}

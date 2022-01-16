package com.example.isa.constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.isa.dto.ReservationDto;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.reservations.AdditionalService;

public class AdditionalServicesConstants {


	public static final Boat BOAT = new Boat("Milicija", "Promo Milicije",0,0);

	public ReservationDto RESERVATION_PARAMS = new ReservationDto(5, 0);
	
	private static final AdditionalService a1 = new AdditionalService("Wifi", 10, 0, BOAT);
	
	
	public Set<AdditionalService> BOAT_ADDITIONAL_SERVICES = new HashSet<>(1) {{
		add(a1);
	}};
		
}

package com.example.isa.dto;

import java.util.List;

import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.BoatReservation;

public class AllBoatOwnerReservationsDTO {
	public List<BoatReservation> boatReservations;
	public List<BoatDiscountReservation> boatDiscountReservations;
	
	public AllBoatOwnerReservationsDTO() {}
}

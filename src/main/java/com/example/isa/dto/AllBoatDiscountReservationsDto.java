package com.example.isa.dto;

import com.example.isa.model.Boat;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;

import java.util.List;

public class AllBoatDiscountReservationsDto {
	
    public List<DiscountReservation> reservedReservations;
    public List<DiscountReservation> freeReservations;

    public AllBoatDiscountReservationsDto(){}
}

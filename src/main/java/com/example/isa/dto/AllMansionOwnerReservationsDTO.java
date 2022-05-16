package com.example.isa.dto;

import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.MansionReservation;

import java.util.List;

public class AllMansionOwnerReservationsDTO {
    public List<MansionReservation> mansionReservations;
    public List<MansionDiscountReservation> mansionDiscountReservations;

    public AllMansionOwnerReservationsDTO(){}
}

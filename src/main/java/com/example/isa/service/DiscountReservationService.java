package com.example.isa.service;

import java.util.List;

import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;

public interface DiscountReservationService {

    public List<DiscountReservation> getDiscountReservations(long id);
    
    public List<BoatDiscountReservation> getReservedDiscountReservations(long id);
    
    public DiscountReservation makeReservationOnDiscount(long resId) throws PeriodNoLongerAvailableException;
    
    
}

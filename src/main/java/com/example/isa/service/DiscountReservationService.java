package com.example.isa.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.reservations.DiscountReservation;

public interface DiscountReservationService {

    public List<DiscountReservation> getAvailableDiscountReservations(long id);
    
    public List<DiscountReservation> getReservedDiscountReservations(long id);
    
    public DiscountReservation createDiscountReservation(NewDiscountReservationDto dto);
    
    public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException;
    
    
}

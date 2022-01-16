package com.example.isa.dto;

import com.example.isa.model.reservations.AdditionalService;

import java.util.Date;

public class AddNewDiscountReservationBoatDto {
    public Long boatId;
    public Date startDate;
    public int days;
    public int hours;
    public int numberOfGuests;
    public double priceWithDiscount;

    public AddNewDiscountReservationBoatDto(){}

}

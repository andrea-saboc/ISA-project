package com.example.isa.dto;

import com.example.isa.model.Boat;
import com.example.isa.model.reservations.AdditionalService;

import java.util.Date;

public class NewDiscountReservationDto {
    public Long boatId;
    public Date startDate;
    public Date validUntil;
    public int days;
    public int hours;
    public int numberOfGuests;
    public double priceWithDiscount;

    public NewDiscountReservationDto(){}
    
    public double getPrice(Boat b) {
		return b.getPricePerDay() * this.days + b.getPricePerHour() * this.hours;
	}

}

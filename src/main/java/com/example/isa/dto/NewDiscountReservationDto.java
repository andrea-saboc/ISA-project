package com.example.isa.dto;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
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

    public double getPrice(Mansion mansion) {
        return mansion.getPricePerDay()*this.days;
    }
    public double getPrice(Adventure adventure) {
        return adventure.getPricePerDay()*this.days + adventure.getPricePerHour() * this.hours;}


    @Override
    public String toString() {
        return "NewDiscountReservationDto{" +
                "boatId=" + boatId +
                ", startDate=" + startDate +
                ", validUntil=" + validUntil +
                ", days=" + days +
                ", hours=" + hours +
                ", numberOfGuests=" + numberOfGuests +
                ", priceWithDiscount=" + priceWithDiscount +
                '}';
    }
}

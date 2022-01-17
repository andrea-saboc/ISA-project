package com.example.isa.dto;

import java.util.Date;
import java.util.List;

import com.example.isa.model.Boat;
import com.example.isa.model.reservations.Reservation;

public class CustomReservationForClientDto {
    public String email;
    public List<Long> additionalServiceSet;
    public Date startDate;
    public int days;
    public  int hours;
    public Long boatId;
    public int numberOfGuests;

    public CustomReservationForClientDto(){}

    @Override
    public String toString() {
        return "MakeBoatReservationForClientDTO{" +
                "email='" + email + '\'' +
                ", additionalServiceSet=" + additionalServiceSet +
                ", startDate=" + startDate +
                ", days=" + days +
                ", hours=" + hours +
                ", boatId=" + boatId +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }

	public double toResSearchDto() {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getPrice(Boat b) {
		return b.getPricePerDay() * this.days + b.getPricePerHour() * this.hours;
	}
	
}

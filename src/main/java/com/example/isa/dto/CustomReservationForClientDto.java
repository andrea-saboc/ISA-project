package com.example.isa.dto;

import java.util.Date;
import java.util.List;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.model.reservations.Reservation;

public class CustomReservationForClientDto {
    public String email;
    public List<AdditionalService> additionalServiceSet;
    public Date startDate;
    public int days;
    public  int hours;
    public Long boatId;
    public int numberOfGuests;
    public boolean isOwnerPresent;


    public CustomReservationForClientDto(){}

    public List<AdditionalService> getAdditionalServiceSet() {
        return additionalServiceSet;
    }

    public void setAdditionalServiceSet(List<AdditionalService> additionalServiceSet) {
        this.additionalServiceSet = additionalServiceSet;
    }

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

	public double toResSearchDto() { //ne znam za cega ovo sluzi
		// TODO Auto-generated method stub
		return 0;
	}
	public double getPrice(Boat b) {
		return b.getPricePerDay() * this.days + b.getPricePerHour() * this.hours;
	}

    public double getPrice(Mansion m){
        return m.getPricePerDay()*this.days;
    }
    public double getPrice(Adventure m){
        return m.getPricePerDay()*this.days+ m.getPricePerHour() * this.hours;
    }
	
}

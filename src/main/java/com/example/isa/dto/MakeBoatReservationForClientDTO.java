package com.example.isa.dto;

import java.util.Date;
import java.util.List;

public class MakeBoatReservationForClientDTO {
    public String email;
    public List<Long> additionalServiceSet;
    public Date startDate;
    public int days;
    public  int hours;
    public Long boatId;
    public int numberOfGuests;

    public MakeBoatReservationForClientDTO(){}

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
}

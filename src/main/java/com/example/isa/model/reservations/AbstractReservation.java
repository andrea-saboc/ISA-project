package com.example.isa.model.reservations;

import java.util.Date;

public abstract class AbstractReservation {
    public abstract double getTotalPrice();
    public abstract Date getStartDate();
    public abstract Date getEndDate();
    public abstract ReservationStatus getStatus();
    public abstract Object getUser();
    public abstract String toString();
}

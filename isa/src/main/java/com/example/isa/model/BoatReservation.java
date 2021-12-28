package com.example.isa.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("BOAT_RESERVATION")
public class BoatReservation extends Reservation{
	
	@OneToOne
	@JoinColumn(name = "boat_id")
	private Boat boat;

	public BoatReservation(String type, User user, Date startDate, int durationInDays, int numberOfClients, Boat boat) {
		super(type, user, startDate, durationInDays, numberOfClients);
		this.boat = boat;
	}
	
	

}

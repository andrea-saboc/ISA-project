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
	
	@OneToOne
	@JoinColumn(name = "feedback_id")
	private ClientFeedback boatFeedback;

	@OneToOne
	@JoinColumn(name = "owner_feedback_id")
	private ClientFeedback boatOwnerFeedback;

	public BoatReservation(User user, Date startDate, int durationInDays, int numberOfClients, Boat boat) {
		super("BOAT", user, startDate, durationInDays, numberOfClients);
		this.boat = boat;
	}

	public BoatReservation() {
		super();
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public ClientFeedback getBoatFeedback() {
		return boatFeedback;
	}

	public void setBoatFeedback(ClientFeedback boatFeedback) {
		this.boatFeedback = boatFeedback;
	}

	public ClientFeedback getBoatOwnerFeedback() {
		return boatOwnerFeedback;
	}

	public void setBoatOwnerFeedback(ClientFeedback boatOwnerFeedback) {
		this.boatOwnerFeedback = boatOwnerFeedback;
	}
	
	
	
	

}

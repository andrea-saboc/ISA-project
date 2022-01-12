package com.example.isa.model.reservations;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;

@Entity
@DiscriminatorValue("BOAT_DISCOUNT_RESERVATION")
public class BoatDiscountReservation extends DiscountReservation{

	@OneToOne
	@JoinColumn(name = "boat_id")
	private Boat boat;
	
	@OneToOne
	@JoinColumn(name = "feedback_id")
	private ClientFeedback boatFeedback;

	@OneToOne
	@JoinColumn(name = "owner_feedback_id")
	private ClientFeedback boatOwnerFeedback;



	
	public BoatDiscountReservation(String type, Date startDate, Date endDate, int numberOfGuests,
			double priceWithDiscount, Boat boat) {
		super(type, startDate, endDate, numberOfGuests, priceWithDiscount);
		this.boat = boat;
	}

	public BoatDiscountReservation() {
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

package com.example.isa.model.reservations;


import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;

@Entity
@DiscriminatorValue("BOAT_DISCOUNT_RESERVATION")
public class BoatDiscountReservation extends DiscountReservation{


	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "boat_id")
	private Boat boat;
	
	@OneToOne
	@JoinColumn(name = "feedback_id")
	private ClientFeedback boatFeedback;

	@OneToOne
	@JoinColumn(name = "owner_feedback_id")
	private ClientFeedback boatOwnerFeedback;

	private Boolean ownerPresent;

	public Boolean getOwnerPresent() {
		return ownerPresent;
	}

	public void setOwnerPresent(Boolean ownerPresent) {
		this.ownerPresent = ownerPresent;
	}

	public BoatDiscountReservation(Date startDate, Date endDate, int numberOfGuests,
								   double priceWithDiscount, Boat boat) {
		super("BOAT", startDate, endDate, numberOfGuests, priceWithDiscount);
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


	
	public BoatDiscountReservation(BoatDiscountReservation res) {
		
		this.setStartDate(res.getStartDate());
		this.setEndDate(res.getEndDate());
		this.boat = res.getBoat();
		this.setNumberOfGuests(res.getNumberOfGuests());
		this.setPercentageOfDiscount(res.getPercentageOfDiscount());
		this.setPriceWithoutDiscount(res.getPriceWithoutDiscount());
		this.setPriceWithDiscount(res.getPriceWithDiscount());
		this.setValidUntil(res.getValidUntil());
		this.setUser(res.getUser());
	}

	@Override
	public String getEntityName() {
		return this.boat.getName();
	}

	@Override
	public double getTotalPrice() {
		return getPriceWithDiscount();
	}
}

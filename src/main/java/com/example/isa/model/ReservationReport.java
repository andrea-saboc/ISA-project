package com.example.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.Reservation;


@Entity
public class ReservationReport {
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;
	
	private String reportText;
	private boolean requestedToSanction;
	private boolean approved;
	private boolean clientShowedUp;
	private boolean notApproved;


	@OneToOne
	private Reservation reservation;
	@OneToOne
	private DiscountReservation discountReservation;
	
	public ReservationReport () {}

	public ReservationReport(String reportText, boolean requestedToSanction, boolean approved, boolean clientShowedUp,
			Reservation reservation, DiscountReservation discountReservation,boolean notApproved) {
		super();
		this.reportText = reportText;
		this.requestedToSanction = requestedToSanction;
		this.approved = approved;
		this.clientShowedUp = clientShowedUp;
		this.reservation = reservation;
		this.discountReservation = discountReservation;
		this.notApproved=notApproved;
	}
	public boolean isNotApproved() {
		return notApproved;
	}

	public void setNotApproved(boolean notApproved) {
		this.notApproved = notApproved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	public boolean isRequestedToSanction() {
		return requestedToSanction;
	}

	public void setRequestedToSanction(boolean requestedToSanction) {
		this.requestedToSanction = requestedToSanction;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isClientShowedUp() {
		return clientShowedUp;
	}

	public void setClientShowedUp(boolean clientShowedUp) {
		this.clientShowedUp = clientShowedUp;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public DiscountReservation getDiscountReservation() {
		return discountReservation;
	}

	public void setDiscountReservation(DiscountReservation discountReservation) {
		this.discountReservation = discountReservation;
	}

	@Override
	public String toString() {
		return "ReservationReport [id=" + id + ", reportText=" + reportText + ", requestedToSanction="
				+ requestedToSanction + ", approved=" + approved + ", clientShowedUp=" + clientShowedUp
				+ ", reservation=" + reservation + ", discountReservation=" + discountReservation + "]";
	}
	
	
	
	
	

}

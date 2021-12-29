package com.example.isa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@DiscriminatorValue("MANSION_RESERVATION")
public class MansionReservation extends Reservation{
	
	@OneToOne
	@JoinColumn(name = "mansion_id")
	private Mansion mansion;

	public MansionReservation(User user, Date startDate, int durationInDays, int numberOfClients, Mansion mansion) {
		super("MANSION", user, startDate, durationInDays, numberOfClients);
		this.mansion = mansion;
	}

	public MansionReservation() {
		super();
	}

	public Mansion getMansion() {
		return mansion;
	}

	public void setBoat(Mansion mansion) {
		this.mansion = mansion;
	}
		


}

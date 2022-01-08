package com.example.isa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BoatAvailablePeriod")
public class BoatAvailablePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_period_id")
    private Long id;
    @Column(name = "start_date")    
	private Date startDate;
    @Column(name = "end_date")
	private Date endDate;

	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_id", referencedColumnName = "id", nullable = true)
    private Boat boat;

	public BoatAvailablePeriod(Long id, Date startDate, Date endDate, Boat boat) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.boat = boat;
	}

	public BoatAvailablePeriod(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}


	
	public BoatAvailablePeriod() {}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public BoatAvailablePeriod(Date startDate, Date endDate, Boat boat) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.boat = boat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AvailablePeriod{" +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", boat=" + boat +
				'}';
	}
}

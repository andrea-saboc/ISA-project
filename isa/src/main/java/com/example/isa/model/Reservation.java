package com.example.isa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "reservation_type", discriminatorType = DiscriminatorType.STRING)
public class Reservation {
	
	private String type="";
	
	
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "system_user")
	private User user;

	private Date startDate;
	private int durationInDays;
	private int numberOfClients;
		
	public Reservation(String type, User user, Date startDate, int durationInDays, int numberOfClients) {
		super();
		this.type = type;
		this.user = user;
		this.startDate = startDate;
		this.durationInDays = durationInDays;
		this.numberOfClients = numberOfClients;
	}
	
	
	
	
	public Reservation() {
		super();
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDurationInDays() {
		return durationInDays;
	}
	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}
	public int getNumberOfClients() {
		return numberOfClients;
	}
	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	
}

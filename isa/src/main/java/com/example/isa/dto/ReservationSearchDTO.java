package com.example.isa.dto;

import java.util.Date;

public class ReservationSearchDTO {
	
	private Date startDate;
	private int numberOfClients;
	private int numberOfDays;
	private String location;
	private float grade;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getNumberOfClients() {
		return numberOfClients;
	}
	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
	
	public ReservationSearchDTO(Date startDate, int numberOfClients, int numberOfDays, String location, float grade) {
		super();
		this.startDate = startDate;
		this.numberOfClients = numberOfClients;
		this.numberOfDays = numberOfDays;
		this.location = location;
		this.grade = grade;
	}
	public ReservationSearchDTO() {
		super();
	}
	
	
	
	
	

}

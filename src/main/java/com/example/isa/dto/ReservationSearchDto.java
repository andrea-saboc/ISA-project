package com.example.isa.dto;

import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;

public class ReservationSearchDto {
	
	private String startDate;
	private String startTime;
	private int numberOfClients;
	private int numberOfDays;
	private int numberOfHours;
	private String location;
	private float grade;
	
	public double getPrice(Mansion m) {
		
		int numberOfWeeks = this.numberOfDays/7;
		return numberOfWeeks*m.getPriceForSevenDays() + (this.numberOfDays - 7*numberOfWeeks)*m.getPricePerDay();
	}
	public double getPrice(Boat b) {
		
		return this.numberOfDays * b.getPricePerDay() + this.numberOfHours * b.getPricePerHour();
	}
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public int getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	public ReservationSearchDto() {}
	
	public ReservationSearchDto(String startDate, String startTime, int numberOfClients, int numberOfDays,
			int numberOfHours, String location, float grade) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.numberOfClients = numberOfClients;
		this.numberOfDays = numberOfDays;
		this.numberOfHours = numberOfHours;
		this.location = location;
		this.grade = grade;
	}

	
	
	
	

}

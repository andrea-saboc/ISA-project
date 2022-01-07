package com.example.isa.dto;

import java.util.List;


public class BoatReservationDTO {
	
	private long boatId;
	private List<Long> additionalServices;
	private int numberOfGuests;
	private double price;
	private String startDate;
	private String startTime;
	private int numberOfDays;
	private int numberOfHours;
	
	
	public long getBoatId() {
		return boatId;
	}
	public void setBoatId(long boatId) {
		this.boatId = boatId;
	}
	public List<Long> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(List<Long> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	
	public BoatReservationDTO() {}
	public int getNumberOfHours() {
		return numberOfHours;
	}
	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	public BoatReservationDTO(long boatId, List<Long> additionalServices, int numberOfGuests, double price,
			String startDate, String startTime, int numberOfDays, int numberOfHours) {
		super();
		this.boatId = boatId;
		this.additionalServices = additionalServices;
		this.numberOfGuests = numberOfGuests;
		this.price = price;
		this.startDate = startDate;
		this.startTime = startTime;
		this.numberOfDays = numberOfDays;
		this.numberOfHours = numberOfHours;
	}
	
	
}

package com.example.isa.dto;

import java.util.List;

public class PotentialReservationDTO {

	private long entityId;
	private String name;
	private String promoDescription;
	private float avgGrade;
	
	private double pricePerDay;
	private double pricePerHour;
	private double pricePerSevenDays;
	
	
	private double totalPrice;
	private List<String> additionalServices;
	private List<Long> additinalServicesId;
	
	
	private int capacity;
	private String cancellationPolicy;

	

	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public double getPricePerHour() {
		return pricePerHour;
	}
	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPromoDescription() {
		return promoDescription;
	}
	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}
	public float getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public double getPricePerSevenDays() {
		return pricePerSevenDays;
	}
	public void setPricePerSevenDays(double pricePerSevenDays) {
		this.pricePerSevenDays = pricePerSevenDays;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<String> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(List<String> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public List<Long> getAdditinalServicesId() {
		return additinalServicesId;
	}
	public void setAdditinalServicesId(List<Long> additinalServicesId) {
		this.additinalServicesId = additinalServicesId;
	}
	
	public PotentialReservationDTO(long entityId, String name, String promoDescription, float avgGrade,
			double pricePerDay, double pricePerSevenDays, double totalPrice, List<String> additionalServices,
			List<Long> additinalServicesId) {
		super();
		this.entityId = entityId;
		this.name = name;
		this.promoDescription = promoDescription;
		this.avgGrade = avgGrade;
		this.pricePerDay = pricePerDay;
		this.pricePerSevenDays = pricePerSevenDays;
		this.totalPrice = totalPrice;
		this.additionalServices = additionalServices;
		this.additinalServicesId = additinalServicesId;
	}
	
	public PotentialReservationDTO(long entityId, String name, String promoDescription, float avgGrade, int capacity,
			String cancellationPolicy, double pricePerHour, double pricePerDay, double totalPrice,
			List<String> additionalServices, List<Long> additinalServicesId) {
		super();
		this.entityId = entityId;
		this.name = name;
		this.promoDescription = promoDescription;
		this.avgGrade = avgGrade;
		this.capacity = capacity;
		this.cancellationPolicy = cancellationPolicy;
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.totalPrice = totalPrice;
		this.additionalServices = additionalServices;
		this.additinalServicesId = additinalServicesId;
	}
	
	public PotentialReservationDTO() {}
}

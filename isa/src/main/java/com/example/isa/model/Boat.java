package com.example.isa.model;

public class Boat {
	
	private String name;
	private String address;
	private String promoDescription;
	private float avgGrade;
		

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPromoDescriptions() {
		return promoDescription;
	}
	public void setPromoDescriptions(String promoDescriptions) {
		this.promoDescription = promoDescriptions;
	}
	public float getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	public Boat(String name, String address, String promoDescription, float avgGrade) {
		super();
		this.name = name;
		this.address = address;
		this.promoDescription = promoDescription;
		this.avgGrade = avgGrade;
	}
	
	

}

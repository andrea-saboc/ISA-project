package com.example.isa.model;

public class Adventure {

	private String name;
	private String address;
	private String promoDescriptions;
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
		return promoDescriptions;
	}
	public void setPromoDescriptions(String promoDescriptions) {
		this.promoDescriptions = promoDescriptions;
	}
	public float getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	public Adventure(String name, String address, String promoDescriptions, float avgGrade) {
		super();
		this.name = name;
		this.address = address;
		this.promoDescriptions = promoDescriptions;
		this.avgGrade = avgGrade;
	}
		
}

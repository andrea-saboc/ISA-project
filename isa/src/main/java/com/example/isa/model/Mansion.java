package com.example.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


//ovo je samo jer mi treba u bazi

@Entity
public class Mansion {
	
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;

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

	public Mansion(){}
	
	
	public Mansion(String name, String address, String promoDescription, float avgGrade) {
		super();
		this.name = name;
		this.address = address;
		this.promoDescription = promoDescription;
		this.avgGrade = avgGrade;
	}
	public Mansion() {
		super();
	}
	
	
}

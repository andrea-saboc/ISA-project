package com.example.isa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Boats")
public class Boat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boatId;

	private String name;
	private String promoDescription;
	private float avgGrade;
	private int capacity;
	public String type;
	public double length;
	public int numberOfEngines;
	public double enginePower;
	public double maxSpeed;
	public boolean GPS;
	public boolean radar;
	public boolean VHFradio;
	public boolean fishfinder;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",
			joinColumns = @JoinColumn(name = "boatId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "address"))
	private Address address;
	@OneToMany
	public List<String> InteriorImages;
	@OneToMany
	public List<String> ExteriorImages;
	@OneToMany
	public List<String> rules;

	public Boat(){}

	public Boat(String ime_mi_je_brod, String neka_adresa, String promo_opis_moj, float v) {
	}

	public Integer getId() {
		return boatId;
	}

	public void setId(Integer id) {
		this.boatId = id;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getNumberOfEngines() {
		return numberOfEngines;
	}

	public void setNumberOfEngines(int numberOfEngines) {
		this.numberOfEngines = numberOfEngines;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public boolean isGPS() {
		return GPS;
	}

	public void setGPS(boolean GPS) {
		this.GPS = GPS;
	}

	public boolean isRadar() {
		return radar;
	}

	public void setRadar(boolean radar) {
		this.radar = radar;
	}

	public boolean isVHFradio() {
		return VHFradio;
	}

	public void setVHFradio(boolean VHFradio) {
		this.VHFradio = VHFradio;
	}

	public boolean isFishfinder() {
		return fishfinder;
	}

	public void setFishfinder(boolean fishfinder) {
		this.fishfinder = fishfinder;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getInteriorImages() {
		return InteriorImages;
	}

	public void setInteriorImages(List<String> interiorImages) {
		InteriorImages = interiorImages;
	}

	public List<String> getExteriorImages() {
		return ExteriorImages;
	}

	public void setExteriorImages(List<String> exteriorImages) {
		ExteriorImages = exteriorImages;
	}

	public List<String> getRules() {
		return rules;
	}

	public void setRules(List<String> rules) {
		this.rules = rules;
	}
}

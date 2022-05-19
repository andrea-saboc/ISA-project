package com.example.isa.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Adventure")
public class Adventure {

	@Id
	@Column(name = "id", unique = true)
	@SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;

	private String name;
	@Column(columnDefinition = "TEXT")
	private String promoDescription;
	@Column(columnDefinition = "TEXT")
	private String cancellationPolicy;
	private int capacity;
	private String biography;
	private  String equipment;


	private double pricePerHour;
	private double pricePerDay;
	private double priceForSevenDays;

	private boolean deleted;

	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	public FishingInstructor fishingInstructor;

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "boat")
	public Set<Rule> rules = new HashSet<Rule>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> images = new HashSet<EntityImage>();

	public Adventure(Long id, String name, String promoDescription, String cancellationPolicy, int capacity, String biography, double pricePerHour, double pricePerDay, double priceForSevenDays,String equipment, boolean deleted, FishingInstructor fishingInstructor, Address address, Set<Rule> rules) {
		this.id = id;
		this.name = name;
		this.promoDescription = promoDescription;
		this.cancellationPolicy = cancellationPolicy;
		this.capacity = capacity;
		this.biography = biography;
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.priceForSevenDays = priceForSevenDays;
		this.deleted = deleted;
		this.fishingInstructor = fishingInstructor;
		this.address = address;
		this.rules = rules;
		this.equipment=equipment;
	}

	public Adventure() {
		super();
	}
	public Set<EntityImage> getImages() {
		return images;
	}

	public void setImages(Set<EntityImage> images) {
		this.images = images;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public double getPriceForSevenDays() {
		return priceForSevenDays;
	}

	public void setPriceForSevenDays(double priceForSevenDays) {
		this.priceForSevenDays = priceForSevenDays;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public FishingInstructor getFishingInstructor() {
		return fishingInstructor;
	}

	public void setFishingInstructor(FishingInstructor fishingInstructor) {
		this.fishingInstructor = fishingInstructor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
}

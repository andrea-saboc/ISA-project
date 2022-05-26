package com.example.isa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//ovo je samo jer mi treba u bazi

@Entity
@Table(name = "Mansion")
public class Mansion {
	
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;
	private String name;
	private double pricePerDay;
	private double priceForSevenDays;
	private  String cancellationPolicy;

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
	private Address address;
	private String promoDescription;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> InteriorImages = new HashSet<EntityImage>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> ExteriorImages = new HashSet<EntityImage>();
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "mansions")
	public Set<Rule> rules = new HashSet<Rule>();
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "mansion")
	private Set<Room> rooms = new HashSet<>();
	private float avgGrade;
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = true)
	private MansionOwner mansionOwner;
	
	
	private boolean deleted;

	public MansionOwner getMansionOwner() {
		return mansionOwner;
	}

	public void setMansionOwner(MansionOwner mansionOwner) {
		this.mansionOwner = mansionOwner;
	}

	public Mansion(){
		this.deleted = false;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public Set<EntityImage> getInteriorImages() {
		return InteriorImages;
	}

	public void setInteriorImages(Set<EntityImage> interiorImages) {
		InteriorImages = interiorImages;
	}

	public Set<EntityImage> getExteriorImages() {
		return ExteriorImages;
	}

	public void setExteriorImages(Set<EntityImage> exteriorImages) {
		ExteriorImages = exteriorImages;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public float getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
}

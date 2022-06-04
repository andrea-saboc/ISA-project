package com.example.isa.model;
import com.example.isa.enums.EntityType;
import com.example.isa.model.reservations.AdditionalService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Boat")
public class Boat extends AbstractEntity{
	
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
	private float avgGrade;
	private int capacity;
	private String type;
	private double length;
	private int numberOfEngines;
	private double enginePower;
	private double maxSpeed;
	private boolean GPS;
	private boolean radar;
	private boolean VHFradio;
	private boolean fishfinder;
	private double pricePerHour;
	private double pricePerDay;
	private double priceForSevenDays;
	
	private boolean deleted;     

	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	public BoatOwner boatOwner;

	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> InteriorImages = new HashSet<EntityImage>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<EntityImage> ExteriorImages = new HashSet<EntityImage>();

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "boat")
	public Set<Rule> rules = new HashSet<Rule>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<FishingEquipment> fishingEquipments = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<AdditionalService> additionalServices = new HashSet<AdditionalService>();

	public Set<FishingEquipment> getFishingEquipments() {
		return fishingEquipments;
	}

	public void setFishingEquipments(Set<FishingEquipment> fishingEquipments) {
		this.fishingEquipments = fishingEquipments;
	}

	public Boat(String ime_mi_je_brod, String neka_adresa, String promo_opis_moj, float v) {
	}
	
	
	//testing purpose
	public Boat(String name, String promoDescription,double pricePerHour,double pricePerDay) {
		super();
		this.name = name;
		this.promoDescription = promoDescription;
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
	}


	public void addRule(Rule newRule){
	//	newRule.addBoat(this);
		rules.add(newRule);
	}

	public User getOwner(){
		return (User) boatOwner;
	}


	public Long getId() {
		return id;
	}

	@Override
	public EntityType getEntityType() {
		return EntityType.BOAT;
	}

	public Set<AdditionalService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
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
		//address.setBoat(this);
	}
	public Boat() {
		this.deleted = false;
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

	public BoatOwner getBoatOwner() {
		return boatOwner;
	}

	public void setBoatOwner(BoatOwner boatOwner) {
		this.boatOwner = boatOwner;
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

	@Override
	public String toString() {
		return "Boat{" +
				"id=" + id +
				", name='" + name + '\'' +
				", promoDescription='" + promoDescription + '\'' +
				", cancellationPolicy='" + cancellationPolicy + '\'' +
				", avgGrade=" + avgGrade +
				", capacity=" + capacity +
				", type='" + type + '\'' +
				", length=" + length +
				", numberOfEngines=" + numberOfEngines +
				", enginePower=" + enginePower +
				", maxSpeed=" + maxSpeed +
				", GPS=" + GPS +
				", radar=" + radar +
				", VHFradio=" + VHFradio +
				", fishfinder=" + fishfinder +
				", pricePerHour=" + pricePerHour +
				", pricePerDay=" + pricePerDay +
				", priceForSevenDays=" + priceForSevenDays +
				", deleted=" + deleted +
				", boatOwner=" + boatOwner +
				", address=" + address +
				", fishingEquipments=" + fishingEquipments +
				'}';
	}

	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}

package com.example.isa.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Boat")
public class Boat {
	
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Integer id;
	
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

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "Addreess",
			joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "address"))
	private Address address;
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "boat")
	public Set<Image> InteriorImages = new HashSet<Image>();
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "boat")
	public Set<Image> ExteriorImages = new HashSet<Image>();
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "boat")
	public Set<Rule> rules = new HashSet<Rule>();




	public Boat(String ime_mi_je_brod, String neka_adresa, String promo_opis_moj, float v) {
	}
	
	
	
	public Boat(String name, String promoDescription) {
		super();
		this.name = name;
		this.promoDescription = promoDescription;
	}


	public void addRule(Rule newRule){
		newRule.addBoat(this);
		rules.add(newRule);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	}
	public Boat() {}
	
	

	public Set<Image> getInteriorImages() {
		return InteriorImages;
	}

	public void setInteriorImages(Set<Image> interiorImages) {
		InteriorImages = interiorImages;
	}

	public Set<Image> getExteriorImages() {
		return ExteriorImages;
	}

	public void setExteriorImages(Set<Image> exteriorImages) {
		ExteriorImages = exteriorImages;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
}

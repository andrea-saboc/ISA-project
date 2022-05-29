package com.example.isa.model.reservations;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;

@Entity
@Table(name = "AdditionalService")
public class AdditionalService {
    @Id
    @Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private Long id;

    private String name;
    private double pricePerDay;
    private double pricePerHour;
    @ManyToOne
    @JoinColumn(name = "boat_id", referencedColumnName = "id", nullable = true)
    private Boat boat;

    @ManyToOne
    @JoinColumn(name = "mansion_id", referencedColumnName = "id", nullable = true)
    private Mansion mansion;
    @ManyToOne
    @JoinColumn(name = "adventure_id", referencedColumnName = "id", nullable = true)
    private Adventure adventure;


    @ManyToMany
    @JoinTable(name="reservation_services",
    joinColumns=@JoinColumn(name="service_id"),
       inverseJoinColumns=@JoinColumn(name="reservation_id"))
    private Set<Reservation> reservations = new HashSet<Reservation>();

    public AdditionalService(String name, double pricePerDay, double pricePerHour, Mansion mansion) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.mansion = mansion;
    }

    public void addReservation(Reservation newBoat){
    	reservations.add(newBoat);
    }

    
	public AdditionalService(){}

    public AdditionalService(String name, double pricePerDay, double pricePerHour, Boat boat) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.boat = boat;
    }

    public AdditionalService(String name, double pricePerDay, double pricePerHour, Adventure adventure) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.adventure = adventure;
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

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Mansion getMansion() {
        return mansion;
    }

    public void setMansion(Mansion mansion) {
        this.mansion = mansion;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }
}

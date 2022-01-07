package com.example.isa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AdditionalService")
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_period_id")
    private Long id;

    private String name;
    private double pricePerDay;
    private double pricePerHour;
    @ManyToOne
    @JoinColumn(name = "boat_id", referencedColumnName = "id", nullable = true)
    private Boat boat;
    

    /*
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_res_id", referencedColumnName = "id", nullable = true)
    private Set<BoatReservation> boatReservations = new HashSet<BoatReservation>();
    
    public void addReservation(BoatReservation newRes){
    	boatReservations.add(newRes);
    }
    */
    
	public AdditionalService(){}

    public AdditionalService(String name, double pricePerDay, double pricePerHour, Boat boat) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.boat = boat;
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
}

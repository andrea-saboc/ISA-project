package com.example.isa.dto;

import com.example.isa.model.Address;
import com.example.isa.model.Boat;

import java.util.List;

public class BoatRegistrationDTO {
    public String name;
    public String type;
    public double length;
    public int numberOfEngines;
    public double enginePower;
    public double maxSpeed;
    public boolean GPS;
    public boolean radar;
    public boolean VHFradio;
    public boolean fishfinder;
    public String address;
    public String city;
    public String country;
    public String longitude;
    public String latitude;
    public String promoDescription;
    public List<String> InteriorImages;
    public List<String> ExteriorImages;
    public int capacity;
    public List<String> rules;

    public BoatRegistrationDTO(){}

    public BoatRegistrationDTO(String name, String type, double length, int numberOfEngines, double enginePower, double maxSpeed, boolean GPS, boolean radar, boolean VHFradio, boolean fishfinder, String address, String city, String country, String longitude, String latitude, String promoDescription, List<String> interiorImages, List<String> exteriorImages, int capacity, List<String> rules) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.numberOfEngines = numberOfEngines;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.GPS = GPS;
        this.radar = radar;
        this.VHFradio = VHFradio;
        this.fishfinder = fishfinder;
        this.address = address;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.promoDescription = promoDescription;
        InteriorImages = interiorImages;
        ExteriorImages = exteriorImages;
        this.capacity = capacity;
        this.rules = rules;
    }

    public Boat createBoat() {
        Address boatAddress = new Address();
        boatAddress.setAddress(address);
        boatAddress.setCity(city);
        boatAddress.setCity(country);
        boatAddress.setLatitude(latitude);
        boatAddress.setLongitude(longitude);
        Boat newBoat = new Boat();
        newBoat.setName(name);
        newBoat.setAddress(boatAddress);
        newBoat.setCapacity(capacity);
        //new.set
        return newBoat;
    }
}

package com.example.isa.dto;

import com.example.isa.model.Address;
import com.example.isa.model.Boat;

import java.util.List;
import java.util.Set;

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
    public Set<String> InteriorImages;
    public Set<String> ExteriorImages;
    public int capacity;
    public Set<String> rules;

    public BoatRegistrationDTO(){}

    public BoatRegistrationDTO(String name, String type, double length, int numberOfEngines, double enginePower, double maxSpeed, boolean GPS, boolean radar, boolean VHFradio, boolean fishfinder, String address, String city, String country, String longitude, String latitude, String promoDescription, Set<String> interiorImages, Set<String> exteriorImages, int capacity, Set<String> rules) {
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

    @Override
    public String toString() {
        return "BoatRegistrationDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", numberOfEngines=" + numberOfEngines +
                ", enginePower=" + enginePower +
                ", maxSpeed=" + maxSpeed +
                ", GPS=" + GPS +
                ", radar=" + radar +
                ", VHFradio=" + VHFradio +
                ", fishfinder=" + fishfinder +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", promoDescription='" + promoDescription + '\'' +
                ", InteriorImages=" + InteriorImages +
                ", ExteriorImages=" + ExteriorImages +
                ", capacity=" + capacity +
                ", rules=" + rules +
                '}';
    }
}

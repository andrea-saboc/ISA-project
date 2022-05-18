package com.example.isa.dto;

import com.example.isa.model.reservations.AdditionalService;

import java.util.ArrayList;
import java.util.Set;

public class AdventureRegistrationDto {


    public String name;

    public String cancellationPolicy;

    public double pricePerHour;
    public double pricePerDay;
    public double priceForSevenDays;
    public String address;
    public String city;
    public String country;
    public String longitude;


    public String latitude;
    public String promoDescription;
    public String equipment;
    public String biography;

    public int capacity;
    public Set<String> rules;
    public Set<AdditionalService> additionalServices;
    public Set<String> imagesForBackend;

    public AdventureRegistrationDto() {
        super();
    }

    public AdventureRegistrationDto(String name, String cancellationPolicy, double pricePerHour, double pricePerDay, double priceForSevenDays, String address, String city, String country, String longitude, String latitude, String promoDescription, String equipment, String biography, int capacity, Set<String> rules, Set<AdditionalService> additionalServices, Set<String> imagesForBackend) {
        this.name = name;
        this.cancellationPolicy = cancellationPolicy;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.priceForSevenDays = priceForSevenDays;
        this.address = address;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.promoDescription = promoDescription;
        this.equipment = equipment;
        this.biography = biography;
        this.capacity = capacity;
        this.rules = rules;
        this.additionalServices = additionalServices;
        this.imagesForBackend=imagesForBackend;
    }



    @Override
    public String toString() {
        return "AdventureRegistrationDto{" +
                "name='" + name + '\'' +
                ", cancellationPolicy='" + cancellationPolicy + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", pricePerDay=" + pricePerDay +
                ", priceForSevenDays=" + priceForSevenDays +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", promoDescription='" + promoDescription + '\'' +
                ", equipment='" + equipment + '\'' +
                ", biography='" + biography + '\'' +
                ", capacity=" + capacity +
                ", rules=" + rules +
                ", additionalServices=" + additionalServices +
                '}';
    }
    public Set<String> getImagesForBackend() {
        return imagesForBackend;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<String> getRules() {
        return rules;
    }

    public void setRules(Set<String> rules) {
        this.rules = rules;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }


    public void setImagesForBackend(Set<String> imagesForBackend) {
        this.imagesForBackend = imagesForBackend;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}

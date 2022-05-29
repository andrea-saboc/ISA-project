package com.example.isa.dto;

import com.example.isa.model.reservations.AdditionalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MansionRegistrationDto {
    public String name;
    public String cancellationPolicy;
    public double pricePerDay;
    public double priceForSevenDays;
    public String address;
    public String city;
    public String country;
    public String longitude;
    public String latitude;
    public String promoDescription;
    public Set<String> InteriorImages;
    public Set<String> ExteriorImages;
    public Set<String> rules;
    public Set<AdditionalService> additionalServices;
    public List<Integer> rooms;

    public MansionRegistrationDto(){}

    public MansionRegistrationDto(String name, String cancellationPolicy, double pricePerDay, double priceForSevenDays, String address, String city, String country, String longitude, String latitude, String promoDescription, Set<String> interiorImages, Set<String> exteriorImages, Set<String> rules, Set<AdditionalService> additionalServices, List<Integer> rooms) {
        this.name = name;
        this.cancellationPolicy = cancellationPolicy;
        this.pricePerDay = pricePerDay;
        this.priceForSevenDays = priceForSevenDays;
        this.address = address;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.promoDescription = promoDescription;
        InteriorImages = interiorImages;
        ExteriorImages = exteriorImages;
        this.rules = rules;
        this.additionalServices = additionalServices;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "MansionRegistrationDto{" +
                "name='" + name + '\'' +
                ", cancellationPolicy='" + cancellationPolicy + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", priceForSevenDays=" + priceForSevenDays +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", promoDescription='" + promoDescription + '\'' +
                ", rules=" + rules +
                ", additionalServices=" + additionalServices +
                ", rooms=" + rooms +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setPriceForSevenDays(double priceForSevenDays) {
        this.priceForSevenDays = priceForSevenDays;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public void setInteriorImages(Set<String> interiorImages) {
        InteriorImages = interiorImages;
    }

    public void setExteriorImages(Set<String> exteriorImages) {
        ExteriorImages = exteriorImages;
    }

    public void setRules(Set<String> rules) {
        this.rules = rules;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }



    public String getName() {
        return name;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getPriceForSevenDays() {
        return priceForSevenDays;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public Set<String> getInteriorImages() {
        return InteriorImages;
    }

    public Set<String> getExteriorImages() {
        return ExteriorImages;
    }

    public Set<String> getRules() {
        return rules;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public List<Integer> getRooms() {
        return rooms;
    }
}

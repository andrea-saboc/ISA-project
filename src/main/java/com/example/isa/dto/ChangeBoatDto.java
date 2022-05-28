package com.example.isa.dto;

import com.example.isa.model.Rule;
import com.example.isa.model.reservations.AdditionalService;
import org.apache.tomcat.util.digester.Rules;

import java.util.Set;

public class ChangeBoatDto {
    public Long id;
    public String name;
    public String type;
    public String cancellationPolicy;
    public double length;
    public int numberOfEngines;
    public double enginePower;
    public double maxSpeed;
    public boolean GPS;
    public boolean radar;
    public boolean VHFradio;
    public boolean fishfinder;
    public double pricePerHour;
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
    public Set<Integer> InteriorImagesToDelete;
    public Set<Integer> ExteriorImagesToDelete;
    public int capacity;
    public Set<Rule> rules;
    public Set<Integer> rulesToDelete;
    public Set<AdditionalService> additionalServices;
    public Set<Long> deleteAdditionalServices;

    @Override
    public String toString() {
        return "ChangeBoatDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cancellationPolicy='" + cancellationPolicy + '\'' +
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
                ", additionalServices=" + additionalServices +
                '}';
    }

    public Set<Long> getDeleteAdditionalServices() {
        return deleteAdditionalServices;
    }

    public void setDeleteAdditionalServices(Set<Long> deleteAdditionalServices) {
        this.deleteAdditionalServices = deleteAdditionalServices;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
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

    public Set<String> getInteriorImages() {
        return InteriorImages;
    }

    public void setInteriorImages(Set<String> interiorImages) {
        InteriorImages = interiorImages;
    }

    public Set<String> getExteriorImages() {
        return ExteriorImages;
    }

    public void setExteriorImages(Set<String> exteriorImages) {
        ExteriorImages = exteriorImages;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }
}

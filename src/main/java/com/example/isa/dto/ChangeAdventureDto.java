package com.example.isa.dto;

import com.example.isa.model.Rule;
import com.example.isa.model.reservations.AdditionalService;

import java.util.Set;

public class ChangeAdventureDto extends AbstractChangeDto {
    public Long id;
    public String name;
    public String biography;
    public String equipment;
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
    public Set<String> images;
    public Set<Integer> imagesToDelete;
    public int capacity;
    public Set<Rule> rules;
    public Set<Integer> rulesToDelete;
    public Set<AdditionalService> additionalServices;
    public Set<Long> deleteAdditionalServices;

    public ChangeAdventureDto() {

    }

    @Override
    public String toString() {
        return "ChangeAdventureDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", equipment='" + equipment + '\'' +
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
                ", images=" + images +
                ", imagesToDelete=" + imagesToDelete +
                ", capacity=" + capacity +
                ", rules=" + rules +
                ", rulesToDelete=" + rulesToDelete +
                ", additionalServices=" + additionalServices +
                ", deleteAdditionalServices=" + deleteAdditionalServices +
                '}';
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
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

    @Override
    public Set<String> getExteriorImages() {
        return null;
    }

    @Override
    public Set<String> getInteriorImages() {
        return null;
    }

    @Override
    public Set<Integer> getExteriorImagesToDelete() {
        return null;
    }

    @Override
    public Set<Integer> getInteriorImagesToDelete() {
        return null;
    }
    @Override
    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }
    @Override
    public Set<Integer> getImagesToDelete() {
        return imagesToDelete;
    }

    public void setImagesToDelete(Set<Integer> imagesToDelete) {
        this.imagesToDelete = imagesToDelete;
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

    public Set<Integer> getRulesToDelete() {
        return rulesToDelete;
    }

    public void setRulesToDelete(Set<Integer> rulesToDelete) {
        this.rulesToDelete = rulesToDelete;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Set<Long> getDeleteAdditionalServices() {
        return deleteAdditionalServices;
    }

    public void setDeleteAdditionalServices(Set<Long> deleteAdditionalServices) {
        this.deleteAdditionalServices = deleteAdditionalServices;
    }
}

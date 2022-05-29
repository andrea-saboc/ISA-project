package com.example.isa.dto;

import com.example.isa.model.Rule;
import com.example.isa.model.reservations.AdditionalService;

import java.util.List;
import java.util.Set;

public class ChangeMansionDto extends AbstractChangeDto {
    public Long id;
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
    public Set<Integer> InteriorImagesToDelete;
    public Set<Integer> ExteriorImagesToDelete;
    public Set<Rule> rules;
    public Set<Integer> rulesToDelete;
    public Set<AdditionalService> additionalServices;
    public Set<Long> deleteAdditionalServices;
    public List<Integer> rooms;

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

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
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
    public Set<String> getInteriorImages() {
        return InteriorImages;
    }

    public void setInteriorImages(Set<String> interiorImages) {
        InteriorImages = interiorImages;
    }

    @Override
    public Set<String> getExteriorImages() {
        return ExteriorImages;
    }

    public void setExteriorImages(Set<String> exteriorImages) {
        ExteriorImages = exteriorImages;
    }

    public Set<Integer> getInteriorImagesToDelete() {
        return InteriorImagesToDelete;
    }

    public void setInteriorImagesToDelete(Set<Integer> interiorImagesToDelete) {
        InteriorImagesToDelete = interiorImagesToDelete;
    }

    public Set<Integer> getExteriorImagesToDelete() {
        return ExteriorImagesToDelete;
    }

    public void setExteriorImagesToDelete(Set<Integer> exteriorImagesToDelete) {
        ExteriorImagesToDelete = exteriorImagesToDelete;
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

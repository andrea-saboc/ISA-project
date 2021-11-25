package com.example.isa.model;

import javax.persistence.*;

@Entity
@Table(name="AdvertiserRegistrationRequest")
public class AdvertiserRegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDRequestRegistration;
    @Column(name = "advertiser_name", nullable = false)
    private String name;
    @Column(name = "advertiser_surname", nullable = false)
    private String surname;
    @Column(name = "advertiser_address", nullable = false)
    private String address;
    @Column(name = "advertiser_city", nullable = false)
    private String city;
    @Column(name = "advertiser_country", nullable = false)
    private String country;
    @Column(name = "advertiser_phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "advertiser_email", nullable = false)
    private String email;
    @Column(name = "advertiser_password", nullable = false)
    private String password;
    @Column(name = "advertiser_type", nullable = false)
    private String type;
    @Column(name = "advertiser_reason", nullable = false)
    private String reason;
    @Column(name = "advertiser_registration_approved", nullable = false)
    private boolean approved;

    public AdvertiserRegistrationRequest() {
    }

    public AdvertiserRegistrationRequest( String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String type, String reason, boolean approved) {
        this.IDRequestRegistration = IDRequestRegistration;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.type = type;
        this.reason = reason;
        this.approved = approved;
    }

    public AdvertiserRegistrationRequest(String name, String surname, String city, String country) {
    }

    public void setIDRequestRegistration(Integer IDRequestRegistration) {
        this.IDRequestRegistration = IDRequestRegistration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Integer getIDRequestRegistration() {
        return IDRequestRegistration;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public boolean isApproved() {
        return approved;
    }

    @Override
    public String toString() {
        return "AdvertiserRegistrationRequest{" +
                "IDRequestRegistration=" + IDRequestRegistration +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", approved=" + approved +
                '}';
    }
}

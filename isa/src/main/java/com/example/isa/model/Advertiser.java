package com.example.isa.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADVERTISER")
public class Advertiser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDRequestRegistration;
    @Column(name = "advertiser_reason", nullable  = false)
    private String reason;
    @Column(name = "advertiser_registration_approved", nullable = false)
    private boolean approved;

    public Advertiser(){}

    public Advertiser(String reason, boolean approved) {
        this.reason = reason;
        this.approved = approved;
    }

    public Advertiser(String email, String password, String reason, boolean approved) {
        super(email, password);
        this.reason = reason;
        this.approved = approved;
    }

    public Advertiser(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, boolean approved) {
        super(name, surname, address, city, country, phoneNumber, email, password);
        this.reason = reason;
        this.approved = approved;
    }
}

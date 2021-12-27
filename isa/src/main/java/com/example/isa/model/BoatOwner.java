package com.example.isa.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("BoatOwner")
public class BoatOwner extends User{
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
   //private Integer IDAdvertiser;
    @Column(name = "advertiser_reason")
    private String reason;
    @Column(name = "type")
    private String advertiserType;
    @Column(name = "advertiser_registration_approved")
    private boolean approved;

    public BoatOwner(){}

    public BoatOwner(String reason, boolean approved) {
        this.reason = reason;
        this.approved = approved;
    }

    public BoatOwner(String email, String password, String reason, boolean approved) {
        super(email, password);
        this.reason = reason;
        this.approved = approved;
    }

    public BoatOwner(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, String type, boolean approved) {
        super(name, surname, address, city, country, phoneNumber, email, password);
        this.advertiserType = type;
        this.reason = reason;
        this.approved = approved;
    }
}
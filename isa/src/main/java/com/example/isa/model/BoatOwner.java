package com.example.isa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOATOWNER")
public class BoatOwner extends Advertiser{
    public BoatOwner() {
    }

    public BoatOwner(String reason, boolean approved) {
        super(reason, approved);
    }

    public BoatOwner(String email, String password, String reason, boolean approved) {
        super(email, password, reason, approved);
    }

    public BoatOwner(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, boolean approved) {
        super(name, surname, address, city, country, phoneNumber, email, password, reason, approved);
    }
}

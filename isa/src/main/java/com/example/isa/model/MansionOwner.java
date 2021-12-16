package com.example.isa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MANSIONOWNER")
public class MansionOwner extends Advertiser{
   public MansionOwner(){}
    public MansionOwner(String reason, boolean approved) {
        super(reason, approved);
    }

    public MansionOwner(String email, String password, String reason, boolean approved) {
        super(email, password, reason, approved);
    }

    public MansionOwner(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, boolean approved) {
        super(name, surname, address, city, country, phoneNumber, email, password, reason, approved);
    }
}

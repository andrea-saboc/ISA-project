package com.example.isa.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("BoatOwner")
public class BoatOwner extends User{
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
   //private Integer IDAdvertiser;
    private transient final String role = "ROLE_BOAT_OWNER";
    private static final long serialVersionUID = 1L;
    @Column(name = "advertiser_reason")
    private String reason;
    @Column(name = "type")
    private String advertiserType;
    @Column(name = "advertiser_registration_approved")
    private boolean approved;
    @Column(name = "advertiser_registration_notapproved")
    private boolean notapproved;
    @Column(name = "boatowner_loyalty_points")
    private int loyaltyPoints;

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

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

    public BoatOwner(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, String type, boolean approved,boolean notapproved,Boolean deleted,int loyaltyPoints) {
        super(name, surname, address, city, country, phoneNumber, email, password,deleted);
        this.advertiserType = type;
        this.reason = reason;
        this.approved = approved;
        this.notapproved=notapproved;
        this.loyaltyPoints=loyaltyPoints;
    }

    public boolean isNotapproved() {
        return notapproved;
    }

    public void setNotapproved(boolean notapproved) {
        this.notapproved = notapproved;
    }

    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdvertiserType() {
		return advertiserType;
	}

	public void setAdvertiserType(String advertiserType) {
		this.advertiserType = advertiserType;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

  /*  public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }
    public void addBoat(Boat newBoat){
        this.boats.add(newBoat);
    }*/
}

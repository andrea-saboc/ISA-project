package com.example.isa.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MansionOwner")
public class MansionOwner extends User{
    private transient final String role = "ROLE_MANSION_OWNER";
    private static final long serialVersionUID = 1L;
    @Column(name = "advertiser_reason")
    private String reason;
    @Column(name = "type")
    private String advertiserType;
    @Column(name = "advertiser_registration_approved")
    private boolean approved;
    @Column(name = "advertiser_registration_notapproved")
    private boolean notapproved;

    public  MansionOwner() {}

    public MansionOwner(String reason, String advertiserType, boolean approved) {
        this.reason = reason;
        this.advertiserType = advertiserType;
        this.approved = approved;
    }

    public MansionOwner(String email, String password, String reason, String advertiserType, boolean approved) {
        super(email, password);
        this.reason = reason;
        this.advertiserType = advertiserType;
        this.approved = approved;
    }

    public MansionOwner(String name, String surname, String address, String city, String country, String phoneNumber, String email, String password, String reason, String advertiserType, boolean approved,boolean notapproved,Boolean deleted) {
        super(name, surname, address, city, country, phoneNumber, email, password,deleted);
        this.reason = reason;
        this.advertiserType = advertiserType;
        this.approved = approved;
        this.notapproved=notapproved;
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
    public boolean isNotapproved() {
        return notapproved;
    }

    public void setNotapproved(boolean notapproved) {
        this.notapproved = notapproved;
    }
}

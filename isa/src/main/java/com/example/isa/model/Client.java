package com.example.isa.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{
	
	private transient final String role = "ROLE_CLIENT";
	
	private static final long serialVersionUID = 1L;
	
    @Column(name = "client_activation_code", length = 64)
    private String activationCode;

    @Column(name = "client_penalty_points")
    private int penaltyPoints;

    @Column(name = "client_loyalty_points")
    private int loyaltyPoints;
    
    public Client() {
        super();
    }

    public Client(String name, String surname, String address, String city, String country, String phoneNumber,
			String email, String password, int penaltyPoints,int loyaltyPoints) {
    	
        super(name,surname,address,city,country,phoneNumber,email, password);
        this.penaltyPoints = penaltyPoints;
        this.loyaltyPoints = loyaltyPoints;
        
    }
    
    public Client(String name, String surname, String address, String city, String country, String phoneNumber,
			String email, String password) {
    	
        super(name,surname,address,city,country,phoneNumber,email, password);
        
    }

    @Override
    public String getRole() {
        return role;
    }

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public int getPenaltyPoints() {
		return penaltyPoints;
	}

	public void setPenaltyPoints(int penaltyPoints) {
		this.penaltyPoints = penaltyPoints;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
    
    

}

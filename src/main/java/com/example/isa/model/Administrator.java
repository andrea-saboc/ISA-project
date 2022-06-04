package com.example.isa.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends User{

	private transient final String role = "ROLE_ADMIN";
	private static final long serialVersionUID = 1L;

	@Column(name = "type")
	private String adminType;
	@Column(name = "is_logged_in")
	private boolean isLoggedIn;


	private Administrator() {
		super();

	}

	public Administrator(String name, String surname, String address, String city, String country, String phoneNumber,
						 String email, String password,String adminType,boolean isLoggedIn,Boolean deleted) {
		super(name, surname, address, city, country, phoneNumber, email, password,deleted);
		this.adminType=adminType;
		this.isLoggedIn=isLoggedIn;

	}

	private Administrator(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRole() {
		return role;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		isLoggedIn = loggedIn;
	}
}

package com.example.isa.dto;

public class ClientDTO {
	
    private String name;
    private String surname;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private String password;
    
    
	@Override
	public String toString() {
		return "ClientDTO [name=" + name + ", surname=" + surname + ", address=" + address + ", city=" + city
				+ ", country=" + country + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + "]";
	}
    
    

}

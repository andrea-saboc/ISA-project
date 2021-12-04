package com.example.isa.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "system_user")

public class User implements UserDetails{
	
	@Id
	@Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
	private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "phoneNumber")
    private String phoneNumber;
    
    @Unique
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "blocked")
    private boolean blocked;
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    
    private List<Role> authorities;
    
    @Column(name = "last_password_reset_date")
    protected Date lastPasswordResetDate;


	public User() {
		// TODO Auto-generated constructor stub
	}
	
	


	public User(@Unique String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.blocked = true;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return !this.blocked;
	}
	
    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    
    public Date getLastPasswordResetDate() {
        return this.lastPasswordResetDate;
    }


	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

    
    
	

}

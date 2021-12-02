package com.example.isa.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="ROLE")
public class Role implements GrantedAuthority{
	
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    Long id;

    @Column(name="name")
    String name;
    
    
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}

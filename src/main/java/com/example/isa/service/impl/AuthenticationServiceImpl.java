package com.example.isa.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.model.User;
import com.example.isa.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Override
	public User getLoggedUser() {
		
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}

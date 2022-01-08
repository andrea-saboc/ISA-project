package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.isa.model.User;
import com.example.isa.repository.UserRepository;

@Service
public class UserCredentialsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;
	
	public UserCredentialsService(UserRepository userRepository){
		this.repo = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repo.findByEmail(email);
	}

}

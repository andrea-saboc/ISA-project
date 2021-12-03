package com.example.isa.service.implemented;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}

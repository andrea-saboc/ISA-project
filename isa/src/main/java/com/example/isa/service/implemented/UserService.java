package com.example.isa.service.implemented;


import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientRegistrationDTO;
import com.example.isa.model.User;
import com.example.isa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public Iterable<User> findAll()  throws AccessDeniedException{
		return userRepository.findAll();
	}
	
	public User save(ClientRegistrationDTO userRequest) {
		User u = new User();
		u.setEmail(userRequest.getEmail());
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		u.setName(userRequest.getName());
		u.setSurname(userRequest.getSurname());
		
		u.setEmail(userRequest.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		//List<Role> roles = roleService.findByName("ROLE_USER");
		//u.setRoles(roles);
		
		return this.userRepository.save(u);
	}
}

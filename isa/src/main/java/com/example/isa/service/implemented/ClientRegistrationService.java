package com.example.isa.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientRegistrationDTO;
import com.example.isa.model.Client;
import com.example.isa.repository.UserRepository;

@Service
public class ClientRegistrationService {
	
	@Autowired
	private UserRepository userRepository;

	public Client registerClient(ClientRegistrationDTO clientDto) {
		
		Client client = clientDto.createClient(clientDto);
		client.setLoyaltyPoints(0);
		client.setPenaltyPoints(0);
		System.out.println("KLIJENT JE ");
		System.out.println(client.getName());
		return userRepository.save(client);
	}
	
	
}

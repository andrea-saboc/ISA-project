package com.example.isa.service.impl;

import org.springframework.stereotype.Service;

import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.repository.ClientRepository;


@Service
public class PenaltyManagementService {
	private ClientRepository clientRepository;

	public void addPenaltyToClient(User user) {
		Client client = (Client) clientRepository.findById(user.getId()).get();
		client.setPenaltyPoints(client.getLoyaltyPoints()+1);
		clientRepository.save(client);
	}

}

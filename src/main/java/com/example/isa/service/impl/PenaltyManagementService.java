package com.example.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.repository.ClientRepository;


@Service
public class PenaltyManagementService {
	@Autowired
	private ClientRepository clientRepository;

	public void addPenaltyToClient(User user) {
		Client client = clientRepository.findById(user.getId()).get();
		client.setPenaltyPoints(client.getLoyaltyPoints()+1);
		clientRepository.save(client);
	}

}

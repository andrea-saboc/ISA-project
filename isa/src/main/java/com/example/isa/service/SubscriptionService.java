package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatSubscription;
import com.example.isa.model.User;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatSubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	BoatSubscriptionRepository boatSubsRepo;
	@Autowired
	BoatRepository boatRepo;
	
	public User getLoggedUser() {	
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	public BoatSubscription newBoatSubscription(long id) {
		System.out.println("id u servisu je "+id);
		System.out.println("Pronadjeni brod je " );
		System.out.println(boatRepo.findById(id).orElse(new Boat()).getName());
		return boatSubsRepo.save(new BoatSubscription(getLoggedUser(),boatRepo.findById(id).orElse(new Boat())));
	}
	
	public List<BoatSubscription> getClientBoatSubscription(){
		return boatSubsRepo.findAllBySubscriber(getLoggedUser());
	}

}

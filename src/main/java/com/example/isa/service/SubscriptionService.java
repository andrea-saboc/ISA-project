package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import com.example.isa.model.*;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
	
	@Autowired
	BoatSubscriptionRepository boatSubsRepo;
	@Autowired
	MansionSubscriptionRepository mansionSubRepo;
	@Autowired
	AdventureSubscriptionRepository adventureSubscriptionRepository;
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	BoatRepository boatRepository;
	@Autowired
	MansionRepository mansionRepository;
	@Autowired
	AdventureRepository adventureRepository;
	
	
	public BoatSubscription newBoatSubscription(Boat boat) {
		return boatSubsRepo.save(new BoatSubscription(authenticationService.getLoggedUser(),boat));
	}
	public MansionSubscription newMansionSubscription(Mansion mansion) {
		System.out.println("new mansion subs");
		return mansionSubRepo.save(new MansionSubscription(authenticationService.getLoggedUser(), mansion));
	}

	public AdventureSubscription newAdventureSubscription(Adventure adventure) {
		return adventureSubscriptionRepository.save(new AdventureSubscription(authenticationService.getLoggedUser(),adventure));

	}
	public void cancelBoatSubscription(BoatSubscription boat) {
		boatSubsRepo.delete(boat);
	}	
	public void cancelMansionSubscription(MansionSubscription mansion) {
		mansionSubRepo.delete(mansion);
	}	
	public List<BoatSubscription> getClientBoatSubscription(){
		return boatSubsRepo.findAllBySubscriber(authenticationService.getLoggedUser());
	}
	
	public List<MansionSubscription> getClientMansionSubscription(){
		return mansionSubRepo.findAllBySubscriber(authenticationService.getLoggedUser());
	}
	public Boolean checkBoatSubscription(Boat boat) {
		return boatSubsRepo.findBySubscriberAndBoat(authenticationService.getLoggedUser(), boat) != null? true:false;
	}
	public Boolean checkMansionSubscription(Mansion mansion) {
		
		if(mansionSubRepo.findBySubscriberAndMansion(authenticationService.getLoggedUser(), mansion) == null) return false;
		else return true;
	}

	public List<BoatSubscription> getClientBoatSubscriptionByBoatOwner(Long id) {
		Client client = clientRepository.findById(id).get();
		List<BoatSubscription> boatSubscriptions = boatSubsRepo.findAllBySubscriber(client);
		return boatSubscriptions;
	}

	public List<AdventureSubscription> getClientAdventureSubscriptionByFishingInstructor(Long id) {
		Client client = clientRepository.findById(id).get();
		List<AdventureSubscription> adventureSubscription = adventureSubscriptionRepository.findAllBySubscriber(client);
		return adventureSubscription;
	}

	public List<User> getAllSubscribersByMansion(Mansion mansion){
		List<MansionSubscription> mansionSubscriptions = new ArrayList<>(mansionSubRepo.findAllByMansion(mansion));
		List<User> clientsSubscribed = new ArrayList<>();
		for (MansionSubscription ms : mansionSubscriptions){
			clientsSubscribed.add(ms.getSubscriber());
		}
		return clientsSubscribed;
	}

	public List<User> getAllSubscribersByAdventure(Adventure adventure){
		List<AdventureSubscription> adventureSubscription = new ArrayList<>(adventureSubscriptionRepository.findAllByAdventure(adventure));
		List<User> clientsSubscribed = new ArrayList<>();
		for (AdventureSubscription ms : adventureSubscription){
			clientsSubscribed.add(ms.getSubscriber());
		}
		return clientsSubscribed;
	}

	public List<User> getAllSubscribersByBoat(Boat boat){
		List<BoatSubscription> boatSubscriptions = new ArrayList<>(boatSubsRepo.findAllByBoat(boat));
		List<User> clientsSubscribed = new ArrayList<>();
		for (BoatSubscription bs : boatSubscriptions){
			clientsSubscribed.add(bs.getSubscriber());
		}
		return clientsSubscribed;
	}

	public List<User> getAllSubscribersByBoatId(Long boatId) {
		return getAllSubscribersByBoat(boatRepository.findById(boatId).get());
	}

	public List<User> getAllSubscribersByMansionId(Long mansionId) {
		return getAllSubscribersByMansion(mansionRepository.findById(mansionId).get());
	}
}

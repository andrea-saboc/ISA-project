package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatSubscription;
import com.example.isa.model.Mansion;
import com.example.isa.model.MansionSubscription;
import com.example.isa.model.User;
import com.example.isa.repository.BoatSubscriptionRepository;
import com.example.isa.repository.MansionSubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	BoatSubscriptionRepository boatSubsRepo;
	@Autowired
	MansionSubscriptionRepository mansionSubRepo;
	
	public User getLoggedUser() {	
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	public BoatSubscription newBoatSubscription(Boat boat) {
		return boatSubsRepo.save(new BoatSubscription(getLoggedUser(),boat));
	}
	public MansionSubscription newMansionSubscription(Mansion mansion) {
		return mansionSubRepo.save(new MansionSubscription(getLoggedUser(), mansion));
	}
	public void cancelBoatSubscription(BoatSubscription boat) {
		boatSubsRepo.delete(boat);
	}	
	public void cancelMansionSubscription(MansionSubscription mansion) {
		mansionSubRepo.delete(mansion);
	}	
	public List<BoatSubscription> getClientBoatSubscription(){
		return boatSubsRepo.findAllBySubscriber(getLoggedUser());
	}
	
	public List<MansionSubscription> getClientMansionSubscription(){
		return mansionSubRepo.findAllBySubscriber(getLoggedUser());
	}
	public Boolean checkBoatSubscription(Boat boat) {
		System.out.println("d ali je user subs  "+boatSubsRepo.findBySubscriberAndBoat(getLoggedUser(), boat).getSubscriber().getName());
		return boatSubsRepo.findBySubscriberAndBoat(getLoggedUser(), boat) != null? true:false;
	}

}

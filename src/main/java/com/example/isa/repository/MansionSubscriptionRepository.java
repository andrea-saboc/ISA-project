package com.example.isa.repository;

import java.util.List;

import com.example.isa.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;
import com.example.isa.model.MansionSubscription;
import com.example.isa.model.User;

public interface MansionSubscriptionRepository extends JpaRepository<MansionSubscription,Long>{

	List<MansionSubscription> findAllBySubscriber(User user);
	MansionSubscription findById(long id);
	MansionSubscription findBySubscriberAndMansion(User user,Mansion mansion);
	List<MansionSubscription> findAllByMansion(Mansion mansion);
}

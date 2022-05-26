package com.example.isa.repository;

import com.example.isa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureSubscriptionRepository extends JpaRepository<AdventureSubscription,Long> {

    List<AdventureSubscription> findAllBySubscriber(User user);
    AdventureSubscription findById(long id);
    AdventureSubscription findBySubscriberAndAdventure(User user, Adventure adventure);
    List<AdventureSubscription> findAllByAdventure(Adventure adventure);

}
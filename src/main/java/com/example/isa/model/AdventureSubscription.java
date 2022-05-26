package com.example.isa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("ADVENTURE_SUBSCRIPTION")
public class AdventureSubscription  extends Subscription{

    @OneToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public AdventureSubscription(Long id, Client subscriber, Adventure adventure) {
        super(id, subscriber);
        this.adventure = adventure;
    }


    public AdventureSubscription(User subscriber,Adventure adventure) {
        super(subscriber);
        this.adventure = adventure;
    }

    public AdventureSubscription() {}

    @Override
    public String toString() {
        return "AdventureSubscription{" +
                "adventure=" + adventure +
                '}';
    }
}

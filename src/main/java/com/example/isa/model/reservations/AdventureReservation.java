package com.example.isa.model.reservations;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@DiscriminatorValue("ADVENTURE")
public class AdventureReservation extends Reservation{
    @OneToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private ClientFeedback adventureFeedback;

    @OneToOne
    @JoinColumn(name = "owner_feedback_id")
    private ClientFeedback fishingInstructorFeedback;




    public AdventureReservation(User user, Date startDate, Date endDate, int numberOfGuests, double totalPrice,
                           Adventure adventure) {
        super("ADVENTURE", user, startDate, endDate, numberOfGuests, totalPrice);
        this.adventure = adventure;
    }

    public AdventureReservation() {
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public ClientFeedback getAdventureFeedback() {
        return adventureFeedback;
    }

    public void setAdventureFeedback(ClientFeedback adventureFeedback) {
        this.adventureFeedback = adventureFeedback;
    }

    public ClientFeedback getFishingInstructorFeedback() {
        return fishingInstructorFeedback;
    }

    public void setFishingInstructorFeedback(ClientFeedback fishingInstructorFeedback) {
        this.fishingInstructorFeedback = fishingInstructorFeedback;
    }
    public String getName(){
        return this.adventure.getName();
    }
}

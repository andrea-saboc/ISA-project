package com.example.isa.model.reservations;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@DiscriminatorValue("ADVENTURE_DISCOUNT_RESERVATION")
public class AdventureDiscountReservation extends DiscountReservation{

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;



    @OneToOne
    @JoinColumn(name = "feedback_id")
    private ClientFeedback adventureFeedback;

    @OneToOne
    @JoinColumn(name = "owner_feedback_id")
    private ClientFeedback adventureOwnerFeedback;

    public AdventureDiscountReservation(Date startDate, Date endDate, int numberOfGuests,
                                   double priceWithDiscount, Adventure adventure) {
        super("ADVENTURE", startDate, endDate, numberOfGuests, priceWithDiscount);
        this.adventure = adventure;
    }

    public AdventureDiscountReservation() {
        super();
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

    public ClientFeedback getAdventureOwnerFeedback() {
        return adventureOwnerFeedback;
    }

    public void setAdventureOwnerFeedback(ClientFeedback adventureOwnerFeedback) {
        this.adventureOwnerFeedback = adventureOwnerFeedback;
    }

    public AdventureDiscountReservation(AdventureDiscountReservation res) {

        this.setStartDate(res.getStartDate());
        this.setEndDate(res.getEndDate());
        this.adventure = res.getAdventure();
        this.setNumberOfGuests(res.getNumberOfGuests());
        this.setPercentageOfDiscount(res.getPercentageOfDiscount());
        this.setPriceWithoutDiscount(res.getPriceWithoutDiscount());
        this.setPriceWithDiscount(res.getPriceWithDiscount());
        this.setValidUntil(res.getValidUntil());
        this.setUser(res.getUser());
    }

    @Override
    public String getEntityName() {
        return this.adventure.getName();
    }


    @Override
    public double getTotalPrice() {
        return getPriceWithDiscount();
    }

}

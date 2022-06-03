package com.example.isa.model;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram{

    @Id
    @Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private Long id;

    public double client_reservation_score;
    public double owner_reservation_score;
    public double system_percentage;
    public double silver_points_min;
    public double client_discount_silver;
    public double gold_points_min;
    public double client_discount_gold;


    public LoyaltyProgram() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getClient_reservation_score() {
        return client_reservation_score;
    }

    public void setClient_reservation_score(double client_reservation_score) {
        this.client_reservation_score = client_reservation_score;
    }

    public double getOwner_reservation_score() {
        return owner_reservation_score;
    }

    public void setOwner_reservation_score(double owner_reservation_score) {
        this.owner_reservation_score = owner_reservation_score;
    }

    public double getPercentage() {
        return system_percentage;
    }

    public void setPercentage(double percentage) {
        this.system_percentage = percentage;
    }

    public double getSilver_points_min() {
        return silver_points_min;
    }

    public void setSilver_points_min(double silver_points_min) {
        this.silver_points_min = silver_points_min;
    }

    public double getClient_discount_silver() {
        return client_discount_silver;
    }

    public void setClient_discount_silver(double client_discount_silver) {
        this.client_discount_silver = client_discount_silver;
    }


    public double getGold_points_min() {
        return gold_points_min;
    }

    public void setGold_points_min(double gold_points_min) {
        this.gold_points_min = gold_points_min;
    }

    public double getClient_discount_gold() {
        return client_discount_gold;
    }

    public void setClient_discount_gold(double client_discount_gold) {
        this.client_discount_gold = client_discount_gold;
    }


}

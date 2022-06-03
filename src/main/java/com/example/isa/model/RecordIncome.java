package com.example.isa.model;

import com.example.isa.model.reservations.Reservation;

import javax.persistence.*;

@Entity
@Table(name = "record_income")
public class RecordIncome {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;
    @Column(name = "profit")
    private int profit;
    @Column(name = "reservation_id")
    private long reservation_id;



    public RecordIncome() {

    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}

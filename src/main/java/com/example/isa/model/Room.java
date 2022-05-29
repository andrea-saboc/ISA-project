package com.example.isa.model;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @Column(name = "id", unique = true)
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private Long id;
    private int numberOfBeds;


    public Room(){}

    public Room( int numberOfBeds){
        this.numberOfBeds = numberOfBeds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfBeds=" + numberOfBeds +
                '}';
    }
}

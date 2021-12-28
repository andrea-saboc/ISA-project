package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

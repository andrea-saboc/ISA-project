package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.reservations.BoatDiscountReservation;

public interface BoatDiscountReservationRepository extends JpaRepository<BoatDiscountReservation,Long>{

}

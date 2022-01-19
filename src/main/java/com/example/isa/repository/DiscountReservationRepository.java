package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.reservations.DiscountReservation;

public interface DiscountReservationRepository extends JpaRepository<DiscountReservation, Long>{
	DiscountReservation findById(long id);
}

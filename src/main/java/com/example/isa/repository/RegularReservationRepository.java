package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.reservations.Reservation;

public interface RegularReservationRepository extends JpaRepository<Reservation, Long> {
	Reservation findById(long id);
}

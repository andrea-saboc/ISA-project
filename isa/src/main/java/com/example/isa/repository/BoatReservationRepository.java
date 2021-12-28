package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.BoatReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;


public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long>{

	List<BoatReservation> findAllByUser(User user);
	
}

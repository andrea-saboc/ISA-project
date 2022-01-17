package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatReservation;


public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long>{

	List<BoatReservation> findAllByUser(User user);
	
	List<BoatReservation> findAllByUserAndCancelledFalse(User user);
	BoatReservation findById(long id);
	List<BoatReservation> findAllByBoat(Boat boat);
	

	List<BoatReservation> findAllByBoatAndBoatFeedbackIsNotNull(Boat boat);
}

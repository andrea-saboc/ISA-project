package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.ReservationStatus;


public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long>{

	List<BoatReservation> findAllByUser(User user);
	
	List<BoatReservation> findAllByUserAndStatus(User user,ReservationStatus status);
	BoatReservation findById(long id);
	List<BoatReservation> findAllByBoat(Boat boat);
	

	List<BoatReservation> findAllByBoatAndBoatFeedbackIsNotNull(Boat boat);
}

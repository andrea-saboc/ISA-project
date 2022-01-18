package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;

public interface BoatDiscountReservationRepository extends JpaRepository<BoatDiscountReservation,Long>{


	List<BoatDiscountReservation> findAllByBoat(Boat b);
	List<DiscountReservation> findAllByBoatAndStatus(Boat b,ReservationStatus status);

	List<BoatDiscountReservation> findAllByUserAndStatus(User u,ReservationStatus status);
	
	BoatDiscountReservation findByIdAndStatus(long id,ReservationStatus status);
}

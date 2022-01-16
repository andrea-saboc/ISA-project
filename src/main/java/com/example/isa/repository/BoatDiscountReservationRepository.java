package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;

public interface BoatDiscountReservationRepository extends JpaRepository<BoatDiscountReservation,Long>{


	List<BoatDiscountReservation> findAllByBoat(Boat b);
	List<DiscountReservation> findAllByBoatAndReservedFalse(Boat b);
	List<BoatDiscountReservation> findAllByBoatAndReservedTrue(Boat b);
	List<BoatDiscountReservation> findAllByUserAndCancelledFalseAndReservedTrue(User u);
	
	BoatDiscountReservation findByIdAndReservedFalse(long id);
}

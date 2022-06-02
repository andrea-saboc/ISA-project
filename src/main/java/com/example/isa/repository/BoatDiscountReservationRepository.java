package com.example.isa.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.example.isa.model.reservations.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;

public interface BoatDiscountReservationRepository extends JpaRepository<BoatDiscountReservation,Long>{

	
	BoatDiscountReservation findById(long id);
	List<BoatDiscountReservation> findAllByBoat(Boat b);
	List<DiscountReservation> findAllByBoatAndStatus(Boat b,ReservationStatus status);
	List<DiscountReservation> findAllByBoatAndStatusNot(Boat b, ReservationStatus status);
	List<BoatDiscountReservation> findAllByUserAndStatusNot(User user, ReservationStatus status);


	List<BoatDiscountReservation> findAllByUserAndStatus(User u,ReservationStatus status);
	
	BoatDiscountReservation findByIdAndStatus(long id,ReservationStatus status);
	
	BoatDiscountReservation findByUserAndStartDateAndEndDateAndStatusAndBoat(User user,Date startDate, Date endDate, ReservationStatus status,Boat boat);

    List<DiscountReservation> findAllByUserAndStatusNotAndBoat(User user, ReservationStatus status, Boat boat);
}

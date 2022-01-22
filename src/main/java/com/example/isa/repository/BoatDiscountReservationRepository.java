package com.example.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.ReservationStatus;

public interface BoatDiscountReservationRepository extends JpaRepository<BoatDiscountReservation,Long>{

	
	BoatDiscountReservation findById(long id);
	List<BoatDiscountReservation> findAllByBoat(Boat b);
	List<DiscountReservation> findAllByBoatAndStatus(Boat b,ReservationStatus status);

	List<BoatDiscountReservation> findAllByUserAndStatus(User u,ReservationStatus status);
	
	BoatDiscountReservation findByIdAndStatus(long id,ReservationStatus status);
	
	BoatDiscountReservation findByUserAndStartDateAndEndDateAndStatusAndBoat(User user,Date startDate, Date endDate, ReservationStatus status,Boat boat);

}

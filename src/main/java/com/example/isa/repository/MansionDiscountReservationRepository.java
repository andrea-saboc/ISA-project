package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;

public interface MansionDiscountReservationRepository extends JpaRepository<MansionDiscountReservation,Long>{

	List<DiscountReservation> findAllByMansion(Mansion mansion);
	List<MansionDiscountReservation> findAllByMansionAndStatus(User user,ReservationStatus status);
	List<MansionDiscountReservation> findAllByUserAndStatus(User u,ReservationStatus status);

}

package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.ReservationStatus;

public interface MansionReservationRepository extends JpaRepository<MansionReservation, Long>{

	List<MansionReservation> findAllByUser(User user);
	
	List<MansionReservation> findAllByUserAndStatus(User user,ReservationStatus status);
	
	MansionReservation findById(long id);
	
	List<MansionReservation> findAllByMansionAndMansionFeedbackIsNotNull(Mansion mansion);
	List<MansionReservation> findAllByMansion(Mansion mansion);
	List<MansionReservation> findAllByMansionAndStatusNot(Mansion mansion, ReservationStatus status);
	List<MansionReservation> findAllByUserAndStatusNot(User user, ReservationStatus status);
}

package com.example.isa.repository;

import java.util.Date;
import java.util.List;

import com.example.isa.model.reservations.AdventureDiscountReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;

public interface MansionDiscountReservationRepository extends JpaRepository<MansionDiscountReservation,Long>{

	List<MansionDiscountReservation> findAllByMansion(Mansion mansion);
	List<MansionDiscountReservation> findAllByMansionAndStatus(User user,ReservationStatus status);
	List<MansionDiscountReservation> findAllByUserAndStatus(User u,ReservationStatus status);
	List<MansionDiscountReservation> findAllByUserAndStatusNot(User user, ReservationStatus status);

	List<MansionDiscountReservation> findAllByUserAndStatusNotAndMansion(User user, ReservationStatus status, Mansion mansion);


	MansionDiscountReservation findByIdAndStatus(long id,ReservationStatus status);
	List<DiscountReservation> findAllByMansionAndStatus(Mansion mansion,ReservationStatus status);
	List<DiscountReservation> findAllByMansionAndStatusNot(Mansion mansion, ReservationStatus status);
	
	MansionDiscountReservation findByUserAndStartDateAndEndDateAndStatusAndMansion(User user,Date startDate, Date endDate, ReservationStatus status,Mansion mansion);

}

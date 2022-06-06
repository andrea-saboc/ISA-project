package com.example.isa.repository;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AdventureDiscountReservationRepository  extends JpaRepository<AdventureDiscountReservation,Long> {

    AdventureDiscountReservation findById(long id);
    List<AdventureDiscountReservation> findAllByAdventure(Adventure b);
    List<DiscountReservation> findAllByAdventureAndStatus(Adventure b, ReservationStatus status);
    List<AdventureDiscountReservation> findAllByUserAndStatusNot(User user, ReservationStatus status);
    List<AdventureDiscountReservation> findAllByUserAndStatusNotAndAdventure(User user, ReservationStatus status,Adventure adventure);

    List<AdventureDiscountReservation> findAllByUserAndStatus(User u, ReservationStatus status);

    AdventureDiscountReservation findByIdAndStatus(long id,ReservationStatus status);

    AdventureDiscountReservation findByUserAndStartDateAndEndDateAndStatusAndAdventure(User user, Date startDate, Date endDate, ReservationStatus status, Adventure adventure);
}

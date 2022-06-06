package com.example.isa.repository;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.AdventureReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
    List<AdventureReservation> findAllByType(String type);
    List<AdventureReservation> findAllByUser(User user);
    AdventureReservation findById(long id);
    List<AdventureReservation> findAllByAdventure(Adventure a);
    List<AdventureReservation> findAllByUserAndStatusNot(User user, ReservationStatus status);
    List<AdventureReservation> findAllByAdventureAndStatusNot(Adventure adventure, ReservationStatus status);
    List<AdventureReservation> findAllByUserAndStatusNotAndAdventure(User user, ReservationStatus status, Adventure adventure);


}

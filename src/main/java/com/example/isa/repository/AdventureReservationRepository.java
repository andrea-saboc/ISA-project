package com.example.isa.repository;

import com.example.isa.model.User;
import com.example.isa.model.reservations.AdventureReservation;
import com.example.isa.model.reservations.BoatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  AdventureReservationRepository extends JpaRepository<AdventureReservation, Long> {
    List<AdventureReservation> findAllByType(String type);
    List<AdventureReservation> findAllByUser(User user);
    AdventureReservation findById(long id);

}

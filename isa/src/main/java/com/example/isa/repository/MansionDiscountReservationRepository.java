package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.reservations.MansionDiscountReservation;

public interface MansionDiscountReservationRepository extends JpaRepository<MansionDiscountReservation,Long>{

}

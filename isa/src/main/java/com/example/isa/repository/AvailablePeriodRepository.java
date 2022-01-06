package com.example.isa.repository;

import com.example.isa.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.AvailablePeriod;

import java.util.List;

public interface AvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {
    List<AvailablePeriod> findByBoat(Boat boat);
}

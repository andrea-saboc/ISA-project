package com.example.isa.repository;


import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.FishingAvailablePeriod;
import com.example.isa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FishingAvailablePeriodRepository extends JpaRepository<FishingAvailablePeriod, Long> {

    List<FishingAvailablePeriod> findByFishingInstructor(FishingInstructor fishingInstructor);

}

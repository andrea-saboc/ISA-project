package com.example.isa.repository;


import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.FishingAvailablePeriod;
import com.example.isa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FishingAvailablePeriodRepository extends JpaRepository<FishingAvailablePeriod, Long> {


    @Query(value = "SELECT available_period_id,start_date,end_date,fishing_id FROM public.fishing_available_period u WHERE u.start_date <= :startDate AND u.end_date >= :endDate AND u.fishing_id = :id",
            nativeQuery = true)
    FishingAvailablePeriod getPeriodOfInterest(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("id") long id);

    @Query(value = "SELECT available_period_id,start_date,end_date,fishing_id FROM public.fishing_available_period u WHERE u.end_date = :endDate AND u.fishing_id = :id",
            nativeQuery = true)
    FishingAvailablePeriod checkIfPeriodHasEndDate(@Param("endDate") Date endDate,@Param("id") long id);

    @Query(value = "SELECT available_period_id,start_date,end_date,fishing_id FROM public.fishing_available_period u WHERE u.start_date = :startDate AND u.fishing_id = :id",
            nativeQuery = true)
    FishingAvailablePeriod checkIfPeriodHasStartDate(@Param("startDate") Date startDate,@Param("id") long id);

    List<FishingAvailablePeriod> findByFishingInstructor(FishingInstructor fishingInstructor);
}



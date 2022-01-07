package com.example.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.isa.model.AvailablePeriod;
import com.example.isa.model.Boat;

public interface BoatAvailablePeriodRepository extends JpaRepository<AvailablePeriod, Long> {
    List<AvailablePeriod> findByBoat(Boat boat);
 

    @Query(value = "SELECT available_period_id,start_date,end_date,boat_id FROM public.boat_available_period u WHERE u.start_date <= :startDate AND u.end_date >= :endDate",
    		nativeQuery = true)
    AvailablePeriod getPeriodOfInterest(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    
    
    @Query(value = "SELECT available_period_id,start_date,end_date,boat_id FROM public.boat_available_period u WHERE u.end_date = :endDate",
    		nativeQuery = true)   
    AvailablePeriod checkIfPeriodHasEndDate(@Param("endDate") Date endDate);   
    
    @Query(value = "SELECT available_period_id,start_date,end_date,boat_id FROM public.boat_available_period u WHERE u.start_date = :startDate",
    		nativeQuery = true)   
    AvailablePeriod checkIfPeriodHasStartDate(@Param("startDate") Date startDate); 
}

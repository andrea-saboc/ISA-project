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
 
    //"select a from Article a where a.creationDateTime <= :creationDateTime"  
    //SELECT * FROM public.available_period WHERE start_date >= '2020-01-01' AND end_date < '2020-02-01';
    @Query(value = "SELECT available_period_id,start_date,end_date,boat_id FROM public.boat_available_period u WHERE u.start_date <= :startDate AND u.end_date >= :endDate",
    		nativeQuery = true)
    AvailablePeriod getPeriodOfInterest(@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}

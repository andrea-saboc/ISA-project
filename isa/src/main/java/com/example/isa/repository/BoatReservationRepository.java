package com.example.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.isa.model.BoatReservation;
import com.example.isa.model.User;


public interface BoatReservationRepository extends JpaRepository<BoatReservation, Long>{

	List<BoatReservation> findAllByUser(User user);
	BoatReservation findById(long id);
	
    
	
}

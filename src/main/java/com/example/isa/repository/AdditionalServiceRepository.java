package com.example.isa.repository;

import java.util.List;

import com.example.isa.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.model.reservations.AdditionalService;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService,Long> {
	
	List<AdditionalService> findAllByBoat(Boat b);
	List<AdditionalService> findAllByMansion(Mansion m);
	List<AdditionalService> findAllByAdventure(Adventure adventure);
}

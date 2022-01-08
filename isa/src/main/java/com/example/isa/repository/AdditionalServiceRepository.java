package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.AdditionalService;
import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService,Long> {
	
	List<AdditionalService> findAllByBoat(Boat b);
	List<AdditionalService> findAllByMansion(Mansion m);
}

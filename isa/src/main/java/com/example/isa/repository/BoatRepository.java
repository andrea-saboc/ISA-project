package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.User;

public interface BoatRepository extends JpaRepository<Boat, Long>{

	Boat findByName(String name);
}

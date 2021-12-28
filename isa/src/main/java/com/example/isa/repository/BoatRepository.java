package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, Long>{

}

package com.example.isa.repository;

import com.example.isa.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatsRepository extends JpaRepository<Boat, Long> {
}

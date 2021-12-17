package com.example.isa.repository;

import com.example.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner, Long> {
}

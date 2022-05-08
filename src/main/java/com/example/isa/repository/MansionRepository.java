package com.example.isa.repository;

import com.example.isa.model.MansionOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;

import java.util.List;


public interface MansionRepository extends JpaRepository<Mansion, Long> {

	Mansion findById(long id);
	List<Mansion> findAllByMansionOwnerAndDeletedFalse(MansionOwner mansionOwner);
	Mansion findByIdAndDeletedFalse(long id);
}

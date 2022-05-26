package com.example.isa.repository;

import com.example.isa.model.Adventure;
import com.example.isa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AdventureRepository extends JpaRepository<Adventure, Long> {
    List<Adventure> findAllByFishingInstructorAndDeleted(FishingInstructor fishingInstructor, boolean delete);

    List<Adventure> findAdventureByFishingInstructor(FishingInstructor fishingInstructor);
}


package com.example.isa.repository;

import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.FishingInstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;


public interface AdventureRepository extends JpaRepository<Adventure, Long> {
    List<Adventure> findAllByFishingInstructorAndDeleted(FishingInstructor fishingInstructor, boolean delete);
    Adventure findByIdAndDeletedFalse(long id);
    List<Adventure> findAdventureByFishingInstructor(FishingInstructor fishingInstructor);
    List<Adventure> findAllByFishingInstructorAndDeletedFalse(FishingInstructor fishingInstructor);

    List<Adventure> findAllByFishingInstructor(FishingInstructor fi);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Adventure findLockedById(Long id);
}


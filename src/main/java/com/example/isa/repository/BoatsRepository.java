package com.example.isa.repository;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface BoatsRepository extends JpaRepository<Boat, Long> {
    Boat findByName(String name);
    List<Boat> findBoatByBoatOwner(BoatOwner boatOwner);
    List<Boat> findAllByBoatOwnerAndDeleted(BoatOwner boatOwner, boolean delete);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Boat findLockedById(Long id);
}

package com.example.isa.repository;



import com.example.isa.model.BoatOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Boat;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface BoatRepository extends JpaRepository<Boat, Long>{

	Boat findByName(String name);
	List<Boat> findBoatByBoatOwner(BoatOwner boatOwner);
	Boat findByIdAndDeletedFalse(long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
	Boat findLockedById(Long id);

    List<Boat> findAllByBoatOwnerAndDeletedFalse(BoatOwner fishingInstructor);
}

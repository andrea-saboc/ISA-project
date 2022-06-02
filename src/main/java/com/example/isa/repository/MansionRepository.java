package com.example.isa.repository;

import com.example.isa.model.MansionOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Mansion;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;


public interface MansionRepository extends JpaRepository<Mansion, Long> {

	Mansion findById(long id);
	List<Mansion> findAllByMansionOwnerAndDeletedFalse(MansionOwner mansionOwner);
	Mansion findByIdAndDeletedFalse(long id);

    List<Mansion> findAllByMansionOwner(MansionOwner mansionOwner);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    Mansion findLockedById(Long id);
}

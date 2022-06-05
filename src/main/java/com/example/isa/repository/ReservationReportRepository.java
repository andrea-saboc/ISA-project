package com.example.isa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.ReservationReport;

public interface ReservationReportRepository extends JpaRepository<ReservationReport, Long> {
	ReservationReport findById(long id);
	List<ReservationReport> findAllByApprovedFalseAndRequestedToSanctionTrue();


}

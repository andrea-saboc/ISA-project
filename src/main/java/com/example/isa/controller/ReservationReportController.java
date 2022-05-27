package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.isa.dto.ReportDTO;
import com.example.isa.model.ReservationReport;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.service.impl.ReservationReportService;

@Controller
public class ReservationReportController {

	@Autowired
	private ReservationReportService reservationReportService;

	@PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_BOAT_OWNER', 'ROLE_MANSION_OWNER','ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.POST,value = "/createReport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationReport> createReport(@RequestBody ReportDTO dto){
		System.out.println(dto);
		ReservationReport report = reservationReportService.createReservationReport(dto);
		System.out.println(report);
		return new ResponseEntity<ReservationReport>(report, HttpStatus.OK);
    }
	
}

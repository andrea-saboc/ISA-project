package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET,value = "/getAllReports", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAll() throws JsonProcessingException {
		List<ReservationReport> reservationReports=reservationReportService.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(reservationReports);
		System.out.println(jsonString);
		System.out.println("In contoler");

		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST,value = "/approveComment", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> approveRequest(@RequestBody ReportCommonDto dto){

		System.out.println("In contoler");

		reservationReportService.setApproveComment(dto);

		return ResponseEntity.ok("Successfully created!");
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST,value = "/unapproveComment", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> unapproveComment(@RequestBody ReportCommonDto dto){
		System.out.println(dto);
		reservationReportService.setNotApproveComment(dto);

		return ResponseEntity.ok("Successfully created!");
	}

	
}

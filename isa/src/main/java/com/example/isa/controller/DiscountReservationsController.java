package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientComplaintDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.Complaint;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.service.reservations.BoatDiscountReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class DiscountReservationsController {
	
	@Autowired
	BoatDiscountReservationService boatReservationService;
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/boatDiscountReservations",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BoatDiscountReservation>> getBoatDiscountReservations(@RequestBody long id) throws JsonProcessingException{	
		
		return ResponseEntity.ok(boatReservationService.getBoatDiscountReservations(id));
	}
	

}

package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.AddNewDiscountReservationBoatDto;
import com.example.isa.dto.AllBoatDiscountReservationsDto;
import com.example.isa.exception.PeriodNoLongerAvailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.service.impl.reservations.BoatDiscountReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DiscountReservationsController {
	
	@Autowired
	BoatDiscountReservationService boatReservationService;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/boatDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBoatDiscountReservations(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for" +id);
		
		List<DiscountReservation> res = boatReservationService.getDiscountReservations(id);
		
		System.out.println("controller hit");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(res);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/makeDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makeDiscountBoatReservation(@RequestParam Long id){
		
		try {
		DiscountReservation res = boatReservationService.makeReservationOnDiscount(id);
		return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
		}
		catch(PeriodNoLongerAvailableException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoatDiscountReservation> createBoatDiscountReservationAll(@RequestBody AddNewDiscountReservationBoatDto dto){
		try{
			return new ResponseEntity<>(boatReservationService.createBoatDiscountReservation(dto), HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getBoatDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<AllBoatDiscountReservationsDto> getBoatDiscountReservationsAll(@RequestParam Long boatId){
		AllBoatDiscountReservationsDto allBoatDiscountReservationsDTO = new AllBoatDiscountReservationsDto();
		allBoatDiscountReservationsDTO.freeReservations = boatReservationService.getDiscountReservations(boatId);
		allBoatDiscountReservationsDTO.reservedReservations = boatReservationService.getReservedDiscountReservations(boatId);
		
		return new ResponseEntity<>(allBoatDiscountReservationsDTO, HttpStatus.OK);
	}
}

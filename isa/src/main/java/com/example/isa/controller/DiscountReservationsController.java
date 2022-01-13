package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.service.reservations.BoatDiscountReservationService;
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
		
		List<BoatDiscountReservation> res = boatReservationService.getBoatDiscountReservations(id);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(res);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/makeDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makeDiscountBoatReservation(@RequestParam Long id){
		
		try {
		BoatDiscountReservation res = boatReservationService.makeBoatReservationOnDiscount(id);
		return new ResponseEntity<>("Reservation for " + res.getBoat().getName() + "successfull!", HttpStatus.OK);
		}
		catch(PeriodNoLongerAvailableException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}
	

}

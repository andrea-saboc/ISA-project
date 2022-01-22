package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.AllBoatDiscountReservationsDto;
import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.service.impl.reservations.BoatDiscountReservationService;
import com.example.isa.service.impl.reservations.MansionDiscountReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DiscountReservationsController {
	
	@Autowired
	BoatDiscountReservationService boatReservationService;
	
	@Autowired
	MansionDiscountReservationService mansionReservationService;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/boatDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBoatDiscountReservations(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for" +id);
		
		List<DiscountReservation> res = boatReservationService.getAvailableDiscountReservations(id);
		
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
		catch(ObjectOptimisticLockingFailureException | OfferNotAvailableException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiscountReservation> createBoatDiscountReservationAll(@RequestBody NewDiscountReservationDto dto){
		try{
			return new ResponseEntity<>(boatReservationService.createDiscountReservation(dto), HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/cancelDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiscountReservation> cancelBoatDiscountReservationAll(@RequestBody long id){
		try{
			return new ResponseEntity<>(boatReservationService.cancelDiscountReservation(id), HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/cancelDiscountMansionReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiscountReservation> cancelMansionDiscountReservationAll(@RequestBody long id){
		try{
			return new ResponseEntity<>(mansionReservationService.cancelDiscountReservation(id), HttpStatus.OK);
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
		allBoatDiscountReservationsDTO.freeReservations = boatReservationService.getAvailableDiscountReservations(boatId);
		allBoatDiscountReservationsDTO.reservedReservations = boatReservationService.getReservedDiscountReservations(boatId);
		
		return new ResponseEntity<>(allBoatDiscountReservationsDTO, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/mansionDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMansionDiscountReservations(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for" +id);
		
		List<DiscountReservation> res = mansionReservationService.getAvailableDiscountReservations(id);
		
		System.out.println("controller hit");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(res);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/makeDiscountMansionReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makeDiscountMansionReservation(@RequestParam Long id){
		
		try {
		mansionReservationService.makeReservationOnDiscount(id);
		return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
		}
		catch(ObjectOptimisticLockingFailureException | OfferNotAvailableException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountMansionReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiscountReservation> createMansionDiscountReservationAll(@RequestBody NewDiscountReservationDto dto){
		try{
			return new ResponseEntity<>(mansionReservationService.createDiscountReservation(dto), HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getMansionDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<AllBoatDiscountReservationsDto> getMansionDiscountReservationsAll(@RequestParam Long boatId){
		//mansion owner
		return null;
	}
}

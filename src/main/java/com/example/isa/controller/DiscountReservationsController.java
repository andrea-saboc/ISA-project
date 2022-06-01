package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.AllAdventureDiscountReservationsDto;
import com.example.isa.model.reservations.AdventureDiscountReservation;
import com.example.isa.service.impl.reservations.AdventureDiscountReservationService;
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
import com.example.isa.exception.CancelledReservationException;
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
	AdventureDiscountReservationService adventureDiscountReservationService;
	
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
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountBoatReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createBoatDiscountReservationAll(@RequestBody NewDiscountReservationDto dto){
		try{
			System.out.println(dto);
			int code= boatReservationService.createDiscountReservation(dto);
			if(code == 1)  return new ResponseEntity<>("Discount reservation successfully created!", HttpStatus.OK);
			else if(code == 2) return new ResponseEntity<>("Discount reservation period overlaps withh availability period!", HttpStatus.OK);
			else if(code == 3) return new ResponseEntity<>("Discount reservation could not be created because there is already a reservation that overlaps with selected period!", HttpStatus.OK);
			else return new ResponseEntity<>("It is impossible to save discount reservation!", HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println("Ne okej status");
			System.out.println(e);
			return new ResponseEntity<>("There is a problem!", HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountMansionReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createMansionDiscountReservationAll(@RequestBody NewDiscountReservationDto dto){
		System.out.println("U kontroleru cdmrr");
		try{
			System.out.println(dto);
			int code= mansionReservationService.createDiscountReservation(dto);
			if(code == 1)  return new ResponseEntity<>("Discount reservation successfully created!", HttpStatus.OK);
			else if(code == 2) return new ResponseEntity<>("Discount reservation period overlaps with availability period!", HttpStatus.OK);
			else if(code == 3) return new ResponseEntity<>("Discount reservation could not be created because there is already a reservation that overlaps with selected period!", HttpStatus.OK);
			else return new ResponseEntity<>("It is impossible to save discount reservation!", HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println("Ne okej status");
			System.out.println(e);
			return new ResponseEntity<>("There is a problem!", HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDiscountAdventureReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createDiscountAdventureReservation(@RequestBody NewDiscountReservationDto dto){
		try{
			System.out.println(dto);
			int code= adventureDiscountReservationService.createDiscountReservation(dto);
			if(code == 1)  return new ResponseEntity<>("Discount reservation successfully created!", HttpStatus.OK);
			else if(code == 2) return new ResponseEntity<>("Discount reservation period overlaps with boat availability period!", HttpStatus.OK);
			else if(code == 3) return new ResponseEntity<>("Discount reservation could not be created because there is already a reservation that overlaps with selected period!", HttpStatus.OK);
			else return new ResponseEntity<>("It is impossible to save discount reservation!", HttpStatus.OK);
		}
		catch (Exception e){
			System.out.println("Ne okej status");
			System.out.println(e);
			return new ResponseEntity<>("There is a problem!", HttpStatus.BAD_REQUEST);
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

	@RequestMapping(method = RequestMethod.GET, value = "/getAdventureDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<AllAdventureDiscountReservationsDto> getAdventureDiscountReservations(@RequestParam Long adventureId){
		AllAdventureDiscountReservationsDto allAdventureDiscountReservationsDto = new AllAdventureDiscountReservationsDto();
		allAdventureDiscountReservationsDto.freeReservations = adventureDiscountReservationService.getAvailableDiscountReservations(adventureId);
		allAdventureDiscountReservationsDto.reservedReservations = adventureDiscountReservationService.getReservedDiscountReservations(adventureId);

		return new ResponseEntity<>(allAdventureDiscountReservationsDto, HttpStatus.OK);
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
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
	@RequestMapping(method = RequestMethod.GET, value = "/getMansionDiscountReservations",produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<AllBoatDiscountReservationsDto> getMansionDiscountReservationsAll(@RequestParam Long boatId){
		AllBoatDiscountReservationsDto allBoatDiscountReservationsDTO = new AllBoatDiscountReservationsDto();
		allBoatDiscountReservationsDTO.freeReservations = mansionReservationService.getAvailableDiscountReservations(boatId);
		allBoatDiscountReservationsDTO.reservedReservations = mansionReservationService.getReservedDiscountReservations(boatId);

		return new ResponseEntity<>(allBoatDiscountReservationsDTO, HttpStatus.OK);
	}
}

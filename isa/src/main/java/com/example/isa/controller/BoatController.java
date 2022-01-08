package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.AddAvailablePeriodDTO;
import com.example.isa.dto.BoatRegistrationDTO;
import com.example.isa.dto.LongIdDTO;
import com.example.isa.model.BoatAvailablePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.isa.model.Boat;
import com.example.isa.service.BoatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BoatController {
	
	@Autowired
	private BoatService service;

	public BoatController(BoatService bs){
		this.service = bs;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/boat",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBoatById(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for" +id);
		Boat boat = service.getById(id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boat);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/boats",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getAllBoats() throws JsonProcessingException{
		
		List <Boat> boats = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boats);
		System.out.println(jsonString);
		System.out.println("In contoler");
				
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/ownersBoats",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getOwnersBoats() throws JsonProcessingException{
		List<Boat> boats = service.getByOwnerId();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boats);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/registerBoat",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> registerBoat(@RequestBody BoatRegistrationDTO dto){
		System.out.println("In registering boat service");
		System.out.println(dto);
		Boat savedBoat = service.registerBoat(dto);

		String responseMessege;
		if (savedBoat != null){
			responseMessege = "Boat successfully registered!";
		} else{
			responseMessege = "Error ocured while saving boat!";
		}
		return new ResponseEntity<>(responseMessege, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBoatAvailability", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<BoatAvailablePeriod>> getAvailablePeriod(@RequestBody LongIdDTO dto) throws JsonProcessingException{
		System.out.println("In getting availability for boats");
		List<BoatAvailablePeriod> boatAvailabilities = service.getBoatAvailbilities(dto.boatId);
		ObjectMapper mapper = new ObjectMapper();
		System.out.print("Before coverting" + boatAvailabilities.toString());
		String jsonString = mapper.writeValueAsString(boatAvailabilities);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(boatAvailabilities, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value ="/addAvailablePeriodForBoat", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> addAvailablePeriodForBoat(@RequestBody AddAvailablePeriodDTO dto) throws JsonProcessingException{
		System.out.println("Adding available period for boat!");
		List<BoatAvailablePeriod> boatAvailabilities = service.addBoatAvailabilities(dto);
		ObjectMapper mapper = new ObjectMapper();
		System.out.print("Before coverting" + boatAvailabilities.toString());
		String jsonString = mapper.writeValueAsString(boatAvailabilities);
		System.out.println(jsonString);
		System.out.println("Finished");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
}

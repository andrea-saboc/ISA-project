package com.example.isa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Boat;
import com.example.isa.model.Mansion;
import com.example.isa.service.BoatService;
import com.example.isa.service.MansionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MansionController {
	
	@Autowired
	MansionService service;

	@RequestMapping(method = RequestMethod.GET, value = "/mansions",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> registerClient() throws JsonProcessingException{
		
		ArrayList<Mansion> mansions = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansions);
				
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	
	

}

package com.example.isa.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientDTO;

@RestController
public class ClientController {
	

	
	//@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/clientRegistration")
	//@CrossOrigin(origins = "*")
    
	@RequestMapping(method = RequestMethod.POST, value = "/clientRegistration",consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> registerClient(ClientDTO clientData){
		
		System.out.println("Client added :)");
		System.out.println(clientData.toString());
		return new ResponseEntity<>("Client is registered", HttpStatus.OK);
	}	
	

}

package com.example.isa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientRegistrationDTO;
import com.example.isa.model.User;
import com.example.isa.service.implemented.ClientRegistrationService;

@RestController
public class ClientController {
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateUser",consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		
		System.out.println("Client to update :)");
		System.out.println(user.getName());

		return new ResponseEntity<>("Client is updated", HttpStatus.OK);
	}
	

}

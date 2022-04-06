package com.example.isa.controller;



import com.example.isa.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ChangingPasswordDto;
import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.service.impl.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ClientController {
	

	@Autowired
    private ClientService userService;
	@Autowired
	private AuthenticationService authenticationService;


	@PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.GET, value = "/userData",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")	
	public ResponseEntity<User> getUserData(){
		System.out.println("-----------------get user data-----------");
		try{
			return ResponseEntity.ok(authenticationService.getLoggedUser());
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/updateUser",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")	
	public ResponseEntity<User> updateUser(@RequestBody Client user) throws JsonProcessingException{	
		return ResponseEntity.ok(userService.updateClientInfo(user));
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/changePassword",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")	
	public ResponseEntity<String> updatePassword(@RequestBody ChangingPasswordDto dto) throws JsonProcessingException{	
		try{
			userService.ChangePassword(dto);
			return ResponseEntity.ok("Password changed");
		}catch(BadCredentialsException e) {
			return ResponseEntity.ok("Wrong old password ");
		}
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/createDeletionRequest",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")	
	public ResponseEntity<User> createDeletionRequest(@RequestBody String reason) throws JsonProcessingException{	
		return ResponseEntity.ok(userService.createDeletionRequest(reason));
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/emailExistsClient")
	public ResponseEntity<Boolean> checkIfClientWithEmailExists(@RequestBody String email) {
		return new ResponseEntity<>(userService.checkIfClientEmailExists(email), HttpStatus.OK);
	}
	



}

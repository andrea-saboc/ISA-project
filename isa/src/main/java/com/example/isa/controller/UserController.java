package com.example.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.isa.model.User;
import com.example.isa.service.implemented.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {
	

	@Autowired
    private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/userData",produces = MediaType.APPLICATION_JSON_VALUE )
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<User> getUserData(/*@RequestBody String userEmail*/) throws JsonProcessingException{	
		return ResponseEntity.ok(userService.getLoggedUser());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateUser",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<User> updateUser(@RequestBody User user) throws JsonProcessingException{	
		return ResponseEntity.ok(userService.updateUserInfo(user));
	}	


}
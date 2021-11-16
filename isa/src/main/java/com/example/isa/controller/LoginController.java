package com.example.isa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientDTO;
import com.example.isa.dto.LoginDTO;

@RestController
public class LoginController {

	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginData){
		
		System.out.println("Client login :)");
		System.out.println(loginData.toString());

		return new ResponseEntity<>("Client is logged in", HttpStatus.OK);
	}
}

package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.LoginDto;
import com.example.isa.dto.UserTokenState;
import com.example.isa.service.UserService;
import com.example.isa.service.impl.LoginServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoginController {
	
	@Autowired	
	LoginServiceImpl loginService;
	@Autowired
	UserService userService;

	
	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserTokenState> login(@RequestBody LoginDto loginData,HttpServletRequest req) throws JsonProcessingException, BadCredentialsException{

		try{
			UserTokenState state = loginService.logIn(loginData);
	        return ResponseEntity.ok(state);
		}
		catch(BadCredentialsException e) {
			return ResponseEntity.ok(null);
		}		
	}
	

	

	
	@RequestMapping(method = RequestMethod.POST, value = "/checkActivationCode",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> checkActivationCode(@RequestBody String c) throws JsonProcessingException{
		
		String code = c.substring(1,c.length()-1);
		
		
		if(userService.userAlreadyActivated(code)) return ResponseEntity.ok("already validated");
				
		boolean valid = userService.checkActivationCode(code);
		
		if(valid) return ResponseEntity.ok("valid");
		else return ResponseEntity.ok("not valid");
	}

}

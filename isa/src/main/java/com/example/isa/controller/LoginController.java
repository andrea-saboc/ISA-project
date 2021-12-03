package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.isa.dto.LoginDTO;
import com.example.isa.dto.UserTokenState;
import com.example.isa.enums.UserType;
import com.example.isa.model.User;
import com.example.isa.service.ClientService;
import com.example.isa.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {
	
	@Autowired	
	LoginService loginService;

	
	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO loginData,HttpServletRequest req) throws JsonProcessingException{
		
        UserTokenState state = loginService.logIn(loginData);
        return ResponseEntity.ok(state);		
		
	}
	

}

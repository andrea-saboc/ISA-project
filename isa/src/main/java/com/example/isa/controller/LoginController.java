package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.isa.dto.LoginDTO;
import com.example.isa.enums.UserType;
import com.example.isa.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {

	
	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginData,HttpServletRequest request) throws JsonProcessingException{
		
		HttpSession session = request.getSession(true);	
		
		if(!userExists(loginData.getEmail())) {
			session.setAttribute("User","No user");
			return new ResponseEntity<>("User does not exist", HttpStatus.OK);
		}
	
		else if (!passwordInvalid(loginData)) {
			session.setAttribute("User","No user");
			return new ResponseEntity<>("Invalid password", HttpStatus.OK);	
		}
		else {
			
			User user = loginUser(loginData);
			session.setAttribute("User", user);

			return new ResponseEntity<>(user.getUserType().name(), HttpStatus.OK);
		}
	}
	
	private boolean userExists(String email) {
		return true;
	}
	
	private boolean passwordInvalid(LoginDTO loginData) {	
		return true;
	}
	
	private User loginUser(LoginDTO loginData) {

		User client = new User(1, "nameee", "surname", "address", "city", "bsckj",
				"hcbjv", "jfjh", "ytyt",UserType.CLIENT);
		return client;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/loginTest",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> loginTest(HttpServletRequest request) throws JsonProcessingException{

		
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("User"));
		if(session.getAttribute("User") == "No user")
			return new ResponseEntity<>("Client is not logged in", HttpStatus.OK);
		else {
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(session.getAttribute("User"));
			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		}
	}
	

}

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
import com.example.isa.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {

	
	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginData,HttpServletRequest request) throws JsonProcessingException{
		
		System.out.println(loginData.getEmail());
		
		if(!userExists(loginData.getEmail()))
			return new ResponseEntity<>("User does not exist", HttpStatus.OK);	
		else if (!passwordInvalid(loginData))
			return new ResponseEntity<>("Invalid password", HttpStatus.OK);			
		else {
			
			System.out.println("DOODODOODODOo");
			Object User = loginUser(loginData);
			HttpSession session = request.getSession();
			session.setAttribute("User", User);
			
			System.out.println(User.toString());
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(session.getAttribute("User"));
			
			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		}
	}
	
	private boolean userExists(String email) { //treba pozvati sve servise i proveriti
		
		//check clients
		//check managers
		//check instructors
		
		return true;
	}
	
	private boolean passwordInvalid(LoginDTO loginData) { //treba pozvati sve servise i proveriti
		
		//check clients
		//check managers
		//check instructors
		
		return true;
	}
	
	private Object loginUser(LoginDTO loginData) {
		
		//
		//		
		//
		Client client = new Client("id", "nameee", "surname", "address", "city", "bsckj",
				"hcbjv", "jfjh", "ytyt",UserType.CLIENT);
		return client;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/loginTest",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> loginTest(HttpServletRequest request) throws JsonProcessingException{

		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("User") == null)
			return new ResponseEntity<>("Client is not logged in", HttpStatus.OK);
		else {
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(session.getAttribute("User"));
			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		}
	}
	

}

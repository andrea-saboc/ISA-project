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
import com.example.isa.enums.UserType;
import com.example.isa.model.User;
import com.example.isa.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {
	
	@Autowired	
	ClientService clientService;

	
	@RequestMapping(method = RequestMethod.POST, value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginData,HttpServletRequest req) throws JsonProcessingException{
		
		@SuppressWarnings("unchecked")
		HttpSession session = req.getSession();
		
		System.out.println("Usli smo u login");
		
		if(!clientService.userExists(loginData.getEmail())) {
			session.setAttribute("User","No user");
			return new ResponseEntity<>("User does not exist", HttpStatus.OK);
		}
	
		else if (!clientService.passwordInvalid(loginData)) {
			session.setAttribute("User","No user");
			return new ResponseEntity<>("Invalid password", HttpStatus.OK);	
		}
		else {
			
			User user = clientService.loginUser(loginData);
			session.setAttribute("User", user);
			System.out.println("postavljen je user atr ali kako cita kad ga uzme od sesije");

			return new ResponseEntity<>("CLIENT", HttpStatus.OK);
		}
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/loginTest",produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> loginTest(HttpSession session) throws JsonProcessingException{
		
		
		User user = (User) session.getAttribute("User");
		
		if(null == user) {
			session.setAttribute("User", new User());
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(clientService.loginUser(new LoginDTO("","")));
			
			return new ResponseEntity<>(jsonString, HttpStatus.OK);
		}
		else {
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(user);
			return new ResponseEntity<>(jsonString, HttpStatus.OK);	
			
		}
				
	}

	

}

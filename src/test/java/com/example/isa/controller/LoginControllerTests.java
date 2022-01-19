package com.example.isa.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.isa.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTests {
	

	    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype());
	    
	    @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private WebApplicationContext webApplicationContext;

	    @BeforeEach
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	    }

	    
	    @Test//(expected=BadCredentialsException.class)
	    public void loginUnsuccessfull() throws Exception{
	    	
	    	LoginDto search = new LoginDto("dmhcfdvkf","cdgsjhcdj");
	    	
	    	ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(search);
	        
	    	mockMvc.perform(post("/login")
	    		    .contentType(MediaType.APPLICATION_JSON)
	    		    .content(jsonString)
	    		    .characterEncoding("utf-8"))
		    		.andExpect(status().is(401));
	    }
	    
	    @Test
	    public void loginSuccessfull() throws Exception{
	    	
	    	LoginDto search = new LoginDto("admin@gmail.com","123");
	    	
	    	ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(search);
	        
	    	mockMvc.perform(post("/login")
	    		    .contentType(MediaType.APPLICATION_JSON)
	    		    .content(jsonString)
	    		    .characterEncoding("utf-8"))
		    		.andExpect(status().isOk());
	    }
	        

}

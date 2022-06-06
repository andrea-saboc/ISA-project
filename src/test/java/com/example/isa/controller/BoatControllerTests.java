package com.example.isa.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.isa.dto.SearchDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BoatControllerTests {
	
    private static final String URL_PREFIX = "/boats";
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

    
    @Test
    public void getBoatSearchResultsNone() throws Exception {
    	
    	SearchDto search = new SearchDto("Name", "Nonexistantname");
    	
    	ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(search);
        
    	mockMvc.perform(post("/boats/search")
    		    .contentType(MediaType.APPLICATION_JSON)
    		    .content(jsonString)
    		    .characterEncoding("utf-8"))
    		    .andExpect(status().isOk())
    		    .andExpect(jsonPath("$", hasSize(0)));
    }
    
    @Test
    public void getSpecificBoatByName() throws Exception {
    	
    	SearchDto search = new SearchDto("Name", "BENETEAU");
    	
    	ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(search);
        
    	mockMvc.perform(post("/boats/search")
    		    .contentType(MediaType.APPLICATION_JSON)
    		    .content(jsonString)
    		    .characterEncoding("utf-8"))
    		    .andExpect(status().isOk())
    		    .andExpect(jsonPath("$", hasSize(1)));
    }
    
}

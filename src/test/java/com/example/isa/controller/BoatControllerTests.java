package com.example.isa.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.isa.dto.AddAvailablePeriodDto;
import com.example.isa.dto.LongIdDto;
import com.example.isa.service.impl.DateCoverter;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.isa.dto.SearchDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
		"APP_URL=https://localhost:8080/",
		"EPASS=eaqmslibsskdayfv",
		"EMAIL=adventurelandisa@gmail.com",
		"PASS=root"})
@AutoConfigureMockMvc
public class BoatControllerTests {
	
    private static final String URL_PREFIX = "/boats";
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
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
    	
    	SearchDto search = new SearchDto("Name", "BENE");
    	
    	ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(search);
        
    	mockMvc.perform(post("/boats/search")
    		    .contentType(MediaType.APPLICATION_JSON)
    		    .content(jsonString)
    		    .characterEncoding("utf-8"))
    		    .andExpect(status().isOk())
    		    .andExpect(jsonPath("$", hasSize(1)));
    }

	@Test
	public void addAvailabilityPeriodForBoatOverlapping() throws Exception{
		AddAvailablePeriodDto dto = new AddAvailablePeriodDto();
		dto.boatId = 3L;
		LocalDateTime start = LocalDateTime.of(2022, Month.JANUARY, 24, 9, 0,0);
		LocalDateTime end = LocalDateTime.of(2022, Month.MAY, 15, 22, 0,0);

		dto.startTime = DateCoverter.convertToDate(start);
		dto.endTime = DateCoverter.convertToDate(end);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(dto);

		//preklapajuci termin samo produzi prethodni ostaje isti broj termina
		mockMvc.perform(post("/addAvailablePeriodForBoat")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)
						.with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)));

	}

	@Test
	public void addAvailabilityPeriodForBoatWithoutOverlapping() throws Exception{
		AddAvailablePeriodDto dto = new AddAvailablePeriodDto();
		dto.boatId = 3L;
		LocalDateTime start = LocalDateTime.of(2023, Month.JULY, 15, 9, 0,0);
		LocalDateTime end = LocalDateTime.of(2023, Month.JULY, 30, 22, 0,0);

		dto.startTime = DateCoverter.convertToDate(start);
		dto.endTime = DateCoverter.convertToDate(end);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(dto);

		//nepreklpajuci termin doda novi termin dostupnosti broda
		mockMvc.perform(post("/addAvailablePeriodForBoat")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonString)
						.with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
						.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(5)));
	}

	@Test
	public void getOwnersBoats() throws Exception{
		//dobavljamo brodove vlasnika
		mockMvc.perform(get("/ownersBoats")
						.contentType(MediaType.APPLICATION_JSON)
						.with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
						.characterEncoding("utf-8"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void deleteBoat() throws Exception{
		LongIdDto dto = new LongIdDto();
		dto.boatId = 7L;
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(dto);
		mockMvc.perform(post("/deleteBoat")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonString)
						.with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
						.characterEncoding("utf-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Boat is reserved, not possible to change or delete!"));
		;
	}




}

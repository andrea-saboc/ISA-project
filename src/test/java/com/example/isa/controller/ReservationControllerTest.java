package com.example.isa.controller;

import com.example.isa.dto.AddAvailablePeriodDto;
import com.example.isa.dto.CustomReservationForClientDto;
import com.example.isa.service.impl.DateCoverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "EPASS=eaqmslibsskdayfv",
        "EMAIL=adventurelandisa@gmail.com",
        "PASS=root"})
@AutoConfigureMockMvc
public class ReservationControllerTest {
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
    public void makeBoatReservationForClientClientWrongMail() throws Exception{
        CustomReservationForClientDto dto = new CustomReservationForClientDto();
        dto.email = "anyaaa@gmail.com";
        dto.additionalServiceSet = new ArrayList<>();
        dto.days = 4;
        dto.hours = 5;
        dto.boatId = 3L;
        dto.numberOfGuests = 5;
        dto.startDate = DateCoverter.convertToDate(LocalDateTime.of(2022, 3, 4,5,3, 5));
        dto.isOwnerPresent = false;

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        //kreiranje rezervacije za klijenta sa pogresnim mejlom
        mockMvc.perform(post("/makeBoatReservationClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Client with selected email adress does not exist in the system!"));
    }

    @Test
    public void makeBoatReservationForClientDoesNotHaveAnActiveReservation() throws Exception{
        CustomReservationForClientDto dto = new CustomReservationForClientDto();
        dto.email = "anya@gmail.com";
        dto.additionalServiceSet = new ArrayList<>();
        dto.days = 4;
        dto.hours = 5;
        dto.boatId = 3L;
        dto.numberOfGuests = 5;
        dto.startDate = DateCoverter.convertToDate(LocalDateTime.of(2022, 3, 4,5,3, 5));
        dto.isOwnerPresent = false;

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        //kreiranje rezervacije za klijenta sa pogresnim mejlom
        mockMvc.perform(post("/makeBoatReservationClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Client does not have an active reservation!"));
    }

    @Test
    public void makeBoatReservationForClientBoatNotAvailable() throws Exception{
        CustomReservationForClientDto dto = new CustomReservationForClientDto();
        dto.email = "anya@gmail.com";
        dto.additionalServiceSet = new ArrayList<>();
        dto.days = 4;
        dto.hours = 5;
        dto.boatId = 8L;
        dto.numberOfGuests = 5;
        dto.startDate = DateCoverter.convertToDate(LocalDateTime.of(2024, 3, 4,5,3, 5));
        dto.isOwnerPresent = false;

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        //kreiranje rezervacije za klijenta sa pogresnim mejlom
        mockMvc.perform(post("/makeBoatReservationClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Boat is not available at selected time!"));
    }

    @Test
    public void makeBoatReservationForClientClientNotAvailable() throws Exception{
        CustomReservationForClientDto dto = new CustomReservationForClientDto();
        dto.email = "littlevamp999@gmail.com";
        dto.additionalServiceSet = new ArrayList<>();
        dto.days = 4;
        dto.hours = 5;
        dto.boatId = 7L;
        dto.numberOfGuests = 5;
        dto.startDate = DateCoverter.convertToDate(LocalDateTime.of(2023, 12, 1,5,3, 5));
        dto.isOwnerPresent = false;

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        //kreiranje rezervacije za klijenta sa pogresnim mejlom
        mockMvc.perform(post("/makeBoatReservationClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").value("Client is not available at selected time!"));
    }

  /*  @Test
    public void makeBoatReservationForClientSuccess() throws Exception{
        CustomReservationForClientDto dto = new CustomReservationForClientDto();
        dto.email = "littlevamp999@gmail.com";
        dto.additionalServiceSet = new ArrayList<>();
        dto.days = 2;
        dto.hours = 0;
        dto.boatId = 7L;
        dto.numberOfGuests = 5;
        dto.startDate = DateCoverter.convertToDate(LocalDateTime.of(2023, 12, 22,5,3, 5));
        dto.isOwnerPresent = false;

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);

        //kreiranje rezervacije za klijenta sa pogresnim mejlom
        mockMvc.perform(post("/makeBoatReservationClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .with(user("markijan@gmail.com").password("123").roles("BOAT_OWNER"))
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").value("Reservation for client is created successfully!"));
    }*/
}
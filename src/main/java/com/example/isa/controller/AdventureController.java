package com.example.isa.controller;

import com.example.isa.dto.*;
import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.FishingAvailablePeriod;
import com.example.isa.service.impl.AdventureService;
import com.example.isa.service.impl.BoatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AdventureController {

    @Autowired
    private AdventureService service;

    public AdventureController(AdventureService bs){
        this.service = bs;
    }


    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/adventure",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getAdventureById(@RequestParam Long id) throws JsonProcessingException{
        System.out.println("We are searchinng for" +id);
        Adventure adventure = service.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(adventure);
        System.out.println(jsonString);
        return new ResponseEntity<>(jsonString, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.POST, value = "/registerAdventure",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> registerAdventure(@RequestBody AdventureRegistrationDto dto){
        System.out.println(dto);
        Adventure savedAdventure = service.registerAdventure(dto);

        String responseMessege;
        if (savedAdventure != null){
            responseMessege = "Adventure successfully registered!";
        } else {
            responseMessege = "Error ocured while saving adventure!";
        }
        return new ResponseEntity<>(responseMessege, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/ownersAdventures",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getOwnersAdventures() throws JsonProcessingException {
        List<Adventure> adventures = service.getByOwnerId();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(adventures);
        System.out.println(jsonString);
        System.out.println("In contoler");
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/getFishingAvailability", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<FishingAvailablePeriod>> getAvailablePeriod(@RequestBody LongIdDto dto) throws JsonProcessingException{
        System.out.println("In getting availability for adventure");
        List<FishingAvailablePeriod> fishingAvailablePeriods = service.getFishingAvailablePeriod(dto.fishingId);
        ObjectMapper mapper = new ObjectMapper();
        System.out.print("Before coverting" + fishingAvailablePeriods.toString());
        String jsonString = mapper.writeValueAsString(fishingAvailablePeriods);
        System.out.println(jsonString);
        System.out.println("In contoler");
        return new ResponseEntity<>(fishingAvailablePeriods, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/addAvailablePeriodForFishing", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addAvailablePeriodForFishing(@RequestBody AddAvailablePeriodFishingInstructorDto dto) throws JsonProcessingException{
        System.out.println("Adding available period for fishingInstructor!");
        List<FishingAvailablePeriod> fishingAvailablePeriods = service.addFishingAvailabilities(dto);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(fishingAvailablePeriods);
        System.out.println(jsonString);
        System.out.println("Finished");
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }



}

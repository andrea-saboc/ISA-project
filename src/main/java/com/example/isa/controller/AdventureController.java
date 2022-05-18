package com.example.isa.controller;

import com.example.isa.dto.AdventureRegistrationDto;
import com.example.isa.dto.BoatRegistrationDto;
import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
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
        System.out.println("In registering boat service");
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

    /*@RequestMapping(value = "/entityImage/images/{entity}/{imgName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getImageRes( @PathVariable String entity,@PathVariable String imgName) throws IOException {
        System.out.println("TU SAM PICKO");
        //byte[] img = entityImageService.getImageByte(role, user, entity, imgName);
        /*byte[] img = entityImageService.getEntityImage(entity, imgName);
        if(img != null){
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(img);
        }else{
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.IMAGE_JPEG).body(null);
        }+/


        return null;

    }*/

}

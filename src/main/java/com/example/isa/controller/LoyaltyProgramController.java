package com.example.isa.controller;

import com.example.isa.model.Boat;
import com.example.isa.model.Client;
import com.example.isa.model.LoyaltyProgram;
import com.example.isa.model.User;
import com.example.isa.repository.LoyaltyProgramRepository;
import com.example.isa.service.impl.LoyaltyProgramService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LoyaltyProgramController {
    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/updateLoyaltyProgram",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<LoyaltyProgram> updateLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram) throws JsonProcessingException {
        return ResponseEntity.ok(loyaltyProgramService.update(loyaltyProgram));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/loyaltyProgram",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> loyaltyProgram() throws JsonProcessingException{

        LoyaltyProgram loyaltyProgram=loyaltyProgramService.getOne();
        System.out.println(loyaltyProgram.toString());
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(loyaltyProgram);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }


}

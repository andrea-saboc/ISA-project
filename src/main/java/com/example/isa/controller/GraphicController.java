package com.example.isa.controller;

import com.example.isa.dto.GraphicDataDto;
import com.example.isa.service.impl.GraphicDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GraphicController {
    @Autowired
    GraphicDataService graphicDataService;

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/graphicDataBoatOwner",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> graphicDataBO() throws JsonProcessingException {
        GraphicDataDto dto = graphicDataService.examinateAllDataBoatOwner();
        if(dto == null) return new ResponseEntity<>("It is impossible to examinate data!", HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/graphicDataMansionOwner",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> graphicDataMO() throws JsonProcessingException {
        GraphicDataDto dto = graphicDataService.examinateAllDataMansionOwner();
        if(dto == null) return new ResponseEntity<>("It is impossible to examinate data!", HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/graphicDataFishingInstructor",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> graphicDataFI() throws JsonProcessingException {
        GraphicDataDto dto = graphicDataService.examinateAllDataFishingInstructor();
        if(dto == null) return new ResponseEntity<>("It is impossible to examinate data!", HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(dto);
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }


}

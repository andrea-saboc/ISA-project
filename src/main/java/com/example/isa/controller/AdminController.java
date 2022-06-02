package com.example.isa.controller;


import com.example.isa.dto.AccountDeletionDTO;
import com.example.isa.dto.ReportAcceptDeletedAccountDTO;
import com.example.isa.dto.ReportAdminDto;
import com.example.isa.dto.ReportDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.ReservationReport;
import com.example.isa.model.User;
import com.example.isa.service.impl.AdministratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdministratorService administratorService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/allUsersWithoutAdmin",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> allUsersWithoutAdmin() throws JsonProcessingException {

        List<User> users= administratorService.getAllUsersWithoutAdmin();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(users);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST,value = "/unapproveRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> unapproveRegistration(@RequestBody ReportAdminDto dto){
        System.out.println(dto);
        administratorService.unapproveRegistration(dto);

        return ResponseEntity.ok("Successfully created!");
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST,value = "/approveRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> approveRegistration(@RequestBody ReportAdminDto dto){
        System.out.println(dto);
        administratorService.approveRegistration(dto);

        return ResponseEntity.ok("Successfully created!");
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/allRequestForDeleteAccount",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> allRequestForDeleteAccount() throws JsonProcessingException {

        List<AccountDeletionDTO> users= administratorService.getAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(users);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST,value = "/approveRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> approveRequest(@RequestBody ReportAcceptDeletedAccountDTO id){

        System.out.println("In contoler");

        administratorService.acceptAccountDeleted(id.getId());

        return ResponseEntity.ok("Successfully created!");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST,value = "/unapproveRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> unapproveRequest(@RequestBody ReportAcceptDeletedAccountDTO dto){
        System.out.println(dto);
        administratorService.unacceptAccountDeleted(dto);

        return ResponseEntity.ok("Successfully created!");
    }
}


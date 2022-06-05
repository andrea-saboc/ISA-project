package com.example.isa.controller;


import com.example.isa.dto.*;
import com.example.isa.model.*;
import com.example.isa.repository.*;
import com.example.isa.service.impl.AdministratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
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
    @Autowired
    private MansionOwnerRepository mansionOwnerRepository;
    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private UserRepository userRepository;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllMansionOwners",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getAllMansionOwners() throws JsonProcessingException{

        List <MansionOwner> mansions = mansionOwnerRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mansions);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllInstructor",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getAllInstructor() throws JsonProcessingException{

        List <FishingInstructor> mansions = fishingInstructorRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mansions);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllClient",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getAllClient() throws JsonProcessingException{

        List <Client> mansions = clientRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mansions);
        System.out.println(jsonString);
        System.out.println("In contoler");

        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBoatOwners",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getAllBoatOwners() throws JsonProcessingException{

        List <BoatOwner> mansions = boatOwnerRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mansions);
        System.out.println(jsonString);
        System.out.println("In contoler");
        return new ResponseEntity<>(jsonString, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAdventure(@RequestBody LongIdDto userId){

        try {
            administratorService.deletedUser(userId.userId);
            return new ResponseEntity<String>("Successfully deleted user!", HttpStatus.OK);
        }
        catch (PessimisticLockingFailureException pe){
            return new ResponseEntity<>("Client is reserving the entity!", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


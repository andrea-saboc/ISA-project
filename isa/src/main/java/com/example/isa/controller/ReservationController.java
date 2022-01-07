package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.BoatReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.service.ReservationService;
import com.example.isa.service.ReservationSuggestionService;
import com.example.isa.service.implemented.UserService;



@RestController
public class ReservationController {
	
	@Autowired 
	ReservationService service;
	@Autowired
	UserService userService;	
	@Autowired
	BoatRepository repo;
	@Autowired
	ClientRepository clients;
	@Autowired
	ReservationSuggestionService suggestionService;
	
	
	
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/boats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BoatReservation>> getBoatReservations(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            return new ResponseEntity<>(service.GetBoatReservationHistory(clients.findByEmail(user.getEmail())), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/mansions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MansionReservation>> getMansionReservations(){

        try {
            return new ResponseEntity<>(service.GetMansionReservationHistory(clients.findByEmail("igi@gmail.com")), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Reservation>> getUserReservations(){
    	
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//System.out.println(user.getEmail());
        try {
            return new ResponseEntity<>(service.GetUserReservations(clients.findByEmail("igi@gmail.com")), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableBoats", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Iterable<Boat>> getAvailableBoats(@RequestBody ReservationSearchDTO search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(suggestionService.getAvailableBoats(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<BoatReservation> createBoatReservation(@RequestBody BoatReservationDTO res){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(service.createBoatReservation(res), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

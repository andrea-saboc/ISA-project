package com.example.isa.controller;

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

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.ReservationService;
import com.example.isa.service.implemented.UserService;



@RestController
public class ReservationController {
	
	@Autowired 
	ReservationService service;
	@Autowired
	UserService userService;
	
	@Autowired
	BoatRepository repo;
	
    @RequestMapping(value = "/reservations/boats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BoatReservation>> getBoatReservations(@RequestBody AdvertiserRegistrationDTO advertiserData){

        try {
            return new ResponseEntity<>(service.GetAllByUser(null), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/reservations/mansions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BoatReservation>> getMansionReservations(@RequestBody AdvertiserRegistrationDTO advertiserData){

        try {
            return new ResponseEntity<>(service.GetAllByUser(null), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/reservations/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BoatReservation>> getAdventureReservations(@RequestBody AdvertiserRegistrationDTO advertiserData){

        try {
            return new ResponseEntity<>(service.GetAllByUser(null), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Iterable<Reservation>> getUserReservations(){
    	
    	User user = userService.getLoggedUser();
    	System.out.println("Uspio user");
        try {
            return new ResponseEntity<>(service.GetUserReservations(user), HttpStatus.OK);
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
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

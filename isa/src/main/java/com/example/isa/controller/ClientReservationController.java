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

import com.example.isa.dto.ActiveReservationDTO;
import com.example.isa.dto.PotentialBoatReservationDTO;
import com.example.isa.dto.PotentialMansionReservationDTO;
import com.example.isa.dto.ReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.service.BoatReservationService;
import com.example.isa.service.BoatReservationSuggestionService;
import com.example.isa.service.MansionReservationService;
import com.example.isa.service.MansionReservationSuggestionService;
import com.example.isa.service.ReservationService;
import com.example.isa.service.implemented.UserService;



@RestController
public class ClientReservationController {
	
	@Autowired 
	BoatReservationService boatResService;
	@Autowired 
	MansionReservationService mansionResService;
	@Autowired
	UserService userService;	
	@Autowired
	BoatRepository repo;
	@Autowired
	ClientRepository clients;
	@Autowired
	BoatReservationSuggestionService boatSuggestionService;
	@Autowired
	MansionReservationSuggestionService mansionSuggestionService;
	@Autowired
	ReservationService reservationService;
	
	
	
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/boats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BoatReservation>> getBoatReservations(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            return new ResponseEntity<>(boatResService.GetBoatReservationHistory(clients.findByEmail(user.getEmail())), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/mansions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MansionReservation>> getMansionReservations(){

        try {
            return new ResponseEntity<>(boatResService.GetMansionReservationHistory(clients.findByEmail("igi@gmail.com")), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ActiveReservationDTO>> getUserReservations(){
    	
        try {
            return new ResponseEntity<>(reservationService.getActiveReservations(), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableBoats", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PotentialBoatReservationDTO>> getAvailableBoats(@RequestBody ReservationSearchDTO search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(boatSuggestionService.getAvailableBoats(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableMansions", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PotentialMansionReservationDTO>> getAvailableMansions(@RequestBody ReservationSearchDTO search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(mansionSuggestionService.getAvailableMansions(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<BoatReservation> createBoatReservation(@RequestBody ReservationDTO res){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(boatResService.createBoatReservation(res), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createMansionReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<MansionReservation> createMansionReservation(@RequestBody ReservationDTO res){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(mansionResService.createMansionReservation(res), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @RequestMapping(method = RequestMethod.POST,value = "/reservations/cancelBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<BoatReservation> cancelBoatReservation(@RequestBody long  resId){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(boatResService.cancelBoatReservation(resId), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/cancelMansionReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<MansionReservation> cancelMansionReservation(@RequestBody long  resId){
    	
        try {
            return new ResponseEntity<>(mansionResService.cancelMansionReservation(resId), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

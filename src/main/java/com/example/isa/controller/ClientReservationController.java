package com.example.isa.controller;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ActiveReservationDTO;
import com.example.isa.dto.MakeBoatReservationForClientDTO;
import com.example.isa.dto.PotentialReservationDTO;
import com.example.isa.dto.ReservationDTO;
import com.example.isa.dto.ReservationSearchDTO;
import com.example.isa.exceptions.PeriodNoLongerAvailableException;
import com.example.isa.mail.MailService;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.service.impl.reservations.BoatReservationServiceImpl;
import com.example.isa.service.impl.reservations.BoatReservationSuggestionServiceImpl;
import com.example.isa.service.impl.reservations.MansionReservationServiceImpl;
import com.example.isa.service.impl.reservations.MansionReservationSuggestionServiceImpl;
import com.example.isa.service.impl.reservations.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class ClientReservationController {
	
	@Autowired 
	BoatReservationServiceImpl boatResService;
	@Autowired 
	MansionReservationServiceImpl mansionResService;
	

	@Autowired
	BoatReservationSuggestionServiceImpl boatSuggestionService;
	@Autowired
	MansionReservationSuggestionServiceImpl mansionSuggestionService;
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	private MailService<String> mailService;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/boats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getBoatReservations(){
    	
    	try {
            return new ResponseEntity<>(boatResService.GetReservationHistory(), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/mansions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getMansionReservations(){

        try {
            return new ResponseEntity<>(mansionResService.GetReservationHistory(), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ActiveReservationDTO>> getUserReservations(){
    	
		System.out.println("Geting all reservations for user");
        try {
            return new ResponseEntity<>(reservationService.getActiveReservations(), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableBoats", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PotentialReservationDTO>> getAvailableBoats(@RequestBody ReservationSearchDTO search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableMansions", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PotentialReservationDTO>> getAvailableMansions(@RequestBody ReservationSearchDTO search){
    	

        try {
            return new ResponseEntity<>(mansionSuggestionService.getAvailableMansions(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createBoatReservation(@RequestBody ReservationDTO res){
    	
    	System.out.println("USli u kontroler");
        try {
        	Reservation newReservation = boatResService.createReservation(res);
        	try {
        		//mailService.sendBoatReservationConfirmationMail(boatResService.getLoggedUser(), (BoatReservation) newReservation);
        	}catch (/*Messaging*/Exception e){
        		return  new ResponseEntity<>("There is a problem with your mail!", HttpStatus.INTERNAL_SERVER_ERROR);
        	}
            return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
        } catch (ParseException e){
            return  new ResponseEntity<>("Check your date again!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (PeriodNoLongerAvailableException e) {
        	return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createMansionReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createMansionReservation(@RequestBody ReservationDTO res){
    	
    	System.out.println("USli u kontroler");
        try {
        	MansionReservation newReservation = mansionResService.createReservation(res);
        	try {
        		//mailService.sendMansionReservationConfirmationMail(boatResService.getLoggedUser(), newReservation);
        	}catch (/*Messaging*/Exception e){
        		return  new ResponseEntity<>("There is a problem with your mail!", HttpStatus.INTERNAL_SERVER_ERROR);
        	}
            return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
        } catch (ParseException e){
            return  new ResponseEntity<>("Check your date again!", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (PeriodNoLongerAvailableException e) {
        	return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/cancelBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Reservation> cancelBoatReservation(@RequestBody long  resId){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(boatResService.cancelReservation(resId), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/cancelMansionReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Reservation> cancelMansionReservation(@RequestBody long  resId){
    	
		System.out.println("res id ..");
        try {
            return new ResponseEntity<>(mansionResService.cancelReservation(resId), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/getBoatOwnerReservations")
    public ResponseEntity<String> getBoatOwnerReservations() {
        try {
            List<BoatReservation> boatReservations = boatResService.getLoggedUserReservations();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(boatReservations);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/getReservedDatesForBoat")
    public ResponseEntity<String> getReservedDatesForBoat(@RequestParam Long boatId){
        try {
            List<BoatReservation> boatReservations = boatResService.getBoatReservationsByBoat(boatId);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(boatReservations);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @RequestMapping(method = RequestMethod.POST, value = "/makeBoatReservationClient")
    public ResponseEntity<String> makeBoatReservationClient(@RequestBody MakeBoatReservationForClientDTO dto){
        System.out.println(dto);
        try{
            BoatReservation boatReservation = boatResService.createBoatReservationForClient(dto);
            if (boatReservation!=null)
            return new ResponseEntity<>("Reservation for client is created successfully!", HttpStatus.OK);
            else return new ResponseEntity<>("Wrong params, try again!", HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error when making a reservation!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

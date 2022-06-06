package com.example.isa.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import com.example.isa.dto.*;

import com.example.isa.exception.BoatOwnerNotAvailable;

import com.example.isa.model.LoyaltyProgram;
import com.example.isa.model.RecordIncome;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.LoyaltyProgramRepository;
import com.example.isa.repository.RecordIncomeRepository;
import com.example.isa.service.impl.RecordIncomeService;
import com.example.isa.service.impl.reservations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
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

import com.example.isa.exception.EntityDeletedException;
import com.example.isa.exception.PeriodNoLongerAvailableException;
import com.example.isa.mail.MailService;
import com.example.isa.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class ReservationController {
	
	@Autowired 
	BoatReservationServiceImpl boatResService;
	@Autowired 
	MansionReservationServiceImpl mansionResService;
    @Autowired
    AdventureReservationServiceImpl adventureReservationService;
	

	@Autowired
	BoatReservationSuggestionServiceImpl boatSuggestionService;
	@Autowired
	MansionReservationSuggestionServiceImpl mansionSuggestionService;
	
	@Autowired
	CollectingBoatReservationsServiceImpl collectingBoatResService;
	@Autowired
	CollectionMansionReservationsImpl collectingMansionResService;
	@Autowired
    CollectingAdventureReservationsServiceImpl collectingAdventureReservationsService;
	@Autowired
	BoatDiscountReservationService boatDiscountReservationService;
    @Autowired
    MansionDiscountReservationService mansionDiscountReservationService;

    @Autowired
    AdventureDiscountReservationService adventureDiscountReservationService;
	
	@Autowired
	CollectingActiveReservationsService reservationService;
	@Autowired
	private MailService<String> mailService;
	@Autowired
	AuthenticationService authentication;
    @Autowired
    RecordIncomeService recordIncomeService;

	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/boats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoatReservation>> getBoatReservations(){
    	
    	try {
            return new ResponseEntity<>(collectingBoatResService.GetReservationHistory(), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations/mansions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MansionReservation>> getMansionReservations(){

        try {
            return new ResponseEntity<>(collectingMansionResService.GetReservationHistory(), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.GET,value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ActiveReservationDto>> getUserReservations(){
    	
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
    public ResponseEntity<List<PotentialReservationDto>> getAvailableBoats(@RequestBody ReservationSearchDto search){
    	
        try {
            return new ResponseEntity<>(boatSuggestionService.getAvailableEntities(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/availableMansions", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PotentialReservationDto>> getAvailableMansions(@RequestBody ReservationSearchDto search){
    	

        try {
            return new ResponseEntity<>(mansionSuggestionService.getAvailableEntities(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createBoatReservation(@RequestBody ReservationDto res){
    	
    	System.out.println("USli u kontroler");
        try {
        	BoatReservation newReservation = boatResService.createReservation(res);
            recordIncomeService.saveBoatRecord(newReservation.getId());
        	try {
        		mailService.sendBoatReservationConfirmationMail(newReservation);
        	}catch (MessagingException e){
        		return  new ResponseEntity<>("There is a problem with your mail!",HttpStatus.OK);
        	}
            return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
        } catch (ParseException e){
            return  new ResponseEntity<>("Check your date again!", HttpStatus.OK);
        }
        catch (PessimisticLockingFailureException pe){
            return new ResponseEntity<>("Client is reserving the entity!", HttpStatus.BAD_REQUEST);
        }
        catch (BoatOwnerNotAvailable be){
            return  new ResponseEntity<>(be.getMessage(), HttpStatus.OK);
        }
        catch (Exception e) {
        	return  new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/createMansionReservation",
    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createMansionReservation(@RequestBody ReservationDto res){
    	
        try {
        	MansionReservation newReservation = mansionResService.createReservation(res);
            recordIncomeService.saveMansionRecord(newReservation.getId());
        	try {
        		mailService.sendMansionReservationConfirmationMail(newReservation);
        	}catch (MessagingException e){
        		return  new ResponseEntity<>("There is a problem with your mail!",HttpStatus.OK);
        	}
            return new ResponseEntity<>("Reservation successfull!", HttpStatus.OK);
        } catch (ParseException e){
            return  new ResponseEntity<>("Check your date again!", HttpStatus.OK);
        } catch (PeriodNoLongerAvailableException e) {
        	return  new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        } catch (Exception e) {
	    	return  new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
	    }
    }
    
	@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(method = RequestMethod.POST,value = "/reservations/cancelBoatReservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Reservation> cancelBoatReservation(@RequestBody long  resId){
    	
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
            List<BoatReservation> boatReservations = collectingBoatResService.getOwnerReservations();
            List<BoatDiscountReservation> boatDiscountReservations = boatDiscountReservationService.getLoggedUserReservation();
            AllBoatOwnerReservationsDTO allBoatOwnerReservationsDTO = new AllBoatOwnerReservationsDTO();
            allBoatOwnerReservationsDTO.boatDiscountReservations = boatDiscountReservations;
            allBoatOwnerReservationsDTO.boatReservations = boatReservations;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(allBoatOwnerReservationsDTO);
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
            List<BoatReservation> boatReservations = collectingBoatResService.getBoatReservationsByBoat(boatId);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(boatReservations);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/getReservedDatesForMansion")
    public ResponseEntity<String> getReservedDatesForMansion(@RequestParam Long boatId){
        try {
            List<MansionReservation> boatReservations = collectingMansionResService.getAllMansionReservationsByMansion(boatId);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(boatReservations);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllReservedAdventureDatesForFishing")
    public ResponseEntity<String> getAllReservedAdventureDatesForFishing(){
        try {
            List<AdventureReservation> adventureReservations = collectingAdventureReservationsService.getAllAdventureReservation();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(adventureReservations);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/allReservationAdventures")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> allReservationAdventures(){
        try {
            //List<AdventureReservation> adventureReservations = collectingAdventureReservationsService.getOwnerReservation();
            List<ReservationCalendarDTO> reservationCalendarDTO= collectingAdventureReservationsService.getAllAdventureForCalendar();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(reservationCalendarDTO);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
    @RequestMapping(method = RequestMethod.POST, value = "/makeBoatReservationClient")
    public ResponseEntity<String> makeBoatReservationClient(@RequestBody CustomReservationForClientDto dto){
        System.out.println(dto);
        try{
            int code = boatResService.createReservationForClient(dto);
            if (code == 1)
                return new ResponseEntity<>("Reservation for client is created successfully!", HttpStatus.OK);
            else if(code==2) return new ResponseEntity<>("Client with selected email adress does not exist in the system!", HttpStatus.OK);
            else if(code==3) return new ResponseEntity<>("It is impossible to save the reservation", HttpStatus.OK);
            else if(code==4) return new ResponseEntity<>("Client is not available at selected time!", HttpStatus.OK);
            else if(code==5) return new ResponseEntity<>("Mansion is not available at selected time!", HttpStatus.OK);
            else if(code==6) return new ResponseEntity<>("Captain is not available at selected time!", HttpStatus.OK);
            else if(code==7) return new ResponseEntity<>("Client does not have an active reservation!", HttpStatus.OK);
            else if(code==8) return new ResponseEntity<>("Boat owner is not available at that time!", HttpStatus.OK);
            else return new ResponseEntity<>("Wrong params, try again!", HttpStatus.OK);
        }
        catch (PessimisticLockingFailureException pe){
            System.out.println(pe);
            return new ResponseEntity<>("Some is already trying to reserve at the same time!", HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("No available periods in selected time!", HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.POST, value = "/makeAdventureReservationClient")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> makeAdventureReservationClient(@RequestBody CustomReservationForClientDto dto){
        System.out.println(dto.getAdditionalServiceSet());
        System.out.println(dto.toString());

        try{
            int code = adventureReservationService.createReservationForClient(dto);
            if (code == 1)
                return new ResponseEntity<>("Reservation for client is created successfully!", HttpStatus.OK);
            else if(code==2) return new ResponseEntity<>("Client with selected email adress does not exist in the system!", HttpStatus.OK);
            else if(code==3) return new ResponseEntity<>("It is impossible to save the reservation", HttpStatus.OK);
            else if(code==4) return new ResponseEntity<>("Client is not available at selected time!", HttpStatus.OK);
            else if(code==5) return new ResponseEntity<>("Adventure is not available at selected time!", HttpStatus.OK);
            else return new ResponseEntity<>("Wrong params, try again!", HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("No available periods in selected time!", HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
    @RequestMapping(method = RequestMethod.POST, value = "/makeMansionReservationClient")
    public ResponseEntity<String> makeMansionReservationClient(@RequestBody CustomReservationForClientDto dto){
        System.out.println(dto);
        try{
           int code = mansionResService.createReservationForClient(dto);
            if (code == 1)
                return new ResponseEntity<>("Reservation for client is created successfully!", HttpStatus.OK);
            else if(code==2) return new ResponseEntity<>("Client with selected email adress does not exist in the system!", HttpStatus.OK);
            else if(code==3) return new ResponseEntity<>("It is impossible to save the reservation", HttpStatus.OK);
            else if(code==4) return new ResponseEntity<>("Client is not available at selected time!", HttpStatus.OK);
            else if(code==5) return new ResponseEntity<>("Mansion is not available at selected time!", HttpStatus.OK);
            else if(code==7) return new ResponseEntity<>("Client does not have an active reservation!", HttpStatus.OK);
            else return new ResponseEntity<>("Wrong params, try again!", HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("No available periods in selected time!", HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
    @RequestMapping(method = RequestMethod.GET, value = "/getMansionOwnerReservations")
    public ResponseEntity<String> getMansionOwnerReservations() {
        try {
            List<MansionReservation> mansionReservations = collectingMansionResService.getOwnerReservation();
            List<MansionDiscountReservation> mansionDiscountReservations = mansionDiscountReservationService.getLoggedUserReservations();
            AllMansionOwnerReservationsDTO allMansionOwnerReservationsDTO = new AllMansionOwnerReservationsDTO();
            allMansionOwnerReservationsDTO.mansionReservations = mansionReservations;
            allMansionOwnerReservationsDTO.mansionDiscountReservations = mansionDiscountReservations;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(allMansionOwnerReservationsDTO);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ROLE_FISHING_INSTRUCTOR')")
    @RequestMapping(method = RequestMethod.GET, value = "/getFishingInstructorReservations")
    public ResponseEntity<String> getFishingInstructorReservations() {
        try {
            List<AdventureReservation> adventureReservations = collectingAdventureReservationsService.getOwnerReservation();
            List<AdventureDiscountReservation> adventureDiscountReservations = adventureDiscountReservationService.getLoggedUserReservation();
            AllFishingInstructorReservationsDTO allFishingInstructorReservationsDTO = new AllFishingInstructorReservationsDTO();
            allFishingInstructorReservationsDTO.adventureDiscountReservations = adventureDiscountReservations;
            allFishingInstructorReservationsDTO.adventureReservations = adventureReservations;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(allFishingInstructorReservationsDTO);
            System.out.println(jsonString);
            return new ResponseEntity<>(jsonString, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }


}

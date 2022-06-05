package com.example.isa.controller;

import java.util.Collection;
import java.util.List;

import javax.management.relation.Role;

import com.example.isa.dto.*;
import com.example.isa.service.impl.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Boat;
import com.example.isa.model.BoatAvailablePeriod;
import com.example.isa.model.User;
import com.example.isa.service.impl.BoatFilteringServiceImpl;
import com.example.isa.service.impl.BoatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BoatController {
	
	@Autowired
	private BoatService service;
	@Autowired
	private BoatFilteringServiceImpl filteringService;
	@Autowired
	private AdditionalServiceService additionalServiceService;

	public BoatController(BoatService bs){
		this.service = bs;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/boat",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getBoatById(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for" +id);
		Boat boat = service.getById(id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boat);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/boats",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getAllBoats() throws JsonProcessingException{

		List <Boat> boats = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boats);
		System.out.println(jsonString);
		System.out.println("In contoler");
				
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.GET, value = "/ownersBoats",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOwnersBoats() throws JsonProcessingException{
		List<Boat> boats = service.getByOwnerId();
		if(boats == null)
			return new ResponseEntity<>("There is no logged user", HttpStatus.METHOD_NOT_ALLOWED);
		System.out.println("Number of boats "+boats.size());
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(boats);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/registerBoat",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBoat(@RequestBody BoatRegistrationDto dto){
		System.out.println("In registering boat service");
		System.out.println(dto);
		Boat savedBoat = service.registerBoat(dto);

		String responseMessege;
		if (savedBoat != null){
			responseMessege = "Boat successfully registered!";
		} else{
			responseMessege = "Error ocured while saving boat!";
		}
		return new ResponseEntity<>(responseMessege, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/changeBoat",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeBoat(@RequestBody ChangeBoatDto dto){
		if(service.isReserved(dto.id)){
			return new ResponseEntity<>("Boat is reserved, not possible to change or delete!", HttpStatus.OK);
		}
		Boat changedBoat = service.changeBoat(dto);
		String responseMessege;
		System.out.println("Trying to change boat");
		if (changedBoat != null){
			additionalServiceService.changeAdditionalServices(dto);
			responseMessege = "Boat successfully changed!";
		} else{
			responseMessege = "Error ocured while trying to change boat!";
		}
		return new ResponseEntity<>(responseMessege, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getBoatAvailability", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<BoatAvailablePeriod>> getAvailablePeriod(@RequestBody LongIdDto dto) throws JsonProcessingException{
		System.out.println("In getting availability for boats");
		List<BoatAvailablePeriod> boatAvailabilities = service.getBoatAvailbilities(dto.boatId);
		ObjectMapper mapper = new ObjectMapper();
		System.out.print("Before coverting" + boatAvailabilities.toString());
		String jsonString = mapper.writeValueAsString(boatAvailabilities);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(boatAvailabilities, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value ="/addAvailablePeriodForBoat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addAvailablePeriodForBoat(@RequestBody AddAvailablePeriodDto dto) throws JsonProcessingException{
		System.out.println("Adding available period for boat!");
		if (dto.endTime == null || dto.startTime==null){
			return new ResponseEntity<>("Neither start or end time can be empty!", HttpStatus.BAD_REQUEST);
		}
		if(dto.endTime.before(dto.startTime)){
			return new ResponseEntity<>("End time is before start time!", HttpStatus.BAD_REQUEST);
		}
		List<BoatAvailablePeriod> boatAvailabilities = service.addBoatAvailabilities(dto);
		ObjectMapper mapper = new ObjectMapper();
		System.out.print("Before coverting" + boatAvailabilities.toString());
		String jsonString = mapper.writeValueAsString(boatAvailabilities);
		System.out.println(jsonString);
		System.out.println("Finished");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	
    @RequestMapping(method = RequestMethod.POST,value = "/boats/search",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Boat>> getAvailableBoats(@RequestBody SearchDto search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(filteringService.searchAll(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@RequestMapping(method = RequestMethod.POST, value = "/freeDaysForBoat",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<AddAvailablePeriodDto>> getFreeDaysForBoat(@RequestBody LongIdDto boatID){
		System.out.println("I'm trying to get boat id:"+ boatID.boatId);
		try{
			List<AddAvailablePeriodDto> av = service.getFreeDaysForBoat(boatID.boatId);
			return new ResponseEntity<>(av , HttpStatus.OK);
		} catch (Exception e){
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_BOAT_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/deleteBoat",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteBoat(@RequestBody LongIdDto boatId){
		if(service.isReserved(boatId.boatId)){
			return new ResponseEntity<>("Boat is reserved, not possible to change or delete!", HttpStatus.OK);
		}
		System.out.println("I'm trying to delete boat id:"+ boatId.boatId);
		try {
			service.deleteBoat(boatId.boatId);
			return new ResponseEntity<String>("Successfully deleted boat!", HttpStatus.OK);
		}
		catch (PessimisticLockingFailureException pe){
			return new ResponseEntity<>("Client is reserving the entity!", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.*;
import com.example.isa.model.MansionAvailablePeriod;
import com.example.isa.service.impl.AdditionalServiceService;
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

import com.example.isa.model.Mansion;
import com.example.isa.service.impl.MansionFilteringServiceImpl;
import com.example.isa.service.impl.MansionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MansionController {
	
	@Autowired
	MansionService service;
	@Autowired
	MansionFilteringServiceImpl filteringService;
	@Autowired
	AdditionalServiceService additionalServiceService;

	@PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/registerMansion",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerMansion(@RequestBody MansionRegistrationDto dto){
		System.out.println("In registering mansion service");
		System.out.println(dto);
		Mansion savedMansion = service.registerMansion(dto);

		String responseMessege;
		if (savedMansion != null){
			responseMessege = "Mansion successfully registered!";
		} else{
			responseMessege = "Error ocured while saving mansion!";
		}
		return new ResponseEntity<>(responseMessege, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
	@RequestMapping(method = RequestMethod.POST, value = "/changeMansion",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeMansion(@RequestBody ChangeMansionDto dto){
		if(service.isReserved(dto.id)){
			return new ResponseEntity<>("Mansion is reserved, not possible to change or delete!", HttpStatus.OK);
		}
		Mansion changedMansion = service.changeMansion(dto);
		String responseMessege;
		System.out.println("Trying to change mansion");
		if (changedMansion != null){
			additionalServiceService.changeAdditionalServices(dto);
			responseMessege = "Boat successfully changed!";
		} else{
			responseMessege = "Error ocured while trying to change boat!";
		}
		return new ResponseEntity<>(responseMessege, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/mansions",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> registerClient() throws JsonProcessingException{
		
		List <Mansion> mansions = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansions);
		System.out.println(jsonString);
		System.out.println("In contoler");
				
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	
    @RequestMapping(method = RequestMethod.POST,value = "/mansions/search",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Mansion>> searchMasnion(@RequestBody SearchDto search){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(filteringService.searchAll(search), HttpStatus.OK);
        } catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PreAuthorize("hasAnyRole('ROLE_MANSION_OWNER','ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST,value = "/deleteMansion",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteMansion(@RequestBody LongIdDto id){
		if(service.isReserved(id.boatId)){
			return new ResponseEntity<>("Mansion is reserved, not possible to change or delete!", HttpStatus.OK);
		}
    	System.out.println("Trying to delete mansion: "+id.boatId);
        try {
			service.deleteMansion(id.boatId);
            return new ResponseEntity<String>("Successfully deleted mansion!", HttpStatus.OK);
        }catch (PessimisticLockingFailureException pe){
			return new ResponseEntity<>("Client is reserving the entity!", HttpStatus.BAD_REQUEST);
		}
		catch (Exception e){
        	System.out.println(e);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/mansion")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getMansionById(@RequestParam Long id) throws JsonProcessingException{
		System.out.println("We are searchinng for mansion" +id);
		Mansion mansion = service.getById(id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansion);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_MANSION_OWNER')")
	@RequestMapping(method = RequestMethod.GET, value = "/ownersMansions",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOwnersMansions() throws JsonProcessingException{
		List<Mansion> mansions = service.getByOwnerId();
		if(mansions == null)
			return new ResponseEntity<>("There is no logged user", HttpStatus.METHOD_NOT_ALLOWED);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansions);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value ="/addAvailablePeriodForMansion", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> addAvailablePeriodForBoat(@RequestBody AddAvailablePeriodDto dto) throws JsonProcessingException{
		System.out.println("Adding available period for mansion!");
		if (dto.endTime == null || dto.startTime==null){
			return new ResponseEntity<>("Neither start or end time can be empty!", HttpStatus.BAD_REQUEST);
		}
		if(dto.endTime.before(dto.startTime)){
			return new ResponseEntity<>("End time is before start time!", HttpStatus.BAD_REQUEST);
		}

		List<MansionAvailablePeriod> mansionAvailabilities = service.addMansionAvailabilities(dto);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansionAvailabilities);
		System.out.println(jsonString);
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getMansionAvailability", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<MansionAvailablePeriod>> getAvailablePeriod(@RequestBody LongIdDto dto) throws JsonProcessingException{
		System.out.println("In getting availability for boats");
		List<MansionAvailablePeriod> boatAvailabilities = service.getMansionAvailbilities(dto.boatId);
		ObjectMapper mapper = new ObjectMapper();
		System.out.print("Before coverting" + boatAvailabilities.toString());
		String jsonString = mapper.writeValueAsString(boatAvailabilities);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(boatAvailabilities, HttpStatus.OK);
	}
	
	

}

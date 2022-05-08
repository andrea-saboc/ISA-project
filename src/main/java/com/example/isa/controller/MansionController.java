package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.BoatRegistrationDto;
import com.example.isa.dto.MansionRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.SearchDto;
import com.example.isa.model.Boat;
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

	@RequestMapping(method = RequestMethod.POST, value = "/registerMansion",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
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
    
    @RequestMapping(method = RequestMethod.POST,value = "/deleteMansion",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteMansion(@RequestBody long id){
    	
    	System.out.println("USli u kontroler");
        try {
            return new ResponseEntity<>(service.deleteMansion(id).getName() + " has been deleted.", HttpStatus.OK);
        } catch (Exception e){
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

	@RequestMapping(method = RequestMethod.GET, value = "/ownersMansions",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getOwnersMansions() throws JsonProcessingException{
		List<Mansion> mansions = service.getByOwnerId();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(mansions);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	
	

}

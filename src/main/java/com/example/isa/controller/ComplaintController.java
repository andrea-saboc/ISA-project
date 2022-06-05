package com.example.isa.controller;

import java.util.List;

import com.example.isa.dto.AdminComplaintDto;
import com.example.isa.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.isa.dto.ClientComplaintDto;
import com.example.isa.mail.MailService;
import com.example.isa.service.impl.ClientComplaintService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ComplaintController {
	
	@Autowired
	ClientComplaintService complaintService;
	
	@Autowired
	private MailService<String> mailService;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addBoatComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBoatComplaint(@RequestBody ClientComplaintDto dto) throws JsonProcessingException{	
		
		BoatComplaint c = complaintService.addBoatComplaint(dto);
	//	mailService.sendBoatComplaintEmail(c);
		return ResponseEntity.ok("Your complaint has been submited!");
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addBoatOwnerComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addBoatOwnerComplaint(@RequestBody ClientComplaintDto dto) throws JsonProcessingException{	
		
		AdvertiserComplaint c = complaintService.addBoatAdvertiserComplaint(dto);
		//mailService.sendAdvertiserComplaintEmail(c);
		return ResponseEntity.ok("Your complaint has been submited!");
	}
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addMansionComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addMansionComplaint(@RequestBody ClientComplaintDto dto) throws JsonProcessingException{	
		
		MansionComplaint c = complaintService.addMansionComplaint(dto);
		//mailService.sendMansionComplaintEmail(c);
		return ResponseEntity.ok("Your complaint has been submited!");
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addMansionOwnerComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addMansionOwnerComplaint(@RequestBody ClientComplaintDto dto) throws JsonProcessingException{	
		
		AdvertiserComplaint c = complaintService.addMansionAdvertiserComplaint(dto);
	//	mailService.sendAdvertiserComplaintEmail(c);
		return ResponseEntity.ok("Your complaint has been submited!");
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/complaints/boats",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Boat>> complainBoats() throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.getBoats());
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/complaints/mansions",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<Mansion>> complainMansions() throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.getMansions());
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllComplain",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> getAllComplain() throws JsonProcessingException{

		List <Complaint> complaints =complaintService.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(complaints);
		System.out.println(jsonString);
		System.out.println("In contoler");
		return new ResponseEntity<>(jsonString, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/complaintsAdd",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> complaintsAdd(@RequestBody AdminComplaintDto dto) throws JsonProcessingException{

		 complaintService.sendComplain(dto);
		//	mailService.sendAdvertiserComplaintEmail(c);
		return ResponseEntity.ok("Your complaint has been submited!");
	}

}

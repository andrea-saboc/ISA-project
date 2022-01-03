package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientComplaintDTO;
import com.example.isa.model.Boat;
import com.example.isa.model.Complaint;
import com.example.isa.model.Mansion;
import com.example.isa.service.ClientComplaintService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ComplaintController {
	
	@Autowired
	ClientComplaintService complaintService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addBoatComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<Complaint> addBoatComplaint(@RequestBody ClientComplaintDTO dto) throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.addBoatComplaint(dto));
	}
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addBoatOwnerComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<Complaint> addBoatOwnerComplaint(@RequestBody ClientComplaintDTO dto) throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.addAdvertiserComplaint(dto));
	}
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addMansionComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<Complaint> addMansionComplaint(@RequestBody ClientComplaintDTO dto) throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.addMansionComplaint(dto));
	}
	@RequestMapping(method = RequestMethod.POST, value = "/complaints/addMansionOwnerComplain",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<Complaint> addMansionOwnerComplaint(@RequestBody ClientComplaintDTO dto) throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.addAdvertiserComplaint(dto));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/complaints/boats",produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<List<Boat>> complainBoats() throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.getBoats());
	}
	@RequestMapping(method = RequestMethod.GET, value = "/complaints/mansions",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<List<Mansion>> complainMansions() throws JsonProcessingException{	
		
		return ResponseEntity.ok(complaintService.getMansions());
	}

}

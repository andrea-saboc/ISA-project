package com.example.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.ClientFeedbackDTO;
import com.example.isa.model.ClientFeedback;
import com.example.isa.service.ClientFeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class FeedbackController {
	
	@Autowired
	ClientFeedbackService feedbackService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/feedbacks/addBoatOwnerFeedback",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@CrossOrigin(origins = "*")	
	public ResponseEntity<ClientFeedback> addBoatOwnerFeedback(@RequestBody ClientFeedbackDTO dto) throws JsonProcessingException{	
		
		return ResponseEntity.ok(feedbackService.addBoatOwnerFeedback(dto));
	}

}

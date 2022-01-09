package com.example.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.BoatSubscription;
import com.example.isa.service.SubscriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class SubscriptionsController {
	
	@Autowired
	SubscriptionService service;

	@RequestMapping(method = RequestMethod.POST, value = "/subscriptions/newBoatSubscription",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoatSubscription> newBoatSubscriptions(@RequestBody long id) throws JsonProcessingException{
		
		System.out.println("Id broda je "+id);
    	try {
            return new ResponseEntity<>(service.newBoatSubscription(id), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	@RequestMapping(method = RequestMethod.GET, value = "/subscriptions/boat",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BoatSubscription>> getClientBoatSubscriptions() throws JsonProcessingException{

    	try {
            return new ResponseEntity<>(service.getClientBoatSubscription(), HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}

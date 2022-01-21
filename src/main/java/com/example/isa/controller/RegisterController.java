package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.AdvertiserRegistrationDto;
import com.example.isa.dto.ClientRegistrationDto;
import com.example.isa.service.impl.AdvertiserRegisterService;
import com.example.isa.service.impl.ClientRegistrationService;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    private final AdvertiserRegisterService advertiserRegisterService;
    private final ClientRegistrationService clientRegistrationService;

    @Autowired
    public RegisterController(AdvertiserRegisterService advertiserRegisterService, ClientRegistrationService clientRegistrationService ) {
        this.advertiserRegisterService = advertiserRegisterService;
        this.clientRegistrationService = clientRegistrationService;
    }

    @RequestMapping(value = "/advertiser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody AdvertiserRegistrationDto advertiserData){
        System.out.println("Advertiser adding :"+advertiserData.toString());
        try {
            this.advertiserRegisterService.saveNewAdvertiser(advertiserData);
            System.out.println(advertiserData.toString());
            return new ResponseEntity<>("Successfull registration", HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>("Failed to register", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
	@RequestMapping(method = RequestMethod.POST, value = "/client",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*")
    public ResponseEntity<String> registerPatient(HttpServletRequest request,@RequestBody ClientRegistrationDto clientDto) {
    	
        
        if (this.clientRegistrationService.clientExists(clientDto.getEmail()))
            return new ResponseEntity<>("User already exists",HttpStatus.OK);
        
        try {
            this.clientRegistrationService.registerClient(clientDto,getSiteURL(request));
            return new ResponseEntity<>("Verification code has been sent to your mail.", HttpStatus.OK);
        }
        catch(Exception e) {        	
        		System.out.println(e);
        		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
	
	
    private String getSiteURL(HttpServletRequest request) {
        return request.getHeader("origin");
    }
}

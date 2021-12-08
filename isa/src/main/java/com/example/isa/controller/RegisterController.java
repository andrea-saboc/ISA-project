package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.dto.ClientRegistrationDTO;
import com.example.isa.service.implemented.AdvertiserRegisterService;
import com.example.isa.service.implemented.ClientRegistrationService;

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
    /* try {
            this.registerPatientService.register(patientDTO, getSiteURL(request));
            return new ResponseEntity<>("/emailSent", HttpStatus.OK);
        } catch (BadUserInformationException e) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(registrationFailedAlert, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

    @RequestMapping(value = "/advertiser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody AdvertiserRegistrationDTO advertiserData){
        System.out.println("Advertiser adding :"+advertiserData.toString());
        try {
            this.advertiserRegisterService.saveAdvertiserRegistrationRequest(advertiserData);
            System.out.println(advertiserData.toString());
            return new ResponseEntity<>("Successfull registratio", HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
	@RequestMapping(method = RequestMethod.POST, value = "/clientRegistration",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	//@CrossOrigin(origins = "*")
    public ResponseEntity<String> registerPatient(HttpServletRequest request, @RequestBody ClientRegistrationDTO clientDto) {
    	
    	System.out.println("pogodjen kontroler za reg");
    	/*
        if (!validUserInfo(patientDTO.getName(), patientDTO.getEmail(), patientDTO.getPassword())) {
            return new ResponseEntity<>(missingBasicUserInfoAlert, HttpStatus.BAD_REQUEST);
        }
        
        if (this.registerPatientService.userExists(patientDTO.getEmail())) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        }
        */
        try {
            this.clientRegistrationService.registerClient(clientDto);
            return new ResponseEntity<>("/emailSent", HttpStatus.OK);
            
            /*
        } catch (BadUserInformationException e) {
            return new ResponseEntity<>(userExistsAlert, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(registrationFailedAlert, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        */
        	} catch(Exception e) {
        		System.out.println("gresca u registraciji");
        	}
        return new ResponseEntity<>("okei",HttpStatus.OK);
        }
}

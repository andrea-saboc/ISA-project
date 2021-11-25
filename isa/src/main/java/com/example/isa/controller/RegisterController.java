package com.example.isa.controller;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.service.implemented.AdvertiserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    private final AdvertiserRegisterService advertiserRegisterService;

    @Autowired
    public RegisterController(AdvertiserRegisterService advertiserRegisterService) {
        this.advertiserRegisterService = advertiserRegisterService;
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
}

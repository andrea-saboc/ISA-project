package com.example.isa.service.implemented;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.model.AdvertiserRegistrationRequest;
import com.example.isa.repository.AdvertiserRegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserRegisterService {

    private final AdvertiserRegistrationRequestRepository advertiserRegistrationRequestRepository;

    @Autowired
    public AdvertiserRegisterService(AdvertiserRegistrationRequestRepository advertiserRegistrationRequestRepository) {
        this.advertiserRegistrationRequestRepository = advertiserRegistrationRequestRepository;
    }

    public AdvertiserRegistrationRequest saveAdvertiserRegistrationRequest(AdvertiserRegistrationDTO dat){
            System.out.println("im in service");
            System.out.println("data"+dat.toString());
            AdvertiserRegistrationRequest newRequest= dat.createAdvertiserRegistrationRequest(dat);
            System.out.println("Creaed rec"+newRequest.toString());
            advertiserRegistrationRequestRepository.save(newRequest);
            newRequest.toString();
            return newRequest;
    }


}

package com.example.isa.service;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.model.Advertiser;
import com.example.isa.repository.AdvertiserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserRegisterService {

    private AdvertiserRepository advertiserRepository;

    public AdvertiserRegisterService(AdvertiserRepository advertiserRepository) {
        this.advertiserRepository = advertiserRepository;
    }

    public Advertiser saveNewAdvertiser(AdvertiserRegistrationDTO dat){
        Advertiser newAdvertiser = dat.createAdvertiser(dat);
            System.out.println("New boat owner is created:"+newAdvertiser.toString());
            advertiserRepository.save(newAdvertiser);
            return newAdvertiser;
    }

}

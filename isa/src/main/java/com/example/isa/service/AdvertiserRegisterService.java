package com.example.isa.service;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.MansionOwner;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.MansionOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserRegisterService {

    private final BoatOwnerRepository boatOwnerRepository;
    private final MansionOwnerRepository mansionOwnerRepository;

    public AdvertiserRegisterService(BoatOwnerRepository boatOwnerRepository, MansionOwnerRepository mansionOwnerRepository) {
        this.boatOwnerRepository = boatOwnerRepository;
        this.mansionOwnerRepository = mansionOwnerRepository;
    }

    public BoatOwner saveNewBoatOwner(AdvertiserRegistrationDTO dat){
            BoatOwner newBoatOwner = dat.createBoatOwner(dat);
            System.out.println("New boat owner is created:"+newBoatOwner.toString());
            boatOwnerRepository.save(newBoatOwner);
            return newBoatOwner;
    }

    public MansionOwner saveNewMansionOwner(AdvertiserRegistrationDTO dat){
        MansionOwner mansionOwner = dat.createMansionOwner(dat);
        System.out.println("New mansion owner is created:"+mansionOwner.toString());
        mansionOwnerRepository.save(mansionOwner);
        return  mansionOwner;
    }


}

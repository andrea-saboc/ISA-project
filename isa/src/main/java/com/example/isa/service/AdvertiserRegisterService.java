package com.example.isa.service;

import com.example.isa.dto.AdvertiserRegistrationDTO;
import com.example.isa.exception.BadUserTypeException;
import com.example.isa.model.BoatOwner;
import com.example.isa.model.MansionOwner;
import com.example.isa.model.User;
import com.example.isa.repository.AdvertiserRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.MansionOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdvertiserRegisterService {
    @Autowired
    private AdvertiserRepository advertiserRepository;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private MansionOwnerRepository mansionOwnerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdvertiserRegisterService(MansionOwnerRepository mansionOwnerRepository, BoatOwnerRepository boatOwnerRepository) {
        this.mansionOwnerRepository = mansionOwnerRepository;
        this.boatOwnerRepository = boatOwnerRepository;
    }

    public User saveNewAdvertiser(AdvertiserRegistrationDTO dat) throws BadUserTypeException {
        String type = dat.getType();
        dat.setPassword(passwordEncoder.encode(dat.getPassword()));
        Object user = null;
        switch(type) {
            case "mansion":
                user = dat.createMansionOwner();
                mansionOwnerRepository.save((MansionOwner) user);
                break;
            case "boat":
                user = dat.createBoatOwner();
                boatOwnerRepository.save((BoatOwner) user);
                break;
            case "fishing":
                /**/
                break;
            default:
                throw new BadUserTypeException();


        }
        return (User) user;
    }

}

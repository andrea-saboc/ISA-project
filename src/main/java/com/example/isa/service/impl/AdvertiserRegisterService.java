package com.example.isa.service.impl;

import com.example.isa.dto.AdvertiserRegistrationDto;
import com.example.isa.exception.BadUserTypeException;
import com.example.isa.model.*;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.FishingInstructorRepository;
import com.example.isa.repository.MansionOwnerRepository;
import com.example.isa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertiserRegisterService {

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private MansionOwnerRepository mansionOwnerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FishingInstructorRepository fishnigInstructorRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AdvertiserRegisterService(MansionOwnerRepository mansionOwnerRepository, BoatOwnerRepository boatOwnerRepository,FishingInstructorRepository fishnigInstructorRepository,RoleRepository roleRepository) {
        this.mansionOwnerRepository = mansionOwnerRepository;
        this.boatOwnerRepository = boatOwnerRepository;
        this.fishnigInstructorRepository=fishnigInstructorRepository;
        this.roleRepository=roleRepository;
    }

    public User saveNewAdvertiser(AdvertiserRegistrationDto dat) throws BadUserTypeException {
        String type = dat.getType();
        dat.setPassword(passwordEncoder.encode(dat.getPassword()));
        Object user = null;
        List<Authority> authorities = new ArrayList<>();
        Authority auth = null;

        switch(type) {
            case "mansion":
                user = dat.createMansionOwner();

                auth = roleRepository.findByName("ROLE_MANSION_OWNER");
                authorities.add(auth);
                MansionOwner mansionOwner = (MansionOwner) user;
                mansionOwner.setAuthorities(authorities);

                mansionOwnerRepository.save(mansionOwner);
                break;
            case "boat":
                user = dat.createBoatOwner();
                auth = roleRepository.findByName("ROLE_BOAT_OWNER");
                authorities.add(auth);
                BoatOwner boatOwner = (BoatOwner) user;
                boatOwner.setAuthorities(authorities);
                boatOwnerRepository.save(boatOwner);
                break;
            case "fishing":
            	 FishingInstructor f = dat.createFishingInstructor();
                 authorities = new ArrayList<Authority>();
                 auth = roleRepository.findByName("ROLE_FISHING_INSTRUCTOR");
                 authorities.add(auth);
                 f.setAuthorities(authorities);
                fishnigInstructorRepository.save(f);


                 break;
            default:
                throw new BadUserTypeException();


        }
        return (User) user;
    }

}

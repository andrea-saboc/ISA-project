package com.example.isa.service.impl;

import com.example.isa.dto.AdventureRegistrationDto;
import com.example.isa.dto.BoatRegistrationDto;
import com.example.isa.model.*;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.AdventureRepository;
import com.example.isa.repository.FishingInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdventureService {
    @Autowired
    private AdventureRepository adventureRepository;
    @Autowired
    public FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;
    @Autowired
    private EntityImageService entityImageService;



    @Autowired
    public AdventureService(AdventureRepository ar,FishingInstructorRepository fishingInstructorRepository,AdditionalServiceRepository additionalServiceRepository,EntityImageService entityImageService) {

        this.adventureRepository = ar;
        this.additionalServiceRepository=additionalServiceRepository;
        this.fishingInstructorRepository=fishingInstructorRepository;
        this.entityImageService=entityImageService;

    }

    public List<Adventure> getAll() {
        List<Adventure> adventures = adventureRepository.findAll();
        System.out.println("Service adventures getting all");
        System.out.println(adventures);
        return adventures;
    }
    public Adventure getById(Long id) {
        return adventureRepository.findById(id).get();
    }
    public Adventure registerAdventure(AdventureRegistrationDto dto) {

        Adventure newAdventure = createAdventure(dto);
        newAdventure = adventureRepository.save(newAdventure);
        System.out.println(newAdventure);
        Set<AdditionalService> additionalServices = addAdventureToServices(dto.additionalServices, newAdventure);
        return newAdventure;
    }
    private Set<AdditionalService> addAdventureToServices(Set<AdditionalService> additionalServices, Adventure adventure) {
        Set<AdditionalService> additionalServiceWithAdventure = new HashSet<>();
        for (AdditionalService as: additionalServices) {
            AdditionalService as1 = as;
            as1.setAdventure(adventure);
            additionalServiceRepository.save(as1);
            additionalServiceWithAdventure.add(as1);
        }
        return additionalServiceWithAdventure;
    }
    private Adventure createAdventure(AdventureRegistrationDto dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor fishingInstructor1 = fishingInstructorRepository.findById(user.getId()).get();
        Address adventureAddress = new Address();
        adventureAddress.setAddress(dto.address);
        adventureAddress.setCity(dto.city);
        adventureAddress.setCountry(dto.country);
        adventureAddress.setLatitude(dto.latitude);
        adventureAddress.setLongitude(dto.longitude);
        Adventure  newAdventure= new Adventure();
        newAdventure.setFishingInstructor(fishingInstructor1);
        //boatOwner.addBoat(newAdventure);
        newAdventure.setBiography(dto.biography);
        newAdventure.setEquipment(dto.equipment);

        newAdventure.setName(dto.name);
        newAdventure.setPromoDescription(dto.promoDescription);

        newAdventure.setAddress(adventureAddress);
        newAdventure.setCapacity(dto.capacity);

        newAdventure.setCancellationPolicy(dto.cancellationPolicy);

//        newAdventure.setRules(convertString2Rule(dto.rules, newAdventure));
        newAdventure.setPricePerHour(dto.pricePerHour);
        newAdventure.setPricePerDay(dto.pricePerDay);
        newAdventure.setPriceForSevenDays(dto.priceForSevenDays);
        ArrayList<EntityImage> images = entityImageService.createAndSaveImages("Instructors",fishingInstructor1.getEmail(),dto.getName(),dto.getImagesForBackend());
        newAdventure.setImages(new HashSet<EntityImage>(images));
        return newAdventure;
    }
    private Set<Rule> convertString2Rule(Set<String> Rules, Adventure adventure){
        Set<Rule> convertedRules = new HashSet<>();
        for (String s: Rules){
            Rule newRule = new Rule(s);
            newRule.addAdventure(adventure);
            convertedRules.add(newRule);
        }
        return convertedRules;
    }

    public List<Adventure> getByOwnerId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Trying to cast to User:"+ user);
        FishingInstructor fishingInstructor = fishingInstructorRepository.findById(user.getId()).get();
        List<Adventure> adventures = adventureRepository.findAllByFishingInstructorAndDeleted(fishingInstructor, false);
        return adventures;
    }


}

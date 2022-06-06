package com.example.isa.service.impl;

import com.example.isa.dto.*;
import com.example.isa.model.*;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.repository.*;
import com.example.isa.service.impl.reservations.AdventureReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private FishingAvailablePeriodRepository fishingAvailablePeriodRepository;
    @Autowired
    private AdventureReservationServiceImpl adventureReservationService;
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    public AdventureService(AdventureReservationServiceImpl adventureReservationService,AdventureRepository ar,FishingInstructorRepository fishingInstructorRepository,AdditionalServiceRepository additionalServiceRepository,EntityImageService entityImageService,FishingAvailablePeriodRepository fishingAvailablePeriodRepository) {
        this.adventureReservationService=adventureReservationService;
        this.adventureRepository = ar;
        this.additionalServiceRepository=additionalServiceRepository;
        this.fishingInstructorRepository=fishingInstructorRepository;
        this.entityImageService=entityImageService;
        this.fishingInstructorRepository=fishingInstructorRepository;

    }

    public List<Adventure> getAll() {
        List<Adventure> adventures = adventureRepository.findAll();
        System.out.println("Service adventures getting all");
        System.out.println(adventures);
        return adventures;
    }

    @Transactional
    public Adventure findLockedById(Long id){
        return adventureRepository.findLockedById(id);
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

    public List<FishingAvailablePeriod> getFishingAvailablePeriod(Long fishingId) {
        FishingInstructor fishingInstructor=fishingInstructorRepository.findById(fishingId).get();

        List<FishingAvailablePeriod> fishingAvailablePeriods = fishingAvailablePeriodRepository.findByFishingInstructor(fishingInstructor);
        return fishingAvailablePeriods;
    }
    public List<FishingAvailablePeriod> addFishingAvailabilities(AddAvailablePeriodFishingInstructorDto dto) {
        FishingInstructor fishingInstructor=fishingInstructorRepository.findById(dto.fishingId).get();
        FishingAvailablePeriod availablePeriod= new FishingAvailablePeriod(dto.startTime, dto.endTime,fishingInstructor);

        fishingAvailablePeriodRepository.save(availablePeriod);
        List<FishingAvailablePeriod> fishingNewAvailability = fishingAvailablePeriodRepository.findByFishingInstructor(fishingInstructor);
        return fishingNewAvailability;
    }
    public boolean isReserved(Long id)
    {
        return adventureReservationService.isThereAReservationAfterToday(adventureRepository.findById(id).get());
    }
    public Adventure changeAdventure(ChangeAdventureDto dto) {

        Adventure adventure=adventureRepository.findById(dto.id).get();
        if(adventure==null) return null;
        adventure.setName(dto.name);
        adventure.setPricePerDay(dto.pricePerDay);
        adventure.setPriceForSevenDays(dto.priceForSevenDays);
        adventure.setPricePerHour(dto.pricePerHour);
        adventure.setBiography(dto.biography);
        adventure.setEquipment(dto.equipment);
        adventure.setCancellationPolicy(dto.cancellationPolicy);
        adventure.setPromoDescription(dto.promoDescription);
        adventure.setCapacity(dto.capacity);
        adventure.setRules(makeRules(dto));
        adventure.setImages(new HashSet<>(entityImageService.removeAndAddNewImage(dto, adventure)));
        Address adventureAdress = addressRepository.findById(adventure.getAddress().getId()).get();
        adventureAdress.setCountry(dto.country);
        adventureAdress.setCity(dto.city);
        adventureAdress.setAddress(dto.address);
        adventureAdress.setLatitude(dto.latitude);
        adventureAdress.setLongitude(dto.longitude);
        addressRepository.save(adventureAdress);
        adventure = adventureRepository.save(adventure);
        return adventure;

    }
    public boolean inAvailabilityPeriods(Date startDate, Date endDate, Long id) {
        List<FishingAvailablePeriod> mansionAvailablePeriods = getFishingAvailablePeriod(id);
        for (FishingAvailablePeriod ma : mansionAvailablePeriods ){
            if(!startDate.after(ma.getEndDate()) && !ma.getStartDate().after(endDate)){
                return true;
            }
        }
        return false;
    }
    @Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public void deleteAdventure(Long adventureId) {
        Adventure entity = adventureRepository.findLockedById(adventureId);
        if (entity == null)
            throw new PessimisticLockingFailureException("Some is already trying to reserve!");
        Adventure adventure = adventureRepository.findById(adventureId).get();
        adventure.setDeleted(true);
        adventureRepository.save(adventure);
    }
    private Set<Rule> makeRules(ChangeAdventureDto dto) {
        System.out.println("Making rules***");
        Adventure adventure = adventureRepository.findById(dto.id).get();
        Set<Rule> newRules = new HashSet<>();
        for (Rule r : dto.rules){
            if(r.getRuleId()==-1){
                Rule newRule = new Rule(r.getRule());
                newRule.addAdventure(adventure);
                newRules.add(newRule);
            }

        }
        for(Integer rid : dto.rulesToDelete){
            Rule rd= ruleRepository.findById(rid).get();
            rd.removeAdventure(adventure);
            ruleRepository.save(rd);
        }
        return newRules;
    }


}

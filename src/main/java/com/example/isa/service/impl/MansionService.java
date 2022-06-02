package com.example.isa.service.impl;

import java.util.*;

import com.example.isa.dto.AddAvailablePeriodDto;
import com.example.isa.dto.ChangeMansionDto;
import com.example.isa.dto.MansionRegistrationDto;
import com.example.isa.model.*;
import com.example.isa.model.reservations.AbstractReservation;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.repository.*;
import com.example.isa.service.impl.reservations.MansionReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MansionService {
	
	@Autowired
	MansionRepository mansionRepo;

	@Autowired
	MansionOwnerRepository mansionOwnerRepository;

	@Autowired
	AdditionalServiceRepository additionalServiceRepository;

	@Autowired
	MansionAvailablePeriodRepository mansionAvailablePeriodRepository;

	@Autowired
	EntityImageService entityImageService;

	@Autowired
	RuleRepository ruleRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	MansionReservationServiceImpl mansionReservationService;

	public List<Mansion> getAll() {
		
		return mansionRepo.findAll();
	}

	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public Mansion deleteMansion(long id) {
		Mansion entity = mansionRepo.findLockedById(id);
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
		Mansion mansion = mansionRepo.findById(id);
		mansion.setDeleted(true);
		return mansionRepo.save(mansion);
	}

	public Mansion getById(Long id) {
		return mansionRepo.findById(id).get();
	}


	public boolean isReserved(Long id)
	{
	  	return (mansionReservationService.isThereAReservationAfterToday(mansionRepo.findById(id).get()));
	}

    public List<Mansion> getByOwnerId() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Trying to cast to User:"+ user);
		MansionOwner mansionOwner = mansionOwnerRepository.findById(user.getId()).get();
		List<Mansion> ownersMansions = mansionRepo.findAllByMansionOwnerAndDeletedFalse(mansionOwner);
		return ownersMansions;
    }

    public Mansion registerMansion(MansionRegistrationDto dto) {
		System.out.println("In registering mansion service!");
		System.out.println(dto);
		Mansion newMansion = createMansion(dto);
		newMansion = mansionRepo.save(newMansion);
		Set<AdditionalService> additionalServices = addMansionToServices(dto.additionalServices, newMansion);
		return newMansion;
    }

	private Set<AdditionalService> addMansionToServices(Set<AdditionalService> additionalServices, Mansion newMansion) {
		Set<AdditionalService> additionalServiceWithMansion = new HashSet<>();
		for (AdditionalService as: additionalServices) {
			AdditionalService as1 = as;
			as1.setMansion(newMansion);
			additionalServiceRepository.save(as1);
			additionalServiceWithMansion.add(as1);
		}
		return additionalServiceWithMansion;
	}

	private Mansion createMansion(MansionRegistrationDto dto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MansionOwner mansionOwner = mansionOwnerRepository.findById(user.getId()).get();
		Address mansionAddress = new Address(dto.getAddress(), dto.getCity(), dto.getCountry(), dto.getLatitude(), dto.getLongitude());
		Mansion newMansion = new Mansion();
		newMansion.setMansionOwner(mansionOwner);
		newMansion.setName(dto.getName());
		newMansion.setPromoDescription(dto.promoDescription);
		newMansion.setAddress(mansionAddress);
		newMansion.setCancellationPolicy(dto.cancellationPolicy);
		newMansion.setExteriorImages(new HashSet<>(entityImageService.createAndSaveImages("MansionOwners",mansionOwner.getEmail(),dto.getName(),dto.getExteriorImages())));
		newMansion.setInteriorImages(new HashSet<>(entityImageService.createAndSaveImages("MansionOwners",mansionOwner.getEmail(),dto.getName(),dto.getInteriorImages())));
		newMansion.setRules(convertString2Rule(dto.rules, newMansion));
		newMansion.setRooms(converMap2Room(dto.rooms));
		newMansion.setPricePerDay(dto.pricePerDay);
		newMansion.setPriceForSevenDays(dto.priceForSevenDays);
		return newMansion;
	}

	private Set<Room> converMap2Room(List<Integer> rooms) {
		List<Integer> listOfBeds = new ArrayList<>(rooms);
		Set<Room> convertedRooms = new HashSet<>();
		for ( Integer i : listOfBeds) {
				Room newRoom = new Room( i);
				convertedRooms.add(newRoom);
		}
		System.out.println(rooms);
		return convertedRooms;
	}

	private Set<Rule> convertString2Rule(Set<String> rules, Mansion mansion) {
		Set<Rule> convertedRules = new HashSet<>();
		for (String s: rules){
			Rule newRule = new Rule(s);
			newRule.addMansion(mansion);
			convertedRules.add(newRule);
		}
		return convertedRules;
	}

    public List<MansionAvailablePeriod> addMansionAvailabilities(AddAvailablePeriodDto dto) {
		Mansion mansion = mansionRepo.findById(dto.boatId).get();
		MansionAvailablePeriod availablePeriod = new MansionAvailablePeriod(dto.startTime, dto.endTime, mansion);
		 if (isThereAReservation(availablePeriod, dto.boatId)){
			return mansionAvailablePeriodRepository.findByMansion(mansion);
		}
		 if(insideOtherPeriod(availablePeriod, mansionAvailablePeriodRepository.findByMansion(mansion))){
			return mansionAvailablePeriodRepository.findByMansion(mansion);
		}
		mansionAvailablePeriodRepository.save(availablePeriod);
		List<MansionAvailablePeriod> mansionNewAvailability = mansionAvailablePeriodRepository.findByMansion(mansion);
		return  mansionNewAvailability;
    }

	private boolean isThereAReservation(MansionAvailablePeriod availablePeriod, Long boatId) {
		Mansion mansion = mansionRepo.findById(boatId).get();
		List<AbstractReservation> reservations = mansionReservationService.getAllMansionReservations(mansion);
		for (AbstractReservation ar : reservations){
			if(ar.getStartDate().after(availablePeriod.getEndDate()) && (availablePeriod.getEndDate().before(ar.getEndDate()))){
				return true;
			}
		}
		return false;
	}

	private boolean insideOtherPeriod(MansionAvailablePeriod availablePeriod, List<MansionAvailablePeriod> byMansion) {
		//(StartDate1 <= EndDate2) and (StartDate2 <= EndDate1)
		Boolean overlaps = false;
		for (MansionAvailablePeriod ma : byMansion){
			if((ma.getStartDate()).before(availablePeriod.getEndDate()) && (availablePeriod.getStartDate().before(ma.getEndDate()))){
				if (ma.getStartDate().after(availablePeriod.getStartDate())){;
					ma.setStartDate(availablePeriod.getStartDate());
				}
				if(ma.getEndDate().before(availablePeriod.getEndDate())){
					ma.setEndDate(availablePeriod.getEndDate());
				}
				mansionAvailablePeriodRepository.save(ma);
				return true;
			}
		}
		return overlaps;
	}

	public Mansion changeMansion(ChangeMansionDto dto) {
		Mansion mansionToChange = mansionRepo.findById(dto.id).get();
		if(mansionToChange == null) return null;
		mansionToChange.setName(dto.name);
		mansionToChange.setPricePerDay(dto.pricePerDay);
		mansionToChange.setPriceForSevenDays(dto.priceForSevenDays);
		mansionToChange.setCancellationPolicy(dto.cancellationPolicy);
		mansionToChange.setPromoDescription(dto.promoDescription);
		mansionToChange.setRules(makeRules(dto));
		mansionToChange.setExteriorImages(new HashSet<>(entityImageService.removeAndAddNewExterior(dto, mansionToChange)));
		mansionToChange.setInteriorImages(new HashSet<>(entityImageService.removeAndAddNewInterior(dto, mansionToChange)));
		mansionToChange.setRooms(converMap2Room(dto.rooms));
		Address boatAddress = addressRepository.findById(mansionToChange.getAddress().getId()).get();
		boatAddress.setCountry(dto.country);
		boatAddress.setCity(dto.city);
		boatAddress.setAddress(dto.address);
		boatAddress.setLatitude(dto.latitude);
		boatAddress.setLongitude(dto.longitude);
		addressRepository.save(boatAddress);
		mansionToChange = mansionRepo.save(mansionToChange);
		return mansionToChange;
	}

	private Set<Rule> makeRules(ChangeMansionDto dto) {
		System.out.println("Making rules mansion***");
		Mansion mansion = mansionRepo.findById(dto.id).get();
		Set<Rule> newRules = new HashSet<>();
		for (Rule r : dto.rules){
			if(r.getRuleId()==-1){
				Rule newRule = new Rule(r.getRule());
				newRule.addMansion(mansion);
				newRules.add(newRule);
			}

		}
		for(Integer rid : dto.rulesToDelete){
			Rule rd= ruleRepository.findById(rid).get();
			rd.removeMansion(mansion);
			ruleRepository.save(rd);
		}
		return newRules;
	}

	public List<MansionAvailablePeriod> getMansionAvailbilities(Long boatId) {
		Mansion mansion =mansionRepo.findById(boatId).get();
		List<MansionAvailablePeriod> mansionAvailablePeriods = mansionAvailablePeriodRepository.findByMansion(mansion);
		return mansionAvailablePeriods;
	}

	public boolean overlapsWithAvailability(Date startDate, Date endDate, Long id) {
		List<MansionAvailablePeriod> mansionAvailablePeriods = getMansionAvailbilities(id);
		for (MansionAvailablePeriod ma : mansionAvailablePeriods ){
			if(ma.getStartDate().before(endDate) && startDate.before(ma.getStartDate())){
				return true;
			}
		}
		return false;
	}

	public boolean isInAvailabilityPeriods(Date startDate, Date endDate, Long id) {
		List<MansionAvailablePeriod> mansionAvailablePeriods = getMansionAvailbilities(id);
		for (MansionAvailablePeriod ma : mansionAvailablePeriods ){
			if(!startDate.before(ma.getStartDate()) && !endDate.after(ma.getStartDate())){
				return true;
			}
		}
		return false;
	}



	@Transactional
	public Mansion findLockedById(Long id){
		return mansionRepo.findLockedById(id);
	}
}

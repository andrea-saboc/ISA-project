package com.example.isa.service.impl;

import java.util.*;

import com.example.isa.dto.MansionRegistrationDto;
import com.example.isa.model.*;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.MansionOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.SearchDto;
import com.example.isa.repository.MansionRepository;

import javax.sound.midi.SysexMessage;

@Service
public class MansionService {
	
	@Autowired
	MansionRepository mansionRepo;

	@Autowired
	MansionOwnerRepository mansionOwnerRepository;

	@Autowired
	AdditionalServiceRepository additionalServiceRepository;
	
	public List<Mansion> getAll() {
		
		return mansionRepo.findAll();
	}

	public Mansion deleteMansion(long id) {
		Mansion mansion = mansionRepo.findById(id);
		mansion.setDeleted(true);
		return mansionRepo.save(mansion);
	}

	public Mansion getById(Long id) {
		return mansionRepo.findById(id).get();
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
		//newMansion.setExteriorImages(convertString2Image(dto.ExteriorImages, newMansion, false));
		//newMansion.setInteriorImages(convertString2Image(dto.InteriorImages, newMansion, true));
		newMansion.setRules(convertString2Rule(dto.rules, newMansion));
		newMansion.setRooms(converMap2Room(dto.rooms, newMansion));
		newMansion.setPricePerDay(dto.pricePerDay);
		newMansion.setPriceForSevenDays(dto.priceForSevenDays);
		return newMansion;
	}

	private Set<Room> converMap2Room(List<Integer> rooms, Mansion newMansion) {
		List<Integer> listOfBeds = new ArrayList<>(rooms);
		Set<Room> convertedRooms = new HashSet<>();
		for ( Integer i : listOfBeds) {
				Room newRoom = new Room(newMansion, i);
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
}

package com.example.isa.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.isa.dto.BoatRegistrationDTO;
import com.example.isa.model.Address;
import com.example.isa.model.Image;
import com.example.isa.model.Rule;
import com.example.isa.repository.BoatsRepository;
import com.example.isa.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;

@Service
public class BoatService {
	@Autowired
	private BoatsRepository boatsRepository;
	@Autowired
	private ImageRepository imageRepository;

	public BoatService(BoatsRepository br, ImageRepository ir){
		this.boatsRepository = br;
		this.imageRepository = ir;
	}

	public List<Boat> getAll() {
		List<Boat> boats = boatsRepository.findAll();
		System.out.println("Service boat getting all");
		System.out.println(boats);
		return boats;
	}

    public Boat registerBoat(BoatRegistrationDTO dto) {
		System.out.println("In registering boat service!");
		Boat newBoat = createBoat(dto);
		boatsRepository.save(newBoat);
		return newBoat;
    }

	private Boat createBoat(BoatRegistrationDTO dto) {
		Address boatAddress = new Address();
		boatAddress.setAddress(dto.address);
		boatAddress.setCity(dto.city);
		boatAddress.setCity(dto.country);
		boatAddress.setLatitude(dto.latitude);
		boatAddress.setLongitude(dto.longitude);
		Boat newBoat = new Boat();
		newBoat.setName(dto.name);
		newBoat.setPromoDescription(dto.promoDescription);
		newBoat.setType(dto.type);
		newBoat.setAddress(boatAddress);
		newBoat.setCapacity(dto.capacity);
		newBoat.setFishfinder(dto.fishfinder);
		newBoat.setEnginePower(dto.enginePower);
		newBoat.setNumberOfEngines(dto.numberOfEngines);
		newBoat.setMaxSpeed(dto.maxSpeed);
		newBoat.setGPS(dto.GPS);
		newBoat.setVHFradio(dto.VHFradio);
		newBoat.setExteriorImages(convertString2Image(dto.ExteriorImages, newBoat, false));
		newBoat.setInteriorImages(convertString2Image(dto.InteriorImages, newBoat, true));
		newBoat.setRules(convertString2Rule(dto.rules, newBoat));
		return newBoat;
	}

	private Set<Image> convertString2Image(Set<String> Images, Boat boat, boolean inter){
		Set<Image> convertedImages = new HashSet<>();
		for (String s: Images) {
			Image newImage = new Image();
			newImage = setBoat(newImage, boat, inter);
			newImage = imageRepository.save(newImage);
			newImage.setImg(s);
			newImage.saveImage();
			convertedImages.add(newImage);
		}
		return convertedImages;
	}

	private Image setBoat(Image newImage, Boat boat, boolean inter) {
		if (inter) newImage.setBoatInter(boat);
		else newImage.setBoatExter(boat);
		return newImage;
	}

	private Set<Rule> convertString2Rule(Set<String> Rules, Boat boat){
		Set<Rule> convertedRules = new HashSet<>();
		for (String s: Rules){
			Rule newRule = new Rule(s);
			newRule.addBoat(boat);
			convertedRules.add(newRule);
		}
		return convertedRules;
	}

	public Boat getById(String id) {
		return boatsRepository.getOne(Integer.parseInt(id));
	}
}

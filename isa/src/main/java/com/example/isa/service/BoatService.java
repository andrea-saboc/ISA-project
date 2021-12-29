package com.example.isa.service;
import java.util.ArrayList;

import com.example.isa.dto.BoatRegistrationDTO;
import com.example.isa.repository.BoatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;

@Service
public class BoatService {
	@Autowired
	private BoatsRepository boatsRepository;

	public BoatService(BoatsRepository br){
		this.boatsRepository = br;
	}

	public ArrayList<Boat> getAll() {
		
		ArrayList<Boat> boats = new ArrayList<>();
		boats.add(new Boat("ime mi je brod","neka adresa","promo opis moj",(float) 2.00));
		return boats;
	}

    public Boat registerBoat(BoatRegistrationDTO dto) {
		Boat newBoat = dto.createBoat();
		boatsRepository.save(newBoat);
		return newBoat;
    }
}

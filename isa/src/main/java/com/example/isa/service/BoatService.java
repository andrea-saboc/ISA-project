package com.example.isa.service;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.isa.model.Boat;

@Service
public class BoatService {

	public ArrayList<Boat> getAll() {
		
		ArrayList<Boat> boats = new ArrayList<>();
		boats.add(new Boat("ime mi je brod","neka adresa","promo opis moj",(float) 2.00));
		return boats;
	}

}

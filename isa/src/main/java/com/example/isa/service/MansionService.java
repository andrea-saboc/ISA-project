package com.example.isa.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.example.isa.model.Mansion;

@Service
public class MansionService {
	public ArrayList<Mansion> getAll() {
		
		ArrayList<Mansion> boats = new ArrayList<>();
		boats.add(new Mansion("ime mi je vikendica","neka adresa","promo opis moj",(float) 2.00));
		return boats;
	}
}

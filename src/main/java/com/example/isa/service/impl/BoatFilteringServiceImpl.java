package com.example.isa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.SearchDto;
import com.example.isa.model.Boat;
import com.example.isa.repository.BoatRepository;

@Service
public class BoatFilteringServiceImpl {

	
	@Autowired
	BoatRepository boatRepo;
	
	public List<Boat> getAll() {
		
		return boatRepo.findAll();
	}
	
	public List<Boat> searchAll(SearchDto search) {
		
		if(search.getType().equals("Name")) return SearchByName(search.getValue());
		else if(search.getType().equals("Location")) return SearchByLocation(search.getValue());
		else return searchByGrade(search.getValue());
	}
	
	private List<Boat> SearchByName(String value) {
		List<Boat> ret = new ArrayList<Boat>();
		for(Boat m: getAll()) {
			if(m.getName().toLowerCase().contains(value.toLowerCase()))
				ret.add(m);
		}
		return ret;
	}
	
	private List<Boat> SearchByLocation(String value) {
		List<Boat> ret = new ArrayList<Boat>();
		for(Boat m: getAll()) {
			if(m.getAddress().getAddress().toLowerCase().contains(value.toLowerCase()) ||
					m.getAddress().getCity().toLowerCase().contains(value.toLowerCase()) ||
					m.getAddress().getCountry().toLowerCase().contains(value.toLowerCase()))
				ret.add(m);
		}
		return ret;
	}
	
	private List<Boat> searchByGrade(String value) {
		float f=Float.parseFloat(value);
		List<Boat> ret = new ArrayList<Boat>();
		for(Boat m: getAll()) {
			if(m.getAvgGrade() >= f)
				ret.add(m);
		}
		return ret;
	}

}

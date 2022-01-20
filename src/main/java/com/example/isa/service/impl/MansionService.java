package com.example.isa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.SearchDto;
import com.example.isa.model.Mansion;
import com.example.isa.repository.MansionRepository;

@Service
public class MansionService {
	
	@Autowired
	MansionRepository mansionRepo;
	
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
	


}

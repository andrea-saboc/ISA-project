package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired 
	ReservationRepository repo;
}

package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BoatReservation;
import com.example.isa.model.MansionReservation;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.repository.UserRepository;

@Service
public class ReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired 
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	UserRepository userRepo;
	
	public Iterable<BoatReservation> GetBoatReservationHistory(User u){
		return boatReservationRepo.findAllByUser(u);
	}
	
	public Iterable<MansionReservation> GetMansionReservationHistory(User u){
		return mansionReservationRepo.findAllByUser(u);
	}
	
	public List<Reservation> GetUserReservations(User u){
		
		List<Reservation> ret = new ArrayList<Reservation>();
		ret.addAll(boatReservationRepo.findAllByUser(u));
		
		return ret;
	}
}

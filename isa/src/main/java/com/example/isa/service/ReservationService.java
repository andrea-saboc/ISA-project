package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.model.BoatReservation;
import com.example.isa.model.Client;
import com.example.isa.model.Reservation;
import com.example.isa.model.User;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.UserRepository;

@Service
public class ReservationService {

	@Autowired 
	BoatReservationRepository boatReservationRepo;
	@Autowired
	UserRepository userRepo;
	
	public Iterable<BoatReservation> GetAllByUser(User u){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return boatReservationRepo.findAllByUser(user);
	}
	public Iterable<Reservation> GetUserReservations(User u){
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<Reservation> ret = new ArrayList<Reservation>();
		ret.addAll(boatReservationRepo.findAllByUser(user));
		
		return ret;
	}
}

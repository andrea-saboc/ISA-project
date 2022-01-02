package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientFeedbackDTO;
import com.example.isa.model.BoatReservation;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.User;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientFeedbackRepository;

@Service
public class ClientFeedbackService {
	
	@Autowired
	ClientFeedbackRepository feedbackRepo;
	@Autowired
	BoatReservationRepository boatReservationRepo;

	public ClientFeedback addBoatOwnerFeedback(ClientFeedbackDTO dto) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ClientFeedback feedback = new ClientFeedback(dto.getContent(), dto.getGrade(),user.getEmail());
		feedbackRepo.save(feedback);
		BoatReservation boatReservation = boatReservationRepo.findById(dto.getReservation());
		boatReservation.setBoatOwnerFeedback(feedback);
		boatReservationRepo.save(boatReservation);
		return feedback;
		
	}
	
	

}

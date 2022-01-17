package com.example.isa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientFeedbackDto;
import com.example.isa.model.Boat;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientFeedbackRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.FeedbackService;

@Service
public class BoatFeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	ClientFeedbackRepository feedbackRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	BoatReservationRepository boatReservationRepo;
	@Autowired
	AuthenticationService authenticationService;
	
	@Override
	public ClientFeedback addEntityFeedback(ClientFeedbackDto dto) {
		ClientFeedback feedback = new ClientFeedback(dto.getContent(), dto.getGrade(),authenticationService.getLoggedUser().getEmail());
		feedbackRepo.save(feedback);
		BoatReservation boatReservation = boatReservationRepo.findById(dto.getReservation());

		Boat boat = boatReservation.getBoat();
		boat.setAvgGrade(updateAvgGrade(dto,boat));
		
		boatReservation.setBoatFeedback(feedback);
		boatReservationRepo.save(boatReservation);
		return feedback;
	}

	@Override
	public ClientFeedback addOwnerFeedback(ClientFeedbackDto dto) {
		
		ClientFeedback feedback = new ClientFeedback(dto.getContent(), dto.getGrade(),authenticationService.getLoggedUser().getEmail());
		feedbackRepo.save(feedback);
		BoatReservation boatReservation = boatReservationRepo.findById(dto.getReservation());
		boatReservation.setBoatOwnerFeedback(feedback);
		boatReservationRepo.save(boatReservation);
		return feedback;
	}
	
	public float updateAvgGrade(ClientFeedbackDto dto,Boat boat) {
		
		List<BoatReservation> list = boatReservationRepo.findAllByBoatAndBoatFeedbackIsNotNull(boat);
		
		int numerOfFeedbacks=list.size();
		System.out.println("koji je size liste " + numerOfFeedbacks);
		return (boat.getAvgGrade() + dto.getGrade())/(numerOfFeedbacks+1);
	}
	
	

}

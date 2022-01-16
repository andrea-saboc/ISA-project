package com.example.isa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientFeedbackDto;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.Mansion;
import com.example.isa.model.User;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.repository.ClientFeedbackRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.FeedbackService;

@Service
public class MansionFeedbackServiceImpl  implements FeedbackService  {
	
	@Autowired
	ClientFeedbackRepository feedbackRepo;
	@Autowired
	MansionRepository mansionRepo;
	@Autowired
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	AuthenticationService authenticationService;
	
	
	@Override
	public ClientFeedback addEntityFeedback(ClientFeedbackDto dto) {
		ClientFeedback feedback = new ClientFeedback(dto.getContent(), dto.getGrade(),authenticationService.getLoggedUser().getEmail());
		feedbackRepo.save(feedback);
		MansionReservation mansionReservation = mansionReservationRepo.findById(dto.getReservation());
		
		Mansion mansion = mansionReservation.getMansion();
		mansion.setAvgGrade(updateAvgGrade(dto,mansion));
		
		mansionReservation.setMansionFeedback(feedback);
		mansionReservationRepo.save(mansionReservation);
		return feedback;
	}

	@Override
	public ClientFeedback addOwnerFeedback(ClientFeedbackDto dto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ClientFeedback feedback = new ClientFeedback(dto.getContent(), dto.getGrade(),user.getEmail());
		feedbackRepo.save(feedback);
		MansionReservation mansionReservation = mansionReservationRepo.findById(dto.getReservation());
		mansionReservation.setMansionOwnerFeedback(feedback);
		mansionReservationRepo.save(mansionReservation);
		return feedback;	
	}
	
	public float updateAvgGrade(ClientFeedbackDto dto,Mansion mansion) {
		
		List<MansionReservation> list = mansionReservationRepo.findAllByMansionAndMansionFeedbackIsNotNull(mansion);
		
		int numerOfFeedbacks=list.size();
		System.out.println("koji je size liste " + numerOfFeedbacks);
		return (mansion.getAvgGrade() + dto.getGrade())/(numerOfFeedbacks+1);
	}

}

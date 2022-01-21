package com.example.isa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientComplaintDto;
import com.example.isa.model.AdvertiserComplaint;
import com.example.isa.model.Boat;
import com.example.isa.model.BoatComplaint;
import com.example.isa.model.Client;
import com.example.isa.model.Complaint;
import com.example.isa.model.Mansion;
import com.example.isa.model.MansionComplaint;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.repository.ComplaintRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.AuthenticationService;

@Service
public class ClientComplaintService {
	
	@Autowired
	ComplaintRepository complaintRepository;
	@Autowired
	BoatRepository boatRepository;
	@Autowired
	MansionRepository mansionRepository;
	@Autowired
	BoatReservationRepository boatReservationRepo;
	@Autowired
	MansionReservationRepository mansionReservationRepo;
	@Autowired
	AuthenticationService authentication;

	
	public BoatComplaint addBoatComplaint(ClientComplaintDto dto) {
		return complaintRepository.save(new BoatComplaint((Client) authentication.getLoggedUser(), dto.getContent(),boatRepository.findById(dto.getEntityId()).orElse(new Boat())));
	}
	
	public AdvertiserComplaint addBoatAdvertiserComplaint(ClientComplaintDto dto) {		
		return complaintRepository.save(new AdvertiserComplaint((Client) authentication.getLoggedUser(), dto.getContent(),boatRepository.findById(dto.getEntityId()).orElse(new Boat()).getBoatOwner()));
	}
	
	public AdvertiserComplaint addMansionAdvertiserComplaint(ClientComplaintDto dto) {
		return complaintRepository.save(new AdvertiserComplaint((Client) authentication.getLoggedUser(), dto.getContent(),mansionRepository.findById(dto.getEntityId()).getMansionOwner()));
	}
	
	
	public MansionComplaint addMansionComplaint(ClientComplaintDto dto) {
		return complaintRepository.save(new MansionComplaint((Client) authentication.getLoggedUser(), dto.getContent(),mansionRepository.findById(dto.getEntityId())));	
	}
	
	
	
	public List<Boat> getBoats() {
		List<Boat> ret = new ArrayList<Boat>();
		for(BoatReservation b : boatReservationRepo.findAllByUserAndStatus(authentication.getLoggedUser(),ReservationStatus.CLOSED)) {
			if(!ret.contains(b.getBoat()))
				ret.add(b.getBoat());			
		}		
		return ret;
	}
	public List<Mansion> getMansions() {
		List<Mansion> ret = new ArrayList<Mansion>();
		for(MansionReservation b : mansionReservationRepo.findAllByUserAndStatus(authentication.getLoggedUser(),ReservationStatus.CLOSED)) {
			if(!ret.contains(b.getMansion()))
				ret.add(b.getMansion());			
		}		
		return ret;
	}

}

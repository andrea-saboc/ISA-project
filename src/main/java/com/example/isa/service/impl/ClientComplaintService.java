package com.example.isa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.isa.dto.AdminComplaintDto;
import com.example.isa.mail.MailService;
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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
	@Autowired
	MailService mailService;

	
	public BoatComplaint addBoatComplaint(ClientComplaintDto dto) {
		return complaintRepository.save(new BoatComplaint((Client) authentication.getLoggedUser(), dto.getContent(),boatRepository.findById(dto.getEntityId()).orElse(new Boat())));
	}
	
	public AdvertiserComplaint addBoatAdvertiserComplaint(ClientComplaintDto dto) {		
		return complaintRepository.save(new AdvertiserComplaint((Client) authentication.getLoggedUser(), dto.getContent(),boatRepository.findById(dto.getEntityId()).orElse(new Boat()).getBoatOwner()));
	}
	
	public AdvertiserComplaint addMansionAdvertiserComplaint(ClientComplaintDto dto) {
		return complaintRepository.save(new AdvertiserComplaint((Client) authentication.getLoggedUser(), dto.getContent(),mansionRepository.findById(dto.getEntityId()).getMansionOwner()));
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void sendComplain(AdminComplaintDto dto) {
		Complaint complaint=complaintRepository.findById(dto.idComlaint).get();
		Client client=complaint.getAuthor();
		User user = null;
		System.out.print("OVDE SAM");
		System.out.print(complaint.getClass().getSimpleName());
		System.out.print(complaint.getClass().getAnnotations());
		System.out.print(complaint.getClass().getName());

		if(complaint.getClass().getSimpleName().equals("BoatComplaint"))
		{
			BoatComplaint mansionComplaint=(BoatComplaint) complaintRepository.findById(complaint.getId()).get();
			 user=mansionComplaint.getBoat().getBoatOwner();

		}

		if(complaint.getClass().getSimpleName().equals("AdvertiserComplaint")) {
			AdvertiserComplaint advertiserComplaint = (AdvertiserComplaint) complaintRepository.findById(complaint.getId()).get();
			user = advertiserComplaint.getAdvertiser();
		}
		if(complaint.getClass().getSimpleName().equals("MansionComplaint"))
		{
			MansionComplaint mansionComplaint=(MansionComplaint) complaintRepository.findById(complaint.getId()).get();
			 user=mansionComplaint.getMansion().getMansionOwner();
		}

		System.out.print(user.toString());
		//mailService.sendClientCompplain(client,dto.reportText);
		//mailService.sendAdvertiserCompplain(user,dto.reportText);
		complaint.setApproved(false);
		complaintRepository.save(complaint);


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
	public List<Complaint> getAll()
	{
		List<Complaint> complaints=complaintRepository.findAll();
		return  complaints;
	}

}

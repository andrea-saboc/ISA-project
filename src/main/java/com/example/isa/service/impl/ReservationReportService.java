package com.example.isa.service.impl;

import com.example.isa.dto.ReportAdminDto;
import com.example.isa.dto.ReportCommonDto;
import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReportDTO;

import java.util.List;

@Service
public class ReservationReportService {
	
	@Autowired
	private ReservationReportRepository reservationReportRepository;
	@Autowired
	private DiscountReservationRepository discountReservationRepository;
	@Autowired
	private RegularReservationRepository regularReservationRepository;
	@Autowired
	private BoatDiscountReservationRepository boatDiscountReservationRepository;
	@Autowired
	private BoatReservationRepository boatRegularReservationRepository;
	@Autowired
	private MansionReservationRepository mansionRegularReservationRepository;
	@Autowired
	private  MansionDiscountReservationRepository mansionDiscountReservationRepository;
	@Autowired
	private AdventureDiscountReservationRepository adventureDiscountReservationRepository;
	@Autowired
	private AdventureReservationRepository adventureReservationRepository;
	@Autowired
	private BoatRepository boatRepository;
	@Autowired
	private MansionOwnerRepository mansionOwnerRepository;
	@Autowired
	private MansionRepository mansionRepository;
	@Autowired
	private AdventureRepository adventureRepository;
	
	@Autowired
	private PenaltyManagementService penaltyManagementService;
	@Autowired
	private MailService mailService;
	@Autowired
	private BoatOwnerRepository boatOwnerRepository;
	@Autowired
	FishingInstructorRepository fishingInstructorRepository;

	public ReservationReport createReservationReport(ReportDTO dto) {
		ReservationReport reservationReport = null;
		switch(dto.reservationType) {
		case REGULAR_BOAT:
			reservationReport=createReportForRegularBoatReservation(dto);
			break;
		case DISCOUNT_BOAT:
			reservationReport=createReportForDiscountBoatReservation(dto);
			break;
			case REGULAR_MANSION:
				reservationReport=createReportForRegularMansionReservation(dto);
				break;
			case DISCOUNT_MANSION:
				reservationReport=createReportForDiscountMansionReservation(dto);
				break;
			case REGULAR_ADVENTURE:
				reservationReport=createReportForRegularAdventureReservation(dto);
				break;
			case DISCOUNT_ADVENTURE:
				reservationReport=createReportForDiscountAdventureReservation(dto);
				break;


		default:
			break;
		}
		/*if(regularReservation!=null) {
			reservationReport = createReportForRegularReservation(dto, regularReservation);
		} else {
			reservationReport = createReportForDiscountReservation(dto, discountReservation);
		}*/
		reservationReportRepository.save(reservationReport);
		return reservationReport;
	}

	private ReservationReport createReportForDiscountMansionReservation(ReportDTO dto) {
		MansionDiscountReservation boatDiscountReservation = mansionDiscountReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setDiscountReservation(boatDiscountReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(boatDiscountReservation.getUser());
		}
		boatDiscountReservation.setStatus(ReservationStatus.REPORT_CREATED);
		mansionDiscountReservationRepository.save(boatDiscountReservation);
		return reservationReport;
	}

	private ReservationReport createReportForRegularMansionReservation(ReportDTO dto) {
		MansionReservation mansionReservation = mansionRegularReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setReservation(mansionReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(mansionReservation.getUser());
		}
		mansionReservation.setStatus(ReservationStatus.REPORT_CREATED);
		mansionRegularReservationRepository.save(mansionReservation);
		return reservationReport;
	}

	private ReservationReport createReportForRegularAdventureReservation(ReportDTO dto) {
		AdventureReservation adventureReservation = adventureReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setReservation(adventureReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(adventureReservation.getUser());
		}
		adventureReservation.setStatus(ReservationStatus.REPORT_CREATED);
		adventureReservationRepository.save(adventureReservation);
		return reservationReport;
	}

	private ReservationReport createReportForDiscountAdventureReservation(ReportDTO dto) {
		AdventureDiscountReservation adventureDiscountReservation = adventureDiscountReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setDiscountReservation(adventureDiscountReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(adventureDiscountReservation.getUser());
		}
		adventureDiscountReservation.setStatus(ReservationStatus.REPORT_CREATED);
		adventureDiscountReservationRepository.save(adventureDiscountReservation);
		return reservationReport;
	}

	private ReservationReport createReportForRegularBoatReservation(ReportDTO dto) {
		BoatReservation boatReservation = boatRegularReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setReservation(boatReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(boatReservation.getUser());
		}
		boatReservation.setStatus(ReservationStatus.REPORT_CREATED);
		boatRegularReservationRepository.save(boatReservation);
		return reservationReport;
	}

	private ReservationReport createReportForDiscountBoatReservation(ReportDTO dto) {
		BoatDiscountReservation boatDiscountReservation = boatDiscountReservationRepository.findById(dto.id).get();
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setDiscountReservation(boatDiscountReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(boatDiscountReservation.getUser());
		}
		boatDiscountReservation.setStatus(ReservationStatus.REPORT_CREATED);
		boatDiscountReservationRepository.save(boatDiscountReservation);
		return reservationReport;
	}

	private ReservationReport createReportForDiscountReservation(ReportDTO dto,
			DiscountReservation discountReservation) {
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setDiscountReservation(discountReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(discountReservation.getUser());
		}
		
		return reservationReport;
	}

	private ReservationReport createReportForRegularReservation(ReportDTO dto, Reservation regularReservation) {
		ReservationReport reservationReport = new ReservationReport();
		reservationReport.setClientShowedUp(dto.clientShowedUp);
		reservationReport.setApproved(false);
		reservationReport.setReservation(regularReservation);
		reservationReport.setReportText(dto.reportText);
		reservationReport.setRequestedToSanction(dto.requestedToSanction);
		reservationReport.setNotApproved(false);
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(regularReservation.getUser());
		}
		
		
		return reservationReport;
	}

	public List<ReservationReport> getAll()
	{
		List<ReservationReport> reservationReports=reservationReportRepository.findAllByApprovedFalseAndRequestedToSanctionTrue();
		return reservationReports;
	}
	public void setApproveComment(ReportCommonDto dto)
	{
		System.out.println(dto);
		ReservationReport reservationReport=reservationReportRepository.findById(dto.idReservation).get();
		if(reservationReport.getReservation()!=null) {
			penaltyManagementService.addPenaltyToClient(reservationReport.getReservation().getUser());

			//mailService.sendNotificationAboutCommonClient(reservationReport.getReservation().getUser());
			reservationReport.setApproved(true);
			reservationReport.setNotApproved(false);

			if (reservationReport.getReservation().getType().equals("BOAT") ) {
				Long id = reservationReport.getReservation().getId();
				BoatReservation boatReservation = boatRegularReservationRepository.findById(id).get();
				Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
				BoatOwner boatOwner = boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();
				//mailService.sendNotificationAboutCommonUser(boatOwner,reservationReport.getReservation().getUser());
			}

			if (reservationReport.getReservation().getType().equals("MANSION") ) {
				Long id = reservationReport.getReservation().getId();
				MansionReservation mansionReservation = mansionRegularReservationRepository.findById(id).get();
				Mansion mansion = mansionRepository.findById(mansionReservation.getMansion().getId()).get();
				MansionOwner mansionOwner = mansionOwnerRepository.findById(mansion.getMansionOwner().getId()).get();
				//mailService.sendNotificationAboutCommonUser(mansionOwner,reservationReport.getReservation().getUser());
			}


			if (reservationReport.getReservation().getType().equals("ADVENTURE") ) {
				Long id = reservationReport.getReservation().getId();
				AdventureReservation adventureReservation = adventureReservationRepository.findById(id).get();
				Adventure adventure = adventureRepository.findById(adventureReservation.getAdventure().getId()).get();
				FishingInstructor fishingInstructor = fishingInstructorRepository.findById(adventure.getFishingInstructor().getId()).get();
				//mailService.sendNotificationAboutCommonUser(fishingInstructor,reservationReport.getReservation().getUser());
			}
			reservationReportRepository.save(reservationReport);
		}
		else
		{
			penaltyManagementService.addPenaltyToClient(reservationReport.getDiscountReservation().getUser());
			DiscountReservation discountReservation=reservationReport.getDiscountReservation();
			//mailService.sendNotificationAboutCommonClient(reservationReport.getDiscountReservation().getUser());
			reservationReport.setApproved(true);
			reservationReport.setNotApproved(false);

			if (reservationReport.getDiscountReservation().getType().equals("BOAT DISCOUNT") ) {
				Long id = reservationReport.getDiscountReservation().getId();
				BoatDiscountReservation boatReservation = boatDiscountReservationRepository.findById(id).get();
				Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
				BoatOwner boatOwner = boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();
				//mailService.sendNotificationAboutCommonUser(boatOwner,reservationReport.getDiscountReservation().getUser());
			}


			if (reservationReport.getDiscountReservation().getType().equals("MANSION DISCOUNT") ) {
				Long id = reservationReport.getDiscountReservation().getId();
				MansionDiscountReservation mansionReservation = mansionDiscountReservationRepository.findById(id).get();
				Mansion mansion = mansionRepository.findById(mansionReservation.getMansion().getId()).get();
				MansionOwner mansionOwner = mansionOwnerRepository.findById(mansion.getMansionOwner().getId()).get();
				//mailService.sendNotificationAboutCommonUser(mansionOwner,reservationReport.getDiscountReservation().getUser());
			}

			if (reservationReport.getDiscountReservation().getType().equals("ADVENTURE DISCOUNT")) {
				Long id = reservationReport.getDiscountReservation().getId();
				AdventureDiscountReservation adventureReservation = adventureDiscountReservationRepository.findById(id).get();
				Adventure adventure = adventureRepository.findById(adventureReservation.getAdventure().getId()).get();
				FishingInstructor fishingInstructor = fishingInstructorRepository.findById(adventure.getFishingInstructor().getId()).get();
				//mailService.sendNotificationAboutCommonUser(fishingInstructor,reservationReport.getDiscountReservation().getUser());
			}
			reservationReportRepository.save(reservationReport);

		}

	}
	public void setNotApproveComment(ReportCommonDto dto) {
		System.out.println(dto);
		ReservationReport reservationReport = reservationReportRepository.findById(dto.idReservation).get();
		if (reservationReport.getReservation() != null) {

			//mailService.sendNotificationAboutNotApproveCommonClient(reservationReport.getReservation().getUser());
			reservationReport.setApproved(false);
			reservationReport.setNotApproved(true);

			if (reservationReport.getReservation().getType().equals("BOAT")) {
				Long id = reservationReport.getReservation().getId();
				BoatReservation boatReservation = boatRegularReservationRepository.findById(id).get();
				Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
				BoatOwner boatOwner = boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(boatOwner,reservationReport.getReservation().getUser());
			}

			if (reservationReport.getReservation().getType().equals("MANSION")) {
				Long id = reservationReport.getReservation().getId();
				MansionReservation mansionReservation = mansionRegularReservationRepository.findById(id).get();
				Mansion mansion = mansionRepository.findById(mansionReservation.getMansion().getId()).get();
				MansionOwner mansionOwner = mansionOwnerRepository.findById(mansion.getMansionOwner().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(mansionOwner,reservationReport.getReservation().getUser());
			}


			if (reservationReport.getReservation().getType().equals("ADVENTURE")) {
				Long id = reservationReport.getReservation().getId();
				AdventureReservation adventureReservation = adventureReservationRepository.findById(id).get();
				Adventure adventure = adventureRepository.findById(adventureReservation.getAdventure().getId()).get();
				FishingInstructor fishingInstructor = fishingInstructorRepository.findById(adventure.getFishingInstructor().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(fishingInstructor,reservationReport.getReservation().getUser());
			}
			reservationReportRepository.save(reservationReport);
		} else {
			//mailService.sendNotificationAboutCommonClient(reservationReport.getDiscountReservation().getUser());
			reservationReport.setApproved(false);
			reservationReport.setNotApproved(true);

			if (reservationReport.getDiscountReservation().getType().equals("BOAT DISCOUNT")) {
				Long id = reservationReport.getDiscountReservation().getId();
				BoatDiscountReservation boatReservation = boatDiscountReservationRepository.findById(id).get();
				Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
				BoatOwner boatOwner = boatOwnerRepository.findById(boat.getBoatOwner().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(boatOwner,reservationReport.getDiscountReservation().getUser());
			}


			if (reservationReport.getDiscountReservation().getType().equals("MANSION DISCOUNT")) {
				Long id = reservationReport.getDiscountReservation().getId();
				MansionDiscountReservation mansionReservation = mansionDiscountReservationRepository.findById(id).get();
				Mansion mansion = mansionRepository.findById(mansionReservation.getMansion().getId()).get();
				MansionOwner mansionOwner = mansionOwnerRepository.findById(mansion.getMansionOwner().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(mansionOwner,reservationReport.getDiscountReservation().getUser());
			}

			if (reservationReport.getDiscountReservation().getType().equals("ADVENTURE DISCOUNT")) {
				Long id = reservationReport.getDiscountReservation().getId();
				AdventureDiscountReservation adventureReservation = adventureDiscountReservationRepository.findById(id).get();
				Adventure adventure = adventureRepository.findById(adventureReservation.getAdventure().getId()).get();
				FishingInstructor fishingInstructor = fishingInstructorRepository.findById(adventure.getFishingInstructor().getId()).get();
				//mailService.sendNotificationAboutNotApproveCommonUser(fishingInstructor,reservationReport.getDiscountReservation().getUser());
			}
			reservationReportRepository.save(reservationReport);

		}
	}

}

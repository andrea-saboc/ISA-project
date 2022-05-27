package com.example.isa.service.impl;

import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReportDTO;
import com.example.isa.model.ReservationReport;

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
	private PenaltyManagementService penaltyManagementService;

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
				reservationReport=createReportForDiscountMansionReservation(dto);
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
		if(!dto.clientShowedUp) {
			penaltyManagementService.addPenaltyToClient(regularReservation.getUser());
		}
		
		
		return reservationReport;
	}

}

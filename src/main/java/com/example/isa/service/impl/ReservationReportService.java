package com.example.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReportDTO;
import com.example.isa.model.ReservationReport;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientRepository;
import com.example.isa.repository.DiscountReservationRepository;
import com.example.isa.repository.RegularReservationRepository;
import com.example.isa.repository.ReservationReportRepository;
import com.example.isa.service.UserService;

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
	private PenaltyManagementService penaltyManagementService;

	public ReservationReport createReservationReport(ReportDTO dto) {
		ReservationReport reservationReport = null;
		switch(dto.reservationType) {
		case REGULAR_BOAT:
			reservationReport=createReportForRegularBoatReservation(dto);
			break;
		case DISCOUNT_BOAT:
			reservationReport=createReportForDiscountBoatReservation(dto);
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

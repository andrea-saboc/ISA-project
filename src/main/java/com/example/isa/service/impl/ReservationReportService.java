package com.example.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ReportDTO;
import com.example.isa.model.ReservationReport;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.Reservation;
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
	private PenaltyManagementService penaltyManagementService;

	public ReservationReport createReservationReport(ReportDTO dto) {
		ReservationReport reservationReport;
		System.out.println("Pre pretrage");
		Reservation regularReservation = regularReservationRepository.findById(dto.regularReservationId).orElse(null);
		System.out.println("Prva pretraga");
		DiscountReservation discountReservation = discountReservationRepository.findById(dto.discountReservationId).orElse(null);
		System.out.println("Druga pretraga");
		if(regularReservation!=null) {
			reservationReport = createReportForRegularReservation(dto, regularReservation);
		} else {
			reservationReport = createReportForDiscountReservation(dto, discountReservation);
		}
		reservationReportRepository.save(reservationReport);
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

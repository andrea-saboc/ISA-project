package com.example.isa.service;

import com.example.isa.constants.ReservationConstants;
import com.example.isa.dto.ReportDTO;
import com.example.isa.enums.ReservationType;
import com.example.isa.repository.*;
import com.example.isa.service.impl.PenaltyManagementService;
import com.example.isa.service.impl.ReservationReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080/",
        "EPASS=eaqmslibsskdayfv",
        "EMAIL=adventurelandisa@gmail.com",
        "PASS=root"})
public class ReservationReportServiceTest {
    @Mock
    private ReservationReportRepository reservationReportRepository;
    @Mock
    private DiscountReservationRepository discountReservationRepository;
    @Mock
    private RegularReservationRepository regularReservationRepository;
    @Mock
    private BoatDiscountReservationRepository boatDiscountReservationRepository;
    @Mock
    private BoatReservationRepository boatRegularReservationRepository;
    @Mock
    private MansionReservationRepository mansionRegularReservationRepository;
    @Mock
    private  MansionDiscountReservationRepository mansionDiscountReservationRepository;
    @Mock
    private AdventureDiscountReservationRepository adventureDiscountReservationRepository;
    @Mock
    private AdventureReservationRepository adventureReservationRepository;

    @Mock
    private PenaltyManagementService penaltyManagementService;


    @InjectMocks
    ReservationReportService reservationReportService;


    private ReservationConstants constants = new ReservationConstants();

    @Test
    public void checkReportReservationCreatedFalse(){
        ReportDTO dto= new ReportDTO();
        dto.clientShowedUp = true;
        dto.reportText = "Client was awesome!";
        dto.reservationType = ReservationType.BOAT;
        dto.requestedToSanction = false;
        dto.id = constants.RESERVATION_PARAMS.getEntityId();
        dto.approved = false;
        assertTrue(reservationReportService.createReservationReport(dto)==null);
    }

    @Test
    public void checkReportReservationCreatedSuccess(){
        ReportDTO dto= new ReportDTO();
        dto.clientShowedUp = true;
        dto.reportText = "Client was awesome!";
        dto.reservationType = ReservationType.BOAT;
        dto.requestedToSanction = false;
        dto.id = 1L;
        dto.approved = false;
        assertFalse(reservationReportService.createReservationReport(dto)!=null);
    }
}

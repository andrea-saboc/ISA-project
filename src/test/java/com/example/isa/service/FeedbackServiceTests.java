package com.example.isa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.isa.constants.FeedbackConstants;
import com.example.isa.model.ClientFeedback;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.repository.BoatReservationRepository;
import com.example.isa.repository.ClientFeedbackRepository;
import com.example.isa.repository.MansionReservationRepository;
import com.example.isa.service.impl.BoatFeedbackServiceImpl;
import com.example.isa.service.impl.MansionFeedbackServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbackServiceTests {

	@Mock
	ClientFeedbackRepository feedbacksRepository;
	
	@Mock
	BoatReservationRepository boatResRepository;
	@Mock
	MansionReservationRepository mansionResRepository;
	
	@InjectMocks
	private BoatFeedbackServiceImpl service;
	
	@InjectMocks
	private MansionFeedbackServiceImpl mansionService;
	
    @Mock
    AuthenticationService authenticationService;
    
    private FeedbackConstants feedbackConstants = new FeedbackConstants();
	
	@Test
	public void addNewBoatFeedbackTest() {
				
        when(authenticationService.getLoggedUser()).thenReturn(FeedbackConstants.CLIENT);
        BoatReservation boatReservarion= new BoatReservation();
        boatReservarion.setId(1L);
        boatReservarion.setBoat(FeedbackConstants.BOAT);
        when(boatResRepository.findById(1L)).thenReturn(boatReservarion);

        

		ClientFeedback newFeedback = service.addEntityFeedback(FeedbackConstants.BOAT_FEEDBACK);
		
		assertTrue(newFeedback!=null);	
	}
	
	@Test
	public void addNewMansionFeedbackTest() {
		
        when(authenticationService.getLoggedUser()).thenReturn(FeedbackConstants.CLIENT);
        MansionReservation mansionReservarion= new MansionReservation();
        mansionReservarion.setId(1L);
        mansionReservarion.setMansion(FeedbackConstants.MANSION);
        when(mansionResRepository.findById(1L)).thenReturn(mansionReservarion);

        

		ClientFeedback newFeedback = mansionService.addEntityFeedback(FeedbackConstants.MANSION_FEEDBACK);
		
		assertTrue(newFeedback!=null);					
	}
	
	@Test
	public void updateBoatAvgGrade() {
		
        List<BoatReservation> emptyList = new ArrayList<BoatReservation>();
        when(boatResRepository.findAllByBoatAndBoatFeedbackIsNotNull(FeedbackConstants.BOAT)).thenReturn(emptyList);

        double updatedAvgGrade = service.updateAvgGrade(FeedbackConstants.BOAT_FEEDBACK, FeedbackConstants.BOAT);
		
		assertTrue(updatedAvgGrade == FeedbackConstants.BOAT_FEEDBACK.getGrade());					
	}
	
	@Test
	public void updateMansionAvgGrade() {
		
        List<MansionReservation> emptyList = new ArrayList<MansionReservation>();
        when(mansionResRepository.findAllByMansionAndMansionFeedbackIsNotNull(FeedbackConstants.MANSION)).thenReturn(emptyList);

        double updatedAvgGrade = mansionService.updateAvgGrade(FeedbackConstants.MANSION_FEEDBACK, FeedbackConstants.MANSION);
		
		assertTrue(updatedAvgGrade == FeedbackConstants.MANSION_FEEDBACK.getGrade());				
	}
	
	
}

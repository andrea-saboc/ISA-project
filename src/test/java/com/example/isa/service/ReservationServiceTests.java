package com.example.isa.service;

import static com.example.isa.constants.ReservationConstants.EXPECTED_PRICE;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.isa.constants.ReservationConstants;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.impl.reservations.BoatDiscountReservationService;
import com.example.isa.service.impl.reservations.BoatReservationServiceImpl;
import com.example.isa.service.impl.reservations.CollectingActiveReservationsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTests {
	
	@Mock
	BoatAvailablePeriodRepository periodsRepository;
	
	@Mock
	BoatRepository boatRepository;
	
	@Mock
	BoatDiscountReservationRepository disReservationRepo;
	
	@Mock
    AuthenticationService authenticationService;
	
	@Mock
	CollectingActiveReservationsService resService;
	
	@InjectMocks
	private BoatReservationServiceImpl service;
	
	@InjectMocks
	private BoatDiscountReservationService disResService;
	
	private ReservationConstants constants = new ReservationConstants();
	
	@Test
	public void CheckAdditionalServicesPrice() {
		//da li funcija za racunanje radi kako treba
		double price = service.accountAdditionalServices(constants.BOAT_ADDITIONAL_SERVICES, constants.RESERVATION_PARAMS);
		assertTrue(price == EXPECTED_PRICE);
	}



	@Test
	public void CancellationNotAllowed() {
						
		boolean allowed = resService.isCancellationAllowed(new Date());		
		assertTrue(allowed == false);
	}




}

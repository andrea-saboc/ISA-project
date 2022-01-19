package com.example.isa.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.isa.constants.AdditionalServicesConstants;
import com.example.isa.constants.AvailablePeriodConstants;
import com.example.isa.model.Boat;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.impl.reservations.BoatReservationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTests {
	
	@Mock
	BoatAvailablePeriodRepository periodsRepository;
	
	@Mock
	BoatRepository boatRepository;
	
	@InjectMocks
	private BoatReservationServiceImpl service;
	
	private AdditionalServicesConstants constants = new AdditionalServicesConstants();
	
	@Test
	public void CheckAdditionalServicesPrice() {
						
		double price = service.accountAdditionalServices(constants.BOAT_ADDITIONAL_SERVICES,constants.RESERVATION_PARAMS);		
		assertTrue(price == 50.00);
	}
	
	@Test
	public void MakeDiscountReservationTest() {
						
		double price = service.accountAdditionalServices(constants.BOAT_ADDITIONAL_SERVICES,constants.RESERVATION_PARAMS);		
		assertTrue(price == 50.00);
	}


}

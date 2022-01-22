package com.example.isa.service;

import static com.example.isa.constants.ReservationConstants.EXPECTED_PRICE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.isa.constants.ReservationConstants;
import com.example.isa.exception.CancelledReservationException;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.impl.reservations.BoatDiscountReservationService;
import com.example.isa.service.impl.reservations.BoatReservationServiceImpl;

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
	
	@InjectMocks
	private BoatReservationServiceImpl service;
	
	@InjectMocks
	private BoatDiscountReservationService disResService;
	
	private ReservationConstants constants = new ReservationConstants();
	
	@Test
	public void CheckAdditionalServicesPrice() {
						
		double price = service.accountAdditionalServices(constants.BOAT_ADDITIONAL_SERVICES,constants.RESERVATION_PARAMS);		
		assertTrue(price == EXPECTED_PRICE);
	}
	
	@Test
	public void MakingBoatDiscountReservationSuccessfullTest() throws ObjectOptimisticLockingFailureException, OfferNotAvailableException, CancelledReservationException {
		

        when(authenticationService.getLoggedUser()).thenReturn(ReservationConstants.CLIENT);		
		when(disReservationRepo.findByIdAndStatus(1L,ReservationStatus.ACTIVE)).thenReturn(null);
		when(disReservationRepo.findByUserAndStartDateAndEndDateAndStatusAndBoat(ReservationConstants.CLIENT,ReservationConstants.BOAT_DISCOUNT_RESERVATION.getStartDate(), 
				ReservationConstants.BOAT_DISCOUNT_RESERVATION.getEndDate(), ReservationStatus.CANCELLED,ReservationConstants.BOAT)).thenReturn(null);
		
		
		DiscountReservation res = disResService.makeReservationOnDiscount(ReservationConstants.BOAT_DISCOUNT_RESERVATION.getId());
		
		assertTrue(res!=null);
		
	}
	
	@Test(expected = OfferNotAvailableException.class)
	public void MakingBoatDiscountReservationExceptionTest() throws ObjectOptimisticLockingFailureException, OfferNotAvailableException, CancelledReservationException {
		

        when(authenticationService.getLoggedUser()).thenReturn(ReservationConstants.CLIENT);
		when(disReservationRepo.findByIdAndStatus(1L,ReservationStatus.ACTIVE)).thenReturn(ReservationConstants.BOAT_DISCOUNT_RESERVATION);
		
		when(disReservationRepo.findByUserAndStartDateAndEndDateAndStatusAndBoat(ReservationConstants.CLIENT,ReservationConstants.BOAT_DISCOUNT_RESERVATION.getStartDate(), 
				ReservationConstants.BOAT_DISCOUNT_RESERVATION.getEndDate(), ReservationStatus.CANCELLED,ReservationConstants.BOAT)).thenReturn(null);
		
				
		disResService.makeReservationOnDiscount(ReservationConstants.BOAT_DISCOUNT_RESERVATION.getId());
		
		
	}
	
	@Test(expected = CancelledReservationException.class)
	public void MakingBoatDiscountReservationCancelledExceptionTest() throws ObjectOptimisticLockingFailureException, OfferNotAvailableException, CancelledReservationException {
		

        when(authenticationService.getLoggedUser()).thenReturn(ReservationConstants.CLIENT);
		when(disReservationRepo.findByIdAndStatus(1L,ReservationStatus.ACTIVE)).thenReturn(ReservationConstants.BOAT_DISCOUNT_RESERVATION);
		
		when(disReservationRepo.findByUserAndStartDateAndEndDateAndStatusAndBoat(ReservationConstants.CLIENT,ReservationConstants.BOAT_DISCOUNT_RESERVATION.getStartDate(), 
				ReservationConstants.BOAT_DISCOUNT_RESERVATION.getEndDate(), ReservationStatus.CANCELLED,ReservationConstants.BOAT)).thenReturn(ReservationConstants.BOAT_DISCOUNT_RESERVATION);
		
				
		disResService.makeReservationOnDiscount(ReservationConstants.BOAT_DISCOUNT_RESERVATION.getId());
		
		
	}



}

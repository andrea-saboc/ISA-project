package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.repository.BoatAvailablePeriodRepository;
import com.example.isa.service.SubscriptionService;
import com.example.isa.service.impl.AdvertisersService;
import com.example.isa.service.impl.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.CancelledReservationException;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.BoatDiscountReservationRepository;
import com.example.isa.repository.BoatOwnerRepository;
import com.example.isa.repository.BoatRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoatDiscountReservationService implements DiscountReservationService{
	
	@Autowired
	BoatDiscountReservationRepository reservationRepo;
	@Autowired
	BoatRepository boatRepo;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	BoatOwnerRepository boatOwnerRepository;
	@Autowired
	MailService<String> mailService;
	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	BoatService boatService;
	@Autowired
	CollectingBoatReservationsServiceImpl collectingBoatReservationsService;
	@Autowired
	AdvertisersService advertisersService;
	@Autowired
	BoatReservationServiceImpl boatReservationService;
	@Autowired
	BoatAvailablePeriodRepository boatAvailablePeriodRepository;
	

	
	@Override
	public List<DiscountReservation> getAvailableDiscountReservations(long id) {
    	Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndStatus(boat,ReservationStatus.ACTIVE);
	}

	@Override
	public List<DiscountReservation> getReservedDiscountReservations(long id) {
		Boat boat = boatRepo.findById(id).orElse(new Boat());
		return reservationRepo.findAllByBoatAndStatus(boat,ReservationStatus.RESERVED);
	}
	
	@Override
	public DiscountReservation cancelDiscountReservation(long resId) {		
		BoatDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.RESERVED);
		recordCanceledReservation(res);
		res.setStatus(ReservationStatus.ACTIVE);
		return reservationRepo.save(res);
	}
	
	public void recordCanceledReservation(BoatDiscountReservation res) {		
		BoatDiscountReservation canceled = new BoatDiscountReservation(res);
		canceled.setStatus(ReservationStatus.CANCELLED);
		reservationRepo.save(canceled);
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException, CancelledReservationException{
		
    	BoatDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.ACTIVE);    	
    	BoatDiscountReservation repeatedRes = reservationRepo.findByUserAndStartDateAndEndDateAndStatusAndBoat(
    			authenticationService.getLoggedUser(), res.getStartDate(), res.getEndDate(), ReservationStatus.CANCELLED,res.getBoat());

    	if(res == null)
			throw new OfferNotAvailableException();
		else if (repeatedRes != null) {
			throw new CancelledReservationException();
		}
    	else {
			Boat entity = boatRepo.findLockedById(res.getBoat().getId());
			if (entity == null)
				throw new PessimisticLockingFailureException("This entity is being deleted!");
	    	res.setStatus(ReservationStatus.RESERVED);
	    	res.setUser(authenticationService.getLoggedUser());
	    	return reservationRepo.save(res);
    	}
	}
	
	public List<BoatDiscountReservation> getLoggedUserReservation(){
		User user = authenticationService.getLoggedUser();
		BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
		List<Boat> ownersBoats = boatRepo.findBoatByBoatOwner(boatOwner);
		List<BoatDiscountReservation> boatReservations = new ArrayList<>();
		for ( Boat boat : ownersBoats) {
			boatReservations.addAll(reservationRepo.findAllByBoat(boat));
		}
		return boatReservations;
	}
	
	public Date getEndDate(NewDiscountReservationDto reservation) {
		Date endDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(reservation.startDate);
		cal.add(Calendar.DAY_OF_MONTH, reservation.days);
		cal.add(Calendar.HOUR, reservation.hours);
		endDate = cal.getTime();
		return endDate;
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public int createDiscountReservation(NewDiscountReservationDto dto) {
		BoatDiscountReservation boatDiscountReservation = new BoatDiscountReservation();
		Boat entity = boatRepo.findLockedById(dto.boatId);
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
		Boat boat = boatRepo.findById(dto.boatId).get();
		boatDiscountReservation.setBoat(boat);
		boatDiscountReservation.setStatus(ReservationStatus.ACTIVE);
		boatDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
		boatDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
		boatDiscountReservation.setValidUntil(dto.validUntil);
		boatDiscountReservation.setStartDate(dto.startDate);
		boatDiscountReservation.setEndDate(getEndDate(dto));
		if (dto.isOwnerPresent && !advertisersService.boatOwnerAvailable(boat.getOwner(), dto.startDate, getEndDate(dto))){
			return 4;
		}
		boatDiscountReservation.setOwnerPresent(dto.isOwnerPresent);
		if(!boatService.inAvailabilityPeriods(boatDiscountReservation.getStartDate(), boatDiscountReservation.getEndDate(), boat.getId())){
			return 2;
		}
		if(collectingBoatReservationsService.overlapsWithActiveReservations(boatDiscountReservation.getStartDate(), boatDiscountReservation.getEndDate(), boat)){
			return 3;
		}
		BoatAvailablePeriod period = boatAvailablePeriodRepository.getPeriodOfInterest(dto.startDate, getEndDate(dto), dto.boatId);
		if(!period.getStartDate().equals(boatDiscountReservation.getStartDate())) {
			BoatAvailablePeriod periodBefore = new BoatAvailablePeriod(period.getStartDate(),boatDiscountReservation.getStartDate(),period.getBoat());
			boatAvailablePeriodRepository.save(periodBefore);
		}
		if(!period.getEndDate().equals(boatDiscountReservation.getEndDate())) {
			BoatAvailablePeriod periodAfter = new BoatAvailablePeriod(boatDiscountReservation.getEndDate(),period.getEndDate(),period.getBoat());
			boatAvailablePeriodRepository.save(periodAfter);
		}
		boatAvailablePeriodRepository.delete(period);

		boatDiscountReservation.setAdditionalServices(boatReservationService.addAdditionalServices(dto.additionalServiceSet));
		boatDiscountReservation.setType("BOAT");
		boatDiscountReservation.setPriceWithoutDiscount(dto.getPrice(boat));
		boatDiscountReservation.calculatePercentageOfDiscount();
		reservationRepo.save(boatDiscountReservation);
		notifyAllSubscribers(boatDiscountReservation);
		return 1;
	}

	private void notifyAllSubscribers(BoatDiscountReservation boatDiscountReservation) {
		List<User> subscribers = subscriptionService.getAllSubscribersByBoat(boatDiscountReservation.getBoat());
		for (User c : subscribers){
			mailService.sendNotificationAboutDiscountReservation((Client) c, boatDiscountReservation);
		}
	}


}

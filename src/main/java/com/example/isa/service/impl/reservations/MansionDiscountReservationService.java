package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.BoatDiscountReservation;
import com.example.isa.repository.MansionOwnerRepository;
import com.example.isa.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.CancelledReservationException;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionDiscountReservation;
import com.example.isa.model.reservations.ReservationStatus;
import com.example.isa.repository.MansionDiscountReservationRepository;
import com.example.isa.repository.MansionRepository;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;

@Service
public class MansionDiscountReservationService implements DiscountReservationService{
	
	@Autowired
	MansionDiscountReservationRepository reservationRepo;
	@Autowired
	MansionRepository mansionRepo;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	MansionOwnerRepository mansionOwnerRepository;
	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	MailService<String> mailService;
	

	@Override
	public List<DiscountReservation> getAvailableDiscountReservations(long id) {
    	Mansion mansion = mansionRepo.findById(id);
		return reservationRepo.findAllByMansionAndStatus(mansion,ReservationStatus.ACTIVE);
	}

	@Override
	public List<DiscountReservation> getReservedDiscountReservations(long id) {
    	Mansion mansion = mansionRepo.findById(id);
		return reservationRepo.findAllByMansionAndStatus(mansion,ReservationStatus.RESERVED);
	}
	
	@Override
	public DiscountReservation cancelDiscountReservation(long resId) {
		
		MansionDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.RESERVED);
		recordCanceledReservation(res);
		res.setStatus(ReservationStatus.ACTIVE);
		return reservationRepo.save(res);
	}

	@Override
	public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException, CancelledReservationException {
		
    	MansionDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.ACTIVE);
    	MansionDiscountReservation repeatedRes = reservationRepo.findByUserAndStartDateAndEndDateAndStatusAndMansion(
    			authenticationService.getLoggedUser(),res.getStartDate(),res.getEndDate(), ReservationStatus.CANCELLED,res.getMansion());
    			
    	if(res == null) {
    		throw new OfferNotAvailableException();
    	}
    	else if( repeatedRes != null)
    	{ 
    		throw new CancelledReservationException();
    	}
    	else {
    	res.setStatus(ReservationStatus.RESERVED);
    	res.setUser(authenticationService.getLoggedUser());
    	return reservationRepo.save(res);
    	}
	}

	@Override
	public DiscountReservation createDiscountReservation(NewDiscountReservationDto dto) {
		MansionDiscountReservation mansionDiscountReservation = new MansionDiscountReservation();
		Mansion mansion = mansionRepo.findById(dto.boatId).get();
		mansionDiscountReservation.setMansion(mansion);
		mansionDiscountReservation.setStatus(ReservationStatus.ACTIVE);
		mansionDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
		mansionDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
		mansionDiscountReservation.setValidUntil(dto.validUntil);
		mansionDiscountReservation.setStartDate(dto.startDate);
		mansionDiscountReservation.setEndDate(getEndDate(dto));
		mansionDiscountReservation.setType("MANSION");
		mansionDiscountReservation.setPriceWithoutDiscount(dto.getPrice(mansion));
		mansionDiscountReservation.calculatePercentageOfDiscount();
		reservationRepo.save(mansionDiscountReservation);
		notifyAllSubscribers(mansionDiscountReservation);
		return mansionDiscountReservation;
	}

	private void notifyAllSubscribers(MansionDiscountReservation mansionDiscountReservation) {
		List<User> subscribers = subscriptionService.getAllSubscribersByMansion(mansionDiscountReservation.getMansion());
		for (User c : subscribers) {
			mailService.sendNotificationAboutDiscountReservation((Client) c, mansionDiscountReservation);
		}
	}

	private Date getEndDate(NewDiscountReservationDto dto) {
		Date endDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dto.startDate);
		cal.add(Calendar.DAY_OF_MONTH, dto.days);
		endDate = cal.getTime();
		return endDate;
	}

	public void recordCanceledReservation(MansionDiscountReservation res) {
		
		MansionDiscountReservation canceled = new MansionDiscountReservation(res);
		canceled.setStatus(ReservationStatus.CANCELLED);
		reservationRepo.save(canceled);
	}


	public List<MansionDiscountReservation> getLoggedUserReservations() {
		User user = authenticationService.getLoggedUser();
		MansionOwner mansionOwner = mansionOwnerRepository.findById(user.getId()).get();
		List<Mansion> ownersMansions = mansionRepo.findAllByMansionOwnerAndDeletedFalse(mansionOwner);
		List<MansionDiscountReservation> mansionDiscountReservations = new ArrayList<>();
		for ( Mansion mansion : ownersMansions) {
			mansionDiscountReservations.addAll(reservationRepo.findAllByMansion(mansion));
		}
		return mansionDiscountReservations;
	}
}

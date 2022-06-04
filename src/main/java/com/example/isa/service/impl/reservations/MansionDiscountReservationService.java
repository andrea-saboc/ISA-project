package com.example.isa.service.impl.reservations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.repository.MansionAvailablePeriodRepository;
import com.example.isa.repository.MansionOwnerRepository;
import com.example.isa.service.SubscriptionService;
import com.example.isa.service.impl.AdditionalServiceService;
import com.example.isa.service.impl.MansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	MansionService mansionService;
	@Autowired
	CollectionMansionReservationsImpl collectionMansionReservationsService;
	@Autowired
	AdditionalServiceService additionalServiceService;
	@Autowired
	MansionAvailablePeriodRepository mansionAvailablePeriodRepository;
	

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
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
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
			Mansion entity = mansionRepo.findLockedById(res.getMansion().getId());
			if (entity == null)
				throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
    	res.setStatus(ReservationStatus.RESERVED);
    	res.setUser(authenticationService.getLoggedUser());
    	return reservationRepo.save(res);
    	}
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
	public int createDiscountReservation(NewDiscountReservationDto dto) {
		MansionDiscountReservation mansionDiscountReservation = new MansionDiscountReservation();
		Mansion entity = mansionRepo.findLockedById(dto.boatId);
		if (entity == null)
			throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
		Mansion  mansion= mansionRepo.findById(dto.boatId).get();
		mansionDiscountReservation.setMansion(mansion);
		mansionDiscountReservation.setStatus(ReservationStatus.ACTIVE);
		mansionDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
		mansionDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
		mansionDiscountReservation.setValidUntil(dto.validUntil);
		mansionDiscountReservation.setStartDate(dto.startDate);
		mansionDiscountReservation.setEndDate(getEndDate(dto));
		if(!mansionService.isInAvailabilityPeriods(mansionDiscountReservation.getStartDate(), mansionDiscountReservation.getEndDate(), mansion.getId())){
			return 2;
		}
		if(collectionMansionReservationsService.overlapsWithActiveReservations(mansionDiscountReservation.getStartDate(), mansionDiscountReservation.getEndDate(), mansion)){
			return 3;
		}
		MansionAvailablePeriod period = mansionAvailablePeriodRepository.getPeriodOfInterest(dto.startDate, getEndDate(dto), dto.boatId);
		if(!period.getStartDate().equals(mansionDiscountReservation.getStartDate())) {
			MansionAvailablePeriod periodBefore = new MansionAvailablePeriod(period.getStartDate(),mansionDiscountReservation.getStartDate(),period.getMansion());
			mansionAvailablePeriodRepository.save(periodBefore);
		}
		if(!period.getEndDate().equals(mansionDiscountReservation.getEndDate())) {
			MansionAvailablePeriod periodAfter = new MansionAvailablePeriod(mansionDiscountReservation.getEndDate(),period.getEndDate(),period.getMansion());
			mansionAvailablePeriodRepository.save(periodAfter);
		}
		mansionAvailablePeriodRepository.delete(period);
		mansionDiscountReservation.setAdditionalServices(additionalServiceService.addAdditionalServices(dto.additionalServiceSet));
		mansionDiscountReservation.setType("MANSION");
		mansionDiscountReservation.setPriceWithoutDiscount(dto.getPrice(mansion));
		mansionDiscountReservation.calculatePercentageOfDiscount();
		reservationRepo.save(mansionDiscountReservation);
		notifyAllSubscribers(mansionDiscountReservation);
		return 1;
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

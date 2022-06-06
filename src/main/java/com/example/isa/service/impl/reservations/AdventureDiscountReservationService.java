package com.example.isa.service.impl.reservations;


import com.example.isa.dto.NewDiscountReservationDto;
import com.example.isa.exception.CancelledReservationException;
import com.example.isa.exception.OfferNotAvailableException;
import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.DiscountReservationService;
import com.example.isa.service.SubscriptionService;
import com.example.isa.service.impl.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdventureDiscountReservationService implements DiscountReservationService {

    @Autowired
    AdventureDiscountReservationRepository reservationRepo;
    @Autowired
    AdventureRepository adventureRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    MailService<String> mailService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    AdventureService adventureService;
    @Autowired
    CollectingAdventureReservationsServiceImpl collectingAdventureReservationsService;
    @Autowired
    FishingAvailablePeriodRepository fishingAvailablePeriodRepository;


    @Override
    public List<DiscountReservation> getAvailableDiscountReservations(long id) {
        Adventure adventure =adventureRepository.findById(id).orElse(new Adventure());
        return reservationRepo.findAllByAdventureAndStatus(adventure,ReservationStatus.ACTIVE);
    }

    @Override
    public List<DiscountReservation> getReservedDiscountReservations(long id) {
        Adventure adventure =adventureRepository.findById(id).orElse(new Adventure());
        return reservationRepo.findAllByAdventureAndStatus(adventure,ReservationStatus.RESERVED);
    }

    @Override
    @Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public int createDiscountReservation(NewDiscountReservationDto dto) {
        AdventureDiscountReservation adventureDiscountReservation = new AdventureDiscountReservation();
        Adventure entity = adventureRepository.findLockedById(dto.boatId);
        if (entity == null)
            throw new PessimisticLockingFailureException("Some is already trying to reserve at the same time!");
        Adventure adventure = adventureRepository.findById(dto.boatId).get();
        FishingInstructor fishingInstructor=adventure.getFishingInstructor();

        adventureDiscountReservation.setAdventure(adventure);
        adventureDiscountReservation.setStatus(ReservationStatus.ACTIVE);
        adventureDiscountReservation.setPriceWithDiscount(dto.priceWithDiscount);
        adventureDiscountReservation.setNumberOfGuests(dto.numberOfGuests);
        adventureDiscountReservation.setValidUntil(dto.validUntil);
        adventureDiscountReservation.setStartDate(dto.startDate);
        adventureDiscountReservation.setEndDate(getEndDate(dto));

        if(!adventureService.inAvailabilityPeriods(adventureDiscountReservation.getStartDate(),adventureDiscountReservation.getEndDate(),fishingInstructor.getId()))

        { return 2;}
        if(collectingAdventureReservationsService.overlapsWithActiveReservations(adventureDiscountReservation.getStartDate(),adventureDiscountReservation.getEndDate(),adventure)) {
            return 3;
        }
        Date endDate=getEndDate(dto);


        FishingAvailablePeriod period = fishingAvailablePeriodRepository.getPeriodOfInterest(dto.startDate, endDate, fishingInstructor.getId());
        if(period == null) {
            return 5;
        }
        if(!period.getStartDate().equals(adventureDiscountReservation.getStartDate())) {
            FishingAvailablePeriod periodBefore = new FishingAvailablePeriod(period.getStartDate(),adventureDiscountReservation.getStartDate(),period.getFishingInstructor());
            fishingAvailablePeriodRepository.save(periodBefore);
        }
        if(!period.getEndDate().equals(adventureDiscountReservation.getEndDate())) {
            FishingAvailablePeriod periodAfter = new FishingAvailablePeriod(adventureDiscountReservation.getEndDate(),period.getEndDate(),period.getFishingInstructor());
            fishingAvailablePeriodRepository.save(periodAfter);
        }
        fishingAvailablePeriodRepository.delete(period);



        adventureDiscountReservation.setType("ADVENTURE");
        adventureDiscountReservation.setPriceWithoutDiscount(dto.getPrice(adventure));
        adventureDiscountReservation.calculatePercentageOfDiscount();
        reservationRepo.save(adventureDiscountReservation);
       // notifyAllSubscribers(adventureDiscountReservation);
        return 1;
    }

    private void notifyAllSubscribers(AdventureDiscountReservation adventureDiscountReservation) {
        List<User> subscribers = subscriptionService.getAllSubscribersByAdventure(adventureDiscountReservation.getAdventure());
        for (User c : subscribers) {
            mailService.sendNotificationAboutDiscountReservation1((Client) c, adventureDiscountReservation);
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

    @Override
    public DiscountReservation cancelDiscountReservation(long resId) {
        AdventureDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.RESERVED);
        recordCanceledReservation(res);
        res.setStatus(ReservationStatus.ACTIVE);
        return reservationRepo.save(res);
    }

    public void recordCanceledReservation(AdventureDiscountReservation res) {
        AdventureDiscountReservation canceled = new AdventureDiscountReservation(res);
        canceled.setStatus(ReservationStatus.CANCELLED);
        reservationRepo.save(canceled);
    }

    @Override
    public DiscountReservation makeReservationOnDiscount(long resId) throws OfferNotAvailableException,ObjectOptimisticLockingFailureException, CancelledReservationException{

        AdventureDiscountReservation res = reservationRepo.findByIdAndStatus(resId,ReservationStatus.ACTIVE);
        AdventureDiscountReservation repeatedRes = reservationRepo.findByUserAndStartDateAndEndDateAndStatusAndAdventure(
                authenticationService.getLoggedUser(), res.getStartDate(), res.getEndDate(), ReservationStatus.CANCELLED,res.getAdventure());

        if(res == null)
            throw new OfferNotAvailableException();
        else if (repeatedRes != null) {
            throw new CancelledReservationException();
        }
        else {
            res.setStatus(ReservationStatus.RESERVED);
            res.setUser(authenticationService.getLoggedUser());
            return reservationRepo.save(res);
        }
    }

    public List<AdventureDiscountReservation> getLoggedUserReservation(){
        User user = authenticationService.getLoggedUser();
        FishingInstructor fishingInstructor = fishingInstructorRepository.findById(user.getId()).get();
        List<Adventure> adventures = adventureRepository.findAdventureByFishingInstructor(fishingInstructor);
        List<AdventureDiscountReservation> adventureDiscountReservations = new ArrayList<>();
        for ( Adventure a : adventures) {
            adventureDiscountReservations.addAll(reservationRepo.findAllByAdventure(a));
        }
        return adventureDiscountReservations;
    }




}

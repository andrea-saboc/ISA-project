package com.example.isa.service.impl.reservations;

import com.example.isa.dto.CustomReservationForClientDto;
import com.example.isa.dto.ReservationDto;
import com.example.isa.exception.EntityDeletedException;
import com.example.isa.exception.ImpossibleDueToPenaltyPoints;
import com.example.isa.exception.PeriodNoLongerAvailableException;
import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.ReservationService;
import com.example.isa.service.impl.DateCoverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly=true)
public class AdventureReservationServiceImpl implements ReservationService {

    @Autowired
    AdventureReservationRepository adventureReservationRepository;
    @Autowired
    AdventureRepository adventureRepository;
    @Autowired
    FishingAvailablePeriodRepository availablePeriodsRepo;
    @Autowired
    AdditionalServiceRepository additinalServicesRepo;
    @Autowired
    FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CollectingAdventureReservationsServiceImpl collectingAdventureReservationsService;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    MailService<String> mailService;
    @Override
    @Transactional(readOnly=false,propagation= Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public int createReservationForClient(CustomReservationForClientDto dto) throws PeriodNoLongerAvailableException, ParseException {

        ReservationDto res = new ReservationDto(dto);
        System.out.println(res.toString());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor fishingInstructor1 = fishingInstructorRepository.findById(user.getId()).get();
        ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
        Date startDate = formatter.startDate;
        Date endDate = formatter.endDate;

        FishingAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,fishingInstructor1.getId());
        Adventure adventure = adventureRepository.findById(res.getEntityId()).orElse(new Adventure());

        if(period == null) {
            throw new PeriodNoLongerAvailableException();
        }
        else {

            Client client = clientRepository.findByEmail(dto.email);
            AdventureReservation newAdventureReservation= new AdventureReservation(client, startDate,endDate, res.getNumberOfGuests(), dto.toResSearchDto(),
                    adventure);

            if(!period.getStartDate().equals(startDate)) {
                FishingAvailablePeriod periodBefore = new FishingAvailablePeriod(period.getStartDate(),startDate,period.getFishingInstructor());
                availablePeriodsRepo.save(periodBefore);
            }
            if(!period.getEndDate().equals(endDate)) {
                FishingAvailablePeriod periodAfter = new FishingAvailablePeriod(period.getStartDate(),startDate,period.getFishingInstructor());
                availablePeriodsRepo.save(periodAfter);
            }


            availablePeriodsRepo.delete(period);
            newAdventureReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
            newAdventureReservation.setTotalPrice(dto.getPrice(adventure) + accountAdditionalServices(newAdventureReservation.getAdditionalServices(),res));
            //mailService.notifyClientAboutCreatedReservation(newAdventureReservation);
            AdventureReservation newRes =adventureReservationRepository.save(newAdventureReservation);
            if (newRes == null){
                return 3;
            }
            return 1;
        }
    }


    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation= Isolation.SERIALIZABLE)
    public AdventureReservation createReservation(ReservationDto res) throws ParseException, PeriodNoLongerAvailableException, EntityDeletedException, ImpossibleDueToPenaltyPoints {

        ReservationStartEndDateFormatter formatter = new ReservationStartEndDateFormatter(res);
        Date startDate = formatter.startDate;
        Date endDate = formatter.endDate;
        FishingAvailablePeriod period = availablePeriodsRepo.getPeriodOfInterest(startDate, endDate,res.getEntityId());
        Adventure adventure=adventureRepository.findByIdAndDeletedFalse(res.getEntityId());

        Client client = clientRepository.findByEmail(authenticationService.getLoggedUser().getEmail());

        if(period == null) {
            throw new PeriodNoLongerAvailableException();
        }else if (adventure == null) {
            throw new EntityDeletedException();
        }else if(client.getPenaltyPoints() > 3) {
            throw new ImpossibleDueToPenaltyPoints();
        }
        else {
            AdventureReservation newAdventureReservation = new AdventureReservation(authenticationService.getLoggedUser(),
                    startDate,endDate, res.getNumberOfGuests(), res.getPrice(),adventure);

            if(!period.getStartDate().equals(startDate)) {
                FishingAvailablePeriod periodBefore = new FishingAvailablePeriod(period.getStartDate(),startDate,period.getFishingInstructor());
                availablePeriodsRepo.save(periodBefore);
            }
            if(!period.getEndDate().equals(endDate)) {
                FishingAvailablePeriod periodAfter = new FishingAvailablePeriod(period.getStartDate(),startDate,period.getFishingInstructor());
                availablePeriodsRepo.save(periodAfter);
            }

            availablePeriodsRepo.delete(period);
            newAdventureReservation.setAdditionalServices(addAdditionalServices(res.getAdditionalServices()));
            newAdventureReservation.setTotalPrice( res.getPrice(newAdventureReservation.getAdventure()) + accountAdditionalServices(newAdventureReservation.getAdditionalServices(),res));


            return adventureReservationRepository.save(newAdventureReservation);
        }
    }

    @Override
    public Set<AdditionalService> addAdditionalServices(List<AdditionalService> additionalServices) {
        Set<AdditionalService> services = new HashSet<AdditionalService>();
        for(AdditionalService id1 : additionalServices) {
            AdditionalService service = additinalServicesRepo.findById(id1.getId()).orElse(null);
            services.add(service);
        }
        return services;
    }

    @Override
    public double accountAdditionalServices(Set<AdditionalService> additinalServices, ReservationDto res) {
        double additinalServicesPrice = 0;
        for(AdditionalService s : additinalServices) {
            additinalServicesPrice += s.getPricePerDay() * res.getNumberOfDays();
            additinalServicesPrice += s.getPricePerHour() * res.getNumberOfHours();
        }
        return additinalServicesPrice;
    }

    @Override
    @Transactional(readOnly=false)
    public Reservation cancelReservation(long resId) {

        AdventureReservation res=adventureReservationRepository.findById(resId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor fishingInstructor1 = fishingInstructorRepository.findById(user.getId()).get();

        FishingAvailablePeriod periodBefore = availablePeriodsRepo.checkIfPeriodHasEndDate(res.getStartDate(),res.getAdventure().getId());
        FishingAvailablePeriod periodAfter = availablePeriodsRepo.checkIfPeriodHasStartDate(res.getEndDate(),res.getAdventure().getId());
        FishingAvailablePeriod periodToAdd;

        if(periodBefore!=null && periodAfter!=null) {
            availablePeriodsRepo.deleteById(periodBefore.getId());
            availablePeriodsRepo.deleteById(periodAfter.getId());
            periodToAdd = new FishingAvailablePeriod(periodBefore.getStartDate(),periodAfter.getEndDate(),fishingInstructor1);
        }
        else if (periodBefore==null && periodAfter!=null) {
            availablePeriodsRepo.deleteById(periodAfter.getId());
            periodToAdd = new FishingAvailablePeriod(res.getStartDate(),periodAfter.getEndDate(),fishingInstructor1);
        }
        else if (periodBefore!=null && periodAfter==null) {
            availablePeriodsRepo.deleteById(periodBefore.getId());
            periodToAdd = new FishingAvailablePeriod(periodBefore.getStartDate(),res.getEndDate(),fishingInstructor1);
        }
        else
            periodToAdd = new FishingAvailablePeriod(res.getStartDate(),res.getEndDate(),fishingInstructor1);


        availablePeriodsRepo.save(periodToAdd);
        AdventureReservation a=adventureReservationRepository.findById(resId);
        a.setStatus(ReservationStatus.CANCELLED);
        adventureReservationRepository.save(a);
        return null;
    }
    public boolean isThereAReservationAfterToday(Adventure adventure) {
        List<AbstractReservation> allReservations = collectingAdventureReservationsService.getAllNotCancelledReservationsByAdventure(adventure);
        LocalDateTime today = LocalDateTime.now();
        for (AbstractReservation ar : allReservations){
            if ((DateCoverter.convertToLocalDateTimeViaInstant(ar.getStartDate()).isAfter((today))
                    || (DateCoverter.convertToLocalDateTimeViaInstant(ar.getStartDate()).isBefore(today) && DateCoverter.convertToLocalDateTimeViaInstant(ar.getEndDate()).isAfter(today))
            ) && (ar.getStatus()== ReservationStatus.RESERVED || ar.getStatus() == ReservationStatus.ACTIVE)){
                return true;
            }
        }
        return false;
    }

}

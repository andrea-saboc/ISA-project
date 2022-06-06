package com.example.isa.service.impl.reservations;


import com.example.isa.dto.ReservationCalendarDTO;
import com.example.isa.dto.ReservationDto;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import com.example.isa.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class CollectingAdventureReservationsServiceImpl {
    @Autowired
    AdventureReservationRepository adventureReservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FishingInstructorRepository fishingInstructorRepository;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AdventureRepository adventureRepository;
    @Autowired
    AdventureDiscountReservationRepository adventureDiscountReservationRepository;



    public List<AdventureReservation> getAllAdventureReservation() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AdventureReservation> adventureReservations = adventureReservationRepository.findAllByUser(user);
        return adventureReservations;
    }
    public List<AdventureReservation> getOwnerReservation() {
        FishingInstructor fishingInstructor = fishingInstructorRepository.findById(authenticationService.getLoggedUser().getId()).get();
        List<Adventure> adventures = adventureRepository.findAllByFishingInstructorAndDeletedFalse(fishingInstructor);

        List<AdventureReservation> adventureReservation= new ArrayList<>();
        for ( Adventure adventure: adventures) {
            adventureReservation.addAll(adventureReservationRepository.findAllByAdventure(adventure));
        }
        return adventureReservation;
    }
    public Collection<? extends AbstractReservation> getAllNotCancelledReservationsByAdventureAndClient(Client client, Long id) {
        List<AbstractReservation> allReservations = new ArrayList<>(adventureReservationRepository.findAllByUserAndStatusNotAndAdventure(client, ReservationStatus.CANCELLED, adventureRepository.findById(id).get()));
        allReservations.addAll(adventureDiscountReservationRepository.findAllByUserAndStatusNotAndAdventure(client, ReservationStatus.CANCELLED, adventureRepository.findById(id).get()));
        System.out.println("Reservation by dventure and client "+ allReservations.size());
        return allReservations;
    }
    public List<ReservationCalendarDTO> getAllAdventureForCalendar()
    {
        List<AdventureReservation> adventureReservation=getOwnerReservation();
        List<ReservationCalendarDTO> reservationCalendarDTO=  new ArrayList<>();

        for(AdventureReservation a : adventureReservation)
        {
            String title="Avantura:"+a.getAdventure().getName();
            String description="Pocetak: "+a.getStartDate()+"\n kraj je "+a.getEndDate()+"\n korisnik koji je rezervisao: "+a.getUser().getName()+ " "+a.getUser().getSurname();
            ReservationCalendarDTO r= new ReservationCalendarDTO(a.getStartDate(),a.getEndDate(),title,description);
            reservationCalendarDTO.add(r);
        }
        return reservationCalendarDTO;

    }


    public boolean overlapsWithActiveReservations(Date startDate, Date endDate, Adventure boat) {
        List<AbstractReservation> allReservations = getAllNotCancelledReservationsByAdventure(boat);
        for (AbstractReservation ar : allReservations){
            if (ar.getStartDate().before(endDate) && startDate.before(ar.getEndDate())){
                return true;
            }
        }
        return false;
    }
    public List<AbstractReservation> getAllNotCancelledReservationsByAdventure(Adventure adventure){
        List<AbstractReservation> allAdventureReservations = new ArrayList<>(adventureReservationRepository.findAllByAdventureAndStatusNot(adventure, ReservationStatus.CANCELLED));
        allAdventureReservations.addAll(adventureDiscountReservationRepository.findAllByAdventureAndStatus(adventure,ReservationStatus.CANCELLED));
        return allAdventureReservations;
    }


}

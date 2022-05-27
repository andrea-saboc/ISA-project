package com.example.isa.service.impl.reservations;


import com.example.isa.model.*;
import com.example.isa.model.reservations.AdventureReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;
import com.example.isa.repository.AdventureRepository;
import com.example.isa.repository.AdventureReservationRepository;
import com.example.isa.repository.FishingInstructorRepository;
import com.example.isa.repository.UserRepository;
import com.example.isa.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

}

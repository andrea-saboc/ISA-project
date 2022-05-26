package com.example.isa.service.impl.reservations;


import com.example.isa.model.Boat;
import com.example.isa.model.User;
import com.example.isa.model.reservations.AdventureReservation;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.repository.AdventureReservationRepository;
import com.example.isa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectingAdventureReservationsServiceImpl {
    @Autowired
    AdventureReservationRepository adventureReservationRepository;
    @Autowired
    UserRepository userRepository;



    public List<AdventureReservation> getAllAdventureReservation() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AdventureReservation> adventureReservations = adventureReservationRepository.findAllByUser(user);
        return adventureReservations;
    }

}

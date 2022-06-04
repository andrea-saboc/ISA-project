package com.example.isa.service.impl;

import com.example.isa.model.LoyaltyProgram;
import com.example.isa.model.RecordIncome;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.Reservation;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordIncomeService {
    @Autowired
    RecordIncomeRepository recordIncomeRepository;
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    BoatReservationRepository boatReservationRepository;
    @Autowired
    MansionReservationRepository mansionReservationRepository;
    @Autowired
    AdventureReservationRepository adventureReservationRepository;

    public void saveBoatRecord(long id)
    {
        Reservation boatReservation=boatReservationRepository.findById(id);
        LoyaltyProgram loyaltyProgram=loyaltyProgramRepository.findById(1L).get();
        RecordIncome recordIncome= new RecordIncome();
        recordIncome.setProfit((int) ((boatReservation.getTotalPrice()*loyaltyProgram.getPercentage())/100));
        recordIncome.setReservation_id(boatReservation.getId());
        recordIncomeRepository.save(recordIncome);
    }
    public void saveAdventureRecord(long id)
    {
        Reservation boatReservation=adventureReservationRepository.findById(id);
        LoyaltyProgram loyaltyProgram=loyaltyProgramRepository.findById(1L).get();
        RecordIncome recordIncome= new RecordIncome();
        recordIncome.setProfit((int) ((boatReservation.getTotalPrice()*loyaltyProgram.getPercentage())/100));
        recordIncome.setReservation_id(boatReservation.getId());
        recordIncomeRepository.save(recordIncome);
    }
    public void saveMansionRecord(long id)
    {
        Reservation boatReservation=mansionReservationRepository.findById(id);
        LoyaltyProgram loyaltyProgram=loyaltyProgramRepository.findById(1L).get();
        RecordIncome recordIncome= new RecordIncome();
        recordIncome.setProfit((int) ((boatReservation.getTotalPrice()*loyaltyProgram.getPercentage())/100));
        recordIncome.setReservation_id(boatReservation.getId());
        recordIncomeRepository.save(recordIncome);
    }
}

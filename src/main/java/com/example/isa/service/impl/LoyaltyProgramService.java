package com.example.isa.service.impl;

import com.example.isa.model.LoyaltyProgram;
import com.example.isa.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoyaltyProgramService {
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgram update(LoyaltyProgram loyaltyProgram) {
        LoyaltyProgram loyaltyProgram1=loyaltyProgramRepository.findById(loyaltyProgram.getId()).get();
        loyaltyProgram1.setClient_reservation_score(loyaltyProgram.getClient_reservation_score());
        loyaltyProgram1.setClient_discount_gold(loyaltyProgram.getClient_discount_gold());
        loyaltyProgram1.setClient_discount_silver(loyaltyProgram.getClient_discount_silver());
        loyaltyProgram1.setGold_points_min(loyaltyProgram.getGold_points_min());
        loyaltyProgram1.setPercentage(loyaltyProgram.getPercentage());
        loyaltyProgram1.setOwner_reservation_score(loyaltyProgram.getOwner_reservation_score());
        loyaltyProgram1.setSilver_points_min(loyaltyProgram.getSilver_points_min());
        return loyaltyProgramRepository.save(loyaltyProgram1);
    }

    public LoyaltyProgram getOne()
    {
       LoyaltyProgram loyaltyProgram=loyaltyProgramRepository.findById(1L).get();

        return loyaltyProgram;
    }
}

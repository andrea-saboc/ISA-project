package com.example.isa.service.impl;

import com.example.isa.dto.ChangeBoatDto;
import com.example.isa.model.Adventure;
import com.example.isa.model.Boat;
import com.example.isa.model.reservations.AdditionalService;
import com.example.isa.repository.AdditionalServiceRepository;
import com.example.isa.repository.AdventureRepository;
import com.example.isa.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceService {
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    public AdditionalServiceService(BoatRepository br, AdditionalServiceRepository asr,AdventureRepository adventureRepository){
        boatRepository = br;
        additionalServiceRepository = asr;
        this.adventureRepository=adventureRepository;
    }

    public List<AdditionalService> getByBoatid(Long id) {
        Boat boat = boatRepository.findById(id).get();
        List<AdditionalService> additionalServices = additionalServiceRepository.findAllByBoat(boat);
        return additionalServices;
    }

    public List<AdditionalService> getByAdventureid(Long id) {
        Adventure adventure=adventureRepository.findById(id).get();
        List<AdditionalService> additionalServices = additionalServiceRepository.findAllByAdventure(adventure);
        return additionalServices;
    }

    public void changeAdditionalServices(ChangeBoatDto dto) {
        Boat boat = boatRepository.findById(dto.id).get();
        for(AdditionalService ad : dto.additionalServices){
            if(ad.getId()==-1){
                AdditionalService asNew = new AdditionalService(ad.getName(), ad.getPricePerDay(), ad.getPricePerDay(), boat);
                additionalServiceRepository.save(asNew);
            }
        }
        for (Long id : dto.deleteAdditionalServices){
            AdditionalService asToDlt = additionalServiceRepository.findById(id).get();
            asToDlt.setBoat(null);
            additionalServiceRepository.save(asToDlt);
        }
    }
}

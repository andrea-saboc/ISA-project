package com.example.isa.service.impl;

import com.example.isa.dto.ChangeBoatDto;
import com.example.isa.model.Boat;
import com.example.isa.model.EntityImage;
import com.example.isa.model.FishingEquipment;
import com.example.isa.repository.FishingEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class FishingEquipmentService {
    @Autowired
    FishingEquipmentRepository fishingEquipmentRepository;

    public List<FishingEquipment> removeAndAddNewFishingEquipments(ChangeBoatDto dto, Boat boatToChange) {
        List<FishingEquipment> fishingEquipments = new ArrayList<>();
        fishingEquipments.addAll(createFishingEquipments(dto.fishingEquipments));
        for(Integer id : dto.deleteFishingEquipment){
            fishingEquipments.remove(fishingEquipmentRepository.findById(id).get());
        }
        return fishingEquipments;
    }

    private List<FishingEquipment> createFishingEquipments(Set<FishingEquipment> fishingEquipments) {
        List<FishingEquipment> fishingEquipmentsToAdd = new ArrayList<>();
        for (FishingEquipment fe : fishingEquipments){
            if(fe.getId() == -1 || fe.getId()==null){
                FishingEquipment fe1 = fishingEquipmentRepository.save(new FishingEquipment(fe.getEquipment()));
                fishingEquipmentsToAdd.add(fe1);
            } else{
                fishingEquipmentsToAdd.add(fe);
            }
    }
        return fishingEquipmentsToAdd;
}
}

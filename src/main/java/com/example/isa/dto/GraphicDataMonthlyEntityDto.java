package com.example.isa.dto;

public class GraphicDataMonthlyEntityDto {
    public Long id;
    public GraphicDataMonthly entityData;
    public String name;

    public GraphicDataMonthlyEntityDto(Long id, String name){
        this.id = id;
        this.name  = name;
        entityData = new GraphicDataMonthly();
    }
}

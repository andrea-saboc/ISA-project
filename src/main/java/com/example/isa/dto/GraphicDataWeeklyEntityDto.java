package com.example.isa.dto;

public class GraphicDataWeeklyEntityDto {
    public GraphicDataWeekly entityData;
    public Long id;
    public String name;

    public GraphicDataWeeklyEntityDto(Long id, String name){
        this.id = id;
        this.name  = name;
        entityData = new GraphicDataWeekly();
    }


}

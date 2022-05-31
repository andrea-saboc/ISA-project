package com.example.isa.dto;

public class GraphicDataYearlyEntityDto {
    public Long id;
    public GraphicDataYearly entityData;
    public String name;

    public GraphicDataYearlyEntityDto(){}

    public GraphicDataYearlyEntityDto(Long id, String name){
        this.id = id;
        this.name  = name;
        entityData = new GraphicDataYearly();
    }

    @Override
    public String toString() {
        return "GraphicDataYearlyEntityDto{" +
                "id=" + id +
                ", entitysData=" + entityData +
                '}';
    }
}

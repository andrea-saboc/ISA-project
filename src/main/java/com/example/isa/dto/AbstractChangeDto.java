package com.example.isa.dto;

import java.util.Set;

public abstract class AbstractChangeDto {
    public abstract Set<String> getExteriorImages();
    public abstract Set<String> getInteriorImages();
    public abstract Set<Integer> getExteriorImagesToDelete();
    public abstract Set<Integer> getInteriorImagesToDelete();
    public abstract Set<String> getImages();
    public abstract Set<Integer> getImagesToDelete();

}

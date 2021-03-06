package com.example.isa.model;

import com.example.isa.enums.EntityType;

import java.util.Set;

public abstract class AbstractEntity {
    public abstract Set<EntityImage> getExteriorImages();
    public abstract Set<EntityImage> getInteriorImages();
    public abstract User getOwner();
    public abstract String getName();
    public abstract Long getId();
    public abstract EntityType getEntityType();
    public abstract Set<EntityImage> getImages();
}

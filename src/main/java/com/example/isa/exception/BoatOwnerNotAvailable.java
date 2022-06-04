package com.example.isa.exception;

public class BoatOwnerNotAvailable extends Exception{
    public BoatOwnerNotAvailable() {
        super("Boat owner is not available, because of another reservation!");
    }
}

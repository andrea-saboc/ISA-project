package com.example.isa.exception;

public class CancelledReservationException extends Exception {
	
	public  CancelledReservationException() {super("You are not allowed to make this reservation again");}
}

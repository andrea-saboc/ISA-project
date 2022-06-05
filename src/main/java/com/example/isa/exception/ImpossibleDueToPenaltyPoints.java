package com.example.isa.exception;

public class ImpossibleDueToPenaltyPoints extends Exception{
	
	public ImpossibleDueToPenaltyPoints() {
		super("Not allowed because of your penalty points");
	}
}

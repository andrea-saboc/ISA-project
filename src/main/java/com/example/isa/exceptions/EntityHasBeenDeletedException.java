package com.example.isa.exceptions;

public class EntityHasBeenDeletedException extends Exception{

	public EntityHasBeenDeletedException() {
		super("Sorry.Entity has been deleted!");
	}
}

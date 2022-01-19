package com.example.isa.exception;

public class EntityHasBeenDeletedException extends Exception{

	public EntityHasBeenDeletedException() {
		super("Sorry.Entity has been deleted!");
	}
}

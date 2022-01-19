package com.example.isa.exception;

public class EntityDeletedException extends Exception{

	public EntityDeletedException() {
		super("Sorry.Entity has been deleted!");
	}
}

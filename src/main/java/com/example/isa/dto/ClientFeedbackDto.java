package com.example.isa.dto;

public class ClientFeedbackDto {

	private String content;
	private int grade;
	private long reservation;
	
	
	public ClientFeedbackDto(String content, int grade, long reservation) {
		super();
		this.content = content;
		this.grade = grade;
		this.reservation = reservation;
	}


	public ClientFeedbackDto() {
		super();
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public long getReservation() {
		return reservation;
	}


	public void setReservation(long reservation) {
		this.reservation = reservation;
	}
	
	
}

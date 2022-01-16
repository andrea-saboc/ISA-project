package com.example.isa.dto;

public class ClientComplaintDto {
	
	private String content;
	private long entityId;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public ClientComplaintDto(String content, long entityId) {
		super();
		this.content = content;
		this.entityId = entityId;
	}
	public ClientComplaintDto() {
		super();
	}
	
	

}

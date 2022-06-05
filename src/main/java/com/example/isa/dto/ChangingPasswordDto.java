package com.example.isa.dto;

public class ChangingPasswordDto {
	
	private String newPassword;
	private String oldPassword;
	public ChangingPasswordDto(String newPassword, String oldPassword) {
		super();
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}
	public ChangingPasswordDto() {}
	
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	};
	
	
	
	
	

}

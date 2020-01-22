package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

public class ResetPasswordDto 
{
	@NotBlank
	private String emailId;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	
	public String getEmailId()
	{
		return emailId;
	}
	
	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}
}

package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

public class ForgotPasswordDto
{
	@NotBlank
	private String emailId;

	public String getEmailId() 
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}	
}

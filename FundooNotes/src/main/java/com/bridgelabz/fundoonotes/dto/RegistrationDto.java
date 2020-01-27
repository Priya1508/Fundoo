package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotBlank;

public class RegistrationDto
{
	@NotBlank
	private String firstName;
	private String lastName;
	@NotBlank
	private String emailId;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	
	public String getFirstName() 
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName() 
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
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

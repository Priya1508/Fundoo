package com.bridgelabz.fundoonotes.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User 
{
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
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
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getEmailId()
	{
		return emailId;
	}
	
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() 
	{
		return "FundooModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", emailId=" + emailId + ", confirmPassword=" + confirmPassword + "]";
	}	
}

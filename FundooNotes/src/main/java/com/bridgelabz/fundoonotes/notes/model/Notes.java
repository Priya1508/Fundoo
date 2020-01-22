package com.bridgelabz.fundoonotes.notes.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Notes 
{
	@Id
	private String id;
	private String title;
	private String description;
	private String emailId;
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getEmailId() 
	{
		return emailId;
	}

	public void setEmailId(String emailId) 
	{
		this.emailId = emailId;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}

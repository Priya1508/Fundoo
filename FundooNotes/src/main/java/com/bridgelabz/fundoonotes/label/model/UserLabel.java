package com.bridgelabz.fundoonotes.label.model;

import org.springframework.data.annotation.Id;

public class UserLabel 
{
	@Id
	private String id;
	private String userId;
	private String labelName;
	
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	
	public String getUserId() 
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getLabelName()
	{
		return labelName;
	}

	public void setLabelName(String labelName) 
	{
		this.labelName = labelName;
	}
}

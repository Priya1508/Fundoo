package com.bridgelabz.fundoonotes.label.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class NoteLabel 
{
	@Id
	private String labelId;
	@NotBlank
	private String labelName;
	
	
	public String getLabelId()
	{
		return labelId;
	}
	
	public void setLabelId(String labelId) 
	{
		this.labelId = labelId;
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

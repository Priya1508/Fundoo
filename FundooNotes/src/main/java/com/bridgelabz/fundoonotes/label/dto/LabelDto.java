package com.bridgelabz.fundoonotes.label.dto;

import javax.validation.constraints.NotBlank;

public class LabelDto 
{
	@NotBlank
	private String labelName;

	public String getLabelName()
	{
		return labelName;
	}

	public void setLabelName(String labelName) 
	{
		this.labelName = labelName;
	}
}

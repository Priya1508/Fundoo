package com.bridgelabz.fundoonotes.notes.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Notes 
{
	@Id
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private String emailId;
	private boolean pin;
	private boolean archive;
	private boolean trash;
	private String remainder;
	
	@CreatedDate
	private LocalDate localDate;
	
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

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean isPin() 
	{
		return pin;
	}

	public void setPin(boolean pin) 
	{
		this.pin = pin;
	}

	public boolean isArchive() 
	{
		return archive;
	}

	public void setArchive(boolean archive) 
	{
		this.archive = archive;
	}

	public boolean isTrash() 
	{
		return trash;
	}

	public void setTrash(boolean trash) 
	{
		this.trash = trash;
	}

	public LocalDate getLocalDate() 
	{
		return localDate;
	}

	public void setLocalDate(LocalDate localDate)
	{
		this.localDate = localDate;
	}

	
	public String getRemainder()
	{
		return remainder;
	}

	public void setRemainder(String remainder)
	{
		this.remainder = remainder;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
}

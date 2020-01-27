package com.bridgelabz.fundoonotes.label.model;

import org.springframework.data.annotation.Id;

public class NoteLabel 
{
	@Id
	private String id;
	private String labelId;
	private String noteId;
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getLabelId()
	{
		return labelId;
	}
	
	public void setLabelId(String labelId) 
	{
		this.labelId = labelId;
	}
	
	public String getNoteId() 
	{
		return noteId;
	}
	
	public void setNoteId(String noteId)
	{
		this.noteId = noteId;
	}
}

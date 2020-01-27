package com.bridgelabz.fundoonotes.notes.service;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.response.LabelResponse;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;

public interface LabelService
{
	public LabelResponse createLabel(LabelDto labelDto, String token);
	
	public LabelResponse readLabel(String id);
	
	public LabelResponse updateLabel(LabelDto labelDto, String id);
	
	public LabelResponse deleteLabel(LabelDto labelDto, String id);
	
	public LabelResponse createNoteLabel(NoteDto noteDto, String noteId);
}

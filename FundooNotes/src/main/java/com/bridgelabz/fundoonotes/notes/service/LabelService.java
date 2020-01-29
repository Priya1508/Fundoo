package com.bridgelabz.fundoonotes.notes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.model.NoteLabel;
import com.bridgelabz.fundoonotes.response.Response;

public interface LabelService
{
	public Response createLabel(LabelDto labelDto, String token);
	
	public Response readLabel(String labelId);
	
	public Response updateLabel(LabelDto labelDto, String labelId, String token);
	
	public Response deleteLabel(LabelDto labelDto, String labelId, String token);
	
	public List<NoteLabel> getAll();
}

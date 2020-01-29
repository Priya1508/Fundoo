package com.bridgelabz.fundoonotes.notes.service;

import java.util.List;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.response.Response;

public interface ServiceNote 
{
	public Response createNote(NoteDto noteDto, String token);
	
	public Response readNote(String id);
	
	public Response updateNote(NoteDto noteDto,String token,String id);
	
	public Response deleteNote(String id);
	
	public Response pinNote(String token, String id);
	
	public Response archiveNote(String token, String id);
	
	public Response trashNote(String token, String id);
	
	public List<Notes> getAll();
	
	public List<Notes> sortByName();
	
	public List<Notes> sortByDate();
	
	public Response addRemainder(String noteId, String token, String date);
	
	public Response editRemainder(String noteId, String token, String date);
	
	public Response deleteRemainder(String noteId, String date);
}

package com.bridgelabz.fundoonotes.notes.service;

import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.response.NotesResponse;

public interface ServiceNote 
{
	public NotesResponse createNote(NoteDto noteDto, String token);
	
	public NotesResponse readNote(String token);
	
	public NotesResponse updateNote(NoteDto noteDto,String token,String id);
	
	public NotesResponse deleteNote(String id);
}

package com.bridgelabz.fundoonotes.notes.service;

import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.response.NotesResponse;

public interface ServiceNote 
{
	public NotesResponse createNote(NoteDto noteDto, String token);
	
	public NotesResponse readNote(String id);
	
	public NotesResponse updateNote(NoteDto noteDto,String token,String id);
	
	public NotesResponse deleteNote(String id);
	
	public NotesResponse pinNote(String token, String id);
	
	public NotesResponse archiveNote(String token, String id);
	
	public NotesResponse trashNote(String token, String id);
}

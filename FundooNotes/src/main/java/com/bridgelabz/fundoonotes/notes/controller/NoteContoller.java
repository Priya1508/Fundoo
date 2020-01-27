package com.bridgelabz.fundoonotes.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.response.NotesResponse;
import com.bridgelabz.fundoonotes.notes.service.ServiceNote;

@RestController
@RequestMapping("/notes")
public class NoteContoller 
{
	@Autowired
	private ServiceNote service;
	
	@PostMapping("/create")
	public ResponseEntity<NotesResponse> createNote(@RequestBody NoteDto noteDto,@RequestParam String token)
	{
		return new ResponseEntity<NotesResponse>(service.createNote(noteDto, token), HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<NotesResponse> readNote(@RequestParam String id)
	{
		return new ResponseEntity<NotesResponse>(service.readNote(id), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<NotesResponse> updateNote(@RequestBody NoteDto noteDto,@RequestParam String token,@RequestParam String id)
	{
		return new ResponseEntity<NotesResponse>(service.updateNote(noteDto, token,id), HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<NotesResponse> deleteNote(@RequestParam String id)
	{
		return new ResponseEntity<NotesResponse>(service.deleteNote(id), HttpStatus.OK);
	}
	
	@PostMapping("/pin")
	public ResponseEntity<NotesResponse> pinNote(@RequestParam String id, @RequestParam String token)
	{
		return new ResponseEntity<NotesResponse>(service.pinNote(token, id), HttpStatus.OK);
	}
	
	@PostMapping("/archive")
	public ResponseEntity<NotesResponse> archiveNote(@RequestParam String id, @RequestParam String token)
	{
		return new ResponseEntity<NotesResponse>(service.archiveNote(token, id), HttpStatus.OK);
	}
	
	@PostMapping("/trash")
	public ResponseEntity<NotesResponse> trashNote(@RequestParam String token, @RequestParam String id)
	{
		return new ResponseEntity<NotesResponse>(service.trashNote(token, id), HttpStatus.OK);
	}
}

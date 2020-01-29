package com.bridgelabz.fundoonotes.notes.controller;

import java.util.List;

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
import com.bridgelabz.fundoonotes.notes.service.ServiceNote;
import com.bridgelabz.fundoonotes.response.Response;

@RestController
@RequestMapping("/notes")
public class NoteContoller 
{
	@Autowired
	private ServiceNote service;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.createNote(noteDto, token), HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Response> readNote(@RequestParam String id)
	{
		return new ResponseEntity<Response>(service.readNote(id), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Response> updateNote(@RequestBody NoteDto noteDto, @RequestParam String token,@RequestParam String id)
	{
		return new ResponseEntity<Response>(service.updateNote(noteDto, token,id), HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Response> deleteNote(@RequestParam String id)
	{
		return new ResponseEntity<Response>(service.deleteNote(id), HttpStatus.OK);
	}
	
	@PostMapping("/pin")
	public ResponseEntity<Response> pinNote(@RequestParam String id, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.pinNote(token, id), HttpStatus.OK);
	}
	
	@PostMapping("/archive")
	public ResponseEntity<Response> archiveNote(@RequestParam String id, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.archiveNote(token, id), HttpStatus.OK);
	}
	
	@PostMapping("/trash")
	public ResponseEntity<Response> trashNote(@RequestParam String token, @RequestParam String id)
	{
		return new ResponseEntity<Response>(service.trashNote(token, id), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List> getAll()
	{
		return new ResponseEntity<List> (service.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/byname")
	public ResponseEntity<List> sortByName()
	{
		return new ResponseEntity<List>(service.sortByName(), HttpStatus.OK);
	}
	
	@GetMapping("bydate")
	public ResponseEntity<List> sortByDate()
	{
		return new ResponseEntity<List>(service.sortByDate(), HttpStatus.OK);
	}
	
	@PostMapping("/addremainder")
	public ResponseEntity<Response> addRemainder(@RequestParam String noteId, @RequestParam String token, @RequestParam String date)
	{
		return new ResponseEntity<Response>(service.addRemainder(noteId, token, date), HttpStatus.OK);
	}
	
	@PostMapping("/editremainder")
	public ResponseEntity<Response> editRemainder(@RequestParam String token, @RequestParam String noteId, @RequestParam String date)
	{
		return new ResponseEntity<Response>(service.editRemainder(noteId, token,  date), HttpStatus.OK);
	}
	
	@PostMapping("/deleteremainder")
	public ResponseEntity<Response> deleteRemainder(@RequestParam String noteId, @RequestParam String date)
	{
		return new ResponseEntity<Response>(service.deleteRemainder(noteId, date), HttpStatus.OK);
	}
}

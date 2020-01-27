package com.bridgelabz.fundoonotes.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.label.response.LabelResponse;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.service.LabelService;

@RestController
@RequestMapping("/noteLabel")
public class NoteController
{
	@Autowired
	private LabelService service;
	
	@PostMapping("/create")
	public ResponseEntity<LabelResponse> createNoteLabel(@RequestBody NoteDto noteDto, @RequestParam String noteId)
	{
		return new ResponseEntity<LabelResponse>(service.createNoteLabel(noteDto, noteId), HttpStatus.OK);
	}
	
}

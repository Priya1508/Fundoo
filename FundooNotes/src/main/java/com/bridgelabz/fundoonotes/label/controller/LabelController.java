package com.bridgelabz.fundoonotes.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.response.LabelResponse;
import com.bridgelabz.fundoonotes.notes.service.LabelService;

@RestController
@RequestMapping("/label")
public class LabelController
{
	@Autowired
	private LabelService service;
	
	@PostMapping("/create")
	public ResponseEntity<LabelResponse> createLabel(@RequestBody LabelDto labelDto, @RequestParam String token)
	{
		return new ResponseEntity<LabelResponse>(service.createLabel(labelDto, token), HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<LabelResponse> readLabel(@RequestParam String id)
	{
		return new ResponseEntity<LabelResponse>(service.readLabel(id), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<LabelResponse> updateLabel(@RequestBody LabelDto labelDto, @RequestParam String id)
	{
		return new ResponseEntity<LabelResponse>(service.updateLabel(labelDto, id), HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<LabelResponse> deleteLabel(@RequestBody LabelDto labelDto, @RequestParam String id)
	{
		return new ResponseEntity<LabelResponse>(service.deleteLabel(labelDto, id), HttpStatus.OK);
	}
}

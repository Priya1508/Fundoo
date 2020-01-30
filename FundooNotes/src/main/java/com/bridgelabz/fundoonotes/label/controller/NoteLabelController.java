package com.bridgelabz.fundoonotes.label.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.notes.service.LabelService;
import com.bridgelabz.fundoonotes.response.Response;

@RestController
@RequestMapping("fundoo/noteLabel")
public class NoteLabelController
{
	@Autowired
	private LabelService service;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createLabel(@Valid @RequestBody LabelDto labelDto, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.createLabel(labelDto, token), HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Response> readLabel(@Valid @RequestParam String labelId)
	{
		return new ResponseEntity<Response>(service.readLabel(labelId),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateLabel(@Valid @RequestBody LabelDto labelDto, @RequestParam String labelId, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.updateLabel(labelDto, labelId, token), HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Response> deleteLabel(@RequestParam String labelId, @RequestParam String token)
	{
		return new ResponseEntity<Response>(service.deleteLabel(labelId, token), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List> getAll()
	{
		return new ResponseEntity<List> (service.getAll(),HttpStatus.OK);
	}
}

package com.bridgelabz.fundoonotes.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.ForgotPasswordDto;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.dto.ResetPasswordDto;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;

@RestController
@RequestMapping("/fundoo/user")
public class UserController
{
	@Autowired
	private UserService service;
	
	@PostMapping("/adduser")
	public ResponseEntity<Response> addUser(@Valid @RequestBody RegistrationDto registrationDto)
	{
		return new ResponseEntity<Response> (service.addUser(registrationDto),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto)
	{
		return new ResponseEntity<Response> (service.login(loginDto),HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotpassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto)
	{
		return new ResponseEntity<Response> (service.forgotPassword(forgotPasswordDto),HttpStatus.OK);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<Response> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto)
	{
		return new ResponseEntity<Response> (service.resetPassword(resetPasswordDto),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List> getAll()
	{
		return new ResponseEntity<List> (service.getAll(),HttpStatus.OK);
	}
	
	@PostMapping("/verify")
	public ResponseEntity<Response> isVerify(@RequestParam String token)
	{
		return new ResponseEntity<Response>(service.isVerified(token), HttpStatus.OK);
	}
}

package com.bridgelabz.fundoonotes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService service;
	
	@PostMapping("/adduser")
	public ResponseEntity<Response> addUser(@RequestBody RegistrationDto registrationDto)
	{
		return new ResponseEntity<Response> (service.addUser(registrationDto),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto,@RequestParam String token)
	{
		return new ResponseEntity<Response> (service.login(loginDto, token),HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotpassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto)
	{
		return new ResponseEntity<Response> (service.forgotPassword(forgotPasswordDto),HttpStatus.OK);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<Response> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto,String token)
	{
		return new ResponseEntity<Response> (service.resetPassword(resetPasswordDto, token),HttpStatus.OK);
	}
}

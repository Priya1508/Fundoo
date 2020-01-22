package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.ForgotPasswordDto;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.dto.ResetPasswordDto;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.response.Response;

public interface UserService 
{
	public Response addUser(RegistrationDto registrationDto);
	
	public Response login(LoginDto loginDto,String token);
	
	public Response forgotPassword(ForgotPasswordDto forgotPasswordDto);
	
	public Response resetPassword(ResetPasswordDto resetPasswordDto,String token);
	
	public List<User> getAll();
}

package com.bridgelabz.fundoonotes.exceptionhandler;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoonotes.response.Response;

@Component
@RestControllerAdvice
@PropertySource("classpath:message.properties")
public class GlobleExceptionHandler 
{	
	@ExceptionHandler(CustomException.InvalidToken.class)
	public Response invalidToken(CustomException.InvalidToken e)
	{
		return new Response(400, e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.InvalidId.class)
	public Response invalidNoteId(CustomException.InvalidId e)
	{
		return new Response(400, e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.InvalidLabelId.class)
	public Response invalidLabelId(CustomException.InvalidLabelId e)
	{
		return new Response(400, e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}

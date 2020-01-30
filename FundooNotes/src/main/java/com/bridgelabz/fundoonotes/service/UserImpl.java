package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.ForgotPasswordDto;
import com.bridgelabz.fundoonotes.dto.LoginDto;
import com.bridgelabz.fundoonotes.dto.RegistrationDto;
import com.bridgelabz.fundoonotes.dto.ResetPasswordDto;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.utility.Jms;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
@PropertySource("classpath:message.properties")
public class UserImpl implements UserService
{
	@Autowired
	private UserRepository repository;

	@Autowired
	private Jwt jwt;

	@Autowired
	private Environment environment;

	@Autowired
	private Jms jms;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	ModelMapper mapper = new ModelMapper();

	/**
	 * @Purpose : To register the user
	 * @return : Given the response is successfully registered or not
	 */
	@Override
	public Response addUser(RegistrationDto registrationDto)
	{
		User user = mapper.map(registrationDto, User.class);
		System.out.println(user.getEmailId());
		User userExist = repository.findByEmailId(registrationDto.getEmailId());
		if (userExist != null)
		{
			return new Response(400, environment.getProperty("USER_PRESENT"), HttpStatus.BAD_REQUEST);
		}
		else
		{
			if (registrationDto.getPassword().equals(registrationDto.getConfirmPassword()))
			{
				String token = jwt.createToken(registrationDto.getEmailId());
				jms.sendMail(registrationDto.getEmailId(), token);
				bcryptPasswordEncoder.encode(registrationDto.getPassword());
				repository.save(user);
				return new Response(200, environment.getProperty("USER_CREATED"), HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("INVALID_PASSWORD"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * @Purpose: To login the user
	 * @return : Given the response is successfully logged in or not
	 */
	@Override
	public Response login(LoginDto loginDto)
	{
		User user = repository.findByEmailId(loginDto.getEmailId());
		if(user.isVerify() == true)
		{
			if(user.getPassword().equals(loginDto.getPassword()))
			{
				String token = jwt.createToken(loginDto.getEmailId());
				jms.sendMail(loginDto.getEmailId(), token);
				return new Response(200, environment.getProperty("LOGIN_SUCCESS"), HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("INVALID_PASSWORD"), HttpStatus.BAD_REQUEST);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Purpose : To reset the forgotten password
	 * @return : Given the response for forgot password is successful or not
	 */
	@Override
	public Response forgotPassword(ForgotPasswordDto forgotPasswordDto)
	{
		User user = mapper.map(forgotPasswordDto, User.class);
		User email = repository.findByEmailId(forgotPasswordDto.getEmailId());
		if (email != null)
		{
			String token = jwt.createToken(forgotPasswordDto.getEmailId());
			jms.sendMail(forgotPasswordDto.getEmailId(), token);
			return new Response(200, environment.getProperty("FORGOT_PASSWORD"), HttpStatus.OK);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Purpose : To reset the password
	 * @return : Given the response if the password is successfully reset or not
	 */
	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDto)
	{
		User user = repository.findByEmailId(resetPasswordDto.getEmailId());
		if (user != null)
		{
			if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword()))
			{
				repository.save(user);
				return new Response(200, environment.getProperty("RESET_SUCCESS"), HttpStatus.OK);
			}
			return new Response(400, environment.getProperty("INVALID_PASSWORD"), HttpStatus.BAD_REQUEST);
		}
		return new Response(400, environment.getProperty("INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
	}

	/**
	 *Purpose : To all the details from the database
	 *@return : Given the response if the the details are successfully printed or not
	 */
	@Override
	public List<User> getAll()
	{
		List<User>  user1 = repository.findAll();
		if(user1 != null)
		{
			System.out.println(user1);
			return user1;
		}
		return null;
	}
	
	public Response isVerified(String token)
	{
		String email = jwt.getUserToken(token);
		User user = repository.findByEmailId(email);
		System.out.println("user"+email);
		if(user != null)
		{
			user.setVerify(true);
			repository.save(user);
			return new Response(200, environment.getProperty("TOKEN_SUCCESS"), HttpStatus.OK);
		}
		else
			return new Response(400, environment.getProperty("TOKEN_ERROR"), HttpStatus.BAD_REQUEST);
	}
}

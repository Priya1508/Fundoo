package com.bridgelabz.fundoonotes.service;

import java.util.List;
import java.util.Optional;

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
			System.out.println(environment.getProperty("USER_PRESENT"));
		} 
		else 
		{
			if (registrationDto.getPassword().equals(registrationDto.getConfirmPassword()))
			{
				String token = jwt.createToken(registrationDto.getEmailId());
				jms.sendMail(registrationDto.getEmailId(), token);
				bcryptPasswordEncoder.encode(registrationDto.getPassword());
				repository.save(user);
				return new Response(environment.getProperty("SERVER_CODE_SUCCESS"), environment.getProperty("USER_CREATED"));
			}
		}
		return new Response(environment.getProperty("SERVER_CODE_ERROR"), environment.getProperty("INVALID_CREDENTIALS"));
	}

	/**
	 * @Purpose: To login the user
	 * @return : Given the response is successfully logged in or not
	 */
	@Override
	public Response login(LoginDto loginDto, String token)
	{
		String email = jwt.getUserToken(token);
		System.out.println("email " + email);
		User user = mapper.map(loginDto, User.class);
		User repo = repository.findByEmailId(email);
		if (repo == null) 
		{
			System.out.println("user not found");
		}
		else 
		{
			if (isVerify(email))
			{
				if (repo.getPassword().equals(loginDto.getPassword())) 
				{
					repository.save(user);
					return new Response(environment.getProperty("SERVER_CODE_SUCCESS"), environment.getProperty("LOGIN_SUCCESS"));
				}
			}
			return new Response(environment.getProperty("SERVER_CODE_ERROR"), environment.getProperty("TOKEN_ERROR"));
		}
		return new Response(environment.getProperty("SERVER_CODE_ERROR"), environment.getProperty("INVALID_CREDENTIALS"));
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
			return new Response(environment.getProperty("SERVER_CODE_SUCCESS"), environment.getProperty("FORGOT_PASSWORD"));
		}
		return new Response(environment.getProperty("SERVER_CODE_ERROR"), environment.getProperty("INVALID_CREDENTIALS"));
	}

	/**
	 * @Purpose : To reset the password
	 * @return : Given the response if the password is successfully reset or not
	 */
	@Override
	public Response resetPassword(ResetPasswordDto resetPasswordDto, String token) 
	{
		User user = mapper.map(resetPasswordDto, User.class);
		User email = repository.findByEmailId(resetPasswordDto.getEmailId());
		if (email != null)
		{
			if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword()))
			{
				repository.save(user);
				return new Response(environment.getProperty("SERVER_CODE_SUCCESS"), environment.getProperty("RESET_SUCCESS"));
			}
		}
		return new Response(environment.getProperty("SERVER_CODE_ERROR"), environment.getProperty("INVALID_CREDENTIALS"));
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
	
	/**
	 * @param token passed to verify the email
	 * @return return true if verify is not null
	 */
	public boolean isVerify(String token)
	{
		User verify = repository.findByEmailId(token);
		if(verify != null)
		{
			return true;
		}
		else
			return false;
	}
}

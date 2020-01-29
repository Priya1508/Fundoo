package com.bridgelabz.fundoonotes.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.bridgelabz.fundoonotes.exceptionhandler.CustomException;

@Component
public class Jwt 
{
	private static final String SECRET_KEY = "SECRET";

	/**
	 * @param emailId-EmailId of the given user
	 * @return Token
	 */
	public String createToken(String email)
	{
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

		return JWT.create().withClaim("email", email).sign(algorithm);
	}

	/**
	 * @param token-generated token
	 * @return EmailId of the user
	 * @throws Exception
	 */
	public String getUserToken(String token)
	{
		try 
		{
			Claim claim = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token).getClaim("email");
			System.out.println("claim"+claim);
			return claim.asString();
		} 
		catch (Exception e)
		{
			throw new CustomException.InvalidToken("invalid token");
		}	
	}
	
	public String checkById(String id) 
	{
		try 
		{
			Claim claim = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(id).getClaim("id");
			System.out.println("claim"+claim);
			return claim.asString();
		}
		catch (Exception e) 
		{
			throw new CustomException.InvalidId("invalid note id");
		}
	}
	
	public String checkByLabelId(String labelId)
	{
		try 
		{
			Claim claim = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(labelId).getClaim("label");
			System.out.println("claim"+claim);
			return claim.asString();
		}
		catch (Exception e) 
		{
			throw new CustomException.InvalidLabelId("invalid label id");
		}
	}
}

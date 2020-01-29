package com.bridgelabz.fundoonotes.exceptionhandler;

public class CustomException
{

	public static class InvalidLabelId extends RuntimeException
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public InvalidLabelId(String meassage)
		{
			super(meassage);
		}
	}

	public static class InvalidId extends RuntimeException 
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public InvalidId(String message) 
		{
			super(message);
		}
	}

	public static class InvalidToken extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidToken(String message)
		{
			super(message);
		}
	}
	
}

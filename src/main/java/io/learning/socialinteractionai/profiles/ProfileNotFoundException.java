package io.learning.socialinteractionai.profiles;

public class ProfileNotFoundException extends RuntimeException
{
	public ProfileNotFoundException(String message)
	{
		super(message);
	}
}


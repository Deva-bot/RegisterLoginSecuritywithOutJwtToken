package RegisterLoginSecurity.Exception;

public class UserNameAlreadyExistsException extends RuntimeException
{

	public UserNameAlreadyExistsException(String message) 
	{
		super(message);
	}
	
}

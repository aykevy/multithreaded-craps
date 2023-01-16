//Kevin Lam lamk10 15673860

/*This file is basically the exception class for balance limit. It is used to 
  send an error message when thrown.*/

public class BALANCELIMITEXCEPTION extends Exception
{
	public BALANCELIMITEXCEPTION(String message)
	{
		super(message);
	}
}
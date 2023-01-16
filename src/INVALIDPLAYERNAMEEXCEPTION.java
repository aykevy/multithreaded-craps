/*This file is basically the exception class for invalid player name. It is used to
  send an error message when thrown.*/

public class INVALIDPLAYERNAMEEXCEPTION extends Exception
{
	public INVALIDPLAYERNAMEEXCEPTION(String message)
	{
		super(message);
	}
}
/*This file is basically the exception class for negative bet. It is used to
  send an error message when thrown.*/

public class NEGATIVEBETEXCEPTION extends Exception
{
	public NEGATIVEBETEXCEPTION(String message)
	{
		super(message);
	}
}
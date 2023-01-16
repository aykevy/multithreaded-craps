/*This file is basically the exception class for unknown answer. It is used to
  send an error message when thrown.*/

public class UNKNOWNANSWEREXCEPTION extends Exception
{
	public UNKNOWNANSWEREXCEPTION(String message)
	{
		super(message);
	}
}
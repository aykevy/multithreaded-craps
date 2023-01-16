/*This file is basically the exception class for negative balance. It is used to
  send an error message when thrown.*/

public class NEGATIVEBALANCEEXCEPTION extends Exception
{
	public NEGATIVEBALANCEEXCEPTION(String message)
	{
		super(message);
	}
}
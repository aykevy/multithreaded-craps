//Kevin Lam lamk10 15673860
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/*This file is basically the main function where you start the program.*/

public class Play
{
	static private String csName;
	static private double csBalance;
	static private double csBet;
	static private PrintWriter consoleInfo;
	Scanner sc = new Scanner(System.in);
	
	static String testPlayerName(String name) throws INVALIDPLAYERNAMEEXCEPTION
	/*Exception Method: This is to check for player name.*/
	{
		if (name == null || name.trim().isEmpty())
		{
			throw new INVALIDPLAYERNAMEEXCEPTION("Invalid name! Please enter a name with characters.");
		}
		return name;
	}
	
	static double testBalance(double balance) throws NEGATIVEBALANCEEXCEPTION
	/*Exception Method: This is to check for negative bet.*/
	{
		if (balance <= 0)
		{
			throw new NEGATIVEBALANCEEXCEPTION("Invalid balance! Please enter a balance above $0.");
		}
		return balance;
	}
	
	static double testBet(double bet) throws NEGATIVEBETEXCEPTION, BALANCELIMITEXCEPTION
	/*Exception Method: This is to check for negative and valid bet range.*/
	{
		if (bet < 0)
		{
			throw new NEGATIVEBETEXCEPTION("Invalid bet! Please enter a bet between $1 and $1000.");
		}
		if (bet == 0 || bet < 1 || bet > 1000)
		{
			throw new BALANCELIMITEXCEPTION("Invalid bet! Please enter a bet between $1 and $1000.");
		}
		return bet;
	}
	
	static char testUnknownAnswer(char answer) throws UNKNOWNANSWEREXCEPTION
	/*Exception Method: This is to check for valid answer to play another bet or play another game.*/
	{
		if (!(answer == 'y' || answer == 'Y' || answer == 'n' || answer == 'N'))
		{
			throw new UNKNOWNANSWEREXCEPTION("Invalid answer! Please enter \'y\' or \'n\'.");
		}
		return answer;
	}
	
	static void initializeVariables()
	{
		Scanner sc = new Scanner(System.in);
		int nameCheck = 0;
		while (nameCheck == 0)
		{
			try 
			{
				System.out.print("Welcome to SimCraps! Enter your user name: ");
				String name = testPlayerName(sc.nextLine());
				consoleInfo.write("Welcome to SimCraps! Enter your user name: " + name + "\n");
				csName = name;
				nameCheck = 1;
				
			}
			catch (INVALIDPLAYERNAMEEXCEPTION e)
			{
				System.out.print(e + "\n");
			}
		}
		
		int balanceCheck = 0;
		while (balanceCheck == 0)
		{
			try
			{
				System.out.print("Enter the amount of money you will bring to the table: ");
				double balance = testBalance(sc.nextDouble());
				consoleInfo.write("Enter the amount of money you will bring to the table: " + balance + "\n");
				csBalance = balance;
				balanceCheck = 1;
			}
			catch (NEGATIVEBALANCEEXCEPTION e)
			{
				
				System.out.print(e + "\n");
				consoleInfo.write(e + "\n");
			}
		}
		
		int betCheck = 0;
		while (betCheck == 0)
		{
			try
			{
				System.out.print("Enter the bet amount between $0 and $1000: ");
				double bet = testBet(sc.nextDouble());
				consoleInfo.write("Enter the bet amount between $0 and $1000: " + bet + "\n");
				csBet = bet;
				betCheck = 1;
			}
			catch (NEGATIVEBETEXCEPTION e)
			{
				System.out.print(e + "\n");
				consoleInfo.write(e + "\n");
			}
			catch (BALANCELIMITEXCEPTION e)
			{
				System.out.print(e + "\n");
				consoleInfo.write(e + "\n");
			}
		}
		
	}
	
	public static void main(String[] args)
	/* The main function that runs the craps game. It includes the making of the 5 threads and calls the
	  simulation that has the runnable interface.*/
	{
		try 
		{
			consoleInfo = new PrintWriter("src/consoleInfo.txt");
		}
		catch (FileNotFoundException e)
		{
			System.out.print("File not found.");
		}
		
		int playAgain = 1;
		while (playAgain == 1)
		{
			initializeVariables();
			CrapsSimulation simulation1 = new CrapsSimulation(csName, csBalance, csBet, "src/thread1.txt", 1);
			CrapsSimulation simulation2 = new CrapsSimulation(csName, csBalance, csBet, "src/thread2.txt", 2);
			CrapsSimulation simulation3 = new CrapsSimulation(csName, csBalance, csBet, "src/thread3.txt", 3);
			CrapsSimulation simulation4 = new CrapsSimulation(csName, csBalance, csBet, "src/thread4.txt", 4);
			CrapsSimulation simulation5 = new CrapsSimulation(csName, csBalance, csBet, "src/thread5.txt", 5);
			
			Thread object1 = new Thread(simulation1);
			Thread object2 = new Thread(simulation2);
			Thread object3 = new Thread(simulation3);
			Thread object4 = new Thread(simulation4);
			Thread object5 = new Thread(simulation5);
			
			System.out.println("Thread1: " + "started at " + System.currentTimeMillis());
			consoleInfo.write("Thread1: " + "started at " + System.currentTimeMillis() + "\n");
			object1.start();
			System.out.println("Thread2: " + "started at " + System.currentTimeMillis());
			consoleInfo.write("Thread2: " + "started at " + System.currentTimeMillis() + "\n");
			object2.start();
			System.out.println("Thread3: " + "started at " + System.currentTimeMillis());
			consoleInfo.write("Thread3: " + "started at " + System.currentTimeMillis() + "\n");
			object3.start();
			System.out.println("Thread4: " + "started at " + System.currentTimeMillis());
			consoleInfo.write("Thread4: " + "started at " + System.currentTimeMillis() + "\n");
			object4.start();
			System.out.println("Thread5: " + "started at " + System.currentTimeMillis());
			consoleInfo.write("Thread5: " + "started at " + System.currentTimeMillis() + "\n");
			object5.start();
			
			
			//These joins do not mess with the threads and does not make them execute in order. This is made
			//so that we can ask if they want to play again while they aren't in the middle of executing.
			try 
			{	
				object1.join();
				object2.join();
				object3.join();
				object4.join();
				object5.join();
			}
			
			catch(Exception e)
			{
				System.out.println("");
				consoleInfo.write("");
			}
			
			try
			{
				System.out.print("Play again? Enter \'y\' or \'n\': ");
				Scanner sc = new Scanner(System.in);
				char letter = sc.next().charAt(0);
				consoleInfo.write("Play again? Enter \'y\' or \'n\': " + letter + "\n");
				testUnknownAnswer(letter);
				if (letter == 'y' || letter == 'Y')
				{
					playAgain = 1;
				}
				else if (letter == 'n' || letter == 'N')
				{
					playAgain = 0;	
					System.out.print("\nTHANKS FOR PLAYING FAM\n");
					consoleInfo.write("\nTHANKS FOR PLAYING FAM\n");
					System.out.print("All information outputted from the console will be in src/consoleInfo.txt");
					consoleInfo.write("All information outputted from the console will be in src/consoleInfo.txt");
					consoleInfo.close();
				}
				
			}
			catch(UNKNOWNANSWEREXCEPTION e)
			{
				System.out.print(e + "\n");
				consoleInfo.write(e + "\n");
			}
		}
	}
}

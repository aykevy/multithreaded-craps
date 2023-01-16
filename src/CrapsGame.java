import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/* This file basically runs the entire craps game. As you play the game the monitor is being
  updated as you play. After you choose not to bet anymore, statistics of the game print. */

public class CrapsGame
{
	/* These are used for the functionality for the playGame. */
	private Scanner sc = new Scanner(System.in);
	private Random dice = new Random();
	String sessionName = "";
	double sessionBalance = 0;
	double originalBalance = 0;
	double sessionBet = 0;
	double originalBet = 0;
	public int threadNum;
	int playAgain = 0;
	PrintWriter pw;
	
	/*These are used to update Monitor Data*/
	CrapsMetricsMonitor monitor;
	private int winStreak = 0;
	private int loseStreak = 0;
	
	CrapsGame(String name, double balance, double bet, String fileName, int threadNum)
	/*Constructor: Initializes a brand new monitor.*/
	{
		this.monitor = new CrapsMetricsMonitor();
		this.sessionName = name;
		this.sessionBalance = balance;
		this.originalBalance = balance;
		this.sessionBet = bet;
		this.originalBet = bet;
		this.threadNum = threadNum;
		try
		{
			this.pw = new PrintWriter(fileName);
		}
		catch(FileNotFoundException e)
		{
			System.out.print("File not found.");
		}
	}
	
	/****************************************************************************/
	/**********************Helper Methods for Play Game**************************/
	/****************************************************************************/
	
	void checkMaxRollCount(int rollCount)
	/* This helper function checks if a session has the highest roll in a single game. */
	{
		if (rollCount > monitor.maxRollsInOneGame)
		{
			monitor.maxRollsInOneGame = rollCount;
		}
	}
	
	void clearStreak()
	/* This helper function clears winning and losing streaks. */
	{
		if (this.winStreak > 0 && this.loseStreak > 0)
		{
			this.winStreak = 0;
			this.loseStreak = 0;
		}
	}
	
	void checkWinningStreak()
	/* This helper function checks if your current win streak is higher than the metrics monitor,
	  if so update the monitor. */
	{
		if (this.winStreak > monitor.winningStreak && this.loseStreak == 0)
		{
			monitor.winningStreak = this.winStreak;
		}
	}
	
	void checkLosingStreak()
	/* This helper function checks if your current lose streak is higher than the metrics monitor,
	  if so update the monitor. */
	{
		if (this.loseStreak > monitor.losingStreak && this.winStreak == 0)
		{
			monitor.losingStreak = this.loseStreak;
		}
	}
	
	void checkMaximumBalance()
	/* This helper function checks if your balance is at the highest it has ever been after
	  playing a game. */
	{
		if (this.sessionBalance > monitor.maximumBalance)
		{
			monitor.maximumBalance = this.sessionBalance;
			monitor.maximumBalanceGameNum = monitor.gamesPlayed;
		}
	}
	
	int diceRoll()
	/* This helper function simply rolls the dice and gives you the result. */
	{
		int firstRollNum = 0;
		int rollCount = 0; 
		int exitRoll = 0;	  
		int diceResult = 0;
		while (exitRoll == 0)
		{
			int roll = dice.nextInt((12 - 1) + 1) + 1;
			pw.write("Rolled two dices that equal: " + roll + " at " + System.currentTimeMillis() + "\n");
			if (rollCount == 0)
			{
				rollCount += 1;
				if ((roll == 2) || (roll == 3) || (roll == 12))
				{
					pw.write("*****Craps! You lose.*****\n");
					monitor.gamesLost += 1;
					monitor.crapsCount += 1;
					clearStreak();
					loseStreak += 1;
					checkLosingStreak();
					exitRoll = 1;
					diceResult = 2;
				}
				if ((roll == 7) || (roll == 11))
				{
					pw.write("*****Natural! You win!*****\n");
					monitor.gamesWon += 1;
					monitor.naturalCount += 1;
					clearStreak();
					winStreak += 1;
					checkWinningStreak();
					exitRoll = 1;
					diceResult = 1;
				}
				else
				{
					firstRollNum = roll;
				}
			}
			else
			{
				rollCount += 1;
				if ((roll == 7))
				{
					pw.write("*****Crap out! You lose.*****\n");
					monitor.gamesLost += 1;
					clearStreak();
					loseStreak += 1;
					checkLosingStreak();
					exitRoll = 1;
					diceResult = 2;
				}
				if ((roll == firstRollNum))
				{
					pw.write("*****Rolled the point! You win!*****\n");
					monitor.gamesWon += 1;
					clearStreak();
					winStreak += 1;
					checkWinningStreak();
					exitRoll = 1;
					diceResult = 1;
				}
			}
		}
		checkMaxRollCount(rollCount);
		return diceResult;
	}
	
	int playGame()
	/* This function plays the CrapsGame using the helper functions and handles user input using
	  the exception methods above, it also writes to the output text file. */
	{
		
		int exitProgram = 0;
		while (exitProgram == 0)
		{
			while (sessionBalance > 0)
			{
				if (sessionBet > sessionBalance) //If Bet is higher than balance, make bet equal to balance.
				{
					sessionBet = sessionBalance;
				}
				else							 //Otherwise, keep it the same.
				{
					sessionBet = originalBet;
				}
				pw.write("Playing a new game... " + sessionName + " bets " + "$" + sessionBet + " at " +System.currentTimeMillis() + "\n");
				int diceSession = diceRoll();
				if (diceSession == 1)
				{
					sessionBalance += sessionBet;
					checkMaximumBalance();
					pw.write(sessionName + "'s balance is " + sessionBalance + "\n");
				}
				if (diceSession == 2)
				{
					sessionBalance -= sessionBet;
					checkMaximumBalance();
					if (sessionBalance < 0) //If the balance is negative, make it 0.
					{
						sessionBalance = 0;
					}
					pw.write(sessionName + "'s balance is " + sessionBalance + "\n");
				}
				monitor.gamesPlayed += 1;
			}
			pw.write("*****************************\n");
			pw.write("*** SIMULATION STATISTICS ***\n");
			pw.write("*****************************\n");
			pw.write("Games Played: " + monitor.gamesPlayed + "\n");
			pw.write("Games Won: " + monitor.gamesWon + "\n");
			pw.write("Games Lost: " + monitor.gamesLost + "\n");
			pw.write("Maximum Rolls in a single game: " + monitor.maxRollsInOneGame + "\n");
			pw.write("Natural Count: " + monitor.naturalCount + "\n");
			pw.write("Craps Count: " + monitor.crapsCount + "\n");
			pw.write("Maximum Winning Streak: " + monitor.winningStreak + "\n");
			pw.write("Maximum Losing Streak: " + monitor.losingStreak + "\n");
			pw.write("Maximum balance: " + monitor.maximumBalance + " during game " + monitor.maximumBalanceGameNum + "\n");
			pw.write("\n");	
			pw.close();
			playAgain = 0;
			pw.write("\nThread: " + threadNum + " ended at " + System.currentTimeMillis() +
					"\n" + "Thread: " + threadNum + " games played: " + monitor.gamesPlayed + "\n");
			System.out.println("\nThread: " + threadNum + " ended at " + System.currentTimeMillis() +
					"\n" + "Thread: " + threadNum + " games played: " + monitor.gamesPlayed + "\n");
			exitProgram = 1;
		}
		return 0;
	}
}

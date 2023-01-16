
/*This file is basically the Craps Metrics Monitor. It is used to
  keep track of important Craps Game data. */

public class CrapsMetricsMonitor
{
	public int gamesPlayed = 0;
	public int gamesWon = 0;
	public int gamesLost = 0;
	public int maxRollsInOneGame = 0;
	public int naturalCount = 0;
	public int crapsCount = 0;
	public int winningStreak = 0;
	public int losingStreak = 0;
	public double maximumBalance = 0;
	public int maximumBalanceGameNum = 0;
	
	CrapsMetricsMonitor()
	/*This is the normal constructor for the CrapsMetricsMonitor.*/
	{
	}

	CrapsMetricsMonitor(CrapsMetricsMonitor toCopy)
	/* This is the copy constructor for the CrapsMetricsMonitor. */
	{
		this.gamesPlayed = toCopy.gamesPlayed;
		this.gamesWon = toCopy.gamesWon;
		this.gamesLost = toCopy.gamesLost;
		this.maxRollsInOneGame = toCopy.maxRollsInOneGame;
		this.naturalCount = toCopy.naturalCount;
		this.crapsCount = toCopy.crapsCount;
		this.winningStreak = toCopy.winningStreak;
		this.losingStreak = toCopy.losingStreak;
		this.maximumBalance = toCopy.maximumBalance;
		this.maximumBalanceGameNum = toCopy.maximumBalanceGameNum;
	}
	
	public void printStatistics() 
	/* This prints out all of the statistics from the session. */
	{
		System.out.println("*****************************");
		System.out.println("*** SIMULATION STATISTICS ***");
		System.out.println("*****************************");
		System.out.println("Games Played: " + gamesPlayed);
		System.out.println("Games Won: " + gamesWon);
		System.out.println("Games Lost: " + gamesLost);
		System.out.println("Maximum Rolls in a single game: " + maxRollsInOneGame);
		System.out.println("Natural Count: " + naturalCount);
		System.out.println("Craps Count: " + crapsCount);
		System.out.println("Maximum Winning Streak: " + winningStreak);
		System.out.println("Maximum Losing Streak: " + losingStreak);
		System.out.println("Maximum balance: " + maximumBalance + " during game " + maximumBalanceGameNum);
		System.out.println("\n");
	}
	
	public void reset()
	/* This function completely resets the monitor statistics. */
	{
		this.gamesPlayed = 0;
		this.gamesWon = 0;
		this.gamesLost = 0;
		this.maxRollsInOneGame = 0;
		this.naturalCount = 0;
		this.crapsCount = 0;
		this.winningStreak = 0;
		this.losingStreak = 0;
		this.maximumBalance = 0;
		this.maximumBalanceGameNum = 0;
	}
}
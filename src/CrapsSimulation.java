import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* This file is basically the CrapsSimulation. It is used to start the craps game.
  If you choose to play more than one session, it will start again. */

public class CrapsSimulation extends Thread implements Runnable
{
	private CrapsMetricsMonitor monitor;
	private CrapsGame game;
	public String csName;
	public double csBalance;
	public double csBet;
	public String fileName;
	public int threadNum;
	public int total = 0;
	private Lock lock = new ReentrantLock();
	
	CrapsSimulation(String name, double balance, double bet, String file, int threadNum)
	/* Constructor: Initializes a brand new craps simulation. */
	{
		this.csName = name;
		this.csBalance = balance;
		this.csBet = bet;
		this.fileName = file;
		this.threadNum = threadNum;
	}
	
	public void run()
	/* This is the run method used to override in Runnable. It simply plays the craps game. */
	{
			lock.lock();
			int end = 0;
			while (end == 0) 
			{
				game = new CrapsGame(csName, csBalance, csBet, fileName, threadNum);
				game.playGame();
				if (game.playAgain == 1)
				{
					monitor = game.monitor;
					end = 0;
				}
				else
				{
					end = 1;
				}
			}
			lock.unlock();
	}
}
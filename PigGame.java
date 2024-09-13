/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
 import java.util.Scanner;
public class PigGame {
	
	private int compFinalScore;
	private int userFinalScore;
	private int userTurnScore;
	private	int compTurnScore;
	private boolean userTurn;
	
	public PigGame()
	{
		compFinalScore = 0;
		userFinalScore = 0;
		userTurnScore = 0;
	    compTurnScore = 0;
	    userTurn = true;
	}
	
	public static void main(String[] args)
	{
		PigGame pg = new PigGame();
		pg.printIntroduction();
		pg.play();
	}
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
	public void play()
	{
		Dice d = new Dice();		
		int diceNum = 0;
		String continueNext2 = new String("");

		while(compFinalScore < 100)
		{
			if(userTurn == true)
			{
				System.out.println("\n**** USER Turn ***");
			}
			else
			{
				System.out.println("\n**** COMPUTER'S Turn ***");
			}
			continueNext2 = printScores();
			while((userTurn == true && continueNext2.equalsIgnoreCase("r")) || (userTurn == false && continueNext2.equalsIgnoreCase("")))
			{
				diceNum = d.roll();
				System.out.println("\nYou ROLL");
				d.printDice();
				if(diceNum != 1)
				{
					if(userTurn	== true)
					{
						userTurnScore = userTurnScore + diceNum;
					}
					else
					{
						compTurnScore = compTurnScore + diceNum;
					}
					System.out.println("\n");
				}
				else
				{
					if(userTurn == true)
					{
						System.out.println("\nYou LOSE your turn.");
						userTurnScore = 0;
						System.out.println("Your total score: " + userFinalScore);
					
					}
					else
					{
						System.out.println("\Computer loses turn.");
						compTurnScore = 0;
						System.out.println("Computer total score: " + compFinalScore);
						
					}
					
				}
				if(userTurnScore !=0 || compTurnScore !=0)
				{
					continueNext2 = printScores();
				}
				else
				{	
					continueNext2 = "h";
				}
			}
			if(continueNext2.equalsIgnoreCase("h"))
			{
				if(userTurn = true)
			}
		}
	}
	
	public String printScores()
	{
		Scanner kb = new Scanner(System.in);
		if(userTurn == true)
		{
			System.out.printf("\n%-20s %d\n", "Your turn score:", userTurnScore);	
			System.out.printf("%-20s %d\n", "Your final score:", userFinalScore);
			System.out.print("\n(r)oll or (h)old -> ");
		}
		else
		{
			System.out.printf("\n%-20s %d\n", "Your turn score:", compTurnScore);	
			System.out.printf("%-20s %d\n", "Your final score:", compFinalScore);
			System.out.print("Press enter for computer turn -> ");	
		}
		Scanner kb = new Scanner(System.in);
		String continueNext = kb.next();
		return continueNext;
	}
	
}

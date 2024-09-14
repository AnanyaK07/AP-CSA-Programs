/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	Ananya Kotla
 *	@since	September 14, 2024
 */
import java.util.Scanner;
public class PigGame 
{
	private int compFinalScore;
	private int userFinalScore;
	private int userTurnScore;
	private	int compTurnScore;
	private boolean userTurn;
	private String continueNext;
	
	public PigGame()
	{
		compFinalScore = 0;
		userFinalScore = 0;
		userTurnScore = 0;
	    compTurnScore = 0;
	    userTurn = true;
		continueNext = new String("");
	}
	
	public static void main(String[] args)
	{
		PigGame pg = new PigGame();
		pg.printIntroduction();
		pg.rolls();
		pg.winner();
	}
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n\n");
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
	
	public void rolls()
	{
		Dice d = new Dice();		
		int diceNum = 0;

		while(compFinalScore < 100 && userFinalScore < 100)
		{
			if(userTurn == true)
			{
				System.out.println("\n**** USER Turn ***\n");
			}
			else if(userTurn == false)
			{
				System.out.println("\n**** COMPUTER'S Turn ***\n");
			}
			printScores();
			if(continueNext.equalsIgnoreCase("h"))
			{
				userTurn = false;
				System.out.println("\n");
			}
			while((userTurn == true && continueNext.equalsIgnoreCase("r")) || (userTurn == false && continueNext.equalsIgnoreCase("")))
			{
				diceNum = d.roll();
				if(userTurn == true)
				{
					System.out.println("\nYou ROLL");
				}
				else
				{
					System.out.println("\nComputer will ROLL");
				}
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
					System.out.println("");
				}
				else
				{
					if(userTurn == true)
					{
						System.out.println("\nYou LOSE your turn.");
						userTurnScore = 0;
						System.out.println("Your total score: " + userFinalScore + "\n");
						userTurn = false;
					
					}
					else
					{
						System.out.println("\nComputer LOSES turn.");
						compTurnScore = 0;
						System.out.println("Computer total score: " + compFinalScore + "\n");
						userTurn = true;
					}
					
				}
				holds();	
			}
		}
	}

	public void holds()
	{
		if(userTurnScore !=0 || compTurnScore !=0)
		{
			printScores();
					
			if((userTurn == false && compTurnScore >= 20) || (userTurn == false && compTurnScore + compFinalScore >= 100))
			{
				continueNext = "h";
				System.out.println("Computer will HOLD");
				compFinalScore = compFinalScore + compTurnScore;
				System.out.println("Computer's total score: " + compFinalScore + "\n");
				compTurnScore = 0;
				userTurn = true;
			}
			else if(continueNext.equalsIgnoreCase("h")&& userTurn == true)
			{
				System.out.println("\nYou HOLD");
				userFinalScore = userFinalScore + userTurnScore;
				System.out.println("Your total score: " + userFinalScore + "\n");
				userTurnScore= 0;
				userTurn = false;
			}
		}
		else
		{	
			continueNext = "h";
		}
	}

	public void printScores()
	{
		Scanner kb = new Scanner(System.in);
		if(userTurn == true)
		{
			System.out.printf("%-20s %d\n", "Your turn score:", userTurnScore);	
			System.out.printf("%-20s %d\n", "Your final score:", userFinalScore);
			System.out.print("\n(r)oll or (h)old -> ");
		}
		else

		{
			System.out.printf("%-20s %d\n", "Computer's turn score:", compTurnScore);	
			System.out.printf("%-20s %d\n", "Computer's final score:", compFinalScore);
			System.out.print("\nPress enter for computer turn -> ");	
		}
		continueNext = kb.nextLine();
	}

	public void winner()
	{
		if(userFinalScore >= 100)
		{
			System.out.println("Congratulations!!! YOU WON!!!!");
			System.out.println("\nThanks for playing the Pig Game!!!");
		}
		else if(compFinalScore >= 100)
		{
			System.out.println("Computer WON!!!! Better Luck Next Time!\n\n");
			System.out.println("\nThanks for playing the Pig Game!!!\n\n");
		}
		
	}
	
}

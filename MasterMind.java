/**
 *	Plays the game of MasterMind.
 *	<Describe the game here>
 *	@author
 *	@since
 */

public class MasterMind {

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F

	public MasterMind()
	{
		reveal = false;
		guesses = new PegArray[MAX_GUESSES];
		for(int y = 0; y < MAX_GUESSES; y++)
		{
			guesses[y] = new PegArray(4);
		}
		master = new PegArray(4);
	}

	public static void main(String[] args)
	{
		MasterMind mm = new MasterMind();
		mm.runIt();
	}

	public void runIt()
	{
		printIntroduction();
		makeMaster();
		askUser();

	}
	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

	public void makeMaster()
	{
		char randomNum = ' ';
		for(int z = 1; z <= 4; z++)
		{
			randomNum = (char)((int)(Math.random() * PEG_LETTERS)+ 65);
			master.getPeg(z-1).setLetter(randomNum);
		}
	}

	public void askUser()
	{
		Prompt p = new Prompt();
		String readUserInput;
		boolean errorGuess = false;
		int exactGuesses = 0;
		readUserInput = p.getString("Hit the Enter key to start the game");
		if(!(readUserInput.equals(null)))
		{
			printBoard();
		}
		for(int a = 1; a <= MAX_GUESSES; a++)
		{
			System.out.println("\nGuess " + a +  "\n");
			while(errorGuess == true || readUserInput.length() != 4)
			{
				errorGuess = false;
				readUserInput = p.getString("Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right");
				readUserInput = readUserInput.toUpperCase();
				for(int b = 0; b < readUserInput.length(); b++)
				{
					if(readUserInput.charAt(b) < 65 || readUserInput.charAt(b) > 70)
					{
						errorGuess = true;
					}
				}
				if(errorGuess || readUserInput.length() != 4)
				{
					System.out.println("ERROR: Bad input, try again.");
				}
			}
			for(int c = 0; c < PEGS_IN_CODE; c++)
			{
				guesses[a-1].getPeg(c).setLetter(readUserInput.charAt(c));
			}
			exactGuesses = guesses[a-1].getExactMatches(master);
			if(exactGuesses == 4)
			{
				reveal = true;
			}
			guesses[a-1].getPartialMatches(master);
			printBoard();
			if(exactGuesses == 4)
			{
				System.out.println("Nice work! You found the master code in " + a + " guesses.\n");
				a = MAX_GUESSES;
			}
			errorGuess = true;
		}
		if(exactGuesses != 4)
		{
			System.out.println("Oops. You were unable to find the solution in 10 guesses.\n");
		}

	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
		{
			if (reveal)
			{
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			}
			else
			{
				System.out.print("  ***  |");
			}
		}
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
		{
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		}
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}

public class Yahtzee
{
	
	private YahtzeePlayer player1;
	private YahtzeePlayer player2;
	private boolean player1Turn;
	private final int MAX_TURNS = 13;
	
	public Yahtzee()
	{
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		player1Turn = false;
	}
	
	public static void main(String[] args)
	{
		Yahtzee y = new Yahtzee();
		y.runGame();
	}
	
	public void runGame()
	{
		printHeader();
		startGame();
		int numOfTurns = 1;
		DiceGroup dg = new DiceGroup();
		while(numOfTurns <= MAX_TURNS)
		{
			playGame(numOfTurns, dg);
			numOfTurns++;
		}
		checkWinner();
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/* Make sure user enters an actual name, not nothing. Case when same score  */
	public void startGame()
	{
		int player1Sum = 0;
		int player2Sum = 0;
		boolean sameScore = true;
		String continueNext = new String("");
		DiceGroup beg = new DiceGroup();
		do
			player1.setName(Prompt.getString("Player 1, please enter your first name"));
		while(player1.getName().equalsIgnoreCase(""));
		System.out.println();
		do
			player2.setName(Prompt.getString("Player 2, please enter your first name"));
		while(player2.getName().equalsIgnoreCase(""));
		while(sameScore == true)
		{
			sameScore = false;
			continueNext = Prompt.getString("\nLet's see who will go first. " + player1.getName() + ", please hit enter to roll the dice");
			player1Turn = true;
			rollDiceAll(beg);
			player1Sum = beg.getTotal();
			continueNext = Prompt.getString("\n" + player2.getName() + ", it's your turn. Please hit enter to roll the dice");
			player1Turn = false;
			rollDiceAll(beg);
			player2Sum = beg.getTotal();
			System.out.println( "\n" + player1.getName() + ", you rolled a sum of " + player1Sum + ", and " + player2.getName() + ", you rolled a sum of " + player2Sum + ".");
			if(player1Sum > player2Sum)
			{
				System.out.println(player1.getName() + ", since your sum was higher, you'll roll first.");
				player1Turn = true;
			}
			else if(player1Sum == player2Sum)
			{
				System.out.println("Both of the players' sums were the same, so let's reroll.");
				sameScore = true;
			}
			else 
			{
				System.out.println(player2.getName() + ", since your sum was higher, you'll roll first.");
				player1Turn = false;
			}
		}
		
	}

	public void playGame(int numOfTurns, DiceGroup dg)
	{
		String continueNext = new String("");
		showScoreboard();
		System.out.println("\nRound " + numOfTurns + " of " + MAX_TURNS + " rounds.\n");
		String currentPlayer = new String("");
		for(int x = 0; x < 2; x++)
		{
			if(player1Turn)
				currentPlayer = player1.getName();
			else
				currentPlayer = player2.getName();
			continueNext = Prompt.getString("\n" + currentPlayer + ", it's your turn to play. Please hit enter to roll the dice");
			rollDiceAll(dg);

			int reRoll = 0;
			boolean invalidHold = true;
			do 
			{
				invalidHold = true;
				while(invalidHold)
				{
					invalidHold = false;
					continueNext = Prompt.getString("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without\n" + 
							"spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125\n" + 
							"(enter -1 if you'd like to end the turn)");
					if(continueNext.equals(""))
					{
						rollDiceAll(dg);
					}
					else if(!continueNext.equalsIgnoreCase("-1"))
					{
						for(int k = 0; k < continueNext.length(); k++)
						{
							if(continueNext.charAt(k) < '1' || continueNext.charAt(k) > '5')
							{
								invalidHold = true;
							}
						}
						if(!invalidHold )
						{
							rollDiceSome(continueNext , dg);
						}
					}
				}
				reRoll++;
			}while(!continueNext.equalsIgnoreCase("-1") && reRoll < 2);
			showScoreboard();
			System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9   " +
				"10   11   12   13\n");
			int valid = 0;
			boolean notChoosen = false;
			do
			{
				valid = Prompt.getInt("\n" + currentPlayer + ", now you need to make a choice. Pick a valid integer from the list above (1 - 13)");
				if(valid >= 1 && valid <= MAX_TURNS)
				{
					if(player1Turn)
						notChoosen = player1.getScoreCard().changeScore(valid, dg);
					else
						notChoosen = player2.getScoreCard().changeScore(valid, dg);
				}
			}while((valid < 1 || valid > MAX_TURNS) || notChoosen == false);
			showScoreboard();
			if(player1Turn)
				player1Turn = false;
			else
				player1Turn = true;
		}
	}

	public void checkWinner()
	{
		int player1Sum = 0;
		int player2Sum = 0;
		for(int a = 1; a <= MAX_TURNS; a++)
		{
			player1Sum = player1Sum + player1.getScoreCard().getScore(a);
			player2Sum = player2Sum + player2.getScoreCard().getScore(a);
		}
		System.out.printf("\n%-16sscore total = %d", player1.getName(), player1Sum);
		System.out.printf("\n%-16sscore total = %d", player2.getName(), player2Sum);
		if(player1Sum > player2Sum)
			System.out.println("\n\nCongratulations " + player1.getName() + ". YOU WON!!! ");
		else if(player1Sum == player2Sum)
			System.out.println("\n\nCongratulations to both players. This is a TIE GAME!!");
		else 
			System.out.println("\n\nCongratulations " + player2.getName() + ". YOU WON!!! ");

	}
	
	public void showScoreboard()
	{
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
	}
	public void rollDiceAll(DiceGroup dg)
	{
		dg.rollDice();
		dg.printDice();
	}
	
	public void rollDiceSome(String holdDie, DiceGroup dg)
	{
		dg.rollDice(holdDie);
		dg.printDice();
	}
}

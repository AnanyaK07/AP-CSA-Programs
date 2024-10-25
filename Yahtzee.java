





public class Yahtzee
{
	
	private YahtzeePlayer player1;
	private YahtzeePlayer player2;
	private int player1Sum;
	private int player2Sum;
	private boolean player1Turn;
	private boolean player2Turn;
	
	public Yahtzee()
	{
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		player1Sum = 0;
		player2Sum = 0;
		player1Turn = false;
		player2Turn = false;
	}
	
	public static void main(String[] args)
	{
		Yahtzee y = new Yahtzee();
		y.runGame();
	}
	
	public void runGame()
	{
		printHeader();
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
	
	public void startGame()
	{
		String continueNext;
		int player1Sum = 0;
		int player2Sum = 0;
		player1.setName(Prompt.getString("Player 1, please enter your first name"));
		player2.setName(Prompt.getString("Player 2, please enter your first name"));
		continueNext = Prompt.getString("Let's see who will go first. " + player1.getName() + " , please hit enter to roll the dice")
		player1Turn = true;
		rollDiceAll();
		continueNext = Prompt.getString(player2.getName() + ", it's your turn. Please hit enter to roll the dice")	
		player1Turn = false;
		player2Turn = true;
		rollDiceAll();
		
	}
	
	public void rollDiceAll()
	{
		DiceGroup dg = new DiceGroup();
		dg.rollDice();
		dg.printDiceHeaders();
		dg.printDice();
		if(player1)
			player1Sum = dg.getTotal;
		else
			player2Sum = dg.getTotal;
	}
	
	public void rollDiceSome(String holdDie)
	{
		DiceGroup dg = new DiceGroup();
		dg.rollDice(holdDie);
		dg.printDiceHeaders();
		dg.printDice();
		if(player1)
			player1Sum = dg.getTotal();
		else
			player2Sum = dg.getTotal();
	}
}


public class YahtzeeScoreCard
{

	private int[] scores;
	private final int NUM_DICE = 5;
	private final int NUM_DICE_SIDES = 6;

	public YahtzeeScoreCard()
	{
		scores = new int[13];
		for(int a = 0; a < scores.length; a++)
		{
			scores[a] = -1;
		}
	}
/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	public int getScore(int num)
	{
		return scores[num - 1];
	}

	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) 
	{
		if(scores[choice - 1] > -1)
			return false;
		if(choice >= 1 && choice <= 6)
			numberScore(choice, dg);
		else if(choice == 7)
			threeOfAKind(dg);
		else if(choice == 8)
			fourOfAKind(dg);
		else if(choice == 9)
			fullHouse(dg);
		else if(choice == 10)
			smallStraight(dg);
		else if(choice == 11)
			largeStraight(dg);
		else if(choice == 12)
			chance(dg);
		else
			yahtzeeScore(dg);

		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) 
	{
		int total = 0;
		for(int a = 1; a <= NUM_DICE; a++)
		{
			if(dg.getOneValue(a) == choice)
			{
				 total = total + choice;
			}
		}
		scores[choice - 1] = total;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) 
	{
		scores[6] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++; 
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] >= 3)
			{
				scores[6] = dg.getTotal();
			}
		}
	}
	
	public void fourOfAKind(DiceGroup dg) 
	{
		scores[7] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++; 
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] >= 4)
				scores[7] = dg.getTotal();
		}
	}
	
	public void fullHouse(DiceGroup dg) 
	{
		scores[8] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		boolean threeKind = false;
		boolean twoKind = false;
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++; 
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] == 3)
				threeKind = true;
			else if(diceValues[c] == 2)
				twoKind = true;
		}
		if(twoKind && threeKind)
			scores[8] = 25;
	}
	
	public void smallStraight(DiceGroup dg) 
	{
		scores[9] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		int consecutive = 0;
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++;
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] > 0)
			{
				consecutive++;
				if(consecutive == 4)
					scores[9] = 30;
			}
			else
				consecutive = 0;
		}
	}
	
	public void largeStraight(DiceGroup dg) 
	{
		scores[10] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		int consecutive = 0;
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++;
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] == 1)
			{
				consecutive++;
				if(consecutive == 5)
					scores[10] = 40;
			}
			else
				consecutive = 0;
		}
	}
	
	public void chance(DiceGroup dg) 
	{
		scores[11] = dg.getTotal();
	}
	
	public void yahtzeeScore(DiceGroup dg) 
	{
		scores[12] = 0;
		int[] diceValues = new int[NUM_DICE_SIDES];
		for(int b = 1; b <= NUM_DICE; b++)
			diceValues[dg.getOneValue(b) - 1]++;
		for(int c = 0; c < diceValues.length; c++)
		{
			if(diceValues[c] == 5)
				scores[12] = 50;
		}
	}
}

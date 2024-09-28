/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author Ananya Kotla
 *  @since  September 28, 2024
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) 
	{	
		pegs = new Peg[numPegs];
		for(int x = 0; x < numPegs; x++)
		{
			pegs[x] = new Peg();
		}

	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) 
	{ 
		return pegs[n]; 
	}
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) 
	{ 
		exactMatches = 0;
		for(int d = 0; d < pegs.length; d++)
		{
			if(master.getPeg(d).getLetter() == pegs[d].getLetter())
			{
				exactMatches++;
			}
		}
		return exactMatches; 
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) 
	{ 
		partialMatches = 0;
		boolean[] wherePartial  = new boolean[4];
		boolean[] whereExact = new boolean[4];
		for(int e = 0; e < pegs.length; e++)
		{
			if(master.getPeg(e).getLetter() != pegs[e].getLetter())
			{
				for(int f = 0; f < pegs.length; f++)
				{
					if(master.getPeg(f).getLetter() == pegs[e].getLetter())
					{
						if(whereExact[f] == false && wherePartial[f] == false)
						{
							partialMatches++;
							wherePartial[f] = true;
							f = pegs.length -1;
						}
					}
				}
			}
			else
			{
				whereExact[e] = true;
				if(wherePartial[e])
				{
					wherePartial[e] = false;
					partialMatches --;
				}
			}
		}
		return partialMatches; 
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() 
	{ 
		return exactMatches; 
	}

	public int getPartial() 
	{ 
		return partialMatches; 
	}

}
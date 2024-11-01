/**
 *	Utilities for handling HTML
 *
 *	@author	Ananya Kotla
 *	@since	November 1, 2024
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		int indexResult = 0;
		String[] result = new String[10000];
		while(str.indexOf('<') != -1)
		{
			result[indexResult] = str.substring(str.indexOf('<'), str.indexOf('>') + 1);
			str = result[indexResult];
			indexResult++;
		}
		String[] actualResult = new String[indexResult];
		for(int x = 0; x < actualResult.length; x++)
		{
			actualResult[x] = result[x];
		}
		// return the correctly sized array
		
		return actualResult;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}



/**
 *	Utilities for handling HTML
 *
 *	@author	Ananya Kotla
 *	@since	November 1, 2024
 */
public class HTMLUtilities {

	private boolean aComment;
	private boolean aPreFText;
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
		str = str.trim();
		while(str.length() != 0)
		{
			if(str.charAt(0) == '<' || aComment == true || aPreFText == true)
			{
				if(str.indexOf("<!--") == 0 || aComment == true)
				{
					if(str.indexOf("-->") == -1)
					{
						str = "";
						aComment = true;
					}
					else
					{
						str = str.substring(str.indexOf('>') + 1);
						aComment = false;
					}
					indexResult--;
				}
				else if(str.equalsIgnoreCase("<pre>") || aPreFText == true)
				{
					result[indexResult]= str;
					str = "";
					aPreFText = true;
					if(result[indexResult].equalsIgnoreCase("</pre>"))
					{
						aPreFText = false;
					}
				}
				else
				{
					result[indexResult] = str.substring(str.indexOf('<'), str.indexOf('>') + 1);
					str = str.substring(str.indexOf('>') + 1).trim();
				}
			}
			else if(Character.isLetter(str.charAt(0)))
			{
				int include = 0;
				boolean check = true;
				while(check)
				{
					if(Character.isLetter(str.charAt(include)) || str.charAt(include) == '-' && Character.isLetter(str.charAt(include + 1)))
					{
						include++;
						if(include == str.length())
						{
							result[indexResult] = str.substring(0);
							str = "";
							check = false;
						}
					}
					else
					{
						result[indexResult] = str.substring(0, include);
						str = str.substring(include).trim();
						check = false;
					}
				}
			}
			else if(str.charAt(0) == ',' || str.charAt(0) ==  '.' || str.charAt(0) == ',' || str.charAt(0) == ';' || str.charAt(0) == ':' || str.charAt(0) == '(' || str.charAt(0) == ')' || str.charAt(0) == '?' || str.charAt(0) == '!' || str.charAt(0) == '=' || str.charAt(0) == '&' || str.charAt(0) == '~' || str.charAt(0) == '+'|| str.charAt(0) == '-' && Character.isDigit(str.charAt(1)) == false)
			{
				result[indexResult] = str.substring(0, 1);
				str = str.substring(1).trim();
			}
			else if(Character.isDigit(str.charAt(0)) || str.charAt(0) == '-' && Character.isDigit(str.charAt(1)))
			{
				int include = 0;
				boolean check = true;
				while(check)
				{
					if(Character.isDigit(str.charAt(include)) || (include + 1 != str.length() && (str.charAt(include) == '-' || str.charAt(include) == '.' )) && Character.isDigit(str.charAt(include + 1)) || str.charAt(include) == 'e' && (str.charAt(include + 1) == '-' || Character.isDigit(str.charAt(include + 1))))
					{
						include++;
						if(include == str.length())
						{
							result[indexResult] = str.substring(0);
							str = "";
							check = false;
						}
					}
					else
					{
						result[indexResult] = str.substring(0, include);
						str = str.substring(include).trim();
						check = false;
					}
				}
			}
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
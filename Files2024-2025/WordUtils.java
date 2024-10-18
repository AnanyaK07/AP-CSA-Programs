
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author 
 *	@since	
 */
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	/* Constructor */
	public WordUtils(int numOfWords) 
	{ 
		 words = new String[numOfWords];
		 
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () 
	{ 
		Scanner input = FileUtils.openToRead(WORD_FILE);
		int numOfWords = 0;
		while(input.hasNext())
		{
			input.next();
			numOfWords++;
		}
		WordUtils wu = WordUtils(numOfWords);
		numOfWords = 0;
		while(input.hasNext())
		{
			words[numOfWords] = input.hasNext();
			numOfWords++;
		}	
		input.close();
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{
		String[] correctWords = new String[100];
		boolean isWord = true;
		for(int b = 0; b < words.length; b+=)
		{
			for(int a = 0; a < words[b].length(); a++)
			{
				char c = words[b].charAt(a);
				if(letters.indexOf(c) > -1)
				{
					letters = letters.substring(0, letters.indexOf(c)) 
					+ letters.substring(letters.indexOf(c) + 1);
				}
				else
					isWord = false;
			}
			if(isWord)
			{
				correctWords[b]  =  word[b];
			}
		}
		return correctWords;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) 
	{
		System.out.println();
			for (int row = 0; row < wordList.length(); row++) {
				for (int col = 0; col < wordList[row]; col++) {
					System.out.printf("%-8s", wordList[col]);
					if ((col + 1) % 10 == 0) System.out.println();
				}
				if (wordList[row] > 0) System.out.println("\n");
			}
			System.out.println();
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		int score[] = new int[wordList.length];
		int highestScore = 0;
		String[] bestWords = new String[10];
		for(int a = 0; a < wordList.length; a++)
		{
			score[a] = getScore(wordList[a], scoreTable);
		}
		for(int c = 0;c < score.length;c++)
		{
			if(score[c] > highestScore)
			{
				highestScore = score[c];
				
			}
		}
		for(int d = 0; d < wordList.length; d++)
		{
			if(highestScore = score[d])
			{
				bestWords[d
			}
		}
		return "";
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int score = 0;
		for(int b = 0; b < word.length(); b++)
		{
			score = score + scoreTable[word.charAt(b) - 'a'];
		}
		return score;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		loadWords();
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}

import java.util.Scanner;

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
	public WordUtils() { }
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () {
		Scanner input = FileUtils.openToRead(WORD_FILE);
		int count = 0;
		while (input.hasNext()) {
			input.next();
			count++;
		}
		words = new String[count];
		count = 0;
		input = FileUtils.openToRead(WORD_FILE);
		while (input.hasNext()) {
			words[count] = input.next();
			count++;
		}
		input.close();
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		String[] foundWords = new String[words.length];
		int foundCount = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i] != null && canFormWord(words[i], letters)) {
				foundWords[foundCount] = words[i];
				foundCount++;
			}
		}
		String[] result = new String[foundCount];
		for (int i = 0; i < foundCount; i++) {
			result[i] = foundWords[i];
		}
		return result;
	}
	
	private boolean canFormWord(String word, String letters) {
		String remainingLetters = letters;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = remainingLetters.indexOf(c);
			if (index == -1) return false;
			remainingLetters = remainingLetters.substring(0, index) + 
							   remainingLetters.substring(index + 1);
		}
		return true;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		for (int i = 0; i < wordList.length; i++) {
			System.out.printf("%-15s", wordList[i]);
			if ((i + 1) % 5 == 0) System.out.println();
		}
		if (wordList.length % 5 != 0) System.out.println();
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
    	if (wordList == null || wordList.length == 0) {
        	return "";
    		}
    
   		String bestWord = "";
    	int highestScore = 0;
    	int countOfBestWords = 0;
    
    	for (int i = 0; i < wordList.length; i++) 
		{
       		int score = getScore(wordList[i], scoreTable);
        	if (score > highestScore) {
            	highestScore = score;
            	bestWord = wordList[i];
            	countOfBestWords = 1;
       		} 
			else if (score == highestScore) 
			{
           		 countOfBestWords++;
            	if (Math.random() < 1.0 / countOfBestWords) 
				{
                	bestWord = wordList[i];
           	 	}
        	}
    	}
    	return bestWord;
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
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c >= 'a' && c <= 'z') {
				score += scoreTable[c - 'a'];
			}
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
		String letters = new String("");
		do
		letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		while(letters.length() < 3 || letters.length() > 12);
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
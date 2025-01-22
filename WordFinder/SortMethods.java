import java.util.ArrayList;
import java.util.List;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Ananya Kotla
 *	@since	November 26, 2024
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) 
	{
		for(int outer = arr.length - 1; outer > 0; outer--)
			for(int inner = 0; inner < outer; inner++)
				if(arr[inner].compareTo(arr[inner + 1]) > 0)
				  swap(arr, inner, inner + 1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) 
	{
		for(int n = arr.length; n > 1; n--)
		{
			int iMax = 0;
			for(int i = 1; i < n; i++)
			{
				if(arr[i] > arr[iMax])
					iMax = i;
			}
			
			int aTemp = arr[iMax];
			arr[iMax] = arr[n-1];
			arr[n-1] = aTemp;
		}	
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for(int n = 1; n < arr.length; n++)
		{
			int aTemp = arr[n];
			int i = n;
			while( i > 0 && aTemp < arr[i-1])
			{
				arr[i] = arr[i-1];
				i--;
			}
			arr[i] = aTemp;
		}
		
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<String> words) 
	{
		
		int n = words.size();
		List<String> temp = new ArrayList<String>();
		for(int a = 0; a < n; a++)
		{
			temp.add(words.get(a));
		}
		recursiveSort(words, 0, n-1, temp);
	}
	
	public void recursiveSort(List<String> words, int from, int to, List<String> temp)
	{
		if(to - from < 2)
		{
			if(to > from && words.get(to).compareTo(words.get(from)) < 0)
			{
				String aTemp = words.get(to);
				words.set(to, words.get(from));
				words.set(from, aTemp);
			}
		}
		else
		{
			int middle = (from + to) /2;
			recursiveSort(words, from, middle, temp);
			recursiveSort(words, middle + 1, to, temp);
			merge(words, from, middle, to, temp);
			
		}
	}
	
	public void merge(List<String> words, int from, int middle, int to, List<String> temp)
	{
		int i = from; 
		int j = middle+1; 
		int k = from;
		while(i <= middle && j <= to)
		{
			if(words.get(i).compareTo(words.get(j)) < 0)
			{
				temp.set(k, words.get(i));
				i++;
			}
			else
			{
				temp.set(k, words.get(j));
				j++;
			}
			k++;
		}
		while( i <= middle)
		{
			temp.set(k, words.get(i));
			i++;
			k++;
		}
		while( j <= to)
		{
			temp.set(k, words.get(j));
			j++;
			k++;
		}
		for(k = from; k <= to; k++)
		{
			words.set(k, temp.get(k));
		}
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		//mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}

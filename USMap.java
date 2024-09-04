/**
 *	USMap.java
 *	Main program of the project
 *
 *	@author	Ananya Kotla
 *	@since	September 4, 2024
 */
 
import java.awt.Font;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class USMap extends GraphicsProgram
{
	public static void main (String[] args)
	{
		USMap us = new USMap();
		us.run();
	}
	
	public void run()
	{
		int x = 0;
		String lineRead;
		double[] xCoord = new double[1200];
		double[] yCoord = new double[275];

		Scanner infile = FileUtils.openToRead("cities.txt");
		while (infile.hasNext()) 
		{
			lineRead = infile.nextLine();
			 xCoord[x] = lineRead.nextDouble(0, ' ');
			 yCoord[x] = lineRead.nextDouble(6, ' ');
			x++;
		}
	}
}

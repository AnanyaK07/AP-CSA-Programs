 /**
 *	USMap.java
 *	This program takes cities from the United States from  txt files
 *  and plots the cities on a canvas. The city that have a bigger population
 *  are different colors and different sizes, which is based on the population
 *	@author	Ananya Kotla
 *	@since	September 4, 2024
 */

import java.awt.Color;
import java.util.Scanner;

public class USMap
{
    private double[] xCoordNormal = new double[1200];
    private double[] yCoordNormal = new double[1200];
    private double[] xCoordBig = new double[300];
    private double[] yCoordBig = new double[300];
    private String[] namesNormal = new String[1200];
    private String[] bigCityName = new String[300];
    private int[] population = new int[300];

	/** 
	  *  Main method; Creates an instance of this class to call other
	  *  methods of this class.
	  */
    public static void main (String[] args)
    {
        USMap us = new USMap();
        us.getCities();
        us.setupCanvas();
        us.drawCities();
    }

	/** 
	  *  Method to get info for cities; Uses FileUtils to open both
	  *  txt files. In the first txt file, it stores the x and y coordinates
	  *  as well as the name of the city. In the second txt file it stores
	  *  the population number, city name, as well as the rank for the 
	  *  highest population. Both use while loops to read each line of the
	  *  file.
	  */
    public void getCities()
    {
        int cityNum = 0;
        String line = new String("");
        int findComma = 0;
        Scanner infile = FileUtils.openToRead("cities.txt");
        while (infile.hasNext()) 
        {
			
            yCoordNormal[cityNum] = infile.nextDouble();
            xCoordNormal[cityNum] = infile.nextDouble();
            line = infile.nextLine().substring(1);
            findComma = line.indexOf(',');
            namesNormal[cityNum] = line.substring(0,findComma - 1);
            cityNum++;
        }

        Scanner infile2 = FileUtils.openToRead("bigCities.txt");
        while (infile2.hasNext())
        {
            cityNum = infile2.nextInt();
            line = infile2.nextLine().substring(1);
            findComma = line.indexOf(',');
            bigCityName[cityNum - 1] = line.substring(0, findComma - 1);
            line = line.substring(findComma + 5);
            population[cityNum - 1] = Integer.parseInt(line);
            cityNum++;
        }

    }
    
    /**
     * Sets up the canvas by using the StdDraw java file
     */
    public void setupCanvas() 
    {
        StdDraw.setTitle("USMap");
        StdDraw.setCanvasSize(900, 512);
        StdDraw.setXscale(128.0, 65.0);
        StdDraw.setYscale(22.0, 52.0);

    }
   
   /**
    * T plot the cities  first check if the name of the city was 
    * a big city. If it was then we check if it's top 10. That determines
    * the color of the point. Then based on the population of that big city
    * we plot how big the point is. If it is not a big city then we plot 
    * the point based on the coordinates with a radius of 0.006.
    */
    public void drawCities()
    {
		 boolean aBigCity = false;
        for(int x = 0; x < 1112; x++)
        {
            for(int y = 0; y < 275; y++)
            {
                if (namesNormal[x].equalsIgnoreCase(bigCityName[y]) && !( namesNormal[x].equalsIgnoreCase(namesNormal[x-1])))
                {
                    if(y < 10)
                    {
                        StdDraw.setPenColor(Color.RED);
                    }
                    else
                    {
                        StdDraw.setPenColor(Color.BLUE);
                    }
                    StdDraw.setPenRadius(0.6 * (Math.sqrt(population[y])/18500));
                    StdDraw.point(xCoordNormal[x], yCoordNormal[x]);
                    aBigCity = true;
                }
            }
            if(x => 1)
            {
				if(aBigCity == false && !( namesNormal[x].equalsIgnoreCase(namesNormal[x-1])))
				{
					StdDraw.setPenColor(Color.BLACK);
					StdDraw.setPenRadius(0.006);
					StdDraw.point(xCoordNormal[x], yCoordNormal[x]);
				}
			}
			else if(x == 0)
			{
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setPenRadius(0.006);
					StdDraw.point(xCoordNormal[x], yCoordNormal[x]);
			}
            aBigCity = false;
        }
	}
}

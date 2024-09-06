package USMap; /**
 *	USMap.java
 *	Do caption at school
 *
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

    public static void main (String[] args)
    {
        USMap us = new USMap();
        us.getCities();
        us.setupCanvas();
    }

    public void getCities()
    {
        int cityNum = 0;
        String line = new String("");
        int findComma = 0;
        Scanner infile = FileUtils.openToRead("src/USMap/cities.txt");
        while (infile.hasNext()) 
        {
            yCoordNormal[cityNum] = infile.nextDouble();
            xCoordNormal[cityNum] = infile.nextDouble();
            line = infile.nextLine().substring(1);
            findComma = line.indexOf(',');
            namesNormal[cityNum] = line.substring(0,findComma - 1);
            cityNum++;
        }

        Scanner infile2 = FileUtils.openToRead("src/USMap/bigCities.txt");
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
    public void setupCanvas() 
    {
        StdDraw.setTitle("USMap");
        StdDraw.setCanvasSize(900, 512);
        StdDraw.setXscale(128.0, 65.0);
        StdDraw.setYscale(22.0, 52.0);
        boolean aBigCity = false;
        for(int x = 0; x < 1112; x++)
        {
            System.out.println(namesNormal[x]);
            for(int y = 0; y < 275; y++)
            {
                if (namesNormal[x].equalsIgnoreCase(bigCityName[y]))
                {
                    if(y < 10)
                    {
                        StdDraw.setPenColor(Color.RED);
                    }
                    else
                    {
                        StdDraw.setPenColor(Color.BLUE);
                    }
                    StdDraw.filledCircle(xCoordNormal[x], yCoordNormal[x], 0.6 * (Math.sqrt(population[y])/1850));
                    aBigCity = true;
                }
            }
            if(aBigCity == false)
            {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.filledCircle(xCoordNormal[x], yCoordNormal[x], 0.06);
            }
            aBigCity = false;
        }
    }
}

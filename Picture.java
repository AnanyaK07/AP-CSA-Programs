import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu and Ananya Kotla
 * @since 
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to keep only the blue*/
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate all the pixels on the pictures*/
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(225 - (pixelObj.getRed()));
        pixelObj.setGreen(225 - (pixelObj.getGreen()));
        pixelObj.setBlue(225 - (pixelObj.getBlue()));
      }
    }
  }
  
  /** Method to turn the picture into shades of gray*/
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
		int average = (pixelObj.getRed() + pixelObj.getBlue() + 
												pixelObj.getGreen())/3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** To pixelate by dividing area into size x size.
   * @param size Side length of square area to pixelate.
   */
  public void pixelate(int size) {
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 0; row < pixels.length; row += size) {
        for (int col = 0; col < pixels[row].length; col += size) {
            int redColor = 0, greenColor = 0, blueColor = 0;
            int pixelCount = 0;
            int rowLimit = Math.min(row + size, pixels.length);
            int colLimit = Math.min(col + size, pixels[row].length);
            for (int r = row; r < rowLimit; r++) {
                for (int c = col; c < colLimit; c++) {
                    Pixel pixel = pixels[r][c];
                    redColor += pixel.getRed();
                    greenColor += pixel.getGreen();
                    blueColor += pixel.getBlue();
                    pixelCount++;
                }
            }
            int avgRed = redColor / pixelCount;
            int avgGreen = greenColor / pixelCount;
            int avgBlue = blueColor / pixelCount;
            for (int r = row; r < rowLimit; r++) {
                for (int c = col; c < colLimit; c++) {
                    pixels[r][c].setRed(avgRed);
                    pixels[r][c].setGreen(avgGreen);
                    pixels[r][c].setBlue(avgBlue);
                }
            }
        }
    }
  }

  /** Method that blurs the picture
	* @param size 	Blur size, greater is more blur
	* @return result 	Blurred picture
	*/
  public Picture blur(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();
    int radius = size / 2;
    for (int row = 0; row < pixels.length; row++) {
        for (int col = 0; col < pixels[row].length; col++) {
            int redColor = 0, greenColor = 0, blueColor = 0, num = 0;
            int startRow = Math.max(0, row - radius);
            int endRow = Math.min(pixels.length - 1, row + radius);
            int startCol = Math.max(0, col - radius);
            int endCol = Math.min(pixels[row].length - 1, col + radius);
            for (int r = startRow; r <= endRow; r++) {
                for (int c = startCol; c <= endCol; c++) {
                    Pixel pixel = pixels[r][c];
                    redColor += pixel.getRed();
                    greenColor += pixel.getGreen();
                    blueColor += pixel.getBlue();
                    num++;
                }
            }
            resultPixels[row][col].setRed(redColor / num);
            resultPixels[row][col].setGreen(greenColor / num);
            resultPixels[row][col].setBlue(blueColor / num);
        }
    }
    return result;
  }

   /** Method that enhances a picture by getting average Color around
    * a pixel then applies the following formula:
    *
    * pixelColor <- 2 * currentValue - averageValue
    *
    * size is the area to sample for blur.
    *
    * @param size Larger means more area to average around pixel
    * and longer compute time.
    * @return result enhanced picture
    */
   public Picture enhance(int size)
   {
      Pixel[][] pixels = this.getPixels2D();
      Picture result = new Picture(pixels.length, pixels[0].length);
      Pixel[][] resultPixels = result.getPixels2D();
      int radius = size / 2;
      for (int row = 0; row < pixels.length; row++) {
          for (int col = 0; col < pixels[row].length; col++) {
              int redColor = 0, greenColor = 0, blueColor = 0, num = 0;

              int startRow = Math.max(0, row - radius);
              int endRow = Math.min(pixels.length - 1, row + radius);
              int startCol = Math.max(0, col - radius);
              int endCol = Math.min(pixels[row].length - 1, col + radius);

              for (int r = startRow; r <= endRow; r++) {
                for (int c = startCol; c <= endCol; c++) {
                    Pixel pixel = pixels[r][c];
                    redColor += pixel.getRed();
                    greenColor += pixel.getGreen();
                    blueColor += pixel.getBlue();
                    num++;
                }
              }
            resultPixels[row][col].setRed((2 * pixels[row][col].getRed()) 
														- (redColor / num));
            resultPixels[row][col].setGreen((2 * pixels[row][col].getGreen()) 
														- (greenColor / num));
            resultPixels[row][col].setBlue((2 * pixels[row][col].getBlue()) 
														- (blueColor / num));
          }
        }
        return result;
   }

  /** Method that will shift pixels to the right and wrap around to the left. 
    * The left half of the picture will end up on the right side.
	* @param size 	Blur size, greater is more blur
	* @return result 	Blurred picture
	*/
   public Picture swapLeftRight()
   {
      Pixel[][] pixels = this.getPixels2D();
      Picture result = new Picture(pixels.length, pixels[0].length);
      Pixel[][] resultPixels = result.getPixels2D();
  
      for (int row = 0; row < pixels.length; row++) {
          for (int col = 0; col < pixels[0].length; col++) {
              int newColumn = (col + pixels[0].length / 2) % pixels[0].length;
              resultPixels[row][newColumn].setColor(pixels[row][col].getColor());
          }
      }
      return result;
   }	

   /** This method will create jagged picture can be made using stair 
     * steps of shifted pixels.
     * @param shiftCount The number of pixels to shift to the right
     * @param steps The number of steps
     * @return result The picture with pixels shifted in stair steps
     */ 
   public Picture stairStep(int shiftCount, int steps) {
      Pixel[][] pixels = this.getPixels2D();
      Picture result = new Picture(pixels.length, pixels[0].length);
      Pixel[][] resultPixels = result.getPixels2D();
  
      int stepHeight = pixels.length / steps; 
  
      for (int row = 0; row < pixels.length; row++) {
          int shiftAmount = (row / stepHeight) * shiftCount; 
          for (int col = 0; col < pixels[0].length; col++) {
              int newColumn = (col + shiftAmount) % pixels[0].length; 
              resultPixels[row][newColumn].setColor(pixels[row][col].getColor());
          }
      }
      return result;
   }

   /**
     * This method will distort the horizontal center of the picture by 
     * shifting pixels horizontally.
     * 
     * @param maxHeight The maximum shift of pixels (controls distortion intensity)
     * @return result A new Picture object with the wavy effect applied
     */
   public Picture liquify(int maxHeight) {   
      Pixel[][] pixels = this.getPixels2D();
      Picture result = new Picture(pixels.length, pixels[0].length);
      Pixel[][] resultPixels = result.getPixels2D();
      int bellWidth = pixels.length / 4;
      for (int row = 0; row < pixels.length; row++) {
          double exponent = Math.pow(row - pixels.length / 2.0, 2) / 
											(2.0 * Math.pow(bellWidth, 2));
          int rightShift = (int) (maxHeight * Math.exp(-exponent)); 
          for (int col = 0; col < pixels[0].length; col++) {
              int newColumn = (col + rightShift) % pixels[0].length; 
              resultPixels[row][newColumn].setColor(pixels[row][col].getColor());
          }
      }
      return result;
   }

   /**
     * Applies a wavy effect by shifting pixels horizontally in a sinusoidal pattern.
     * This creates an oscillating distortion across the entire image.
     * 
     * @param amplitude The maximum shift of pixels (controls distortion intensity)
     * @return result A new Picture object with the wavy effect applied
     */
   public Picture wavy(int amplitude) {
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
  
      Picture result = new Picture(height, width);
      Pixel[][] resultPixels = result.getPixels2D();
      double phaseShift = 0;   
      for (int row = 0; row < height; row++) {
          int rightShift = (int) (amplitude * Math.sin(2 * (Math.PI/100) * row + phaseShift));

          for (int col = 0; col < width; col++) {
              int newColumn = (col + rightShift + width) % width; 
              resultPixels[row][newColumn].setColor(pixels[row][col].getColor());
          }
      }
      return result;
  }


  /** Method that creates a green screen picture
	* @return bkgnd		green screen picture
	*/
  public Picture greenScreen() {
    Picture bkgnd = new Picture("greenScreenImages/IndoorHouseLibraryBackground.jpg");
    Pixel[][] bkgndPixels = bkgnd.getPixels2D();
    
    Picture cat = new Picture("greenScreenImages/kitten1GreenScreen.jpg");
    Pixel[][] catPixels = cat.getPixels2D();
    Picture newCat = resize(catPixels, getWidth()/4, getHeight()/3);
    catPixels = newCat.getPixels2D();
    
    Picture mouse = new Picture("greenScreenImages/mouse1GreenScreen.jpg");
    Pixel[][] mousePixels = mouse.getPixels2D();
    Picture newMouse = resize(mousePixels, getWidth()/6, getHeight()/8);
    mousePixels = newMouse.getPixels2D();

    int greenThreshold = 150;
    double ratioThreshold = 1.5;

    int catRowOffset = 365; 
    int catColOffset = 510; 
    for (int row = 0; row < catPixels.length; row++) {
        for (int col = 0; col < catPixels[row].length; col++) {
            Pixel catPixel = catPixels[row][col];
            int red = catPixel.getRed();
            int green = catPixel.getGreen();
            int blue = catPixel.getBlue();
            if (!(green > greenThreshold && green > red * ratioThreshold && green > blue * ratioThreshold)) {
                bkgndPixels[row + catRowOffset][col + catColOffset].setColor(catPixel.getColor());
            }
        }
    }
    
    int mouseRowOffset = 360; 
    int mouseColOffset = 290; 
    for (int row = 0; row < mousePixels.length; row++) {
        for (int col = 0; col < mousePixels[row].length; col++) {
            Pixel mousePixel = mousePixels[row][col];
            int red = mousePixel.getRed();
            int green = mousePixel.getGreen();
            int blue = mousePixel.getBlue();
            
            if (!(green > greenThreshold && green > red * ratioThreshold && green > blue * ratioThreshold)) {
                bkgndPixels[row + mouseRowOffset][col + mouseColOffset].setColor(mousePixel.getColor());
            }
        }
    }
    return bkgnd;
  }

  /** A helper method that rescales the mouse and cat pictures 
   * @param oldPixels	 pixels of the picture getting rescaled
   * @param newWidth	 the new width of the rescaled picture
   * @param newHeight	 the new height of the rescaled picture
   * @return resizedPicture 	the rescaled picture
   */
  public Picture resize(Pixel[][] oldPixels, int newWidth, int newHeight) {
    int oldWidth = oldPixels[0].length;
    int oldHeight = oldPixels.length;

    Picture resizedPicture = new Picture(newHeight, newWidth);
    Pixel[][] newPixels = resizedPicture.getPixels2D();

    double xScale = (double) oldWidth / newWidth;
    double yScale = (double) oldHeight / newHeight;
    for (int newX = 0; newX < newWidth; newX++) {
        for (int newY = 0; newY < newHeight; newY++) {
            int oldX = (int) (newX * xScale);
            int oldY = (int) (newY * yScale);
            newPixels[newY][newX].setColor(oldPixels[oldY][oldX].getColor());
        }
    }
    return resizedPicture;
  }

  /**
   * Rotate image in radians, clean up "drop-out" pixels
   * @param angle	 angle of rotation in radians
   * @return rotatedImage	Picture that is rotated
   */
  public Picture rotate(double angle) {
    Pixel[][] pixels = this.getPixels2D();
    int width = pixels[0].length;
    int height = pixels.length;
    int centerX = (width) / 2;
    int centerY = (height) / 2;
    int newWidth = (int)(width * Math.abs(Math.cos(angle)) + height * Math.abs(Math.sin(angle)));
    int newHeight = (int)(width * Math.abs(Math.sin(angle)) + height * Math.abs(Math.cos(angle)));
    Picture rotatedImage = new Picture(newHeight, newWidth);
    Pixel[][] rotatedPixels = rotatedImage.getPixels2D();
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            int x0 = col - centerX;
            int y0 = row - centerY;
            int x1 = (int)(x0 * Math.cos(angle) - y0 * Math.sin(angle));
            int y1 = (int)(x0 * Math.sin(angle) + y0 * Math.cos(angle));
            x1 += (newWidth / 2);
            y1 += (newHeight / 2);
            if (x1 >= 0 && x1 < newWidth && y1 >= 0 && y1 < newHeight)
            {
              rotatedPixels[y1][x1].setColor(pixels[row][col].getColor()); 
            }
        }
      }
      for (int row = 1; row < newHeight -1 ; row++) {
        for (int col = 1; col < newWidth -1; col++) {
            Pixel pixel = rotatedPixels[row][col];
            if (pixel.getColor().equals(new Color(255, 255, 255))) {
              if(!hasWhiteNeighbor(rotatedPixels, row, col, newHeight, newWidth))
              {
                Color nearestColor = findNearestColor(rotatedPixels, row, col, newHeight, newWidth);
                pixel.setColor(nearestColor);
              }
            }
        }
    }
    return rotatedImage;
  } 

  /** Helper method that fills in the pixels that were dropped out 
   * @param pixels	 the pixels of the picture
   * @param row	     the dropout pixel's row
   * @param col	     the dropout pixel's column
   * @param height	 the height of the picture
   * @param width	 the width of the picture
   * @return the closest's pixel's color to the dropout pixel
   */
  public Color findNearestColor(Pixel[][] pixels, int row, int col, int height, int width) {
    Color nearestColor = new Color(255, 255, 255); 
    int minDistance = Integer.MAX_VALUE;
    for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
            int newRow = row + i;
            int newCol = col + j;
            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                Pixel neighbor = pixels[newRow][newCol];
                if (!neighbor.getColor().equals(new Color(255, 255, 255))) {  
                    int distance = colorDistance(nearestColor, neighbor.getColor());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestColor = neighbor.getColor();
                    }
                }
            }
        }
    }
	return nearestColor;
  }

  /** helper method that checks if the white pixel has any white pixels
   * that neighbor it. If it does, then we know it's part of the background,
   * not the picture itself. 
   * @param pixels	 the pixels of the picture
   * @param row	     the dropout pixel's row
   * @param col	     the dropout pixel's column
   * @param height	 the height of the picture
   * @param width	 the width of the picture
   * @return true if it's a dropout pixel, false if it's not
   */
  public boolean hasWhiteNeighbor(Pixel[][] pixels, int row, int col, int height, int width) {
	int[][] directions = {
      {-1, -1}, {-1, 0}, {-1, 1},
      { 0, -1},         { 0, 1},
      { 1, -1}, { 1, 0}, { 1, 1}
	};
	int counter = 0;
	for (int[] dir : directions) {
		int newRow = row + dir[0];
		int newCol = col + dir[1];

		if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
			if (pixels[newRow][newCol].getColor().equals(new Color(255, 255,255))) {
				counter++; 
			}
		}
	}
	if(counter > 3)
		return true;
	return false; 
}

  

  /** Helper method to calculate the distance between two colors 
   * (used in nearest neighbor filling) 
   * @param color1	 the color of one pixel
   * @param color2	 the color of another pixel
   * @return distance between the two colors
   */
  public int colorDistance(Color color1, Color color2) {
	int rDiff = color1.getRed() - color2.getRed();
	int gDiff = color1.getGreen() - color2.getGreen();
	int bDiff = color1.getBlue() - color2.getBlue();
	return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;  
  }

  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }

  /** Method that creates an edge detected black/white picture
    * @param threshold 	threshold as determined by Pixel’s colorDistance metho
    * @return result 	edge detected picture
    */
  public Picture edgeDetectionBelow(int threshold) 
  {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();
    for (int row = 0; row < pixels.length - 1; row++) { // Ensure we don't go
        for (int col = 0; col < pixels[row].length; col++) {
            Pixel currentPixel = pixels[row][col];
            Pixel belowPixel = pixels[row + 1][col];
            if (currentPixel.colorDistance(belowPixel.getColor()) > 
                threshold) 
              resultPixels[row][col].setColor(Color.BLACK);
            else 
              resultPixels[row][col].setColor(Color.WHITE);
        }
      }
      for (int col = 0; col < pixels[0].length; col++) {
        resultPixels[pixels.length - 1][col].setColor(Color.WHITE);
      }
    return result;
  }
  
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this

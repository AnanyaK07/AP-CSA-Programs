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
 * @author Barbara Ericson ericson@cc.gatech.edu
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
  
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
		int average = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen())/3;
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

            // Determine actual block size (handles edges correctly)
            int rowLimit = Math.min(row + size, pixels.length);
            int colLimit = Math.min(col + size, pixels[row].length);

            // Compute average color for the block
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
	* @param size Blur size, greater is more blur
	* @return Blurred picture
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
    * @return enhanced picture
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
            resultPixels[row][col].setRed((2 * pixels[row][col].getRed()) - (redColor / num));
            resultPixels[row][col].setGreen((2 * pixels[row][col].getGreen()) - (greenColor / num));
            resultPixels[row][col].setBlue((2 * pixels[row][col].getBlue()) - (blueColor / num));
          }
        }
        return result;
    }

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

    /** <Description here>
     * @param shiftCount The number of pixels to shift to the right
     * @param steps The number of steps
     * @return The picture with pixels shifted in stair steps
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

    public Picture liquify(int maxHeight) {   
      Pixel[][] pixels = this.getPixels2D();
      Picture result = new Picture(pixels.length, pixels[0].length);
      Pixel[][] resultPixels = result.getPixels2D();
      int bellWidth = pixels.length / 4;
      for (int row = 0; row < pixels.length; row++) {
          double exponent = Math.pow(row - pixels.length / 2.0, 2) / (2.0 * Math.pow(bellWidth, 2));
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
     * @return A new Picture object with the wavy effect applied
     */
    public Picture wavy(int amplitude) {
      Pixel[][] pixels = this.getPixels2D();
      int width = pixels[0].length;
      int height = pixels.length;
  
      Picture result = new Picture(height, width);
      Pixel[][] resultPixels = result.getPixels2D();

      double frequency = 0.05; 
      double phaseShift = 0;   

      for (int row = 0; row < height; row++) {
          int rightShift = (int) (amplitude * Math.sin(2 * Math.PI * frequency * row + phaseShift));

          for (int col = 0; col < width; col++) {
              int newColumn = (col + rightShift + width) % width; // Wrap pixels around
              resultPixels[row][newColumn].setColor(pixels[row][col].getColor());
          }
      }

      return result;
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

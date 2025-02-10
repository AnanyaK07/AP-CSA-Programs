/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  public static void testKeepOnlyBlue()
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  public static void testNegate()
  {
	Picture beach = new Picture("images/beach.jpg");
	beach.explore();
    beach.negate();
    beach.explore();
  }
  
  public static void testGrayScale()
  {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
   public static void testPixelate()
  {
	Picture swan = new Picture("images/swan.jpg");
	swan.explore();
    swan.pixelate(10);
    swan.explore();
  }
  
  public static void testBlur()
  {
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
    Picture newBeach = beach.blur(11);
    newBeach.explore();
  }

  public static void testEnhance()
  {
    Picture water = new Picture("images/water.jpg");
    water.explore();
    Picture newWater = water.enhance(21);
    newWater.explore();
  }
  
  public static void testSwapLeftRight()
  {
    Picture redMotorcycle = new Picture("images/redMotorcycle.jpg");
    redMotorcycle.explore();
    Picture newRedMotorcycle = redMotorcycle.swapLeftRight();
    newRedMotorcycle.explore();
  }

  public static void testStairStep()
  {
    Picture redMotorcycle = new Picture("images/redMotorcycle.jpg");
    redMotorcycle.explore();
    Picture newRedMotorcycle = redMotorcycle.stairStep(1,400);
    newRedMotorcycle.explore();
  }

  public static void testLiquify()
  {
    Picture redMotorcycle = new Picture("images/redMotorcycle.jpg");
    redMotorcycle.explore();
    Picture newRedMotorcycle = redMotorcycle.liquify(150);
    newRedMotorcycle.explore();
  }

  public static void testWavy()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    Picture newSwan = swan.wavy(5);
    newSwan.explore();
  }


  
  
  
  
  
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayScale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testSwapLeftRight();
    //testStairStep();
    //testLiquify();
    testWavy();
  }
}

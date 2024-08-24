/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Ananya Kotla
 *	@since	August 23, 2024
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	
    	//	Line 2
    	GLabel s2 = new GLabel("For the first half of summer I went to"
							 + " Europe. We stayed at my mom's friend's house in", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	
    	//	Line 3
    	GLabel s3 = new GLabel("Switzerland. We visited many places in Switzerland," 
							 + " such as Zurich, Zermatt, Luzern, and ", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	
    	//	Line 4
    	GLabel s4 = new GLabel("Interlaken. We used trains in order to travel"
							 + " to all the places. There were many times where  ", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	
    	//	Line 5
    	GLabel s5 = new GLabel("we all most missed the next train. Eventually "
							 + "we got the hang of it though. We also took"
							 + " trains", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	
    	//	Line 6
    	GLabel s6 = new GLabel("to visit both Paris and Italy. In Paris, we"
							 + " saw the Eiffel Tower and climbed to the"
							 + " top of it.", 10, 120);
    	s6.setFont(f);
    	add(s6);
    	
    	//	Line 7
    	GLabel s7 = new GLabel("We took a lot of pictures and we were also"
							 + " able to see a great view of Paris. We also saw", 10, 140);
    	s7.setFont(f);
    	add(s7);
    	
    	//	Line 8
    	GLabel s8 = new GLabel("many preparations for the Olympics, which "
							 + "was cool. We ate some pretty good pasteries " 
							 + "and", 10, 160);
    	s8.setFont(f);
    	add(s8);
    	
    	//	Line 9
    	GLabel s9 = new GLabel("tried fench onion soup for the first time."
							+ " We visited the museum where the Mona Lisa "
							+ "is held.", 10, 180);
    	s9.setFont(f);
    	add(s9);
    	
    	//	Line 10
    	GLabel s10 = new GLabel("In Italy we visited Milan and did a bunch"
							  + " of shopping, since Milan is the fashion "
							  + "capital of ", 10, 200);
    	s10.setFont(f);
    	add(s10);
    	
    	//	Line 11
    	GLabel s11 = new GLabel("Italy. We also saw Duomo di Milano and "
							  + "drank the best cappuccino ever! We also ate ", 10, 220);
    	s11.setFont(f);
    	add(s11);
    	
    	//	Line 12
    	GLabel s12 = new GLabel("the most delicious pizza and pasta. "
							  + "In Switzerland, we visited the Lindor "
							  + "factory and bought a", 10, 240);
    	s12.setFont(f);
    	add(s12);
    	
    	//	Line 13
    	GLabel s13 = new GLabel("ton of chocolate. We also visited the highest "
							  + "point in Europe. It was very cold and windy."
							  + " There", 10, 260);
    	s13.setFont(f);
    	add(s13);
    	
    	//	Line 14
    	GLabel s14 = new GLabel("was a language problem somtimes. We couldn't "
							  + "communicate with people, so Google Translate" , 10, 280);
    	s14.setFont(f);
    	add(s14);
    	
    	//	Line 15
    	GLabel s15 = new GLabel("came in hand a lot of times. After the "
							  + "Europe trip, I didn't really do much at home." , 10, 300);
    	s15.setFont(f);
    	add(s15);
    	
    	//	Line 16
    	GLabel s16 = new GLabel("Overall, my summer vacation was really "
							  + "fun and I was able to experience and try new things." , 10, 320);
    	s16.setFont(f);
    	add(s16);   	
    }
}

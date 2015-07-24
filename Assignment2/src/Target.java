/*File: Target.java
 * ----------------
 * This program basically draws 3 GOval shapes (circles) that are inside within each other.
 * The purpose of this is to basically create a target board with the 3 circles being red, white and red, respectively.
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		displayOuterCircle();
		displayInnerCircle();
		displayBullsEye();
	}
	
	/*Method: displayOuterCircle
	 * --------------------------
	 * This creates and displays the main circle right in the middle of the window.
	 * 
	 */
	
	private void displayOuterCircle(){
		double radiusWidth, radiusHeight, height, width;
		radiusWidth = 72;
		radiusHeight = 72;
		height = ((getHeight() / 2) - (radiusHeight /2));
		width = ((getWidth() / 2) - (radiusWidth / 2));
		
		GOval outerCircle = new GOval(width, height, radiusWidth, radiusHeight); //width, height, width and height
		outerCircle.setColor(Color.RED);
		outerCircle.setFillColor(Color.RED);
		outerCircle.setFilled(true);
		add(outerCircle);
		}
	
	/*Method: displayInnerCircle
	 * --------------------------
	 * This creates the displays the inner white circle that resides within the larger circle.
	 * 
	 */

	private void displayInnerCircle(){
		double radiusWidth, radiusHeight, height, width;
		
		radiusWidth = 0.65 * 72; //gets the radius width of the smaller circle
		radiusHeight = 0.65 * 72;
		height = ((getHeight() / 2) - (radiusHeight / 2));
		width = ((getWidth() / 2) - (radiusWidth / 2));
		
		GOval innerCircle = new GOval(width, height, radiusWidth, radiusHeight);
		innerCircle.setColor(Color.RED);
		innerCircle.setFillColor(Color.WHITE);
		innerCircle.setFilled(true);
		add(innerCircle);
	}
	
	/*Method: displayBullsEye
	 * --------------------------
	 * This creates the displays the red bullseye that resides in the middle of the board.
	 * 
	 */

	
	private void displayBullsEye(){
		double radiusWidth, radiusHeight, height, width;
		
		radiusWidth = 0.3 * 72;
		radiusHeight = 0.3 * 72;
		height = ((getHeight() / 2) - radiusHeight / 2);
		width = ((getWidth() / 2) - radiusWidth / 2);
		
		GOval Bullseye = new GOval(width, height, radiusWidth, radiusHeight);
		Bullseye.setColor(Color.RED);
		Bullseye.setFillColor(Color.RED);
		Bullseye.setFilled(true);
		add(Bullseye);
	}
}

/*
 * File: Pyramid.java
 *--------------------
 * This program will begin stacking bricks a row at at time.
 * The end result will show us a pyramid
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		createPyramid();
	}
	
	/*Pre-condition: The program starts
	 * Post-Condition: The program adds the pyramid picture onto the java applet.
	 * 
	 */
	private void createPyramid(){
		int a, b, c, d; //declaration of variables
		a = BRICK_WIDTH;
		b = BRICK_HEIGHT; //this just creates another variable pointing to the constants so that they're changeable
		c = getWidth() / 2; //this gets to mid-point of the screen
		d = 30; //this is a control variable whereby it is used to determine the next position of the brick for the next column
		
		
		for(int rows = 14; rows > 0; rows--){ //loops 14 times, this creates the entire pyramid; the 14 bricks will be decreased each time it finishes one loop
			for(int bricks = 0; bricks < rows; bricks++){ //this works on each individual row and prints the number of bricks that the 'rows' variable is currently on
				GRect rect = new GRect((c/2) + (bricks * a) ,b * rows,a,b); 
				add(rect);
			}
			c = ((getWidth() / 2) + d); //the next column will have a new width position so add another 30 to get to that position
			d+= 30; // increment the variable by 30 so that c will be plussed by 30, 60, 90 etc.
		} //end for
	}
	
}


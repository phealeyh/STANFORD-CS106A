/*Program: RandomCircles
 * -----------------------
 * This program will basically generate and create random circles at different locations with different colours and different sizes.
 * 
 */
import java.awt.*;
import acm.graphics.*;
import acm.util.*;
import acm.program.*;

public class RandomCircles extends GraphicsProgram {
	
	/* Private Class Variables */
	private static final int minRadius = 5;
	private static final int maxRadius = 50;
	private static final int numberOfCircles = 10;
	
	public void run(){
		for(int i = 0; i < numberOfCircles; i++){
			createCircles();
		}
	}
	
	
	/*Method: createCircles
	 * ---------------------
	 * This method will generate a new circle at a new position, with a new size and colour.
	 * 
	 */
	
	private void createCircles(){
		double radius = rgen.nextInt(minRadius, maxRadius);
		int x = rgen.nextInt(1, getWidth());
		int y = rgen.nextInt(1, getHeight());
		GOval circle = new GOval(x, y, radius, radius);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		add(circle);
	}
	
	/* Private Instance Variables */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}

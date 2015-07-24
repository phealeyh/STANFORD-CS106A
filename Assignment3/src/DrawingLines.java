/*Program: DrawingLines
 * ---------------------
 * This program will basically simulate a rubber-banding action.
 * A single mouse-pointer will first appear on the screen.
 * If the user holds the mouse button and drags it across the screen, it will draw the user a line segment.
 * If the user moves up or down vertically while holding the button, then the line segment will not extend but it will follow the user instead.
 * When the user releases the mouse click, then a line segment is drawn from the initial starting point to the ending point.
 * 
 */

import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;

public class DrawingLines extends GraphicsProgram {
	
	/* Method: Init
	 * -------------
	 * This method will initialise the mouse listeners for mouse clicks from the user
	 * 
	 */
	
	public void init(){
		addMouseListeners();
	}
	
	/* Method: mousePressed
	 * -------------
	 * This method is called when a user clicks on the mouse.
	 * It basically creates a starting point of the line.
	 * 
	 */

	public void mousePressed(MouseEvent e){
		line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	/* Method: mouseDragged
	 * ---------------------
	 * When the user drags the mouse while holding down on the click, the user will be able to move the line.
	 * When the user lets go of the click, then the line will be drawn from the start point and end point.
	 * 
	 */

	public void mouseDragged(MouseEvent e){
		line.setEndPoint(e.getX(), e.getY());
	}
	
	
	/* Private Instance Variables */
	private GLine line;
}

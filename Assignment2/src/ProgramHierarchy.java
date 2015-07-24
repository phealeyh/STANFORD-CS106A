/*File: ProgramHierarchy
 * ----------------------
 * This program will basically generate 4 rectangles and 3 lines that connect to each other (from the mid-top box to the bottom 3).
 * The boxes will also contain labels that describe what it is for.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int boxWidth = 120;
	private static final int boxHeight = 40;
	
	public void run() {
		createRectangles();
		createLabels();
		createLines();
	}
	
	/* Method: createRectangles
	 * --------------------------
	 * This will basically create the 4 rectangles. 1 at the middle of the screen and 3 underneath it.
	 */
	private void createRectangles(){
		int hPos = getHeight() / 4; //50
		int wPos = getWidth() / 4; //50
		int height = boxHeight;
		int width = boxWidth;
			for(int firstBox = 0; firstBox < 1; firstBox++){
				GRect topRect = new GRect((wPos + 140), (hPos), width, height); //wPos = 165; hPos = 50;
				add(topRect);
					for(int i = 1; i <= 3; i++){
						GRect bottomRect = new GRect(wPos,(hPos + 80), width, height); //wPos = 25; hPos = 130;
						add(bottomRect);
						wPos = getWidth() /4 + (140 * i); //190; 330; 470
					} //end for */
			}
	}
	
	/* Method: createLabels
	 * --------------------------
	 * This will basically create the corresponding labels for each rectangle and place it right in the middle.
	 */

	private void createLabels(){
		int heightPos = getHeight() / 4;
		int widthPos = getWidth() / 4;
		double a = boxWidth;
		double b = boxHeight;
		int f = 140;
			for(int i = 0; i < 1; i++){
				GLabel label = new GLabel(getLabel(i), widthPos + f,heightPos); //widthpos 
				double c = (a - label.getWidth()) / 2;
				double z = b - label.getAscent();
				label.move(c,z);
				add(label);
				f = 0;
					for(int x = 1; x <= 3; x++ ){
						GLabel labels = new GLabel((getLabel(x)), widthPos + f, (heightPos  + 80));
						add(labels);
						double d = (a - labels.getWidth()) / 2;
						double e = (b - label.getAscent());
						labels.move(d, e);
						f += 140;
					}
			} //end for
	}
	
	/* Method: createLines
	 * --------------------------
	 * This will create lines whereby the bottom boxes will all connect to the top 'program' box, creating a hierarchy.
	 * The lines will be situated at the middle of the box.
	 */

	private void createLines(){
		int xPoint, yPoint;
		xPoint = getWidth() / 4;
		yPoint = getHeight() / 4;
		int a = 60;
		for(int i = 0; i < 3; i++){
			GLine line = new GLine(xPoint + 200, yPoint + 40, xPoint + a,130);
			add(line);
			a += 140;
		} //end for
	}
	
	/* Method: getLabel
	 * --------------------------
	 * This switch is used for the "create label" method and is basically used in conjunction with the for loop.
	 */

	private String getLabel(int x){
		switch(x){
		case 1: return "GraphicsProgram";
		case 2: return "ConsoleProgram";
		case 3: return "DialogProgram";
		default: return "Program";
		} //end switch
	}
}


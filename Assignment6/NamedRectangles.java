/* Program: NamedRectangles
 * -----------------------------
 * This program will create the GLabel and GRectangle when its constructor is called.
 * 
 */

import acm.graphics.*;

public class NamedRectangles extends GCompound{
	
	/* Private Instance Variables */
	private double box_width, box_height;
	private GRect rectangle;
	private GLabel name;
	
	
	public NamedRectangles(String rectangleName, double width, double height){
		box_width = width;
		box_height = height;
		double midX = getWidth() /2;
		double midY = getHeight() / 2;
		rectangle = new GRect(midX,midY,box_width,box_height);
		name = new GLabel(rectangleName,midX + (box_width / 2),midY + (box_height / 2));
		name.move(-(name.getWidth() / 2), name.getHeight() / 2);
		add(rectangle);
		add(name);
	}
	/* Method: getObjectAt
	 * -----------------------
	 * This method will return the point of the named string.
	 */


	public GObject getObjectAt(String rectName){
		GObject rect = new GObject();
		//check if the two strings (names) match
		if(rectName.equals(name.getLabel())){
			name.get
			//remove the label and rectangle
			point = name.getLocation();
		}
		return point;
	}


}

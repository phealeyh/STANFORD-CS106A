/* Program: Interactors
 * ----------------------
 * This program will allow users to create boxes with appropriate names. The user will
 * have the ability to remove specific objects by name, add objects and clear the canvas.
 */

import acm.program.*;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Interactors extends GraphicsProgram {

	/* Class Constants */
	  private static final double BOX_WIDTH = 120;
      private static final double BOX_HEIGHT = 50;

	public void init(){
		contents = new HashMap<String,GObject>();
		//creates a new label with words
		add(new JLabel("Name: "),SOUTH);
		//creates the text field
		text = new JTextField(15);
		//this will set the action name of the command
		//it should be used when you're called the .getActionCommand()
		text.setActionCommand("name");
		//adds the text field in the south region
		add(text,SOUTH);
		//adds the j buttons involved in the program
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		//This will listen and get all the mouse events
		addMouseListeners();
		//This will listen and get all the button events
		addActionListeners();
	}
	
	/* Method: mousePressed
	 * ---------------------
	 * When a user clicks and holds (without letting go) on the mouse
	 * it will basically select an object
	 */
	
	public void mousePressed(MouseEvent e){
		//store the pressed object into the object variable
		lastPoint = new GPoint(e.getPoint());
		currentObject = getElementAt(lastPoint);
		//user presses on the mouse meaning that if an object exists then the object is selected
	}
	
	public void mouseDragged(MouseEvent e){
		//check that there is an actual object selected 
		if(currentObject != null){
			currentObject.move(e.getX() - lastPoint.getX(), e.getY() - lastPoint.getY());
			lastPoint = new GPoint(e.getPoint());
		}
		//user drags the mouse and the object moves across the canvas with it
	}
	
	
	/* Method: actionPerformed
	 * ------------------------
	 * This will basically allow you to make the right decisions
	 * when the user performs a specific action.
	 */

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Add")){
			String name = text.getText();
			createRectangle(name);
		}
		if(e.getActionCommand().equals("Remove")){
			String name = text.getText();
			removeRectangle(name);
		} 
		if(e.getActionCommand().equals("Clear")){
			//remove the entire canvas by calling removeAll();
			removeAll();
		}
	}

	/* Method: removeRectangle
	 * ------------------------
	 * This method will basically remove whatever named rectangle is.
	 *
	 */

	private void removeRectangle(String rectName){
		GObject object = contents.get(rectName);
			if(object != null){
				remove(object);
			}
	}

	/* Method: createRectangle
	 * ------------------------
	 * This method will create the rectangle along with the name.
	 * It will be stored inside a hash map.
	 * 
	 */
	private void createRectangle(String rectName){
		GCompound box = new GCompound();
		GRect outline = new GRect(getWidth() / 2, getHeight() /2, BOX_WIDTH,BOX_HEIGHT);
		GLabel name = new GLabel(rectName);
		//adds the objects into a the g compound.
		box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		box.add(name, -name.getWidth() / 2, name.getAscent() / 2); 
		add(box, getWidth() / 2, getHeight() / 2);
		contents.put(rectName, box);
	}

	/* Private Instance Variables */

	/* Hash map of the rectangles */
	Map<String, GObject> contents;
	
	/* The object that is selected on the canvas */
	private GObject currentObject;

	/* The last point where the object is clicked */
	private GPoint lastPoint;
	
	/* The text field */
	private JTextField text;
}

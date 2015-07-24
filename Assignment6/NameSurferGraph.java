/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//create the lines that segments the years
		createLines();
		//create the labels that are placed beneath the columns
		createLabels();

	}

	/* Method: creaatLines
	 * --------------------
	 * This method will create the lines that segments the years
	 */

	private void createLines(){
		//create 10 lines to segment the 11 columns; put it in a for loop which loops 10 times
		createColumns();
		//loop 2 times to create 2 lines horizontally
		createRows();
	}

	/* Method: createColumns
	 * ----------------------
	 * This method will create the necessary columns found in the graphics canvas
	 *
	 */

	private void createColumns(){
		for(int i = 0; i < NDECADES; i++){
			//this will be the x location of the column
			//it will increase at every loop
			int x_location = (APPLICATION_WIDTH / NDECADES) * i;
			GLine col = new GLine(x_location, 0,
			x_location, APPLICATION_HEIGHT);
			add(col);
		}
	}

	/* Method: createLabels
	 * ---------------------
	 * This will create the labels that are found underneath each column for a year
	 *
	 */

	private void createLabels(){
		//create labels from 1900 to 2000
		int multiplier = 0;
		for(int i = START_DECADE; i <= 2000; i+= 10){
			//the y position will always be the same
			//the x position should be always moving by application_width / decades * i
			int x_pos = (APPLICATION_WIDTH / NDECADES) * multiplier;
			//convert the integer into a string
			GLabel year = new GLabel(Integer.toString(i),x_pos + 3,APPLICATION_HEIGHT - 5);
			add(year);
			multiplier++;
		}
		
	}

	/* Method: createRows
	 * -------------------
	 * This will divide the window into three sections, the upper section, the mid section
	 * and the lower section
	 */

	private void createRows(){
		//first row that is closest to the top of the window
		GLine rowOne = new GLine(0, 0 + GRAPH_MARGIN_SIZE, APPLICATION_WIDTH,
		 0 + GRAPH_MARGIN_SIZE);
		//the second row that is closest to the bottom of the window
		GLine rowTwo = new GLine(0, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE, APPLICATION_WIDTH,
			APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE);
		add(rowOne);
		add(rowTwo);
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/

	public void clear() {
		//delete all entries from the list; basically clears the graph
		//create an iterator that is based on the array list
		list.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/

	public void addEntry(NameSurferEntry entry) {
		//adds a new name surfer entry
		//get the name of person in question
		String currentName = entry.getName();
		//get each decades value and map it on the graph
		//do this eleven times for the eleven values
		int columnNumber = 0;
		for(int i = 1900; i <= 2000; i+= 10){
			//the first score for the beginnng should be stored into an integer
			int firstRank = entry.getRank(i);
			//the second score for the next decade that follows it should be stored here
			if(i != 2000){
				int secondRank = entry.getRank(i + 10);
				list.add(graphLine(columnNumber,firstRank,secondRank,currentName));
			}
			//increment the column number by 1;
			columnNumber++;
		}
		//check whether or not the color count is at 3(Magenta)
		if(colourCount != 3){
			//increment the colour scheme by 1 for the next column
			colourCount++;
		}
		else{ //colour count is at 3
			colourCount = 0;
		} 
		//check if colour is at the last colour (magenta)
		update();
	}

	/* Method: graphLine
	 * ------------------
	 * This method will graph the lines for each decade based on the scores that were received
	 * for that name. It will also produce a GLabel that contains the name and its popularity
	 * value (in numbers).
	 */ 

	private GObject graphLine(int columnNumber,int firstRank, int secondRank, String currentName){
		//make the y points between each score smaller
		GCompound decadeScore = new GCompound();
		//next_y_point = firstRank;
		int x_pos = (APPLICATION_WIDTH / NDECADES);
		//get the label and check whether or not there should be an astericks indicating
		//a 0.
		addLabel(currentName,firstRank,decadeScore, columnNumber);
		//this will basically plot from the initial score to the second score; creating a line
		addLine(columnNumber,firstRank,secondRank,decadeScore);
		//check if we're at the second last column
		if(columnNumber == 9){
			//this will basically create the label for the last column and then it adds
			//to the gcompound
			GLabel last = new GLabel(currentName + " " + secondRank);
			decadeScore.add(last, x_pos * (columnNumber + 1) + 5, secondRank + 10);
		}
		//this will set the colour for the entry to the current count
		//note that there are 4 counts: 1 for black; 2 for red;
		//3 for blue; and 4 for magenta
		decadeScore.setColor(getColor());
		return decadeScore;
	}

	/* Method: getColor
	 * -----------------
	 * This method will return a colour based on the colorCount variable
	 * It will increment that instance variable by 1 everytime the method is called to indicate
	 * that the entry has been coloured.
	 */

	private Color getColor(){
		Color selectedColor = null;
		switch(colourCount){
			case 0: selectedColor = Color.black;
					break;
			case 1: selectedColor = Color.red;
					break;
			case 2: selectedColor = Color.blue;
					break;
			case 3: selectedColor = Color.magenta;
					break;
		}
		return selectedColor;
	}


	/* Method: addLabel
	 * -----------------
	 * This method will basically determine whether an astericks is warranted for the 
	 * name and rank and will return the given label according to the rank given.
	 *
	 */

	private void addLabel(String currentName, int firstRank, GCompound decadeScore, int columnNumber){
		//check whether the rank is at 0

		int x_pos = (APPLICATION_WIDTH / NDECADES);
		int y_baseline = APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE;
		if(firstRank == 0){
			//create a name that just has an astericks after the name
			firstRank = y_baseline;
			GLabel name = new GLabel(currentName + " *");
			decadeScore.add(name, x_pos * columnNumber,firstRank - 5);
		}
		//if the rank isn't at 0 then produce a normal looking label
		else{
			int y_pos = 0;
			GLabel name = new GLabel(currentName + " " + firstRank);
			//check if the rank is under the top 20
			if(firstRank <= 20){
				y_pos = 20;
			}
			else{ //rank is over 20
				y_pos = 5;
			}
			decadeScore.add(name, x_pos * columnNumber + 5,firstRank + y_pos);
		}
	}

	/* Method: addLine
	 * ---------------
	 * This method will get the line that intersects between the columns
	 *
	 */

	private void addLine(int columnNumber, int firstRank, int secondRank, GCompound decadeScore){
		int x_pos = (APPLICATION_WIDTH / NDECADES);
		//record the base-line score
		int y_baseline = APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE;
		if(firstRank == 0){
			firstRank = y_baseline;
			GLine line = new GLine(x_pos * columnNumber,
		 	firstRank
			,x_pos * (columnNumber + 1), y_baseline - secondRank);
			next_y_point =  y_baseline - secondRank;
			decadeScore.add(line);
		}
		else{
			int current_y_point = 0;
			if(columnNumber > 1){
				current_y_point =  next_y_point;
			}
			else{
				current_y_point = firstRank;
			}
			GLine line = new GLine(x_pos * columnNumber,
			current_y_point,
			x_pos * (columnNumber + 1),secondRank);
			//store the y point for which will be used for
			//the next rank's position.
			next_y_point = secondRank;
			//line.setColor(color);
			decadeScore.add(line);
		}
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//remove every object from the canvas
		removeAll();
		//create the lines that segments the years
		createLines();
		//create the labels that are placed beneath the columns
		createLabels();
		
		Iterator<GObject> it = list.iterator();
		while(it.hasNext()){
			add(it.next());
		}
	}
	
	
	/* Private Instance Variables */
	/* The array list that holds the values */
	private ArrayList<GObject> list = new ArrayList<GObject>();
	/* This will hold the  starting point for the next rank */
	private int next_y_point;
	/* This will be the color selector; it will be originally set to 0 */
	private int colourCount = 0;
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update();}
	public void componentShown(ComponentEvent e) { }
}

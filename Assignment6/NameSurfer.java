/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		//creates a new graph
		graph = new NameSurferGraph();
		//adds the graph to the canvas
		add(graph);
		//create the text field in the program
		createTextField();
		//create the buttons found in the program
		createButtons();
		//creates a data structure based on the given file
		database = new NameSurferDataBase(NAMES_DATA_FILE);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph")){
			//get the name from the text field and store it into a variable
			String name = text.getText();
			//initialises the entry instance to the given name
			entry = database.findEntry(name);
			//pass in the entry object if the entry object currently has an object
			if(entry != null){
				graph.addEntry(entry);
			}
			else{ //entry is set to null
			//	currentEntry = "The name " + name + " was not found.";
			}
		//	println("Graph: " + "\"" + currentEntry + "\"");
		}
		if(e.getActionCommand().equals("Clear")){
			//remove all the names from the canvas
			graph.clear();
		}
	}

/* Method: createTextField
 * ------------------------
 * This method will create the label along with the text field.
 */

	private void createTextField(){
		//This will create the label text
		add(new JLabel("Name: "),SOUTH);
		//This will create the text field
		text = new JTextField(15);
		//adds the text field into the canvas
		add(text,SOUTH);

	}

/* Method: createButtons
 * ------------------------
 * This method will create the buttons found in the program.
 */

	private void createButtons(){
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"),SOUTH);
		//adds the listener for button events
		addActionListeners();
	}


	/* Private Instance Variables */

	/* The text field */
	private JTextField text;

	/*The graph */
	private NameSurferGraph graph;
	
	/* This is the entry of the name */
	private NameSurferEntry entry;

	/* This is the name surfer database */
	private NameSurferDataBase database;

}

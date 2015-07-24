/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.util.*;
import java.io.*;
public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */

	public NameSurferDataBase(String filename) {
		//store the file name as a buffered reader object
		BufferedReader file = readFile(filename);
		//go through each line and store the data into a hashmap
		recordDatabase(file);
		
	}
	
/* Method: readFile
 * -----------------
 * This method will basically return a buffered reader of a file link
 */
	
	private BufferedReader readFile(String filename){
		BufferedReader file = null;
		try{
			file = new BufferedReader(new FileReader(filename));
		} catch(IOException io){
			System.out.println("Bad File.");
		}
		return file;
	}

/* Method: recordDatabase
 * -----------------------
 * This method will record each line of the database into an array
 */

	private void recordDatabase(BufferedReader file){
		nameList = new HashMap<String,String>();
		try{
			while(true){
				String line = file.readLine();
				if(line == null) break; //break when at the end of the file
				//feed the current line to to the name surfer entry constructor
				NameSurferEntry currentEntry = new NameSurferEntry(line);
				//add the name as the key and the entire entry as the value (name and scores)
				nameList.put(currentEntry.getName(),line);
			}
		} catch(IOException io){
			System.out.println("Bad Line");
		}
	}

	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */

	public NameSurferEntry findEntry(String name) {
		NameSurferEntry entry;
		//if the name isn't in the hashmap, set the entry object to null;
		if(!nameList.containsKey(name)){
			entry = null;
		}
		//this will basically first search the name in the hashmap
		//when it finds a proper key it will return the value of that name
		//it will then feed in the entire value of that name into the name surfer constructor
		else{
			entry = new NameSurferEntry(nameList.get(name));
		}
		return entry;
	}

	/* Private Instance Variables */

	private Map<String,String> nameList;
}


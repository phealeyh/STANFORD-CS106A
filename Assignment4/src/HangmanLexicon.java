/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.*;
import java.util.*;
	
public class HangmanLexicon {
	
	public HangmanLexicon(){
		BufferedReader rd = readFile(); //stores the lexicon file into the rd object.
		lexiconList = storeLines(rd); //reads the contents of the buffered reader and stores it into the array list.
	}
	
/* Method: readLexiconFile
 * -------------------------
 * This method will basically read the lexicon file and store it as a buffered reader object
 * It will finally return the buffered reader as an object.
 */
	
	private BufferedReader readFile(){
		BufferedReader rd = null; //sets it to the default value.
		try{ //try this solution before throwing an exception
			rd = new BufferedReader(new FileReader("hangmanlexicon.txt"));
		} catch(IOException io){
			System.out.println("File not found !"); // if an exception occurs; throw this message.
		}
		return rd; //return the object
	}
	
/* Method: readContents
 * ----------------------
 * This method will read the contents of the buffered reader object that is linked to a file/
 * It will read it line by line and store it into an array list.
 * It will then return this array list.
 */
	private ArrayList<String> storeLines(BufferedReader rd){
		ArrayList<String> lexiconList = new ArrayList<String>(); //creates a new array list
		try{
			while(true){
				String line = rd.readLine();
				if(line == null) break; //break out of the loop when you get to the end of the file.
				lexiconList.add(line);
			}
		} catch(IOException io){
			System.out.println("Bad File.");
		}
		return lexiconList;
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexiconList.size();
	}

/** Returns the word at the specified index. */
	public String getWord() {
		//generate a random word from the array
		//store into the word
		String word = lexiconList.get(rgen.nextInt(0,lexiconList.size())); //generates a random word in between 0 and the size of the array.
		return word;
	}
	/* Private Instance Variables */
	
	/* Array list */
	private ArrayList<String> lexiconList;
	
	/* Random Generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();

}

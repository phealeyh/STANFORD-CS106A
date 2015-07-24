/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		//assign the entry variable with the given line
		entry = line;
		//String dummy: this will just basically test the stub
		//entry = "Anika 0 0 0 0 0 0 0 991 0 943 628";
		//record the score of each decade into a hash map
	}

/* Method: recordDecades
 * -----------------------
 * This method will record the popularity of each decade and assign it to a hash map
 */

	private void recordDecades(){
		//create the hash map
		popularity = new HashMap<Integer,Integer>();
		//cut the string up to only include the data and not the name
		String data = entry.substring(entry.indexOf(" ") + 1);
		//create a for loop that loops 11 times (for each decade)
		for(int i = 0; i <= 10; i++){		
		int	lastPos = data.indexOf(" ");
			//get the position of the space found after a score
			if(i == 10){
				//get the end of the string
				lastPos = data.length();
			}
			//get the string starting from the 0 and right before the index of the first space
			int value = Integer.parseInt(data.substring(0,lastPos));
			//record the value into the hash map
			popularity.put(getDecade(i),value);
			if(i != 10){
				//cut the original string to exclude the recorded score
				data = data.substring(lastPos + 1);
			}
		}
	}

/* Method: getDecade
 * ------------------
 * This method will decide which decade to return based on the number it was given
 */

	private int getDecade(int decade){
		//this method will return the decade
		int year = 0;
		switch(decade){
			case 0: year = 1900;
					break;
			case 1: year = 1910;
					break;
			case 2: year = 1920;
					break;
			case 3: year = 1930;
					break;
			case 4: year = 1940;
					break;
			case 5: year = 1950;
					break;
			case 6: year = 1960;
					break;
			case 7: year = 1970;
					break;
			case 8: year = 1980;
					break;
			case 9: year = 1990;
					break;
			case 10: year = 2000;
					break;
		}
		return year;
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		//get the index of the first space
		int endOfName = entry.indexOf(" ");
		//get the name of the string
		String name = entry.substring(0,endOfName);
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		//creates the appropriate hashmap of the scores for each decade based on the entry
		recordDecades();
		// You need to turn this stub into a real implementation
		int value = popularity.get(decade);
		return value;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		//store a readable string form of the entry
		String name = entry.substring(0,entry.indexOf(" ")) + " ["
		  + entry.substring(entry.indexOf(" ") + 2) + "]"; 
		//return the simplified string
		return name;
	}

	/* Private instance variables */

	/* This is the hash map that holds the popularity of the name (in decades) */
	private Map<Integer,Integer> popularity;

	/* The entry that includes the name and the associated data */
	private String entry;
}


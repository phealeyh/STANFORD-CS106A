/* Program: Histograms
 * --------------------
 * This program will basically count and print out the amount of people that achieved x score.
 * 
 */
import java.util.*;
import java.io.*;

import acm.program.*;
import acm.util.ErrorException;

public class Histograms extends ConsoleProgram {
/* Class Variables */
	public void run(){
		makeHistogramArray();
		BufferedReader rd = readFile("Please enter the file you want read: ");
		readContents(rd);
		printTally();
	}
	
/* Method: makeHistogramArray
 * ---------------------------
 * This will basically make the array for the histogram.
 * 
 */
	private void makeHistogramArray(){
		histogramArray = new int[11]; //creates a 10-sized array for the score ranges
		for(int i = 0; i < histogramArray.length; i++){
			histogramArray[i] = 0;
			println(histogramArray[i]);
		}
	}
	
/* Method: readContents
 * --------------------
 * This method will basically tally the scores for each given score range.
 * It will then assign the values for each score to a given tally
 */
	private void readContents(BufferedReader rd){
		int number = 0;
		try{
			while(true){
				String line = rd.readLine();
				if(line == null) break; //break out of the loop because we know we have reached the end of the file.
				number = Integer.parseInt(line);
				if (number < 0 || number > 100) { //means that the score is out of range.
					throw new ErrorException("That score is out of range");
				}
				else{ //if it is in range 
					int range = number / 10;
					println("simplified score is " + range + " the actual number is " + number);
					histogramArray[range]++; //adds the tally to the array
				}
			}
		} catch(IOException ex){
			println("Error occurred.");
		}
	}

/* Method: readFile
 * ------------------
 * This file will basically ask the user for a file name.
 * It will then accept that file and link it up to the buffered reader object
 * The Buffered Reader object is then returned at the end of the method.
 * 
 */
	private BufferedReader readFile(String prompt){
		BufferedReader rd = null;
		while(rd == null){ //keep looping until rd isn't not; which means that rd is properly linked to a file.
			String name = readLine(prompt); //read the user input
			try{
				rd = new BufferedReader(new FileReader(name)); //link up file with the buffered reader
			} catch(IOException ex){ //only catch if there is an exception; in this case -- the file doesnt exist
				println("BAD FILE NAME DUDE"); 
			} //end catch
		}
		return rd; //return the file
	}
/* Method: printTally
 * -------------------
 * This method will basically print the tallied scores in a score-range format.
 * 
 */
	private void printTally(){
		int minRange = 0;
		int maxRange = 10;
		for(int i = 0; i < 10; i++){
			String tally = printTally(minRange + "-" + maxRange + ":", i);
			println(tally);
			minRange += 10;
			maxRange += 10;
		}
	}

/* Method: printTally
 * -------------------
 * This method will basically return a string for a specific range.
 * 
 */
	private String printTally(String range, int minRange){
		String tally = "";
		String stars = "";
		switch(minRange){
			case 0: for(int i = 0; i < histogramArray[0]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 1: for(int i = 0; i < histogramArray[1]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 2: for(int i = 0; i < histogramArray[2]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 3: for(int i = 0; i < histogramArray[3]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 4: for(int i = 0; i < histogramArray[4]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 5: for(int i = 0; i < histogramArray[5]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 6: for(int i = 0; i < histogramArray[6]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 7: for(int i = 0; i < histogramArray[7]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 8: for(int i = 0; i < histogramArray[8]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
			case 9: for(int i = 0; i < histogramArray[9]; i++){
						stars += "*";
					}
					tally = range + stars;
					break;
		}
		return tally;
	}
	
/* Instance Variables */

	/* This is the histogram array */
	private int[] histogramArray;
}
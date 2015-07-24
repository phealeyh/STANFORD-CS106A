/* Program: WordCount
 * --------------------
 * This program will firstly ask for a user input of a file.
 * It will then process this file and check how many words, characters and lines there are in the text file.
 * 
 */
import java.util.*;
import java.io.*;
import acm.program.*;

public class WordCount extends ConsoleProgram {
/* Class Variables */
	
	public void run(){
		int words = 0;
		int chars = 0;
		int lines = 0;
		BufferedReader rd = readFile("Enter the file you want opened: ");
		try{
			while(true){
				String line = rd.readLine();
				if(line == null) break; //breaks the line at the very end of the string.
				lines++; //we know that the readline method counts line by line so just increment this by one each time.
				words += countWords(line);
				println(line);
				chars += line.length();
			}
			rd.close();
		} catch(IOException ex){
			println("Error");
		}
		println("Lines: " + lines + "\n"
					+ "Characters: " + chars + "\n" +
					"Words: " + words);
	}

/*
* Asks the user for the name of an input file and returns a BufferedReader attached to its contents. If the file does not exist, the user is given another chance to try.
*/

	private BufferedReader readFile(String fileName){
		BufferedReader rd = null;
		while(rd == null){
			String name = readLine(fileName);
			try{
				rd = new BufferedReader(new FileReader(name));
			} catch (IOException ex){ //error output
				println("The file doesn't exist");
			}
		}
		return rd;
	}	

/* Method: countWords
 * -------------------
 * This method will basically count the amount of words found inside a single line
 * 
 */
	private int countWords(String line){
		int words = 0;
		boolean inWord = false;
		for(int i = 0; i < line.length(); i++){
			char ch = line.charAt(i);
			if(Character.isLetterOrDigit(ch)){
				inWord = true;
			} else {
				if(inWord) words++;
				inWord = false;
			}
		}
		if(inWord) words++;
		return words;
	}
/* Instance Variables */
}

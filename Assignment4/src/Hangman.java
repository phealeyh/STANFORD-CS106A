/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
    	boolean replayGame;
    	String secretWord;
    	println(greetUser());
    	do{
    		canvas.reset(); //this will basically reset the graphics panel to include the scaffold and the tiny bit of rope above the head
       		secretWord = chooseWord(); //get the secret word from the lexicon
        	revealedWord = showDashedWord(secretWord);
    		println(revealedWord);
    		playGame(secretWord);
    		//loop until the player does not want to play the game any longer
    		println("The secret word is " + secretWord);
    		//Decide whether the player wants to play another game
    		replayGame = replayGameResponse();
    	}while(replayGame == true);
    	println("Thanks for playing");
	}
    
	/* Method: playGame()
	 * -------------------
	 * This method will basically ask the user for a letter and check if it is correct.
	 * It will update the dashed version of the word accordingly if the user gets the correct guess.
	 * 
	 */
	
	private void playGame(String secretWord){
		String letter;
		//ask for the initial letter that the user wants to use as a guess.
		while(GUESS_COUNT != 0 && !revealedWord.equals(secretWord)){
		//asks the user for a letter
		letter = askForLetter();
			//This will check if the letter matches with any of the letters in the secret word
			if(letterMatchesWord(letter.charAt(0),secretWord)){
				println("MATCH FOUND !");
				//This will reveal the hidden letters
				revealedWord = addLetters(secretWord, letter.charAt(0)); //now have the revealed letter
				canvas.displayWord(revealedWord); //displays the word on the graphics panel
 				}
			//This means the user has guessed the wrong letter
			else{
				println("There are no " + letter + " 's" + " in the word." );
				GUESS_COUNT--;
				//The incorrect guess will result in showing the body part in the graphics canvas
				//it will show the incorrect guess and then displays it accordingly on the graphics canvas
				canvas.noteIncorrectGuess(letter.charAt(0));
			}
		//show the current word
    	println(revealedWord);
		//shows the current guesses the user has left
    	println(scoreUpdate(secretWord));
		}//end while loop
	}
	

	/* Method: addLetters
	 * --------------------
	 * This method will basically add the letters to the hidden message.
	 * 
	 */
	
	private String addLetters(String secretWord, char letter){
		String newText = "";
		int index = 0;
		char dash = '-';
		//This will loop until index reaches the end of the letter
			while(index != secretWord.length()){
				// if one of the positions of the character is equal to the letter; then add it in.
				if(secretWord.charAt(index) == letter){
					newText += letter;
					index++;
				}
				//if the current position does not equal to a letter; then add a dash ONLY if there isn't a letter in place for the revealed word.
				else{
					//if there are no dashes then add in the current letter
					if(revealedWord.charAt(index) != dash){
						newText += revealedWord.charAt(index);
						index++;
					}
					//if the revealed word has a dash then duplicate it
					else if(revealedWord.charAt(index) == dash){
						newText += "-";
						index++;
					}
				}
			} //end while
		return newText;
	}

	
	/* Method: letterMatchesWord
	 * ---------------------------
	 * This method will return true if there is a match found in the secret word.
	 * 
	 */
	
	
	private boolean letterMatchesWord(char letter, String secretWord){
		if(secretWord.indexOf(letter) != -1){ //match found
			return true;
		}
		else{
			return false;
		}
	}
	
	/* Method: askForLetter
	 * -----------------------
	 * This method will basically ask the user for a letter and it will check whether the letter is a proper single-character letter.
	 * 
	 */
	
	private String askForLetter(){
		String letter;
		do{
			letter = readLine("Enter any letter: ");
		}while(!isProperLetter(letter)); //checks the integrity of the letter and returns false if the letter is anything but a single-character letter.
		letter = letter.toUpperCase(); //this will capitalise the letter
		return letter;
	}
    
    /* Method: greetUser()
     * ---------------------
     * This method will basically greet the user and inform the user on the instructions of the game.
     * 
     */
    
    private String greetUser(){
    	return "This Hangman game will basically generate a secret word and show a dashed version of it."+
    				"\n" + "Your job is to guess the letters found in the word." +
    				"\n" + "You have 8 guesses.";
    }
    
    
    /* Method: chooseWord
     * -------------------
     * This method will basically choose a random word from the lexicon and use it as a secret word.
     * 
     */

	private String chooseWord(){
    	randomWord = new HangmanLexicon();
		String secretWord = randomWord.getWord();
		return secretWord;
	}
	
	/* Method: showDashedWOrd()
	 * -------------------------
	 * This will basically show the dashed-word version of the secret word.
	 * 
	 */
	
	private String showDashedWord(String secretWord){
		String dashedWord = "";
		char dash = '-';
		for(int i = 0; i < secretWord.length(); i++){ //generates a dash for every single character in the word
			if(secretWord.charAt(i) != dash){
			dashedWord += "-";
			} //end if
		}
		return dashedWord;
	}
	
		
	/* Method: isProperLetter()
	 * ----------------------
	 * This method will basically check the integrity of the letter.
	 * Letters can either be a lower case or upper case character.
	 * Letters cannot be anything other than letters.
	 * No numbers or illegal characters are allowed.
	 * 
	 */
	
	private boolean isProperLetter(String letter){
		for(int i = 0; i <= 10; i++){ //this will loop through every number and check if a number has been entered
			String s = "" + i; //this will convert the number to a string for comparison purposes.
			if(letter.equals(s)){
				println("A number has been entered. Please enter a proper letter.");
				return false;
			}
		}
		if(letter.length() >= 2){ //This means that the user has entered more than one letter.
			println("Please enter a single character.");
			return false;
		}
		else{ //user has entered a single-character letter.
			return true;
		}
	}
	
	/* Method: replayGameResponse
	 * ---------------------
	 * This method will basically return a true or false boolean if the user decides that he/she wants to play another game.
	 * It will also prompt the player about if he/she wants to play the game again.
	 * The string will then be processed in the if statements.
	 */

	private boolean replayGameResponse(){
		String response = "";
		do{
		response = readLine("Do you want to play again?");
		//keep looping if anything other than a yes or no is entered.
		}while(!(response.equals("YES") || response.equals("Y") || response.equals("y") || response.equals("yes") || response.equals("NO") || response.equals("no") || response.equals("n") || response.equals("N")));
		//this will basically change the response to an upper case.
		response = response.toUpperCase();	
		char answer = response.charAt(0);
		if(answer == 'Y'){
			GUESS_COUNT = 8;
			return true;
		}
		else{
			return false;
		}

	}

	
	/* Method: scoreUpdate()
	 * ----------------------
	 * This method just prints out how many guesses the user currently has.
	 * If the GUESS_COUNT is at 0 then it will return a losing message.
	 * 
	 */
	
	private String scoreUpdate(String secretWord){
		//if the user still has over 0 amounts of guesses left and has not revealed the word then tell the user how many guesses they have.
		if(GUESS_COUNT != 0 && !revealedWord.equals(secretWord)){
			return "You currently have " + GUESS_COUNT + " guesses left.";
		}
		//if the revealed word(the word the user has been guessing) is equal to the secret word then congratulate them.
		else if(revealedWord.equals(secretWord)){
			return "You've guessed the right word!";
		}
		else{ //guess count is actually at 0.
			return "You're completely hung.";
		}
	}

	/* Method: Init()
	 * ---------------
	 * This method will basically create the canvas object and then adds it to the canvas.
	 *
	 */
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	

	/* Instance Variables */
	
	/* This will be the hidden word that gets partially revealed after a correct guess */
	private String revealedWord;
	
	/* The Hangman canvas */
	private HangmanCanvas canvas;

	/* The random lexicon word */
	private HangmanLexicon randomWord;

	/* This will be the number of turns the user has */
	private int GUESS_COUNT = 8;


}

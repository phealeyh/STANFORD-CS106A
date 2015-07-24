/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
/* Instance Variables */

private GLabel hiddenWord, guessedLetters;

/* This will be the String of incorrect guesses that user has so far guessed */
private String incorrectGuesses; 

/* This will count the body parts */
private int PART_NUMBER = 0;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		PART_NUMBER = 0; //RESETS THE BODY PART COUNT
		removeAll();
		incorrectGuesses = "";
		createScaffold();
		createLabels();
	}

/* Method: createScaffold()
 * -------------------------
 * This method will basically create the scaffold and beam
 * along with the bit of rope above the player's head.
 * 
 */
	private void createScaffold(){
		//this will basically create the scaffold
		double SCAFFOLD_X_START = APPLICATION_WIDTH / 3;
		double SCAFFOLD_Y_START = APPLICATION_HEIGHT / 2;
		GLine scaffold = new GLine(SCAFFOLD_X_START,SCAFFOLD_Y_START, SCAFFOLD_X_START, SCAFFOLD_Y_START - SCAFFOLD_HEIGHT);
		add(scaffold);
		//this will basically create the beam
		GLine beam = new GLine(SCAFFOLD_X_START, SCAFFOLD_Y_START - SCAFFOLD_HEIGHT, SCAFFOLD_X_START + BEAM_LENGTH, SCAFFOLD_Y_START - SCAFFOLD_HEIGHT);
		add(beam);
		//this will basically create the rope that is found underneath the beam
		GLine rope = new GLine(SCAFFOLD_X_START + BEAM_LENGTH, (SCAFFOLD_Y_START - SCAFFOLD_HEIGHT) + ROPE_LENGTH, SCAFFOLD_X_START + BEAM_LENGTH, SCAFFOLD_Y_START - SCAFFOLD_HEIGHT);
		add(rope);
	}
	

/* Method: createLabels()
 * ------------------------
 * This method will basically declare and create the object labels.
 * It will include two labels for both the hidden word (that becomes partially revealed)
 * and the guessed word label which shows the user's list of guessed words.
 * 
 */

	private void createLabels(){
		//Creates the hidden word label (but doesn't add it to the canvas)
		hiddenWord = new GLabel("",APPLICATION_WIDTH / 3, (APPLICATION_HEIGHT / 2) + 50);
		hiddenWord.setFont("SERIF-20");
		//Creates the guessed letters label (but doesn't add it to the canvas)
		guessedLetters = new GLabel("",APPLICATION_WIDTH / 3, (APPLICATION_HEIGHT / 2) + 50 + hiddenWord.getAscent() * 2);
		guessedLetters.setFont("SERIF-20");

	}

/*
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		//this will set the label to whatever the revealed word is so far
		hiddenWord.setLabel(word);
		add(hiddenWord);
	}

/*
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		//this method also prints out the incorrect letter in the canvas
		recordLetter(letter);
		//This method will add the body part onto the canvas.
		addBodyPart();
		//increments the part number to the next one
		PART_NUMBER++;
	}

/* Method: addBodyPart
 * --------------------
 * This method will basically add in the individual body part onto the canvas.
 * 
 */

	private void addBodyPart(){
		//These two points will be used as a reference point.
		double ROPE_X_POINT = (APPLICATION_WIDTH / 3) + BEAM_LENGTH;
		double ROPE_Y_POINT = ((APPLICATION_HEIGHT / 2) - SCAFFOLD_HEIGHT) + ROPE_LENGTH;
		double HEAD_X = ROPE_X_POINT - (HEAD_RADIUS / 2);
		double HEAD_Y = ROPE_Y_POINT;
		double BODY_Y_REFERENCE = HEAD_Y + HEAD_RADIUS;
		switch(PART_NUMBER){
			case 0: 
					GOval head = new GOval(HEAD_X,HEAD_Y,HEAD_RADIUS,HEAD_RADIUS);
					add(head);
					break;
			case 1: 
					GLine body = new GLine(ROPE_X_POINT, HEAD_Y + BODY_LENGTH + HEAD_RADIUS, ROPE_X_POINT, HEAD_Y + HEAD_RADIUS);
					add(body);
					break;
			case 2: 
				 	GLine leftUpperArm = new GLine(ROPE_X_POINT, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD, ROPE_X_POINT + UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD);
	 				add(leftUpperArm);
					GLine leftLowerArm = new GLine(ROPE_X_POINT + UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH, ROPE_X_POINT + UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD);
					add(leftLowerArm);
					break;
			case 3: 
				 	GLine rightUpperArm = new GLine(ROPE_X_POINT - UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD, ROPE_X_POINT, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD);
	 				add(rightUpperArm);
	 				GLine rightLowerArm = new GLine(ROPE_X_POINT - UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH, ROPE_X_POINT - UPPER_ARM_LENGTH, BODY_Y_REFERENCE + ARM_OFFSET_FROM_HEAD);
					add(rightLowerArm);
					break;
			case 4: 
					GLine leftTorso = new GLine(ROPE_X_POINT, BODY_Y_REFERENCE + BODY_LENGTH,ROPE_X_POINT + HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH);
					add(leftTorso);
					GLine leftLeg = new GLine(ROPE_X_POINT + HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH, ROPE_X_POINT + HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH + LEG_LENGTH);
					add(leftLeg);
					break;
			case 5: 
					GLine rightTorso = new GLine(ROPE_X_POINT - HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH, ROPE_X_POINT, BODY_Y_REFERENCE + BODY_LENGTH);
					add(rightTorso);
					GLine rightLeg = new GLine(ROPE_X_POINT - HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH + LEG_LENGTH, ROPE_X_POINT - HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH);
					add(rightLeg);
					break;
			case 6: 		
					GLine leftFoot = new GLine(ROPE_X_POINT + HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH + LEG_LENGTH, ROPE_X_POINT + HIP_WIDTH + FOOT_LENGTH, BODY_Y_REFERENCE + BODY_LENGTH +LEG_LENGTH);
					add(leftFoot);
					break;
			case 7:
					GLine rightFoot = new GLine(ROPE_X_POINT - HIP_WIDTH - FOOT_LENGTH, BODY_Y_REFERENCE + BODY_LENGTH + LEG_LENGTH, ROPE_X_POINT - HIP_WIDTH, BODY_Y_REFERENCE + BODY_LENGTH + LEG_LENGTH);
					add(rightFoot);
					break;
		}
	}
	
/* Method: recordLetter
 * ----------------------
 * This method will simply record the incorrect letter and displays it accordingly on the screen.
 * It helps the user keep track of his/her incorrect guesses.
 * 
 */
	private void recordLetter(char letter){
		//records the character and appends it onto the list of guessed letters.
		//sets the font label to the list of incorrect characters
		incorrectGuesses += letter;
		guessedLetters.setLabel(incorrectGuesses);
		add(guessedLetters);
	}
	
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	//this is basically the difference between the heads position at the shoulders
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int APPLICATION_HEIGHT = SCAFFOLD_HEIGHT * 3;
	private static final int APPLICATION_WIDTH = BEAM_LENGTH * 3;

}

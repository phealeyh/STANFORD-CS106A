/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		//nplayers will be used to set the maximum size of the array
		//Note: this should initally be set to 1 for testing purposes.
		//Suggestion: maybe put this in a do-while loop so that it only accepts
		//2 players and less
		nPlayers = dialog.readInt("Enter number of players");
		//return an array of strings based on the number of players
		//the array might possibly have to be larger by 1 in size
		//this is because we have an index that is counting from 1 instead of 0.
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i); 
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	

	private void playGame() {
		
		//this will keep track of the turns found in each round of rolls
		int turns = 13;
		//the first player will have the first roll of the dice
		playerNumber = 1;
		playerScores = new int[nPlayers + 1][N_CATEGORIES + 1]; //first player index is at 1
		//creates the boolean array for the player-chosen categories
		//the array will basically record true on all chosen categories that are given a score
		// (including 0) and will set everything as false.
		chosenCategories = new boolean[nPlayers + 1][N_CATEGORIES + 1];
		//keep looping until all players have filled up the combinations
		while(true){
		//prompts whoever's turn it is to roll
		promptPlayer();
		completeTurn();
		playerNumber++;
			//check to see if we have passed the last player
			if(playerNumber == nPlayers + 1){
				//at the end of a round when all players have rolled, you should reduce
				//the turn count by 1 because we know that everyone (even in a single player game)
				//has selected one category
				turns--;
				if(turns == 0) break;
				//means that we've passed the final player and should
				//go back to the first player
				playerNumber = 1;
			}
		}
		display.printMessage(showWinner());
	}
	
	/* Method: showWinner
	 * ---------------------
	 * This method will basically return a string of the winner;
	 * It will also show the total score of that player.
	 */

	private String showWinner(){
		//sets the last players highest score
		int largest = playerScores[nPlayers][17];
		String playerName = playerNames[nPlayers - 1]; //this will be the last player
		//loop through the arrays length
		for(int i = 1; i <= nPlayers; i++){
			int num = playerScores[i][17]; 
			if(num > largest){
				largest = num;
				playerName = playerNames[i - 1];
			}
		}
		return "Congratulations, " + playerName + ". You're the winner with a total score of " +
			largest + ".";
	}


	/* Method: promptPlayer
	 * ---------------------
	 * This method will inform which player's turn it is
	 *
	 */

	private void promptPlayer(){
		//loop through the players name array
		for(int i = 0; i < nPlayers; i++){
			if((i + 1 ) == playerNumber){
				String name = playerNames[i];
				display.printMessage("It is currently " + name.toUpperCase() + "'S turn." +
				 "Click the 'Roll Dice' button to roll the dice.");
			}
		}
	}
	
	/* Method: completeTurn
	 * ---------------------
	 * This method will basically allow the user to roll his/her 3 dices
	 * with the ability to choose which dices he/she wants rolled on the 2nd and 3rd rolls.
	 * It will then allow that player to choose a combination found on the list.
	 */

	private void completeTurn(){
		display.waitForPlayerToClickRoll(playerNumber);
		//how many rolls the player intially has
		int playerRolls = 3;
		//these are the dices and the values they hold
		int[] dices = new int[N_DICE];
		//keep looping until the player runs out of rolls
		while(playerRolls != 0){
			//sets the dices to a randomised value
			dices = rollDices(dices, playerRolls);
			playerRolls--;
			display.printMessage("Select the dice you wish to re-roll and click 'Roll again'.");
		}
		display.printMessage("Select a category for this roll.");
		//Allow the user to choose the available combinations.
		int selectedCategory = getCategory(dices);
		//calculate the actual score and add it to the array position
		int categoryScore = calculateScore(selectedCategory, dices);
		//update the score card with the category, player and respective score.
		display.updateScorecard(selectedCategory, playerNumber, categoryScore);
		//check if player has filled in the entire upper or lower section
		checkFilledCategories();
	}


	/* Method: checkFilledCategories
	 * ------------------------------
	 * This method will check whether the lower section or upper section categories are filled
	 * if they're then the total scores will be printed out for their respective sections
	 */

	private void checkFilledCategories(){
		int upperSum = 0;
		int lowerSum = 0;
		if(isUpperSectionFilled()){
			int bonusTotal = 0;
			upperSum = getUpperSectionTotal();
			if(upperSum >= 63){ //checks whether the total is eligible for a bonus score
				bonusTotal = 35;
				upperSum += bonusTotal;
				//update the sum category
				display.updateScorecard(UPPER_SCORE, playerNumber, upperSum);
			}
			//no bonuses awarded
			else{
				display.updateScorecard(UPPER_SCORE, playerNumber, upperSum);
			}
			display.updateScorecard(UPPER_BONUS, playerNumber, bonusTotal);
			//update the display score; the selected category will be 
		}
		if(isLowerSectionFilled()){
			lowerSum = getLowerSectionTotal();
			display.updateScorecard(LOWER_SCORE, playerNumber, lowerSum);
			//upadte the display score
		}
		//both sections are filled; calculate the total for that player
		if(isLowerSectionFilled() && isUpperSectionFilled()){
			int totalScore = upperSum + lowerSum;
			playerScores[playerNumber][17] = totalScore;
			display.updateScorecard(TOTAL, playerNumber, totalScore);
		}
	}

	/* Method: getUpperSectionTotal
	 * -----------------------------
	 * This method will get the sum of all the scores in the upper section
	 *
	 */

	private int getUpperSectionTotal(){
		//declare a total variable 
		int total = 0;
		//loop through the entire array and append the scores from 1 to 6
		for(int i = 1; i < 7; i++){
			total += playerScores[playerNumber][i];
		}
		return total;
		//return the total score
	}

	/* Method: getLowerSectionTotal
	 * -----------------------------
	 * This method will get the sum of the lower section score total and return it as an integer
	 *
	 */

	private int getLowerSectionTotal(){
		int total = 0;
		for(int i = 9; i < 16; i++){
			total += playerScores[playerNumber][i];
		}
		return total;
	}

	/* Method: isUpperSectionFilled
	 * -----------------------------
	 * This method will go through the player's category array from 1-6
	 */

	private boolean isUpperSectionFilled(){
		for(int i = 1; i < 7; i++){ //loops 6 times (1-6)
			if(chosenCategories[playerNumber][i] == false){
				return false;
			}
		}
		//if it get this far without reaching a false statement then the categories must
		//be filled
		return true;
	}

	/* Method: isLowerSectionFilled
	 * -----------------------------
	 * This method will check whether the categories 9 - 16 are already filled
	 *
	 *
	 */
	private boolean isLowerSectionFilled(){
		for(int i = 9; i < 16; i++){
			if(chosenCategories[playerNumber][i] == false){
				return false;
			}
		}
		return true;
	}

	/* Method: calculateScore
	 * -----------------------
	 * This method will basically calculate the score for a given category
	 * It will do this by comparing the patterns found within the categories
	 * 
	 */

	private int calculateScore(int category, int[] dices){
		int score = 0;
		//category is in the high order
		if(category <= 6){
			if(category == 1){
				score = getSingleSum(1, dices);
				playerScores[playerNumber][category] = score;
			}
			else if(category == 2){
				score = getSingleSum(2,dices);
				playerScores[playerNumber][category] = score;
			}
			else if(category == 3){
				score = getSingleSum(3, dices);
				playerScores[playerNumber][category] = score;
			}
			else if(category == 4){
				score = getSingleSum(4, dices);
				playerScores[playerNumber][category] = score;

			}
			else if(category == 5){
				score = getSingleSum(5, dices);
				playerScores[playerNumber][category] = score;
			}
			else if(category == 6){
				score = getSingleSum(6, dices);
				playerScores[playerNumber][category] = score;
			}
		}
		//category is in the low order
		else if(category >= 9){
			String arrangedDice = sort(dices);
			//Three of a kind found
			if(category == 9 && diceOccurrence(arrangedDice) == 3){
				//The score is the sum of all dices
				score = getSum(dices);
				playerScores[playerNumber][category] = score;
			}
			//Four of a kind found
			else if(category == 10 && diceOccurrence(arrangedDice) == 4){
				//The score is the sum of all dices
				score = getSum(dices);
				playerScores[playerNumber][category] = score;
			}
			else if(category == 11 && isFullHouse(arrangedDice)){
				//score = 25
				playerScores[playerNumber][category] = 25;
				score = 25;
			}
			//small straight set found
			else if(category == 12 && isDiceSmallStraight(arrangedDice)){
				playerScores[playerNumber][category] = 30;
				score = 30;
			}
			//large straight set found
			else if(category == 13 && isDiceLargeStraight(arrangedDice)){
				playerScores[playerNumber][category] = 40;
				score = 40;
			}
			//yahtzee is found; 5 of a kind !
			else if(category == 14 && diceOccurrence(arrangedDice) == 5){
				//if a yahtzee is found then the score will be 50;
				playerScores[playerNumber][category] = 50;
				score = 50;
			}
			//chance is chosen; this is basically the sum of all the dices
			else if(category == 15){
				score = getSum(dices);
				playerScores[playerNumber][category] = score;
			}
		} //end else if
		//records the category chosen and sets it to true so it won't ever be used again.
		chosenCategories[playerNumber][category] = true;
		return score;
	}

	/* Method: getSingleSum
	 * ----------------------
	 * This method will multiply the targetted value with its frequency e.g. 1,1,1,3,3
	 * will be calculated as 1 x 3 giving a total value of 3.
	 */

	private int getSingleSum(int targetValue, int[] dices){
		int sum = 0;
		//go through the array and add up all the times it occurs
		for(int i = 0; i < dices.length; i++){
			if(dices[i] == targetValue){
				sum += targetValue;
			}
		}
		return sum;
	}

	/* Method: getSum
	 * ---------------
	 * This method will get the total value of the 5 rolled dices.
	 *
	 */

	private int getSum(int[] dices){
		//hold a variable that counts the total values of the dices
		int total = 0;
		//loop through the entire array while appending the value to the variable
		for(int i = 0; i < dices.length; i++){
			total += dices[i];
		}
		return total;
	}

	/* Method: isFullHouse
	 * --------------------
	 * This method will check if the dices rolled consist of a pair and 
	 * a 3-of-a-kind combination.
	 *
	 */

	private boolean isFullHouse(String dices){
		//this will keep track of any pairs
		int pair = 0;
		for(int i = 0; i < dices.length(); i++){
			//keep looping until the end of the dice
			int n1 = dices.charAt(i);
			for(int j = 0; j < dices.length(); j++){
				int n2 = dices.charAt(j);
				//pair found
				if(n1 == n2 && pair <= 2){ pair++;};
			}
			if(pair == 2){
				//test if theres a full house
				if(diceOccurrence(dices) == 3){
					return true;
				}
			}
			else if(pair != 2){
				pair = 0;
			}
		}
		//no full house was found
		return false;
	}

	/* Method: diceOccurrence
	 * -----------------------
	 * This method will decide how many times (if any) a certain value is repeated.
	 * It will return either a 3,4 or 5 if any of the dices is repeated that frequently
	 * It will also return a minimum of 2 if a dice is occurs twice.
	 */

	private int diceOccurrence(String arrangedDice){
		int occurrences = 0;
		//make a int variable that will count how many occurrences are in a letter
		//create two for loops that firstly assigns the current number and make it go through
		//the array looking for a possible match.
		for(int i = 0; i < arrangedDice.length(); i++){
			//get the first target number that you want compared
			int n1 = arrangedDice.charAt(i);
			for(int j = 0; j < arrangedDice.length(); j++){
				//go through the array and compare the assigned number with the numbers
				//in the array.
				int n2 = arrangedDice.charAt(j);
				//compare the two numbers
				if(n1 == n2) occurrences++;
			} //end for
			//now check if the occurrences variable is at 3.
			if(occurrences == 3){
				return occurrences;
			}
			//check if a four-of-a-kind has occurred.
			else if(occurrences == 4){
				return occurrences;
			}
			//check for 5 of a kind (YAHTZEE)
			else if(occurrences == 5){
				return occurrences;
			}
			else{ //no three-of-a-kind or four-of-a-kind found for that number
				occurrences = 0;
			}
		} //end for
		//if it gets to here then there are no three-of-a-kinds, four-of-a-kinds or a yahtzee.
		return occurrences;
	}

	/* Method: sort
	 * --------------------
	 * This method will basically re-arrange the dices found in the original 
	 * array from smallest to largest and it will print it out 
	 */

	private String sort(int[] dices){
		String sortedDice = "";
		for(int i = 0; i < dices.length; i++){
			int smallest = findSmallest(dices, i, dices.length);
			swapElements(dices, i, smallest);
		}
		
		for(int i = 0; i < dices.length; i++){
			sortedDice += dices[i];
		}
		return sortedDice;
	}

	/* Method: findSmallest
	 * ----------------------
	 * This method will basically find the smallest number in the array and re-arrange it
	 * appropriately.
	 */


	private int findSmallest(int[] dices, int p1, int p2){
		int smallestIndex = p1;
		for(int i = p1 + 1; i < p2; i++){
			if(dices[i] < dices[smallestIndex]) smallestIndex = i;
		}
		return smallestIndex;
	}

	/* Method: swapElements
	 * ---------------------
	 * This method will basically swap the elements around to make the arrange the array's
	 * elements from smallest to largest.
	 */
	private void swapElements(int[] array, int p1, int p2){
		int temp = array[p1];
		array[p1] = array[p2];
		array[p2] = temp;
	}
	
	/* Method: isDiceSmallStraight
	 * ----------------------------
	 * This method will deteremine whether a small straight exists
	 * It does this by comparing the string to the three other small straights.
	 */

	private boolean isDiceSmallStraight(String dices){
		String cutDice = cutDiceSize(dices);
		//this will cut the dice to an appropriate size if it hasn't already been done so
		if(cutDice.length() == 5){
			cutDice = cutDice.substring(0,4);
		}
		String smallStraight = "";
		for(int i = 1; i <= 3; i++){
			for(int j = 0 + i; j <= (4 + i) - 1; j++){
				smallStraight += j;
			}
			if(cutDice.equals(smallStraight)){
				//we have a match
				return true;
			}
			else{
				smallStraight = "";
			}
		} 
		 //if it gets to the end of the for loop without a match, then the dice does not equal
		 // equal a small straight set.
		return false;
	}
	/* Method: cutDiceSize
	 * ----------------------
	 * This method will cut the dice size to match up with the small straight
	 *
	 */
	
	private String cutDiceSize(String dices){
		for(int i = 1; i < dices.length() - 1; i++){
			if(dices.charAt(i) == dices.charAt(i - 1)){
				dices = dices.substring(0,i - 1) + dices.substring(i);
			}
		}
		return dices;
	}

	/* Method: isDiceLargeStraight
	 * ----------------------------
	 * This method will determine whether a large straight set exists
	 * It will do this by comparing the contents of the dices with the large
	 * sets found in the game.
	 */
	
	private boolean isDiceLargeStraight(String dices){
		String largeStraight = "";
		for(int i = 1; i <= 2; i++){
			for(int j = 0 + i; j <= (5 + i) - 1; j++){
				largeStraight += j;
			}
			if(dices.equals(largeStraight)){
				//we have a match
				return true;
			}
			else{
				largeStraight = ""; //reset the string to its original state
			}
		} //if it gets to the end of the for loop without a match, then the dice does not equal
		 // equal a large straight set.
		return false;
	}


	/* Method: getCategory
	 * ---------------------
	 * This method will interpret the users click on a category as an int variable
	 * This variable will then be analysed and a decision will be made on whether or not
	 * the category chosen is a valid one.
	 * If it meets the conditions then it will return this category.
	 * 
	 */
	
	private int getCategory(int[] dices){
		int category = 0;
		do{
		//get the users chosen category
		category = display.waitForPlayerToSelectCategory();
		//this while loop checks whether the selected category is already occupied
		//in the array; it returns a true if the category is already filled(true)
		// which in turn will allow the player to choose another category that is not filled
		// (set to false).
		}while(isCategoryOccupied(category) == true);
		//it will only return a category if it knows the category has not yet been scored
		return category;
	}

	/* Method: isCategoryOccupied
	 * ------------------------
	 * This method will check whether the category that a player has chosen does not have any
	 * valid scores in them; if it does then it will return a null indicitating that
	 * the category is available for usage.
	 */

	private boolean isCategoryOccupied(int category){
		//if the category chosen is set to true then return true
		if(chosenCategories[playerNumber][category] == true){
			//if the space is occupied then return true
			return true;
		}
		//else if the space is set to false(not occupied) then return a false
		else{
			return false;
		}
	}


   /* Method: rollDices
	* --------------------
	* This method will roll the dices and generate the random values for the selected indexes
	* It will generate random values for all the dices if the player is on his first roll
	*/

	private int[] rollDices(int[] dices, int playerRolls){
		//player is on his 3rd roll
		if(playerRolls == 3){
			for(int i = 0; i < N_DICE; i++){
				//randomly generate a value for every dice
				dices[i] = rgen.nextInt(1,6);
			}
		}
		//player is on his 2nd or last roll
		else if(playerRolls != 3){
			//waits for the player to select the dices he/she wants re-rolled
			display.waitForPlayerToSelectDice();
			dices = rollSelectedDice(dices);
		}
		//displays the values of the dices
		display.displayDice(dices);
		return dices;
	}

	/* Method: rollSelectedDice
	 * -------------------------
	 * This method will generate random values for all the dices
	 * that are selected by the player to be re-rolled.
	 */

	private int[] rollSelectedDice(int[] dices){
		for(int i = 0; i < N_DICE; i++){
			//check whether the index for a dice is selected
			if(display.isDieSelected(i)){
				dices[i] = rgen.nextInt(1,6);
			}
		}
		return dices;
	}

/* Private instance variables */

	/* These are the chosen categories*/
	private boolean[][] chosenCategories;

	/* The player scores found in the game */
	private int[][] playerScores;

	/* Amount of players in the game */
	private int nPlayers;

	/* The player's number */
	private int playerNumber;

	/* The array holding the name of the players */
	private String[] playerNames;

	/* Creates the display of the game */
	private YahtzeeDisplay display;

	/* The random generator */
	private RandomGenerator rgen = new RandomGenerator();

}

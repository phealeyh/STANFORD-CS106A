/* File: CheckboardKarel.java
 * ---------------------------
 * This program will basically go through an entire row and land checkers on every 2nd cell.
 * This process will be alternated on each row.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		while (facingWest() || facingEast()) {
			if (frontIsBlocked()) {
				turnKarelNorth();
			} //end if
			transverseRow();
			transverseEvenRow();
		} //end while
	}

	/*
	 * Method: turnKarelNorth
	 *  ---------------------- 
	 * Pre-condition: The program is in a 8x1 (1 column) world and Karel has no choice but to turn north
	 * Post-condition: This makes Karel turn left and face north
	 */
	private void turnKarelNorth() {
		putBeeper();
		turnLeft();
		moveUpwards();
	}
	
	/*
	 * Method: moveUpwards
	 *  ---------------------- 
	 *  This makes Karel lay down the beepers every 2 moves
	 */


	private void moveUpwards() {
		while (facingNorth()) {
			move();
			move();
			putBeeper();
		}
	}
	/*
	 * Method: transverseRow
	 *  ---------------------- 
	 *  Pre-Condition:Karel is facing east and is ready to place the beeprs on the first odd row
	 *  Post-Condition: Karel places all the beepers every 2 steps on the first row
	 */

	private	void transverseRow() {
		while (facingEast()) {
			putBeeper();
			move();
			isFrontClear();
			isFrontBlocked();
		} // end while
	}
	
	/*
	 * Method: isFrontClear (if statements)
	 *  ---------------------- 
	 *  This checks whether Karel is being obstructed.
	 *  She takes anther move if the front is clear.
	 *  Otherwise, if she is blocked, she executes placeBeeperOnNextRow
	 */

	private void isFrontClear() {
		if (frontIsClear()) {
			move();
		} // end if
		else if (frontIsBlocked()) {
			placeBeeperOnNextRow();
		} // end else
	}
	
	/*
	 * Method: isFrontBlocked (if statement)
	 *  ---------------------- 
	 *  This checks whether Karel is being obstructed.
	 *  She puts the beeper and places a final beeper on the row.
	 */

	private void isFrontBlocked() {
		if (frontIsBlocked()) {
			putBeeper();
			placeLastBeeperOnRow();
		}
	}
	
	/*
	 * Method: placeLastBeeperOnRow (if statement)
	 *  ---------------------- 
	 *  This checks if Karel is facing east
	 */

	private void placeLastBeeperOnRow() {
		if (facingEast()) {
			ascendOnOddColumn();
		}
	}
	/*
	 * Method: placeBeeperOnNextRow (if)
	 *  ---------------------- 
	 *  This method makes Karel ascend at the end of an even column only if she is facing east
	 */

	private void placeBeeperOnNextRow() {
		if (facingEast()) {
			ascendOnEvenColumn();
		}
	}

	/*
	 * Method: ascendOnEvenColumn
	 *  ---------------------- 
	 *  This method makes Karel climb up the row and place a beeper on the new row
	 */

	private void ascendOnEvenColumn() {
		turnLeft();
		move();
		putBeeper();
		turnLeft();
		move();
	}

	/*
	 * Method: ascendOnOddColumn 
	 * ---------------------- 
	 * This method makes Karel
	 * climb up the row and place a beeper on the new row
	 */

	private void ascendOnOddColumn() {
		turnLeft();
		move();
		turnLeft();
	}
	
	/*
	 * Method: transverseEvenRow
	 *  ---------------------- 
	 *  Pre-condition: Karel lays the checkers on the first row and is now on the 2nd row
	 *  Post-condition: Karel lays the checkers alternately on every 2 steps
	 */

	private void transverseEvenRow() {
		while (facingWest()) {
			move();
			putBeeper();
			isFrontClear();
			isFrontBlockedForEvenRow();
		}
	}
	/*
	 * Method: isFrontBlockedForEvenRow
	 *  ---------------------- 
	 *  This checks whether Karel (on an even row) is being blocked
	 *  It is in between the steps to help Karel move step-by-step without hitting a wall (which would break the loop)
	 * 	 
	 *  */

	private void isFrontBlockedForEvenRow() {
		if (frontIsBlocked()) {
			turnRight();
			move();
			turnRight();
		} //end if
	}

}

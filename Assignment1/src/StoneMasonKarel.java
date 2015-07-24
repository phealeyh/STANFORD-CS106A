/*File:StoneMasonKarel.java
 * -------------------------
 * The StoneMasonKarel program will first the 4 columns on each row.
 * Once it reaches the end of the row, it determines whether or not it is facing west or east.
 * It will then perform a turn and climb maneuver and start again on the next row.
 * The program ceases to exist when Karel's front is not clear.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() { // level 1
		while (facingEast()) {
			fixColumn(); // push to 2nd
			moveToNextColumn(); // push to 2nd
		} // end while
	}

	/*
	 * Method: fixColumn ----------------- Pre-condition: Karel must be in a
	 * position where the front is clear Post-condition: Karel repairs the
	 * column it is currently in
	 */

	private void fixColumn() { // 2nd lvl
		faceNorth(); // push to 3rd
		checkInitialBeeper(); // push to 3rd
		buildColumn(); // push to 3rd
		faceSouth(); // push to 3rd
		moveToStart(); // push to 3rd
		faceEast(); // push to 3rd
	} // end method

	/*
	 * Method: moveToNextColumn ------------------------- Move to the next
	 * column by moving 4 steps.
	 */
	private void moveToNextColumn() { // 2nd lvl
		for (int i = 0; i < 4; i++) {
			move();
		} // end for
	}

	/*
	 * Method: checkInitialBeeper ------------------ Check the beeper at the
	 * inital position
	 */

	private void checkInitialBeeper() { // 3rd lvl
		if (noBeepersPresent()) {
			putBeeper();
		}
	}

	/*
	 * Method: faceNorth ------------------ This method makes Karel face upwards
	 * to view the column
	 */

	private void faceNorth() { // 3rd lvl
		turnLeft();
	}

	/*
	 * Method: buildColumn ------------------ This method starts to place the
	 * beepers in the column
	 */

	private void buildColumn() { // 3rd lvl
		while (frontIsClear()) {
			checkBeeper(); // push to 4th
			moveUp(); // push to 4th
			checkBeeper(); // push to 4th
		} // end while
	}

	/*
	 * Method: checkBeeper ------------------ Pre-condition: The front of karel
	 * is clear Post-condition: Puts a beeper if there is none present
	 */
	private void checkBeeper() { // 4th lvl
		if (noBeepersPresent()) {
			putBeeper();
		} // end if
	}

	/*
	 * Method: moveUp ------------------ Karel simply moves in an upward
	 * direction.
	 */
	private void moveUp() { // 4th lvl
		move();
	}

	/*
	 * Method: faceSouth ------------------ Pre-condition: Karel completes the
	 * restoration of the column Post-Condition: Karel faces south
	 */
	private void faceSouth() { // 3rd lvl
		for (int i = 0; i < 2; i++) {
			turnRight();
		} // end for
	}

	/*
	 * Method: moveToStart ------------------ Karel moves back down.
	 */

	private void moveToStart() { // 3rd lvl
		while (frontIsClear()) {
			move();
		}
	}

	/*
	 * Method: faceEast() ------------------ Pre-condition: Karel is at the
	 * starting position Post-Condition: Karel is now viewing to the east
	 */

	private void faceEast() { // 3rd lvl
		turnLeft();
	}

}

/*File: MidpointFindingKarel.java
 * -------------------------------
 * Karel will first start in the world and start laying the beepers across the row.
 * Once she hits the final wall she flips around.
 * She then starts cleaning up the beepers until she hits the middle of the row.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		checkInitialWorld();
		layCheckersEastwards();
		collectCheckers();
	}
	
	/*Method: checkInitialWorld
	 * ------------------------
	 * If Karel is in a one-column world or 2-column world
	 * then she will place 1 beeper on the first cell and 1 beeper on the 2nd cell, respectively.
	 */
	private void checkInitialWorld(){
		if(frontIsBlocked()){
		putBeeper();
		}
		else if(frontIsClear()){
			move();
			putBeeper();
		}
	}

	/*
	 * Method: layCheckersEastwards
	 *  -----------------------------
	 * Pre-condition: Karel is in the start position facing east. Post-condition: Karel hits the first east wall and then flips over.
	 */

	private void layCheckersEastwards() {
		while (frontIsClear() && facingEast()) {
			moveEast();
			checkWallPresentEast();
			moveEast();
			checkWallPresentEast();
		} // end while
	}

	/*
	 * Method: moveEast
	 *  -----------------------------
	 * Karel starts to move eastwards and puts a beeper on every cell that has none.
	 */

	private void moveEast() {
		if (facingEast()) {
			move();
			if (noBeepersPresent() && frontIsClear()) {
				putBeeper();
			}
		} // end if
	}

	/*
	 * Method: checkWallPresentEast 
	 * -----------------------------
	 * 
	 * Turns karel around if there is a wall when Karel is facing east.
	 */

	private void checkWallPresentEast() {
		if (frontIsBlocked() && facingEast()) {
			turnKarel();
		}// end if
	}

	/*
	 * Method: turnKarel
	 *  -----------------------------
	 * 
	 * This method basically flips karel around and makes her face the opposition direction
	 */

	private void turnKarel() {
		for (int i = 1; i <= 2; i++) {
			turnRight();
		} // end for
	}

	/*
	 * Method: collectCheckers
	 *  -----------------------------
	 * 
	 * Pre-Condition: Karel is now facing the west and is now ready to clean up
	 * the beepers.
	 *  Post-condition: Karel ends up in the middle of the row.
	 */

	private void collectCheckers() {
		cleanUpCheckers();
	}

	/*
	 * Method: cleanUpCheckers
	 *  -----------------------------
	 * 
	 * When there are no beepers present Karel moves to the next beeper present.
	 */

	private void cleanUpCheckers() {
		while (noBeepersPresent() && facingWest()) {
			move();
			checkBeeperWest();
		}
	}

	/*
	 * Method: checkBeeperWest
	 *  -----------------------------
	 * 
	 * When Karel hits the next beeper on the row, she goes to the last beeper on the row
	 */

	private void checkBeeperWest() {
		if (beepersPresent()) {
			getLastBeeper();
		} // end if
	}

	/*
	 * Method: getLastBeeper 
	 * -----------------------------
	 * Karel gets the last beeper on the row
	 */

	private void getLastBeeper() {
		while (beepersPresent() && notFacingSouth()) {
			move();
			beeperNotPresent();
		}
	}

	/*
	 * Method: beeperNotPresent
	 *  -----------------------------
	 * 
	 * If there are no beepers present then that means the mid point is
	 * potentially found. If Karel turns around and moves to the next spot
	 * (which must be a beeper). She picks up the beeper and then moves again.
	 * If there is no beeper next to the one taken then the last position MUST
	 * be the mid-point.
	 */

	private void beeperNotPresent() {
		if (noBeepersPresent() && facingWest()) {
			turnKarel();
			move();
			pickBeeper();
			move();
			checkMidPoint();
		} else if (noBeepersPresent() && facingEast()) {
			turnKarel();
			move();
			pickBeeper();
			move();
			checkMidPoint();
		}
	}

	/*
	 * Method: checkMidPoint
	 *  -----------------------------
	 * 
	 * If Karel finds no beepers present after the beeper she has picked up.
	 * Then the last position is the mid-point.
	 */

	private void checkMidPoint() {
		if (noBeepersPresent()) {
			turnKarel();
			move();
			putBeeper();
			faceSouthPosition();
		}
	}

	/*
	 * Method: faceSouthPosition
	 *  -----------------------------
	 * This makes Karel face south and breaks the while loop.
	 */

	private void faceSouthPosition() {
		if (facingWest()) {
			turnLeft();
		}
		if (facingEast()) {
			turnRight();
		}
	}

}
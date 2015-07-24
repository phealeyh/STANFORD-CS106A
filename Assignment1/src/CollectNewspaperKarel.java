import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		goToLocation();
		pickUpNews();
		returnToLocation();
	}

	/*
	 * Karel will now go to the location of the newspaper
	 */
	private void goToLocation() { // 2nd level method
		turnRight();
		move();
		turnLeft();
		for (int i = 0; i < 3; i++) { // start for loop
			move();
		}
	} // end method

	private void pickUpNews() { // 2nd level method
		if (beepersPresent()) {
			pickBeeper();
		}
		turnAround();
	}

	private void returnToLocation() { // 2nd level method
		for (int x = 0; x < 3; x++) {
			move();
		}
		turnRight();
		move();
	}

}
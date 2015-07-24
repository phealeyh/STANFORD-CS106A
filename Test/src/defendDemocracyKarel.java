import stanford.karel.SuperKarel;

public class defendDemocracyKarel extends SuperKarel {
	public void run(){
move();
while(frontIsClear()){
	checkBlock();

	moveToNextBlock();
	}
}

	private void checkBlock(){
	if(noBeepersPresent()){
		cleanBlock();
		}
	}

	private void cleanBlock(){

	checkNorthPart();
	returnToCenter();
	checkSouthPart();
	returnToCenter();
			}

	// Makes Karel check South part of ballot for beepers and remove them if
	// present
	private void checkSouthPart(){
	turnRight();
	move();
	while(beepersPresent()){
		pickBeeper();
		}
	}

	// Mkes Karel check North part of ballot for beepers and remove them if
	// present
	private void checkNorthPart(){

	turnLeft();
	move();
	while(beepersPresent()){
		pickBeeper();
		}
	}

	// Makes Karel return to the center of the ballot and face East
	private void returnToCenter(){
	turnAround();
	move();
	turnEast();
	}

	// Makes KArel turn left until facing East

	private void turnEast(){

	while(notFacingEast()){
		turnLeft();
		}
	}

	// Makes Karel move two corners if the front is clear

	private void moveToNextBlock(){
	if(frontIsClear()){
		move();
		if(frontIsClear()){
			move();
			}
		}
	}
	}
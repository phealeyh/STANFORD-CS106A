/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class defendDemocracyKarel extends SuperKarel {

	public void run(){
		move();
		for(int i = 0; i < 9; i++){
		goToTop();
		initiateCheck();
		move();
		}
	}	
	
	private void goToTop(){
		faceNorth();
		move();
		faceSouth();
	}
	
	private void faceNorth(){
		if(facingEast()){
			turnLeft();
		}
		else if(facingSouth()){
			for(int i = 0; i < 2; i++){
				turnRight();
			}//end for
		}
	}
	
	private void faceSouth(){
		for(int i = 0; i < 2; i++){
			turnRight();
		} //end for
	}
	
	
	//------------------------------------------
	private void initiateCheck(){
		if(beepersPresent()){
			move();
			checkBallot();
			goToStart();
		}
		else if(noBeepersPresent()){
			move();
			move();
			goToStart();
		}
	}
	
	private void goToStart(){
		if(facingNorth()){
		faceSouth();
		move();
		turnLeft();
		} //end if
		
		if(facingSouth()){
			faceNorth();
			move();
			turnRight();
		}
	}
	
	private void checkBallot(){
		if(noBeepersPresent()){
			move();
			clean();
		}//end if
		else if(beepersPresent()){
			move();
			checkLast();
		}
	}
	
	private void checkLast(){
		if(beepersPresent()){
			goToStart();
		}
	}
	
	private void clean(){
		while(beepersPresent()){
			pickBeeper();
		} //end while
		faceNorth();
		cleanTop();
	}
	
	private void cleanTop(){
		while(noBeepersPresent()){
			move();
		} //end while
		pickBeeper();
	}
	
}

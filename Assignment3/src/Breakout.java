/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
		
/** Delay of the motion of the ball */
	private static final int DELAY = 5;
		
	
/* Method: run() */
/** Runs the Breakout program. */
	
	
	public void run() {
		setupGame();
		initialiseBall();
		while(prompt.isVisible()){
			pauseProgram();
		}
		startGame();
	}
	
	/* Method: setupGame
	 * ---------------------
	 * This will basically set the game up by creating the paddle and bricks at the correct position.
	 * 
	 */

	private void setupGame(){ //2nd lvl
		createBricks();
		createPaddle();
		createStatusLabels();
	}
	
	/* Method: createStatusLabels()
	 * -----------------------------
	 * This will create the respective status labels for the player to keep track of his/her progress
	 * 
	 */
	
	private void createStatusLabels(){
		status = new GLabel("Player Status: Alive(" + lifeCount +")");
		status.move((APPLICATION_WIDTH / 2) - (status.getWidth() / 2), status.getAscent());
		add(status);
		
		brickStatus = new GLabel("Amount of bricks on screen: " + BRICKS_AMT);
		brickStatus.move((APPLICATION_WIDTH / 2) - (brickStatus.getWidth() / 2), brickStatus.getAscent() + status.getAscent());
		add(brickStatus);
		
		prompt = new GLabel("Click on the middle of the screen to the start the game");
		prompt.move((APPLICATION_WIDTH / 2) - (prompt.getWidth() / 2), APPLICATION_HEIGHT / 2);
		add(prompt);

		SCORE_LABEL = new GLabel("Player Score: " + SCORE);
		SCORE_LABEL.move((APPLICATION_WIDTH / 2) - (SCORE_LABEL.getWidth() / 2), SCORE_LABEL.getAscent() + brickStatus.getAscent() + status.getAscent());
		add(SCORE_LABEL);
		}
	
	
	/* Method: createBricks()
	 * ---------------------
	 * This method will basically create the bricks found in the game.
	 * 
	 */
	
	private void createBricks(){
		for(int i = 0; i < NBRICK_ROWS; i++){
			for(int j = 0; j < NBRICKS_PER_ROW; j++){
				brick = new GRect((BRICK_WIDTH * j) + (BRICK_SEP * j),BRICK_Y_OFFSET + (BRICK_HEIGHT * i) + (BRICK_SEP * i),BRICK_WIDTH,BRICK_HEIGHT);
				colourBricks(brick,i);
				brick.setFilled(true);
				add(brick);
			} //end 2nd for
		} //end 1st for
	}
	
	/* Method: colourBricks()
	 * ----------------------
	 * This method will basically colour in the rows of the bricks by determining where the control variable i is currently at.
	 * 
	 */
	
	private void colourBricks(GRect brick,int a){
		if(a <= 1 ){ //rows 0 and 1 will be coloured red
			brick.setColor(Color.RED);
		}
		else if(a <= 3){ //rows 2 and 3 will coloured orange
			brick.setColor(Color.ORANGE);
		}
		else if (a <= 5){ //rows 4 and 5 will be coloured yellow
			brick.setColor(Color.YELLOW);
		}
		else if (a <= 7){ //rows 6 and 7 will be coloured green
			brick.setColor(Color.GREEN);
		}
		else if (a <= 9){ //rows 8 and 9 will be coloured cyan
			brick.setColor(Color.CYAN);
		}
	}
	/* Method: createPaddle()
	 * ----------------------
	 * This method will basically create an initial paddle that is fixed right into the middle of the screen.
	 */
	
	private void createPaddle(){
		paddle = new GRect((WIDTH / 2) - (PADDLE_WIDTH / 2), HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	/* Method: pauseProgram()
	 * ---------------------------
	 * This will basically pause the program until the pre-condition while-loop is met (the prompt label disappears)
	 * 
	 */
	
	private void pauseProgram(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* Method: init()
	 * --------------
	 * This method will basically initialise the mouse listeners so that the program can track the movements of the mouse.
	 * 
	 */
	
	public void init(){
		addMouseListeners();
	}

	/* Method: mouseMoved()
	 * --------------
	 * Whenever the user moves the mouse in accordance to the x location, then the paddle will move in conjunction the mouses x location.
	 * 
	 */

	
	public void mouseMoved(MouseEvent e){
		if(e.getX() < APPLICATION_WIDTH - PADDLE_WIDTH){
			paddle.setLocation(e.getX(), HEIGHT - PADDLE_Y_OFFSET);
			}
	}
	
	/* Method: mousePressed()
	 * -----------------------
	 * This will basically start the program when the user clicks on the screen
	 * 
	 */
	public void mousePressed(MouseEvent e){
		if(e.getX() < APPLICATION_WIDTH && e.getY() < APPLICATION_HEIGHT){
			prompt.setVisible(false);
		}
	}
	
	/* Method: startGame()
	 * --------------------
	 * This will basically start the game where the ball will start moving.
	 * 
	 */
	
	private void startGame(){
		remove(prompt); //this basically removes the prompt label because we are now in the 'play' stage.
		while(playerIsAlive() && BRICKS_AMT != 0){
			playGame();
		}
	}
		
	/* Method: intialiseBall()
	 * --------------
	 * This initaliases the ball into the right settings
	 * 
	 */

	private void initialiseBall(){ 
		createBall();
		setVelocity();
	}
	
	/* Method: createBall()
	 * ---------------------
	 * This creates the initial ball and places right in the middle of the game's application window.
	 * 
	 */

	private void createBall(){
		ball = new GOval(APPLICATION_WIDTH / 2, (APPLICATION_HEIGHT / 2) ,BALL_RADIUS, BALL_RADIUS);
		add(ball);
		ball.setFilled(true);
	}
	
	/* Method: setVelocity()
	 * ----------------------
	 * This basically sets the velocity of vy and vx to their values.
	 * 
	 */
	
	private void setVelocity(){
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 3.0;
		if(rgen.nextBoolean(+0.5)){
			vx = -vx;
		}
	}
	
	/* Method: playGame()
	 * --------------
	 * This starts to move the ball downwards
	 * 
	 */
	
	private void playGame(){
		ball.move(vx, vy);
		collider = getCollidingObject(); //sets the collider to one of the elements on the canvas (e.g. brick or paddle) that hits with the ball
		if(collider != null){ //checks to see if the collider is set to an existing object in the canvas
			checkCollision();
		}
		checkWallCollision();
		pause(DELAY);
	}
	
	/* Method: checkCollision()
	 * --------------------------
	 * This basically checks if the ball has hit any of the objects in the canvas (including the paddle).
	 * 
	 */
	
	private void checkCollision(){
		if(brickPresent()){
			removeBrick(collisionLocation()); //removes the brick at the specified location
			calculateScore();
			if(brickHitFromAbove()){ //checks to see whether the brick was hit from a ball coming upwards or downwards and adjusts the y velocity accordingly
				vy = +vy;
			}
			else{
				vy = -vy;
			}
			playCollisionSound();
		} //end if
		else if(paddlePresent()){ //collider is currently set to a non-brick element (paddle, labels etc.)
			if (collider == paddle){
			    vy = -1 * Math.abs(vy); //if the ball hits the paddle (and not the sides) then it will inverse the vy sign to make the ball bounce of the paddle.
			    playCollisionSound();
			}
		}// end else-if
	}
	
	/* Method: calculateScore()
	 * -----------------------
	 * This method will basically add on points to the player's total score.
	 * 
	 */
	
	private void calculateScore(){
		if(collider.getColor() == Color.CYAN){
			SCORE += 2;
			SCORE_LABEL.setLabel("Player Score: " + SCORE );
		}
		else if(collider.getColor() == Color.GREEN){
			SCORE += 4;
			SCORE_LABEL.setLabel("Player Score: " + SCORE );
		}
		else if(collider.getColor() == Color.YELLOW){
			SCORE += 6;
			SCORE_LABEL.setLabel("Player Score: " + SCORE );
		}
		else if(collider.getColor() == Color.ORANGE){
			SCORE += 8;
			SCORE_LABEL.setLabel("Player Score: " + SCORE );
		}
		else if(collider.getColor() == Color.RED){
			SCORE += 10; 
			SCORE_LABEL.setLabel("Player Score: " + SCORE );
		}
	}
	
	/* Method: brickHitFromAbove
	 * --------------------------
	 * Checks to see whether the ball has hit the brick coming southwards.
	 * 
	 */
	
	private boolean brickHitFromAbove(){
		if(vy == -vy){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	/* Method: brickPresent
	 * -------------------------
	 * This basically returns a true statement if the collider is currently set to one of the bricks and false if it isn't
	 * 
	 */
	
	private boolean brickPresent(){
		if(collider != paddle && collider != status && collider != youWin && collider != brickStatus && collider != SCORE_LABEL){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* Method: checkPaddlePresent()
	 * -----------------------------
	 * Checks to see whether the collider is set to a paddle.
	 * 
	 */
	
	private boolean paddlePresent(){
		if(collider == paddle){
			return true;
		}
		else{
			return false;
		}
	}
		
	/* Method: getCollidingObject()
	 * -----------------------------
	 * This method will basically return an object if there is a collision at any points of the ball.
	 * 
	 */
	
	
	private GObject getCollidingObject(){
		if(getElementAt(ball.getX(),ball.getY()) != null){ //gets the upper-left corner position of the ball.
			return getElementAt(ball.getX(),ball.getY());
		}
		if(getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY()) != null){ //gets the upper-right corner position of the ball
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY());
		}
		if(getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2)) != null){ //gets the bottom-left corner position of the ball
			return getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2));
		}
		if(getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2)) != null){ //gets the bottom-right corner position of the ball
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2));
		}
		else{
			return null;
		}
	}
	
	
	/* Method: playCollisionSound
	 * ---------------------------
	 * This will play the 'bounce' sound everytime a collison occurs
	 * 
	 */
	
	private void playCollisionSound(){
		bounceClip.play();
	}
		
	/* Method: updateBrickCounter()
	 * -----------------------------
	 * This method prints and updates the amount of bricks left on the screen; it also increments the kicker by 1 each time a brick is hit.
	 * 
	 */
	
	private void updateBrickCounter(){
		BRICKS_AMT--;
		brickStatus.setLabel("Amount of bricks on screen: " + BRICKS_AMT);
		if(BRICKS_AMT == 0){
			showWinningMessage();
		}
	}

	
	
	/* Method: showWinningMessage()
	 * -----------------------------
	 * Shows the winning message if the last brick has been hit
	 * 
	 */
	
	private void showWinningMessage(){
		youWin = new GLabel("YOU WIN !");
		youWin.move((APPLICATION_WIDTH / 2) - (youWin.getWidth() / 2), APPLICATION_HEIGHT / 2);
		youWin.setColor(Color.RED);
		add(youWin);
	}
	
	/* Method: showLosingMessage()
	 * -----------------------------
	 * Shows the losing message if the player misses the ball
	 * 
	 */
	
	private void showLosingMessage(){
		youLose = new GLabel("YOU LOSE !");
		youLose.move((APPLICATION_WIDTH / 2) - (youLose.getWidth() / 2), APPLICATION_HEIGHT / 2);
		youLose.setColor(Color.RED);
		add(youLose);
	}

	
	/* Method: collisionLocation()
	 * ---------------------------
	 * This checks where the collision has occurred and returns the value of that element.
	 */
	
	private int collisionLocation(){
		int i = 0;
		while(collider != getElement(i)){ // keep going through each element until a match is found
			i++;
		}
		return i;
	}
	
	/* Method: removeBrick
	 * ---------------------
	 * This method will basically remove a brick if the ball clashes with it.
	 * 
	 */
	
	private void removeBrick(int i){
		remove(getElement(i));
		updateBrickCounter(); //updates the number of bricks found on the screen
	}
		

	
	/* Method: checkWallCollision()
	 * -----------------------------
	 * Check if the ball is being obstructed and inverse the sign of vy or vx if it is.
	 * 
	 */
	
	private void checkWallCollision(){
		checkNorthWall();
		checkSouthWall();
		checkEastWall();
		checkWestWall();
	}


	/* Method: checkSouthWall()
	 * -------------------------
	 * This basically checks if the ball hits the south wall and adjusts the vy velocity to negative if it does
	 * 
	 */
	
	private void checkSouthWall(){
		if(ball.getY() + (BALL_RADIUS * 2) >= APPLICATION_HEIGHT){ //checks if the ball hits the south wall
			vy = -vy;
			lifeCount--;
			status.setLabel("Player Status: Alive(" + lifeCount + ")");
		}
	}
	
	
	/* Method: checkNorthWall()
	 * --------------------------
	 * This basically checks if the ball hits the north wall and adjusts the vy velocity to a positive number.
	 * 
	 */
	
	private void checkNorthWall(){
		if(ball.getY() <= 0){ //checks if the ball hits the north wall
			vy = -vy;
		}
	}
	
	/* Method: checkEastWall()
	 * ------------------------
	 * This will basically check if the ball hits the east wall and inverses the vx sign to a negative number.
	 * 
	 */

	private void checkEastWall(){
		if(ball.getX() + (BALL_RADIUS * 2) >= APPLICATION_WIDTH){ //checks to see if the ball hits the east wall
			vx = -vx;
		}
	}
	
	/* Method: checkWestWall()
	 * -------------------------
	 * This method will basically check if the ball hits the west wall and will inverse the negative vx to a positive vx number.
	 * 
	 */
	
	private void checkWestWall(){
		if(ball.getX() <= 0){ //checks to see if the ball hits the west wall
			vx = -vx;
		}
	}
	
	/* Method: playerIsAlive()
	 * ------------------------
	 * This method checks the player's status and returns false if it is set to false and true if it is set to it's natural state of true.
	 * 
	 */
	
	private boolean playerIsAlive(){
		if(lifeCount > 0){
			return true;
		}
		else{ //if lifeCount is currently at 0
			showLosingMessage();
			status.setLabel("Player Status: Dead(" + lifeCount+")"); //shows the player's status as 'dead'.
			return false;
		}
	}

	
/* Instance Variables */
/** The amount of lives the player has */
	private int lifeCount = NTURNS;
	
/** Label for losing the game */
	private GLabel youLose;

/** Label for winning the game */
	private GLabel youWin;
	
/** Label for the player's status */
	private GLabel status;
	
/** Label for the amount of bricks */
	private GLabel brickStatus;
	
/** Amount of bricks found on the screen */
	private int BRICKS_AMT = NBRICKS_PER_ROW * NBRICK_ROWS;
	
/** This is the prompt message that the user receives at the start of the program */
	private GLabel prompt;
	
/** The collider is basically an object that has been hit */
	private GObject collider;
	
/** The paddle */
	private GRect paddle;
	
/** The bricks */
	private GRect brick;
	
/** The ball */
	private GOval ball;
	
/** Player's score */
	private int SCORE;
	
/** Label for player's score */
	private GLabel SCORE_LABEL;
	
/**	This is basically the x and y coordinate of the ball */
	private double vx, vy;
	
/**	This basically generates a random number */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
/** Adds the bounce sound */
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au"); 
	
}

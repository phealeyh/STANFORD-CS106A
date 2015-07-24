
public class frogger{
	/* Class Variables */
	public static final int SQSIZE = 75;
  	public static final int NCOLS = 7;
  	public static final int NROWS = 3;

	public void run(){
		createImage();
	}
	private void createImage(){
		frog = new GImage('frog', APPLICATION_WIDTH() / 2, APPLICATION_HEIGHT() - (18/2)));
		add(frog);
	}

	public void init(){
		addMouseListeners();
	}

	public void mouseClicked(){
		if(e.getX() > frog.getX() && e.getX() < APPLICATION_WIDTH()){ //mouse is clicked to the right of the frog and inside the application's width
			moveEast();
		}
		else if(e.getX() < frog.getX() && e.getX() > 0){ //mouse is clicked between the left side of the window and the frog; making it move left
			moveWest();
		}
		else if(e.getY() < frog.getY() && e.getY() > 0){ //mouse is clicked between the the top of the window and the frog; making it move north
			moveNorth();
		}
		else if(e.getY() > frog.get() && e.getY() < APPLICATION_HEIGHT()){ //mouse is clicked between the bottom of the window and the frog; making it move south.
			moveSouth();
		}
	}

	private void moveEast(){
		frog.move(frog.getX() + SQ_SIZE, frog.getY()); //move right
	}

	private void moveWest(){
		frog.move(frog.getX() - SQ_SIZE, frog.getY()); //move left
	}

	private void moveNorth(){
		frog.move(frog.getX(), frog.getY() - SQ_SIZE); //move up
	}

	private void moveSouth(){
		frog.move(frog.getX(), frog.getY() + SQ_SIZE); //move down
	}

	/* Private instance variables */
	private GImage frog;

}
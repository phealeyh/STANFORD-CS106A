/* Program: RobotFace
 * --------------------
 * This program will basically draw a face of a robot at the center of the screen.
 * 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {
		
	private static final int HEAD_WIDTH = 150; // divide by 2 = 75
	private static final int HEAD_HEIGHT = 210;  // divide by 2 = 105
	private static final int EYE_RADIUS = 30;
	private static final int MOUTH_WIDTH = (HEAD_WIDTH / 2); // 75
	private static final int MOUTH_HEIGHT = (HEAD_HEIGHT / 8); //26.5
	
	
		public void run(){
			drawRobot();
		}
		
		/* Method: drawRobot
		 * --------------------
		 * This method will draw the face first, then the mouth and finally, the eyes.
		 * 
		 */

		private void drawRobot(){
			drawFace();
			drawMouth();
			drawEyes();
		}
		
		/* Method: drawFace
		 * --------------------
		 * This method will draw the face of the robot with a set color of gray.
		 * 
		 */

		private void drawFace(){
			GRect face = new GRect(getWidth(), getHeight() / 8, HEAD_WIDTH, HEAD_HEIGHT);
			face.setFillColor(Color.GRAY);
			face.setFilled(true);
			add(face);
		}
		
		/* Method: drawFace
		 * --------------------
		 * This method will draw the robot's mouth.
		 * The height location of the eyes is horizontally one quarter of the head's height.
		 * The width location of the eyes is also one quarter of the head's width, vertically.
		 * The eyes are also set to a fill color of yellow.
		 */

		private void drawMouth(){
			int mouth_height_location, mouth_width_location;
			mouth_width_location = getWidth() + (MOUTH_WIDTH / 2);
			mouth_height_location = ((getHeight() / 8) * 7);
			GRect mouth = new GRect(mouth_width_location, mouth_height_location, MOUTH_WIDTH, MOUTH_HEIGHT);
			mouth.setFillColor(Color.WHITE);
			mouth.setColor(Color.WHITE);
			mouth.setFilled(true);
			add(mouth);
		}
		
		/* Method: drawEyes
		 * --------------------
		 * This method will draw the eyes of the robot.
		 * It will basically create 2 eyes (which the for loop takes care of) that is filled in with the color yellow.
		 * The position of the eye is first centered at the middle of the head and then it is subtracted by half of the eye's radius.
		 * The final position for the first eye is one-quarter of the face's width and 3-quarters of the face's width.
		 */

		private void drawEyes(){
			int eye_width_location = getWidth() - (EYE_RADIUS / 2); // 37.5
			int eye_height_location = (getHeight() / 8) - (EYE_RADIUS / 2); //105
			for(int i = 0; i < 2; i++){
				GOval eyes = new GOval(eye_width_location + (HEAD_WIDTH / 4), eye_height_location + (HEAD_HEIGHT / 4), EYE_RADIUS, EYE_RADIUS);
				eyes.setFillColor(Color.YELLOW);
				eyes.setFilled(true);
				eyes.setColor(Color.YELLOW);
				add(eyes);
				eye_width_location += (HEAD_WIDTH / 4) * 2;
			} //end for
		}
}

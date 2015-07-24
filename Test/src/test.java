import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import javax.swing.*;


public class test extends GraphicsProgram{
	public void init() {
		createCross();
		createButtons();
	}
	
	private void createCross(){
		int x = (getWidth() / 2) - (line_length / 2);
		int y = (getHeight() / 2) - (line_length / 2);
		GLine line = new GLine(x, y, x + line_length, y + line_length);
		GLine line2 = new GLine(x, y + line_length, x + line_length,y);
		add(line);
		add(line2);

	}
	
	private void createButtons(){
		add(new JButton("North"), SOUTH);
		
	}
	/*Class Constants */
	private static final int line_length = 50;
}
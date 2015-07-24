import acm.graphics.*;
import acm.program.*;

public class flipImage extends GraphicsProgram {
	public void run(){
		GImage image = new GImage("mgs.jpg"); //this will set the image to the file location
		image = flipHorizontally(image);
		image.setLocation(150,150) ;
		add(image);
	}
	
	/* Method: flipHorizontally
	 * --------------------------
	 * This method will basically receive an image and the associated array.
	 * It will then re-arrange the array of pixels and reverse the position of the pixels.
	 * The end result will produce an image that is 'flipped' sideways.
	 */
	
	private GImage flipHorizontally(GImage image){
		int[][] pixelsImage = image.getPixelArray(); //this will set
		//implement for loop that goes through half of the indexes in the array
		int height = pixelsImage.length;
		int width = pixelsImage[0].length;
		for(int i = 0; i < height; i++){ //go through the width of the array
			for(int j = 0; j < width / 2; j++){
				int temp = pixelsImage[i][j];
				int last = width - j - 1;
				pixelsImage[i][j] = pixelsImage[i][last];
				pixelsImage[i][last] = temp;
			}
		}
		return new GImage(pixelsImage);
	}
	
}
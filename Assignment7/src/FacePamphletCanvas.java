/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		//glabel 
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		removeAll();
		GLabel message = new GLabel(msg);
		double X_LOCATION = (APPLICATION_WIDTH / 2) - (message.getWidth() / 2);
		double Y_LOCATION = APPLICATION_HEIGHT - BOTTOM_MESSAGE_MARGIN;
		message.move(X_LOCATION,Y_LOCATION);
		message.setFont(MESSAGE_FONT);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		showName(profile);
		//show image
		showImage(profile);
		//the friends label
		showFriends(profile);
		//show status
		showStatus(profile);

	}

	private void showImage(FacePamphletProfile profile){
		//the image
		if(profile.getImage() != null){
			if(getElementAt(LEFT_MARGIN,IMAGE_MARGIN + TOP_MARGIN) != null){
				remove(getElementAt(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN));
			}
			GImage image = profile.getImage();
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			image.setLocation(LEFT_MARGIN,IMAGE_MARGIN + TOP_MARGIN);
			add(image);
		}
		else{ //image is set to null
			showBlankImage();
		}
	}
	
	private void showBlankImage(){
		double x_mid_point = IMAGE_WIDTH / 2;
		double y_mid_point = IMAGE_HEIGHT / 2;
		GLabel noImage = new GLabel("NO IMAGE");
		noImage.move((LEFT_MARGIN + x_mid_point) - (noImage.getWidth()) ,
		 y_mid_point + TOP_MARGIN + IMAGE_MARGIN);
		noImage.setFont(PROFILE_IMAGE_FONT);
		add(noImage);
		//create the two vertical lines
		for(int i = 0; i < 2; i++){
			double x_pos = i * IMAGE_WIDTH;
			GLine vertical = new GLine(LEFT_MARGIN + x_pos ,
			IMAGE_MARGIN + TOP_MARGIN + IMAGE_HEIGHT,LEFT_MARGIN + x_pos,
			IMAGE_MARGIN + TOP_MARGIN);
			add(vertical);
		}
		//create the two horizontal lines
		for(int i = 0; i < 2; i++){
			double y_pos = i * IMAGE_HEIGHT;
			GLine horizontal = new GLine(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + y_pos,
				LEFT_MARGIN + IMAGE_WIDTH, IMAGE_MARGIN + TOP_MARGIN + y_pos);
			add(horizontal);
		}
	}

	private void showName(FacePamphletProfile profile){
		if(profile != null){
			//display name 			
			if(getElementAt(LEFT_MARGIN,TOP_MARGIN) != null){
				remove(getElementAt(LEFT_MARGIN, TOP_MARGIN));
			}
			GLabel name = new GLabel(profile.getName(),LEFT_MARGIN,TOP_MARGIN);
			name.setColor(Color.BLUE);
			name.setFont(PROFILE_NAME_FONT);
			add(name);
		}
	}

	private void showStatus(FacePamphletProfile profile){
		//the status label
		double status_y_pos = APPLICATION_HEIGHT - STATUS_MARGIN;
		if(profile.getStatus() != null){
			//checks whether or not there is an element at the designated position
			if(getElementAt(LEFT_MARGIN,status_y_pos) != null){
				remove(getElementAt(LEFT_MARGIN, status_y_pos));
			}
			GLabel status = new GLabel(profile.getName() + " is "
				+ profile.getStatus(), LEFT_MARGIN, status_y_pos);
			status.setFont(PROFILE_STATUS_FONT);
			add(status);
		}
		else{
			GLabel status = new GLabel("No current status",LEFT_MARGIN, status_y_pos);
			status.setFont(PROFILE_STATUS_FONT);
			add(status);
		}
	}

	private void showFriends(FacePamphletProfile profile){
		if(getElementAt(APPLICATION_WIDTH / 2, TOP_MARGIN) != null){
			remove(getElementAt(APPLICATION_WIDTH / 2, TOP_MARGIN));
		}
		//make a for loop for the friends list
		GLabel friendList = new GLabel("Friends: ", APPLICATION_WIDTH / 2, TOP_MARGIN);
		friendList.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendList);
		//only loop if the profile has at least one friend
		if(profile.getFriends().hasNext()){
			Iterator<String> it = profile.getFriends();
			double y = 0;
			while(it.hasNext()){
				y += friendList.getHeight();
				String friendName = it.next();
				if(getElementAt(APPLICATION_WIDTH / 2, TOP_MARGIN + y) == null){
					GLabel friends = new GLabel(friendName, APPLICATION_WIDTH / 2, TOP_MARGIN + y);
					friends.setFont(PROFILE_FRIEND_FONT);
					add(friends);
				}
			} //end while
		} //end if
	}

	/* Private Instance Variable */

}

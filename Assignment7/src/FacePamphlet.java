/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		//create the buttons and text field that is located at the north of the window
		createNameButtons();
		//create the buttons and text fields that are located at the west of the window
		createProfileButtons();
		//this will listen and get all the button events
		addActionListeners();
		//create the canvas
		canvas = new FacePamphletCanvas();
		//add the canvas
      	add(canvas);
    }
	
	/* Method: createNameButtons
	 * ---------------------------
	 * This method will basically create the 3 buttons along with
	 * the name text field and name label located at the north region
	 * 
	 */
	
	private void createNameButtons(){
		//name + name field
		add(new JLabel("Name: "), NORTH);
		nameText = new JTextField(TEXT_FIELD_SIZE);
		//this will not have an action command for enter
		add(nameText, NORTH);
		//the buttons
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
	}
	
	/* Method: createProfileButtons
	 * -----------------------------
	 * This method will basically create the buttons that are located the west
	 * of the window. They are mainly used for adjusting the profile of the user.
	 * All of the text fields should have action commands for the action enter.
	 */

	private void createProfileButtons(){
		statusText = new JTextField(TEXT_FIELD_SIZE);
		add(statusText,WEST);
		add(new JButton("Change Status"),WEST);
		//added space between the interactors
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		pictureText = new JTextField(TEXT_FIELD_SIZE);
		add(pictureText,WEST);
		add(new JButton("Change Picture"),WEST);
		//added space between the interactors
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		friendText = new JTextField(TEXT_FIELD_SIZE);
		add(friendText,WEST);
		add(new JButton("Add Friend"), WEST);
		//implement action commands for enter
		statusText.addActionListener(this);
		pictureText.addActionListener(this);
		friendText.addActionListener(this);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	String name = nameText.getText();

    	//the getselectionstart command will return a 0 if the text field is blank
    	if(e.getActionCommand().equals("Add") && nameText.getSelectionStart() != 0){
    		//add a new profile
    		addProfile(name);
   		}
   		if(e.getActionCommand().equals("Delete") && nameText.getSelectionStart() != 0){
   			deleteProfile(name);
   		}
   		if(e.getActionCommand().equals("Lookup") && nameText.getSelectionStart() != 0){
   			lookupProfile(name);
   		}
    	if(e.getActionCommand().equals("Change Status") || e.getSource() == statusText
    		&& statusText.getSelectionStart() != 0){
    		changeStatus();
	    }
	 	if(e.getActionCommand().equals("Change Picture") || e.getSource() == pictureText
	 		&& pictureText.getSelectionStart() != 0){
	 		changePicture();
	    }
	    if(e.getActionCommand().equals("Add Friend") || e.getSource() == friendText
	    	&& friendText.getSelectionStart() != 0){
	    	addFriend();
	    }
    }

    /* Method: addProfile
     * --------------------
     * This method will add the profile with the associated name given
     */

    private void addProfile(String name){
    	//create a new profile
        profile = new FacePamphletProfile(name);
        if(database.containsProfile(name)){
        	//add the profile to the database
       		database.addProfile(profile);
       		canvas.showMessage("Replaced " + name);
       	}
       	else{ //name doesn't exist in the hash map
       		database.addProfile(profile);
       		canvas.showMessage("Added: " + name);
       	}
       	canvas.displayProfile(profile);
    }

    /* Method: deleteProfile
     * ----------------------
     * This method will delete the profile that was typed in by the user
     */

    private void deleteProfile(String name){
    	//check if the profile is within the database
    	if(database.containsProfile(name)){
    		//deletes the profile from the database
    		database.deleteProfile(name);
       		canvas.showMessage("Deleted: " + name);
    	}
    	else{ //no profile match in the database
    		canvas.showMessage(name + "'s profile does not exist.");
    	}
    	//there will be no profiles after calling this method
    	profile = null;
    	canvas.showMessage("No profiles selected.");
    }

    /* Method: lookupProfile
     * -----------------------
     * This will look up the profile typed in by the user
     */

    private void lookupProfile(String name){
    	if(database.containsProfile(name)){
	    	//set the profile to one that is being looked up
	    	profile = database.getProfile(name);
	    	//basically prints out the name, status and the friends of the user
       		canvas.showMessage("Displaying " + name);
       		canvas.displayProfile(profile);
	    }
	    else{ //the profile name doesn't exist in the database
	    	canvas.showMessage(name + "'s profile does not exist.");
	    	//current profile is set to null
	    	profile = null;
	    }
    }

    /* Method: changeStatus
     * ---------------------
     * This will change the status of the currently selected profile
     */

    private void changeStatus(){
    	String status = statusText.getText();
    	if(profile != null){
	   		profile.setStatus(status);
	   		canvas.showMessage("Status Changed");
	   		canvas.displayProfile(profile);
	    }
	    else{ //no profile is selected (profile is set to null)
    		canvas.showMessage("No profiles selected.");
	    } //end else
    }

    /* Method: changePicture
     * ----------------------
     * This method will change the picture of the profile
     */

    private void changePicture(){
    	GImage picture = readImage(pictureText.getText());
    	if(profile != null){
		   	profile.setImage(picture);
		   	canvas.showMessage("Changed picture");
		   	canvas.displayProfile(profile);
		}
		else{
    		canvas.showMessage("No profiles selected.");
		}
    }
    
    /* Method: addFriend
     * ------------------
     * This method will add a friend to the associated profile
     */

    private void addFriend(){
    	String friend = friendText.getText();
    	if(profile != null){
    		//check if the person is in the database
    		if(database.containsProfile(friend)){
	    		//check if friend is on list
	    		checkFriendList(friend);
	    		canvas.showMessage(friend + " has been added to your list.");
	    		canvas.displayProfile(profile);
	    	}
	    	else{
	    		canvas.showMessage(friend + " can not be found in the database");
	    	}
	    }
	    else{ //no profile is currently selected
    		canvas.showMessage("No profiles selected.");
	    }	
    }

    /* Method: checkFriendList
     * -----------------------
     * This method checks whether the friend is alrady on the list.
     * If they are not; then it will update it both friends list accordingly.
     */

    private void checkFriendList(String friend){
   		if(profile.addFriend(friend)){
		   	canvas.showMessage(friend + " added to " + profile.getName() + "'s' friends list.");
		   	//set the local variable profile to the added friend
		   	FacePamphletProfile friendProfile = database.getProfile(friend);
		   	//now add the current profile to the friend's friend list
		   	if(friendProfile.addFriend(profile.getName())){
			//add the previous profile name to the current friend list
				canvas.showMessage(profile.getName() + " added to " 
					+ friendProfile.getName() + "'s' friends list.");
			}
	 	}
	   	else{ //friend is already on list
		   	canvas.showMessage("This person is already in your friend list.");
		}
    }


    /* Method: readImage
     * ------------------
     * This method will read the file that contains the image; it will return null if the
     * file does not exist.
     */

    private GImage readImage(String location){
        GImage image = null;
        try {
          	image = new GImage(location);
        } catch (ErrorException ex) {
        	System.out.println("File not found");
        }
        return image;
    }

	/* Private Instance Variables */

	/* The profile object */
	private FacePamphletProfile profile;
	/* The database object */
	private FacePamphletDatabase database = new FacePamphletDatabase();
	/* The text fields */
	private JTextField nameText, statusText, pictureText, friendText;
	/*The canvas object */
	private FacePamphletCanvas canvas;

}

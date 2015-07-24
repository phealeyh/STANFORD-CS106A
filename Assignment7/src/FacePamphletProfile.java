/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
	public FacePamphletProfile(String name) {
		//set the instance variable
		this.name = name;
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return name;
	}

	/** 
	 * This method returns the image associated with the profile.  
	 * If there is no image associated with the profile, the method
	 * returns null. */ 
	public GImage getImage() {
		// You fill this in.  Currently always returns null.
		return image;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		this.image = image;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		if(status != null){
			return status;
		}
		else{ //status has no value
			return null;
		}
	}
	
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		this.status = status;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		// You fill this in.  Currently always returns true.
		//check if name is already in the array list of friends
		if(friends.contains(friend)){
			return false;
		}
		else{
			friends.add(friend);
			return true;
		}
		//if it is then return a false because the friend is already there
		//if it isn't then add the friend in the array list and return true
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		// Use this method when a profile is deleted
		if(friends.contains(friend)){
			friends.remove(friend);
			return true;
		}
		else{
			return false;
		}
	}

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	public Iterator<String> getFriends() {
		// You fill this in.  Currently always returns null.
		//create the iterator for the hashmap
		Iterator<String> it = friends.iterator();
		return it;
	}
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	public String toString() {
		// You fill this in.  Currently always returns the empty string.
		String friendsList = friends.toString();
		//these two variables gets the end and start position of the friends list
		int begin = friendsList.indexOf("[") + 1;
		int end = friendsList.indexOf("]");
		return name + " (" + getStatus() + ") :  " + friendsList.substring(begin,end);
	}

	/* Private Instance Variables */

	/* The name and status of the person */
	private String name, status;
	
	/* The profile image associated with the person */
	private GImage image;

	/* The array list that keeps track of the person's friends */
	private ArrayList<String> friends = new ArrayList<String>();

	
}

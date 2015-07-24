/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		profiles = new HashMap<String,FacePamphletProfile>();
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		// You fill this in
		// check if the profile exists
		if(profiles.containsKey(profile.getName())){
			//remove existing map
			profiles.remove(profile.getName());
			//add the new one to the hash map
			profiles.put(profile.getName(),profile);
		}

		else{ //no profile exists; so don't remove anything
			profiles.put(profile.getName(), profile);
		}

	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		//check if the profile exists in the profiles database
		if(profiles.containsKey(name)){
			//return the profile
			return profiles.get(name);
		}
		else{ //return null if no profile exists in the hash map
			return null;
		}
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		//remove the value by finding the key
		profiles.remove(name);
		//update the friends list for each person with this profile as a friend
		updateFriendsList(name);
	}

	/* Method: updateFriendsList
	 * --------------------------
	 * This method will go through the entire hash map and remove all the occurrences
	 * of the removed profile found in the each profiles friends list.
	 */

	private void updateFriendsList(String removedName){
		//go through the entire hash map of strings
		for(String profile: profiles.keySet()){
			//send in the selected profile to a method
			//checkProfile(profiles.get(profile),removedName);
			//set the profile
			FacePamphletProfile selectedProfile = profiles.get(profile);
			if(selectedProfile.removeFriend(removedName)){
				System.out.println("Friend " + removedName + " removed from "
				 + selectedProfile.getName() + " list.");
			}
		} //end for
	} 

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		// check if the hash map contains the profile.
		if(profiles.containsKey(name)){
			return true;
		}
		else{
			return false;
		}
	}

	/* Private Instance Variables */

	/* The hashmap holding the profiles */
	private Map<String,FacePamphletProfile> profiles;

}

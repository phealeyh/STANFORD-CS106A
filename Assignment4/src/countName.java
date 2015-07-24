/* Program: nameCount
 * ---------------------
 * This program will basically ask the user the input names.
 * When the user presses enter, the program will stop.
 * It will then print out the number of times a specific name has been entered.
 * 
 */
import java.util.*;

import acm.program.*;

public class countName extends ConsoleProgram {
	
	
	public void run(){
	ArrayList<String> nameList = new ArrayList<String>();
		while(true){
			String name = readLine("Enter a name: ");
			if(name.equals("")) break;
			nameList.add(name);
		}
		for(int i = 0; i < nameList.size();i++){
			if(nameNotOnList(i, nameList)){
				println(getNameAmount(nameList,i));
			}
		}
	}

	/* Method: getNameAmount
	 * ------------------------
	 * This method will basically return the number of times a name has been printed out (in a string).
	 * It will do this by checking the array that is passed as an arguement.
	 * 
	 */
	
	private String getNameAmount(ArrayList<String> nameList, int target){
		String targetName = nameList.get(target);
		int counter = 0;
		for(int i = 0; i < nameList.size(); i++){
			if(targetName.equals(nameList.get(i))){
				counter++;
			}			
		}		
		return "Entry " + "[" + targetName + "] " + "has " + counter + " count.";
	}
	
	/* Method: nameNotOnList
	 * ----------------------
	 * This method will basically determine if a name has been already counted
	 * 
	 */
	
	private boolean nameNotOnList(int index, ArrayList<String> name){
		String s = name.get(index);
		if(name.indexOf(s) == index){
			return true;
		}
		else{
			return false;
		}
	}
}

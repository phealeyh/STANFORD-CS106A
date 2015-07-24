/* Program: UniqueNames
 * ---------------------
 * This program will basically ask the user for input names and enter the names into an array
 * It will then print out the array and list the names found inside the array.
 */

import java.util.*;
import acm.program.*;

public class UniqueNames extends ConsoleProgram {
	public void run(){
        ArrayList<String> nameList = makeArrayList("Enter name:"); //creates an array
		println("Unique name list contains the following names: ");
		readArray(nameList);
	}
/* Method: makeArrayList
 * -----------------------
 * This will basically ask the user for a list of names.
 * It will then append these names onto the array list.
 * 
 */
	private ArrayList<String> makeArrayList(String prompt){
		ArrayList<String> list = new ArrayList<String>();
		while(true){
			String name = readLine(prompt);
			if(name.equals("")) break; //break the loop if the user just presses enter
			list.add(name);
		}
		return list;
	}
	
/* Method: readArray(strings)
 * -------------------
 * This method will basically loop through the entire array and print out its contents
 * This method is specifcally used for strings
 * 
 */
	private void readArray(ArrayList<String> s){
		for(int i = 0; i < s.size(); i++ ){ //loop from the very beginning of the array to the very end of the array
			println(s.get(i));
		}
	}

}

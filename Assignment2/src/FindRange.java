/*File: FindRange
 * ---------------
 * 
 * This program will basically ask the user to input numbers.
 * After the numbers are entered then the program will find the lowest and highest numbers that were put in by the user.
 * 
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	public void run() {
		explainProgram();
		findRange();
	}
	
	/*Method: explainProgram
	 * ----------------------
	 * This method will explain what the program does.
	 * 
	 */
	
	private void explainProgram(){
		println("This program finds the largest and smallest numbers.");
	}
	
	/*Method: findRange
	 * ----------------------
	 * This method will accept input from the user and then start comparing the numbers.
	 * The smallest and largest numbers will be printed out at the end of the method.
	 * The program will give a prompt and end if the user initially enters a value of 0.
	 */

	private void findRange(){
		int smallest, largest;
		int a = readInt("?");
			if(a == 0){
				print("No value has been entered.");
			} //end if
			else {
				smallest = a;
				largest = a;
					while(a != 0){
						a = readInt("?");
							if(smallest > a && a != 0){
								smallest = a;
							} // end if
							else if (largest < a){
								largest = a;
							} //end else
					} //end while
					print("The smallest number is " + smallest + "." + "\n" + "The largest number is " + largest + ".");
			} //end else
	}
	
	
		
}


/* Program: simplifyNumerics
 * --------------------------
 * This program will basically simplify the numerics that a user enters by adding commas at every millionth,hundredth-thousand,thousands,hundredths and tens.
 * 
 */

import java.util.*;
import acm.program.*;


public class Test extends ConsoleProgram{
	public void run(){
		while(true){
			String digits = readLine("Enter a numberic string: ");
			if(digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}
	/* Method: addCommasToNumericString
	 * ---------------------------------
	 * This method will basically get add a comma to every single 3rd 0 (counting from right-to-left) to make a large more readable.
	 * 
	 */
	
	private String addCommasToNumericString(String digits){
		int count = 0;
		for(int i = digits.length() - 1; i != 0; i--){
			count++;
			if(count == 3){
				digits = digits.substring(0, i) + "," + digits.substring(i,digits.length());
				count = 0;
			}
		}
		return digits;
	}
}

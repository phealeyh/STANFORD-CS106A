/*File: HailStone
 * ---------------
 * This program basically gets a number input from the user.
 * The number then goes through a hailstone process where if n is odd or even, then perform the correct formula.
 */
import acm.program.*;

public class Hailstone extends ConsoleProgram {
	private int sum = 0;

	public void run() {
		inputNumber();
	}

	
	/*Method: inputNumber
	 * -------------------
	 * This basically prompts the user for a number.
	 * If the number is odd, then perform 3n + 1, where n is the odd number.
	 * If the number is even, then halve the number by 2.
	 * 
	 */
	
	private void inputNumber() {
		int even, odd, sum;
		sum = 0;
		int a = readInt("Enter a number: ");
		while (a != 1) {
			if ((a % 2) != 0) {
				println(a + " is odd, so I make 3n + 1: " + ((3 * a) + 1));
				a = (a * 3) + 1;
				sum++;
			}
			while ((a % 2) == 0) {
				println(a + " is even, so I take half: " + a / 2);
				a = a / 2;
				sum++;
			} // end while
		} //end first while
		print("The process took " + sum + " to reach 1");
	}

}

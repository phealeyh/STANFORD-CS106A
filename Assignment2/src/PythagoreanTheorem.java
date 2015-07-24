/*File: PythagoreanTheorem
 * ------------------------
 * This program will basically take in the user inputs of a and b.
 * It will then use pythagorean's theorem to calculate the 2 values.
 * The formula is represented as: a^2 +b^2 =c^2 or c = square root of a^2 + b^2.
 * 
 */
import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		askUser();
	}
	
	/*Method: askUser
	 * --------------
	 * This method will take in the users 2 input
	 * 
	 */
	
	private void askUser(){
		println("Enter the values to compute Pythagorean Theorem: ");
		print("a: ");
		int a = readInt();
		print("b: ");
		int b = readInt();
		computePythagorean(a, b);
	}
	
	/*Method: computePythagorean
	 * --------------------------
	 * This method will basically square root the summed value of a^2 and b^2.
	 * It will then store that calculated value into c.
	 */
	private void computePythagorean(int a, int b){
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		outputAnswer(c);
	}
	
	/*Method: outputAnswer
	 * --------------------
	 * This method will print out the final value of c to the user.
	 * 
	 */
	
	private void outputAnswer(double c){
		print("c = " + c);
	}
}

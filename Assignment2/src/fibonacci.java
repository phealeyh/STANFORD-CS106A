/*Method: Fibonacci
 * -----------------
 * This program will basically show the fibonacci sequence all the way up to 6765.
 * 
 */

import acm.program.ConsoleProgram;

public class fibonacci extends ConsoleProgram {

	private static final int MAX_TERM_VALUE = 10000;
	
		public void run(){
			explainProgram();
			executeFibonacci();
		}
		
		/*Method: explainProgram
		 * -----------------
		 * This explains to the user what the program is doing.
		 * 
		 */

		private void explainProgram(){
			println("This program lists the fibonacci sequence.");
		}
		
		/*Method: executeFibonacci
		 * -----------------
		 * This method will basically start generating the fibonacci sequences.
		 * The pre-calculation made inside the while statement is basically pre-checking whether the associated fibonacci number will generate a number over 20,000.
		 * If it does, then it will not execute the loop.
		 */

		private void executeFibonacci(){
			int term = 0;
				while(getFib(term) < MAX_TERM_VALUE){
					println("Fib(" + term + ")" + " = 	" + getFib(term));
					term++;
				}
		}
		
		/*Method: getFib
		 * -----------------
		 * This method will basically get a term as an argument.
		 * If it is over 1 then the for loop will basically loop for the number of terms it was given.
		 * So if it was given a fibonacci term of 19, it will do the fibonacci calculation 19 times and give the last value.
		 * 
		 */

		private int getFib(int a){
			int answer, number1, number2;
			number1 = 1;
			number2 = 0;
			answer = 0;
				if(a > 1){
					for(int i = 0; i < a; i++){
						answer = number1 + number2;
						number1 = number2;
						number2 = answer;
					}
					return answer;
				}
				else{
					answer = 0;
						if(a == 1){
							answer = 1;
							return answer;
						} //end if
					return answer;
				} //end else
		}
	
}

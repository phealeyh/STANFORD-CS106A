import java.util.*;
import acm.program.*;

public class RemoveCharacters extends ConsoleProgram {
	public void run(){
		String str = readLine("Enter a string: ");
		String occurence = readLine("Enter a string/character you want removed from the string: ");
		char ch = occurence.charAt(0);
		println(removeAllOccurrences(str,ch));
	}
	
	/* Method: removeAllOccurences
	 * -----------------------------
	 */
	private String removeAllOccurrences(String str, char ch){
		int occurenceIndex = str.indexOf(ch);
		String repl = "";
		if(occurenceIndex != -1){ //if there is a match (meaning it isn't equals to minus 1) then execute the body
		repl = str.substring(0,occurenceIndex) + ch + str.substring(occurenceIndex + str.length());
		return repl;
		}
		return repl;
	}
}

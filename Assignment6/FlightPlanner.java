/* Program: FlightPlanner
 * ----------------------
 * This program will firstly read a file containing the flight details of various destinations.
 * It will then display the complete list of cities upon request.
 * The user will then be allowed to choose from the various location to depart from and it shows 
 * the corresponding list of available flight destinations taken from that location.
 * The user should keep choosing various destinations until they reach the starting location.
 * It would then print out the round-trip chosen consisting of various destinations.
 */
import java.util.*;
import java.io.*;
import acm.program.*;


public class FlightPlanner extends ConsoleProgram {
	
	/* Class Constants */
	/* This will be the file location of the flights */
	private static final String fileLocation = "flights.txt";
		
	public void run(){
		String initialCity, chosenDestination;
		BufferedReader rd = readFile();
		//create a new arraylist based on the file selected in the rd object
		flights = getContents(rd);
		//this array list will hold the chosen destinations from the user
		ArrayList<String> route = new ArrayList<String>();
		//display all the cities
		printInitialCities();
		//store the initial city in a string
		initialCity = chooseCity();
		//set the chosen destination as the initial city
		chosenDestination = initialCity;
		do{
			//add the destination to the array list
			route.add(chosenDestination);
			//store the destinations in an array
			ArrayList<String> destinations = getDestinations(chosenDestination);
			println("These are the cities you can depart from: ");
			//print out the destinations that can be taken
			printDestionations(destinations);
			//this will ask the user to select a list of available flights from
			//the chosen destination
			chosenDestination = getCorrespondingDestination(destinations);
			//keep looping this section until the original departure location is chosen.
		}while(!chosenDestination.equals(initialCity)); //loop until original city is chosen
		//this should be the last destination; the initial city
		route.add(chosenDestination);
		//print out the round-trip showing the destinations being taken(the route).
		println("This is the route that will be taken: " + "\n" + getRoute(route));
	}
	
	/* Method: readFile
	 * ------------------
	 * This method will read the file from disk and store it as
	 * a buffered reader object.
	 */

	private BufferedReader readFile(){
		BufferedReader rd = null;
		while(rd == null){
			try{
				rd = new BufferedReader(new FileReader(fileLocation));
			}catch (IOException io){
				System.out.println("Bad File.");
			}
		} //end while loop
		return rd;
	}

	/* Method: getContents
	 * --------------------
	 * This method will get the contents from the text file and puts it into
	 * the array
	 */

	private ArrayList<String> getContents(BufferedReader rd){
		ArrayList<String> contents = new ArrayList<String>();
		while(true){
			try{
				String line = rd.readLine();
				if(line == null) break; //break at the end of the file
				if(!line.isEmpty()){
					//add the line to the array
					contents.add(line);
				}
			} catch(IOException io){
				System.out.println("Bad line.");
			}
		} //end while
		return contents;	
	}

	/* Method: chooseCity
	 * -------------------
	 * This will allow the user to choose a departure location which means
	 * that every single city will be eligible for selection.
	 *
	 */

	private String chooseCity(){
		String city = "";
		while(true){
			//ask user to select a city
			city = readLine("Choose a city to depart from: ");
			if(isValidCity(city)) break; //break from the loop if a valid city is chosen
		}
		//return the string
		return city;
	}
	
	/* Method: isValidCity
	 * ------------------
	 * This will basically check to see if the entered city is valid
	 * 
	 */
	private boolean isValidCity(String city){
		Iterator<String> it = flights.iterator();
		while(it.hasNext()){
			String line = it.next();
			//get the index of the last character for the first word
			int last = line.indexOf("-");
			//check if the city equals with the string
			if(city.equals(line.substring(0, last - 1))){
				return true;
			}
		}	
		return false;
	}
	
	/* Method: printInitialCities
	 * ---------------------------
	 * This method should be called before the user chooses the first city.
	 * It should basically print out every single city.
	 */

	private void printInitialCities(){
		//this will hold the city name
		String city = "";
		Iterator<String> it = flights.iterator();
		while(it.hasNext()){
			//get the line only if there is one in the first place
			//this if statement checks to see
			String line = it.next();
			//get the index of the last character for the first word
			int last = line.indexOf("-");
			//check if the city doesn't equal to the previous city
			if(!city.equals(line.substring(0, last - 1))){
				city = line.substring(0, last - 1);
				println(city);
			}
		}//end while
	}
	
	/* Method: printDestionations
	 * --------------------------
	 * This will print out the destinations that can be chosen.
	 * It's result is based on the users chosen route
	 */
	
	private void printDestionations(ArrayList<String> destinations){
		//go through the array
		Iterator<String> dit = destinations.iterator();
		while(dit.hasNext()){
			String line = dit.next();
			//print out the cities
			println(line);
		} //end while
	}
	
	/* Method: getDestinations
	 * ------------------------
	 * This will get the destinations that are available and will store it as an array
	 * It will then return that array.
	 */

	private ArrayList<String> getDestinations(String chosenDestination){
		ArrayList<String> destinations = new ArrayList<String>();
		Iterator<String> sit = flights.iterator();
		while(sit.hasNext()){
			String line = sit.next();
			int last = line.indexOf("-");
			//get the name for comparison
			String name = line.substring(0, last - 1);
			//check if the cities match; if they do then print out the corresponding destinations
			if(name.equals(chosenDestination)){
				//print the corresponding destination associated with this city
				String city = line.substring(last + 3);
				destinations.add(city);
			}
		} //end while
		return destinations;
	}
	

	
	/* Method: getCorrespondingDestination
	 * ------------------------------------
	 * This will ask the user to choose a destination based on its current route.
	 * It will then return this destination as a string
	 */

	private String getCorrespondingDestination(ArrayList<String> destinations){
		String chosenCity = "";
		while(true){
			chosenCity = readLine("Please choose a city: ");
			if(matchFound(chosenCity,destinations)) break;
		}
		return chosenCity;
	}
	
	/* Method: this method will check to see whether or not the chosen destination
	 * is a valid one.
	 */
	
	private boolean matchFound(String chosenCity, ArrayList<String> destinations){
		for(int i = 0; i < destinations.size(); i++){
			if(chosenCity.equals(destinations.get(i))){
				return true;
			}
		}
		return false;

	}
	
	private String getRoute(ArrayList<String> route){
		String finalRoute = "";
		Iterator<String> it = route.iterator();
		while(it.hasNext()){
			
			finalRoute += it.next() + " -> ";
		}
		finalRoute += "END OF ROUTE";
		return finalRoute;
	}
	

	/* This array list will hold the flights */
	ArrayList<String> flights = new ArrayList<String>();
}

//DS 1: All fligth paths are stored in array list(string)
//DS 2: Tailored flight path will be recorded in a array list in sequential order
/**
 * 
 */
package phase2;

import java.io.File;
import java.util.List;

import exceptions.NoSuchUserException;
import flights.Flight;
import flights.Itinerary;

import users.Client;


/**
 * Driver class used for testing
 * @author Faisal Usmani
 */
public class Driver {
	
	// the file that stores all the users
	private final static File USER_FILE = new File("users.ser");
	
	private final static File AIRPORT_FILE = new File("airports.ser");
	
	// system control that stores all the data
	private static SystemControl SYSTEM_CONTROL = 
			new SystemControl(USER_FILE, AIRPORT_FILE);
	
	
	
	
	/**
	 * Uploads all the CLients in the csv file at path.
	 * @param path the path of the csv file
	 */
	public static void uploadClientInfo (String path)  {
		SYSTEM_CONTROL.uploadUsers(path);
	}
	
	/**
	 * Uploads all the flights that are in the file at path.
	 * @param path the path of the csv file
	 */
	public static void uploadFlightInfo (String path) {
		SYSTEM_CONTROL.uploadFlights(path);
	}
	
	
	/**
	 * Gets the Client with the given email
	 * @param email the email of the Client to search for
	 * @return Client with email email
	 */
	public static String getClient (String email) {
		Client client = null;
		try {
			client = SYSTEM_CONTROL.getUser(email);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
		return client.toString(); 
	}

	
	/**
	 * Gets all the flights that leave origin going to destination 
	 * on date date.
	 * @param date the date of all Flights to look for
	 * @param origin the origin of all the Flights to look for
	 * @param destination the Destination of all the Flights to look for
	 * @return String representation of all the Flights that go from origin 
	 * to destination on date
	 */
	public static String getFlights (String date, String origin, 
			String destination) {
		
		List<Flight> flights = SYSTEM_CONTROL.getFlights(date, origin, destination);
		String result = "";
		if (flights.size() == 0) {
			return result;
		}
		
		for (int i = 0; i < flights.size() - 1; i++) {
			result += flights.get(i).toString() + "," 
					+ flights.get(i).getCost() + "\n";
		}
		return result + flights.get(flights.size() - 1).toString() 
				+ "," + flights.get(flights.size() - 1).getCost();
		
	}
	
	/**
	 * Gets all Itineraries that leave origin going to destination 
	 * on date date
	 * @param date the date the Itineraries will start
	 * @param origin the origin of the Itineraries
	 * @param destination the destination of the itineraries
	 * @return String representation of all the Itineraries that go from 
	 * origin to destination on date
	 */
	public static String getItineraries (String date, String origin, 
			String destination) {
		
		List<Itinerary> itineraries = 
				SYSTEM_CONTROL.getItineraries(date, origin, destination);
		String result = "";
		if (itineraries.size() == 0) {
			return result;
		}
		
		for (int i = 0; i < itineraries.size() - 1; i++) {
			result += itineraries.get(i).toString() + "\n";
		}
		return result + itineraries.get(itineraries.size() - 1).toString();
	}
	
	/**
	 * Gets all Itineraries that leave origin going to destination 
	 * on date date sorted by total cost
	 * @param date the date the Itineraries will start
	 * @param origin the origin of the Itineraries
	 * @param destination the destination of the itineraries
	 * @return String representation of all the Itineraries that go from 
	 * origin to destination on date
	 */
	public static String getItinerariesSortedByCost (String date, 
			String origin, String destination) {
		
		List<Itinerary> itineraries = 
				SYSTEM_CONTROL.getItinerariesSortedByCost(date, origin, destination);
		String result = "";
		if (itineraries.size() == 0) {
			return result;
		}
		
		for (int i = 0; i < itineraries.size() - 1; i++) {
			result += itineraries.get(i).toString() + "\n";
		}
		return result + itineraries.get(itineraries.size() - 1).toString();
	}
	
	/**
	 * Gets all Itineraries that leave origin going to destination 
	 * on date date sorted by total time
	 * @param date the date the Itineraries will start
	 * @param origin the origin of the Itineraries
	 * @param destination the destination of the itineraries
	 * @return String representation of all the Itineraries that go from 
	 * origin to destination on date
	 */
	public static String getItinerariesSortedByTime (String date, 
			String origin, String destination) {
		
		List<Itinerary> itineraries = 
				SYSTEM_CONTROL.getItinerariesSortedByTime(date, origin, destination);
		String result = "";
		if (itineraries.size() == 0) {
			return result;
		}
		
		for (int i = 0; i < itineraries.size() - 1; i++) {
			result += itineraries.get(i).toString() + "\n";
		}
		return result + itineraries.get(itineraries.size() - 1).toString();
	}
	
	

}

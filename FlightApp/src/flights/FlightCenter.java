/**
 * 
 */
package flights;


import android.annotation.SuppressLint;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FlightTooLongException;
import exceptions.InvalidDepartureOrArrivalDateTimeException;
import exceptions.NoSuchNodeException;




/**
 * A representation of a flight center that organizes flights.
 * @author Faisal Usmani
 *
 */
public class FlightCenter implements Serializable {
	
	/**
	 * A serial ID used for serialization.
	 */ 
	private static final long serialVersionUID = 5727398844196057320L;
	
	// map of location to Airport
	private Map<String, Airport<Flight>> locationToAirport;
	
	// a DirectedGraph for the Airports
	private DirectedGraph<Airport<Flight>> graph;
	
	// map from location to id used in the graph
	private Map<String, Integer> locationToId;
	
	private final File AIRPORT_FILE;
	
	// counter to keep track of the number of nodes in the graph
	private int counter;
	
	/**
	 * Creates a new FlightCenter
	 */
	public FlightCenter(File airport_file) {
		this.AIRPORT_FILE = airport_file;
		this.locationToAirport = new HashMap<String, Airport<Flight>>();
		setAirports();
		this.graph = new DirectedGraph<Airport<Flight>>();
		this.locationToId = new HashMap<String, Integer>();
		this.counter = 0;
		
	}
	
	// Gets all the Airports from the file airports.ser
	@SuppressWarnings("unchecked")
	private void setAirports () {
		boolean empty = AIRPORT_FILE.length() == 0;
		try {
			FileInputStream inFile = new FileInputStream(AIRPORT_FILE);
			BufferedInputStream buffer = new BufferedInputStream(inFile);
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(buffer);
			if (! empty) {
				this.locationToAirport = (HashMap<String, Airport<Flight>>)
						in.readObject();
			} else {
				putAirports();
			}
		} catch (IOException | ClassNotFoundException e) {
			try {
				AIRPORT_FILE.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	// Update the airport.ser file
	private void putAirports () {
		
		try {
			FileOutputStream outFile = new FileOutputStream(AIRPORT_FILE);
			BufferedOutputStream buffer = new BufferedOutputStream(outFile);
			ObjectOutputStream out = new ObjectOutputStream(buffer);
			out.writeObject(locationToAirport);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns the Airport at the given location in this FlightCenter.
	 * @return the Airport at location 
	 */
	public Airport<Flight> getAirport(String location) {
		return locationToAirport.get(location);
	}
	
	/**
	 * Adds the airport to this FlightCenter.
	 * @param airport the airport to be added
	 */
	public void addAirport(Airport<Flight> airport) {
		locationToAirport.put(airport.getLocation(), airport);
		putAirports();
	}
	
	/**
	 * Adds the flight to the appropriate Airport in this FlightCenter.
	 * @param flight the flight to be added
	 * @throws IOException if there is no file Airport.txt
	 */
	public void addFlight (Flight flight) {
		if (locationToAirport.containsKey(flight.getOrigin())) {
			Airport<Flight> airport = locationToAirport.get(flight.getOrigin());
			if (! hasFlight(flight)) {
				airport.addDeparture(getFlightIndex(airport,
						flight.getDepartureDateTime()), flight);
			} else {
				for (int i = 0; i < airport.getDepartures().size(); i++){
					if (flight.getFlightNum() 
							== airport.getDepartures().get(i).getFlightNum()) {
						airport.getDepartures().set(i, flight);
					}
				}
			}
			
		} else {
			Airport<Flight> airport = new Airport<Flight>(flight.getOrigin());
			airport.addDeparture(0, flight);
			locationToAirport.put(flight.getOrigin(), airport);
			graph.addNode(counter++, airport);
			locationToId.put(airport.getLocation(), counter);
		}
		putAirports();
	
	}
	
	// Finds out whether flight is already stored in the system
	private boolean hasFlight (Flight flight) {
		for (Flight f : locationToAirport.get(flight.getOrigin())
				.getDepartures()) {
			if (f.getFlightNum() == flight.getFlightNum()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds flights from csv file to this FlightCenter
	 * @param FlightFile path the csv file
	 */
	public void uploadFlights(String flightFile){
		BufferedReader br = null;
		String line;
		String cvsSplit = ",";
		
		try{
			br = new BufferedReader(new FileReader(flightFile));
			while((line = br.readLine()) != null ){
				
				String[] flight = line.split(cvsSplit);
				Flight newFlight = null;
				try {
					newFlight = new Flight(Integer.parseInt(flight[0]), 
							flight[1], flight[2], flight[3], flight[4], 
							flight[5], Double.parseDouble(flight[6]), 
							Integer.parseInt(flight[7]));
				} catch (NumberFormatException | FlightTooLongException
						| InvalidDepartureOrArrivalDateTimeException e) {
					e.printStackTrace();
				}
				addFlight(newFlight);
				
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try{
					br.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		putAirports();
		
	}
	
	/* Gets the index for the new Flight with departure date date to be inserted
	 * in the airport.
	 */
	private int getFlightIndex(Airport<Flight> airport, String date) {
		
		for (int i = 0; i < airport.getDepartures().size(); i++) {
			if (timeToLong(date) <= 
			timeToLong(airport.getDepartures().get(i).getDepartureDateTime())) {
				return i;
			}
		}
		return airport.getDepartures().size() - 1;
	}
	
	/**
	 * Gets all the Flights that go from origin to destination on date
	 * in this FlightCenter
	 * @param date the date of the Flights to look for
	 * @param origin the origin of the Flights to look for
	 * @param destination the destination of the Flights to look for
	 * @return the Flight with going from origin to destination on date
	 */
	public List<Flight> getFlights (String date, String origin, 
			String destination) {
		
		if (locationToAirport.containsKey(origin)) {
			int indexStart = getFlightIndex(locationToAirport
					.get(origin), date), indexEnd;
			List<Flight> flights = new ArrayList<Flight>();
		
			for (indexEnd = indexStart; indexEnd < locationToAirport
					.get(origin).getDepartures().size() &&
					locationToAirport.get(origin).getDepartures()
					.get(indexEnd).getDepartureDateTime().equals(date); 
					indexEnd++) {
				if (locationToAirport.get(origin).getDepartures()
						.get(indexEnd).getDestination().equals(destination)) {
					flights.add(locationToAirport.get(origin).getDepartures()
							.get(indexEnd));
				}
			}
		return flights;
		}
		return new ArrayList<Flight>();
	}
	
	
	/**
	 * Returns the Set of locations of all Airports registered in this
	 * Flight Center.
	 * @return Set of all locations of the Airports
	 */
	public Set<String> getLocations() {
		return locationToAirport.keySet();
	}
	
	/**
	 * Finds all the itineraries given the date, origin, and destination.
	 * @param date the date of the itinerary to be searched for 
	 * @param origin the origin of the itinerary to be searched for
	 * @param destination the destination of the itinerary to be searched for
	 * @return List of Itineraries that go from origin to destination on date
	 */
	public List<Itinerary> findItineraries (String date, String origin, 
			String destination) {
		if (locationToAirport.containsKey(origin)) {
			Airport<Flight> airport = locationToAirport.get(origin);
			List<List<Flight>> paths = new ArrayList<List<Flight>>();
			List<Itinerary> itineraries = new ArrayList<Itinerary>();
			try {
				fillGraph(date, airport, destination, 0, paths, 
						new HashSet<Flight>());
			} catch (ParseException | NoSuchNodeException e) {
				e.printStackTrace();
			}
		
			for (List<Flight> flights : paths) {
				itineraries.add(new Itinerary(flights, origin, 
						destination, date));
			}
		
			return itineraries;
		}
		return new ArrayList<Itinerary>();
		
	}
	
	// fills the graph with nodes and then finds all the paths between 
	// origin and destination.
	private void fillGraph (String date, Airport<Flight> airport, 
			String destination, int time, List<List<Flight>> paths, 
			HashSet<Flight> path) 
					throws ParseException, NoSuchNodeException {
		
		int index = getFlightIndex(airport, date);
		double timeLapse;
		Flight flight;
		
		for (; index < airport.getDepartures().size() &&
				(timeLapse = timeDiff(date, 
				(flight = airport.getDepartures().get(index))
				.getDepartureDateTime()))
				<= time && timeLapse >= 0; index++) {
			
			//id = locationToId.get(flight.getDestination());
			if (flight.getDestination().equals(destination)) {
				path.add(flight);
				paths.add(new ArrayList<Flight>(path));
				path.remove(flight);
				//return;
				
			} else {
			
				//graph.addEdge(locationToId.get(airport.getLocation()), id);
				path.add(flight);
				if (locationToAirport.get(flight.getDestination()) != null) {
					fillGraph (addOneMin(flight.getArrivalDateTime(), 60000),
							locationToAirport.get(flight.getDestination()),
							destination, 
							6, paths, path);
				}
				path.remove(flight);
			}
		}
	}
	
	// Adds addTime to time
	private String addOneMin (String time, long addTime) {
		return longToTime(timeToLong(time) + addTime);
	}
	
	// Returns the time in the format YYYY-MM-DD HH:MM as a string
	@SuppressLint("SimpleDateFormat") 
	private String longToTime (long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date dateTime = new Date(time);
		return format.format(dateTime);
	}
	
	// returns the number of milliseconds since January 1 1970 until time
	@SuppressLint("SimpleDateFormat") 
	private long timeToLong (String time) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date dateTime = null;
		try {
			dateTime = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateTime.getTime();
	}
	
	// Gets the time difference between start and finish in hours.
	private double timeDiff (String start, String finish) {
		
		long diff = timeToLong(finish) - timeToLong(start);
		
		Calendar st = Calendar.getInstance(), fn = Calendar.getInstance();
		st.setTimeInMillis(timeToLong(finish));
		fn.setTimeInMillis(timeToLong(start));
		
		long diffMinutes = diff / (60 * 1000) % 60;         
		long diffHours = diff / (60 * 60 * 1000) % 24; 
		
		
		return diffHours + diffMinutes / 60.0;	
	}
	
	/**
	 * Finds all the itineraries given the date, origin, and destination
	 * sorted by total cost. 
	 * @param date the date of the itinerary to be searched for 
	 * @param origin the origin of the itinerary to be searched for
	 * @param destination the destination of the itinerary to be searched for
	 * @return List of Itineraries that go from origin to destination on date
	 */
	public List<Itinerary> findItinerariesSortedByCost (String date, 
			String origin, String destination) {
		
		List<Itinerary> itineraries = findItineraries(date, origin, 
				destination);
		List<Itinerary> sortedItineraries = new ArrayList<Itinerary>();
		Map<Double, Itinerary> costsToItinerary = new HashMap<Double, 
				Itinerary>();
		double [] costs = new double[itineraries.size()];
		
		for (int i = 0; i < itineraries.size(); i++) {
			costsToItinerary.put(itineraries.get(i).getTotalCost(), 
					itineraries.get(i));
			costs[i] = itineraries.get(i).getTotalCost();
		}
		
		Arrays.sort(costs);
		
		for (double cost : costs) {
			sortedItineraries.add(costsToItinerary.get(cost));
		}
		return sortedItineraries;
		
	}
	
	
	/**
	 * Finds all the itineraries given the date, origin, and destination
	 * sorted by total time. 
	 * @param date the date of the itinerary to be searched for 
	 * @param origin the origin of the itinerary to be searched for
	 * @param destination the destination of the itinerary to be searched for
	 * @return List of Itineraries that go from origin to destination on date
	 */
	public List<Itinerary> findItinerariesSortedByTime (String date, 
			String origin, String destination) {
		
		List<Itinerary> itineraries = findItineraries(date, origin, 
				destination);
		List<Itinerary> sortedItineraries = new ArrayList<Itinerary>();
		Map<Double, Itinerary> timeToItinerary = new HashMap<Double, 
				Itinerary>();
		double [] times = new double[itineraries.size()];
		double time;
		
		for (int i = 0; i < itineraries.size(); i++) {
			time = Double.parseDouble(itineraries.get(i)
					.getTotalTime().substring(0, 2)) 
					+ Double.parseDouble(itineraries.get(i)
							.getTotalTime().substring(3, 5))
					/ 60.0;
			timeToItinerary.put(time, itineraries.get(i));
			times[i] = time;
		}
		
		Arrays.sort(times);
		
		for (double t : times) {
			sortedItineraries.add(timeToItinerary.get(t));
		}
		return sortedItineraries;
	}
	
	/**
	 * Gets the FLight whit the given flight number.
	 * @param flightNum the flight number of the flight to be searched for
	 * @return the Flight the the flight number flightNum
	 */
	public Flight getFlight (int flightNum) {
		for (Airport<Flight> airport : 
			locationToAirport.values()) {
			
			for (Flight flight : airport.getDepartures()) {
				if (flight.getFlightNum() == flightNum) {
					return flight;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

}

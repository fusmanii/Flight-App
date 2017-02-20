/**
 * 
 */
package flights;

import java.io.Serializable;
import java.util.*;


/**
 * A representation of an itinerary
 * @author Silina
 */
public class Itinerary implements Serializable {
	
	/**
	 * A serial ID used for serialization.
	 */
	private static final long serialVersionUID = -5224143265228252365L;

	 

	/** A list of flights that belong to this itinerary. */
	private List<Flight> flights;

	/** total cost of this itinerary. */
	private double totalCost;

	/** Total time take to reach from origin to destination. */
	private String totalTime;

	/** Is this itinerary just one flight? */
	boolean nonStop = false;

	/** Does this itinerary use only one airline? */
	boolean singleAirline = false;

	/** List of airlines in this itinerary. */
	List<String> airlines = new ArrayList<String>();
	
	/** The destination of the flight. */
	private String destination;

	/** The origin of the flight. */
	private String origin;
	
	/**
	 * The date and time when the flight will leave its origin in the form
	 * YYYY-MM-DD HH:MM.
	 */
	private String departureDateTime;

	/**
	 * The date and time when the flight will arrive to its destination in the
	 * form YYYY-MM-DD HH:MM.
	 */
	private String arrivalDateTime;

	/**
	 * Generates new Itinerary
	 */
	public Itinerary(List<Flight> flights, String origin, 
			String destination, String date) {

		this.flights = flights;
		if (flights.size() == 1) {
			this.nonStop = true;
		}

		for (Flight f : flights) {
			if (f.getDestination().equals(destination)) {
				this.arrivalDateTime = f.getArrivalDateTime();
			}
			if (!airlines.contains(f.getAirline())) {
				airlines.add(f.getAirline());
			}
		}

		if (airlines.size() == 1) {
			this.singleAirline = true;
		}
		
		this.origin = origin;
		this.destination = destination;
		this.departureDateTime = date;

		// Calculate the total cost
		TotalCost();
		// Calculates total time taken to get from origin to destination.
		TotalTime();
	}

	/**
	 * Returns a list of all airlines in itinerary.
	 * 
	 * @return A list of all airlines in itinerary.
	 */
	public List<String> getAirlines() {
		return airlines;
	}

	/**
	 * Returns true if and only if there is one airline in the itinerary.
	 * 
	 * @return True if and only if there is one airline in the itinerary.
	 */
	public boolean getSingleAirline() {
		return singleAirline;
	}

	/**
	 * Calculates the total cost of the itinerary.
	 */
	public void TotalCost() {
		this.totalCost = 0;
		for (Flight f : flights) {
			this.totalCost += f.getCost();
		}
	}

	/**
	 * Calculates the total travel time of the itinerary in the form
	 * DD:HH:MM(days:hours:minutes).
	 */
	public void TotalTime() {

		int totalDays = 0;
		int totalHours = 0;
		int totalMins = 0;

		// adds all the days, hours and minutes from each flight in this
		// itinerary.
		for (Flight f : flights) {
			String travelTime = f.getTravelTime();
			totalDays += Integer.parseInt(travelTime.substring(0, 2));
			totalHours += Integer.parseInt(travelTime.substring(3, 5));
			totalMins += Integer.parseInt(travelTime.substring(6,
					travelTime.length()));

			while (totalMins >= 60) {
				totalHours += 1;
				totalMins = totalMins - 60;
			}

			while (totalHours >= 24) {
				totalDays += 1;
				totalHours = totalHours - 24;
			}
		}

		// If days, hours, minutes is less than 10 then add a zero in front of
		// it.
		if (totalDays < 10) {
			this.totalTime = "0" + totalDays;
		} else {
			this.totalTime = "" + totalDays;
		}

		if (totalHours < 10) {
			this.totalTime += ":0" + totalHours;
		} else {
			this.totalTime += ":" + totalHours;
		}
		if (totalMins < 10) {
			this.totalTime += ":0" + totalMins;
		} else {
			this.totalTime += ":" + totalMins;
		}
	}

	/**
	 * Returns the total travel time of the itinerary in the form HH:MM.
	 * 
	 * @return The totalTime the total travel time of the itinerary in the form
	 *         DD:HH:MM(days:hours:minutes).
	 */
	public String getTotalTime() {
		return totalTime;
	}

	/**
	 * Returns a list of all flights in this itinerary.
	 * 
	 * @return A list of all flights in this itinerary.
	 */
	public List<Flight> getFlights() {
		return flights;
	}

	/**
	 * Returns total cost of this itinerary.
	 * 
	 * @return Total cost of this itinerary.
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Returns true if and only if this itinerary is just one flight.
	 * 
	 * @return True if and only if this itinerary is just one flight.
	 */
	public boolean getNonStop() {
		return nonStop;
	}

	/**
	 * Returns the origin of the itinerary.
	 * 
	 * @return The origin of the itinerary.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Returns the destination of the itinerary.
	 * 
	 * @return The destination of the itinerary.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Gets the departure time and date for this Itinerary.
	 * @return the departure date and time
	 */
	public String getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * Gets the arrival date and time for this Itinerary.
	 * @return the arrival date and time
	 */
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "";
		
		for (Flight flight : getFlights()) {
			result += flight.toString() + "\n";
		}
		return result + getTotalCost() + "\n" + getTotalTime().substring(3);
	}
	
	
}

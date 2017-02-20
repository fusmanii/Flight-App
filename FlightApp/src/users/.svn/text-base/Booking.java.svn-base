package users;

import java.util.List;

import flights.Itinerary;


/**
 * An interface that states methods for booking itineraries and billing info
 * @author Vicky
 *
 */
public interface Booking {
	
	/**
	 * Changes one of the billing information
	 * @param editInfo string that represent the parameter that be changed
	 * @param value the value what the new value of the parameter is
	 */
	public void editBilling(String editInfo, String value);

	/**
	 * Returns the list of booked itineraries
	 * @return A list of booked itineraries
	 */
	public List<Itinerary> getBookedItinerary();
	
	/**
	 * Stores a itinerary that the client has booked to client 
	 * @param An itinerary object that the user will store as booked
	 */
	public boolean bookItinerary(Itinerary itinerary);
	
}

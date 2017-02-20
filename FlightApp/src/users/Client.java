package users;

import java.util.ArrayList;
import java.util.List;

import flights.Itinerary;



/**
 * Representation of a client
 * @author Vicky
 *
 */
public class Client extends User implements Booking{
	
	/**
	 * A serial ID used for serialization
	 */
	private static final long serialVersionUID = -5222379275197811850L;
	
	/** A list of itineraries that the client has booked */
	private List<Itinerary> bookedItinerary;
	
	/** determined whether this Client is an admin */
	private boolean isAdmin = false;
	
	/**
	 * Generates a new client
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 * @param username the log id of the user
	 * @param password id password that lets the user access the account
	 */
	public Client(String lastName, String firstName, String email, 
			String address, String creditNum, String creditExpiry, String 
			userName, String password){

		super(firstName, lastName, email, address, creditNum, creditExpiry,
				userName, password);
	
		bookedItinerary = new ArrayList<Itinerary>();
	}

	
	/**
	 * Generates a new client
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 */
	public Client(String lastName, String firstName, String email, 
			String address, String creditNum,String creditExpiry){

		super(firstName, lastName, email, address, creditNum, creditExpiry);
	
		bookedItinerary = new ArrayList<Itinerary>();
	}

	/*
	 * (non-Javadoc)
	 * @see phase2.Booking#editBilling(java.lang.String, java.lang.String)
	 */
	public void editBilling(String editInfo, String value) {
		if(editInfo == "creditNum"){
			setCreditNum(value);
		}

		else if(editInfo == "creditExpiry"){
			setCreditExpiry(value);
		}
	}
	
	
	/**
	 * Gets the list of all the itineraries booked for this Client.
	 */
	public List<Itinerary> getBookedItinerary() {
		
		return bookedItinerary;
	}

	/**
	 * Books the given itinerary for this Client.
	 * @param itinerary the itinerary to be booked
	 */
	public boolean bookItinerary(Itinerary itinerary) {
		if ( ! bookedItinerary.contains(itinerary)) {
			for (Itinerary itin : bookedItinerary) {
				if (itin.getFlights().size() == 1 
					&& itinerary.getFlights().size() == 1 
					&& itin.getFlights().get(0).getFlightNum() 
					== itinerary.getFlights().get(0).getFlightNum()) {
					return false;
				}
			}
			bookedItinerary.add(itinerary);
			return true;
		}
		return false;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public String toString(){
		return super.toString() + "," + this.getCreditNum() + "," +
				this.getCreditExpiry();
	}
	
	
}

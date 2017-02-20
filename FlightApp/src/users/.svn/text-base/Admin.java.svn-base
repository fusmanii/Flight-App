package users;

import java.util.List;

import exceptions.FlightTooLongException;
import exceptions.InvalidDepartureOrArrivalDateTimeException;
import flights.Flight;
import flights.FlightCenter;



/**
 * A representation of an admin
 * @author Vicky
 *
 */
public class Admin extends Client{

	/**
	 * A serial ID used for serialization 
	 */
	private static final long serialVersionUID = 7415490055205437247L;
	
	// indicates whether this User is a client or admin
	private boolean isAdmin = true;


	/**
	 * Generates a new Admin with the given last name, first name, 
	 * email, address, credit number, credit card expire date.
	 * 
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 * @param username the log id of the user
	 * @param password id password that lets the user access the account
	 */
	public Admin(String lastName, String firstName, String email, 
			String address, String creditNum, String creditExpiry, String 
			userName, String password){

		super(lastName, firstName, email, address, creditNum, creditExpiry, 
				userName, password);
	}
	
	/**
	 * Generates a new Admin with the given last name, first name, 
	 * email, address, credit number, credit card expire date.
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param email email of the user
	 * @param address home address of the user
	 * @param creditNum credit card the user is using to pay
	 * @param creditExpiry date of when the credit card expires in YYYY-MM-DD
	 */
	public Admin(String lastName, String firstName, String email, String address,
			 String creditNum, String creditExpiry){

		super(lastName, firstName, email, address, creditNum, creditExpiry);
	}
	
	
	/**
	 * View all clients
	 * @param clients the clients to be viewed
	 */
	public void viewClients(List<Client> clients){
		for(Client i: clients){
			System.out.println(i);
		}
	}
	
	
	/**
	 * Edit one of the client's personal data
	 * 
	 * @param client client object that will be changed
	 * @param editInfo string that represent one of the personal parameter
	 * @param value a string which is the new data for what will be edited
	 */
	public void editClient(Client client, String editInfo, String value){

			client.editPersonal(editInfo, value);
			
	}
	
	/**
	 * Edit one of the client's billing data
	 * 
	 * @param client an client object that will be changed
	 * @param editInfo a string that represent one of the billing parameters
	 * @param value a string which is the new data for one of the parameter
	 */
	public void editClientBill(Client client, String editInfo, String value){
		
		client.editBilling(editInfo, value);
		
	}
	
	/**
	 * Adds a flight to the FlightCenter
	 * 
	 * @param center a FlightCenter that contains all flight objects
	 * @param flightNum the number of the flight
	 * @param flightDepartTime date of when flight will depart
	 * @param flightArrivalTime date when the flight will arrive
	 * @param Airline what airline the flight belongs to
	 * @param origin the starting point of the flight
	 * @param destination the location it is planning to land to
	 * @param price the cost of the flight
	 */
	public void addFlight(FlightCenter center, int flightNum,
			String flightDepartTime, String flightArrivalTime, 
			String Airline, String origin, String destination,
			Double price, int numSeats){
		Flight flight = null;
		try {
			flight = new Flight (flightNum, flightDepartTime,
					flightArrivalTime, Airline, origin, destination, 
					price, numSeats);
		} catch (FlightTooLongException
				| InvalidDepartureOrArrivalDateTimeException e) {
			e.printStackTrace();
		}
		center.addFlight(flight);
	}
	
	/**
	 * Checks if this user id an Admin.
	 * @return True iff this user is and Admin.
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	
	
	
}

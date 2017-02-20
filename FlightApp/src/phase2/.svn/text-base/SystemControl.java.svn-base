/**
 * 
 */
package phase2;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import exceptions.FlightTooLongException;
import exceptions.InvalidDepartureOrArrivalDateTimeException;
import exceptions.NoSuchUserException;
import exceptions.NotLogedInException;
import exceptions.UserAlreadyExistsException;
import exceptions.WrongPasswordException;
import flights.Flight;
import flights.FlightCenter;
import flights.Itinerary;

import users.Admin;
import users.Client;
import users.User;

/**
 * A class representation of a flight control system
 * @author senyasmolett
 */
public class SystemControl implements Serializable {

	
	// Used to serialize this class.
	private static final long serialVersionUID = -2922141794313326984L;
	
	// The pass for registering an Admin.
	public final String ADMIN_PASS = "admin111";

	// the list of all the users
	private ArrayList<Client> users;
	
	// the flightCenter that stores all the flights
	private FlightCenter flightCenter;
	
	// the user that is currently logged in
	private Client user;
	
	// indicate whether a user is logged in
	private boolean isLoggedIn = false;
	
	// the file that stores all the users
	private final File USER_FILE;
	
	// the file for storing airports
	private final File AIRPORT_FILE;
	
	/**
	 * Creates a new SystemControl.
	 */
	public SystemControl (File user_file, File airport_file) {
		this.AIRPORT_FILE = airport_file;
		this.flightCenter = new FlightCenter(AIRPORT_FILE);
		this.users = new ArrayList<Client>();
		this.USER_FILE = user_file;
		setUsers();
	}
	
	// Gets all the users from the file users.ser
	@SuppressWarnings("unchecked")
	private void setUsers () {
		boolean empty = USER_FILE.length() == 0;
		try {
			FileInputStream inFile = new FileInputStream(USER_FILE);
			BufferedInputStream buffer = new BufferedInputStream(inFile);
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(buffer);
			if (! empty) {
				this.users = (ArrayList<Client>) in.readObject();
			} else {
				this.users = new ArrayList<Client>();
				putUsers();
			}
		} catch (IOException | ClassNotFoundException e) {
			try {
				USER_FILE.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	// Update the user
	private void putUsers () {
		
		try {
			FileOutputStream outFile = new FileOutputStream(USER_FILE);
			BufferedOutputStream buffer = new BufferedOutputStream(outFile);
			ObjectOutputStream out = new ObjectOutputStream(buffer);
			out.writeObject(users);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Uploads all the users from the file fileName to this SystemControl.
	 * @param fileName the file name of the file to be uploaded
	 */
	public void uploadUsers (String fileName) {
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader br = new BufferedReader(file);
			String line;
			String [] splitString;
			while ((line = br.readLine()) != null ) {
				splitString = line.split(",");
				addUser(splitString[0], splitString[1], splitString[2], splitString[3],
						splitString[4], splitString[5]);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Uploads the users with the given csv file and a password file.
	 * @param fileName the path of the csv file
	 * @param passFile the path of the password file
	 */
	public void uploadUsers (String fileName, String passFile) {
		try {
			FileReader fileClient = new FileReader(fileName);
			FileReader filePass = new FileReader(passFile);
			BufferedReader brClient = new BufferedReader(fileClient);
			BufferedReader brPass = new BufferedReader(filePass);
			String lineC, lineP;
			String [] splitStringClient, splitStringPass;
			while ((lineC = brClient.readLine()) != null ) {
				lineP = brPass.readLine();
				splitStringClient = lineC.split(",");
				splitStringPass = lineP.split(",");
				addUser(splitStringClient[0], splitStringClient[1], 
						splitStringClient[2], splitStringClient[3], 
						splitStringClient[4], splitStringClient[5],
						splitStringPass[0], splitStringPass[1]);
			}
			brClient.close();
			brPass.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates an User with the given firstName, lastName, email, address,
	 * creditNum, creditExpiry, userName and password.
	 * @param firstName The firstName of the User to be created
	 * @param lastName The lastName of the User to be created
	 * @param email The email of the User to be created
	 * @param address The address of the User to be created
	 * @param creditNum The creditNum of the User to be created
	 * @param creditExpiry The creditExpiry of the User to be created
	 */
	public void addUser (String lastName, String firstName, String email, 
			String address, String creditNum,String creditExpiry, 
			String username, String password) {
		Client admin = new Client (firstName, lastName, email, address, 
				creditNum, creditExpiry);
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				users.set(users.indexOf(user), admin);
				putUsers();
				return;
			}
		}
		users.add(admin);
		putUsers();
		
	}
	
	/**
	 * Creates an User with the given firstName, lastName, email, address,
	 * creditNum, creditExpiry, userName and password.
	 * @param firstName The firstName of the User to be created
	 * @param lastName The lastName of the User to be created
	 * @param email The email of the User to be created
	 * @param address The address of the User to be created
	 * @param creditNum The creditNum of the User to be created
	 * @param creditExpiry The creditExpiry of the User to be created
	 */
	public void addUser (String lastName, String firstName, String email, 
			String address, String creditNum,String creditExpiry) {
		Client admin = new Client (firstName, lastName, email, address, 
				creditNum, creditExpiry);
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				users.set(users.indexOf(user), admin);
				putUsers();
				return;
			}
		}
		users.add(admin);
		putUsers();
		
	}
	
	/**
	 * Creates an UseClientr with the given firstName, lastName, email, address,
	 * creditNum, creditExpiry, userName, password, username, and password 
	 * @param firstName The firstName of the Client to be created
	 * @param lastName The lastName of the Client to be created
	 * @param email The email of the Client to be created
	 * @param address The address of the Client to be created
	 * @param creditNum The creditNum of the Client to be created
	 * @param creditExpiry The creditExpiry of the Client to be created
	 * @param password The password of the Client to be created
	 * @param username the username of the Client to be created
	 * @throws UserAlreadyExistsException if a User with given 
	 * email or username already exists.
	 */
	public void addClient (String lastName, String firstName, String email, 
			String address, String creditNum,String creditExpiry, 
			String username, String password) 
					throws UserAlreadyExistsException {
		
		Client client = new Client (firstName, lastName, email, address, 
				creditNum, creditExpiry, username, password);
		for (User user : users) {
			if (user.getEmail().equals(email) 
					|| user.getUsername().equals(username)) {
				throw new UserAlreadyExistsException("User with " +
						"this username or email already exists.");
			}
		}
		users.add(client);
		putUsers();
		
	}
	
	
	/**
	 * Creates an Admin with the given firstName, lastName, email, address,
	 * creditNum, creditExpiry, userName and password.
	 * @param firstName The firstName of the Admin to be created
	 * @param lastName The lastName of the Admin to be created
	 * @param email The email of the Admin to be created
	 * @param address The address of the Admin to be created
	 * @param creditNum The creditNum of the Admin to be created
	 * @param creditExpiry The creditExpiry of the Admin to be created
	 * @param userName The userName of the Admin to be created
	 * @param password The password of the Admin to be created
	 * @throws UserAlreadyExistsException if a User with given 
	 * email or username already exists.
	 */
	public void addAdmin (String lastName, String firstName, String email, 
			String address, String creditNum,String creditExpiry, 
			String username, String password) 
					throws UserAlreadyExistsException {
		
		Admin admin = new Admin (firstName, lastName, email, address, 
				creditNum, creditExpiry, username, password);
		for (User user : users) {
			if (user.getEmail().equals(email) || user.getUsername()
					.equals(username)) {
				throw new UserAlreadyExistsException("User with this " +
						"username or email already exists.");
			}
		}
		users.add(admin);
		putUsers();
		
	}
	
	
	/**
	 * Books the given itinerary.
	 * @param itinerary The itinerary to be booked
	 * @throws NotLogedInException Throws the exception if User is not 
	 * logged in
	 */
	public void bookItinerary (Itinerary itinerary) 
			throws NotLogedInException {
		if (isLoggedIn){
			user.bookItinerary(itinerary);
			putUsers();
		}
		throw new NotLogedInException("The User is not logged in!");
	}
	
	/**
	 * Checks if User with the given email exists in users and logs that 
	 * User in.
	 * @param email The email of the User to be logged in 
	 * @throws WrongPasswordException if the password is incorrect.
	 */
	public void loginUser (String username, String password) 
			throws WrongPasswordException {
		
		for (Client newUser : users) {
			if (newUser.getUsername().equals(username)){
				if (newUser.getPassword().equals(password)) {
					user = newUser;
					isLoggedIn = true;
					return;
				} else {
					throw new WrongPasswordException("Incorrect " +
							"Username or Password!");
				}
			}
		}
		throw new WrongPasswordException("Incorrect Username " +
				"or Password!");
		
	}
	
	
	/**
	 * Returns itineraries with the given date, origin and destination.
	 * @param date the date of the itineraries to be searched for
	 * @param origin the origin of the itineraries to be searched for
	 * @param destination the destination of the itineraries to be searched for
	 * @return itineraries that leave origin going to destination on date
	 */
	public List<Itinerary> getItineraries (String date, String origin, 
			String destination){
		return flightCenter.findItineraries(date, origin, destination);
		
	}
	
	
	/**
	 * Gets all Itineraries that leave origin going to destination 
	 * on date date sorted by total cost.
	 * @param date the date the Itineraries will start
	 * @param origin the origin of the Itineraries
	 * @param destination the destination of the itineraries
	 * @return String representation of all the Itineraries that go from 
	 * origin to destination on date
	 */
	public List<Itinerary> getItinerariesSortedByCost (String date, 
			String origin, String destination) {
		
		return flightCenter.findItinerariesSortedByCost(date, origin, 
				destination);
	}
	
	/**
	 * Gets all Itineraries that leave origin going to destination 
	 * on date date sorted by total time.
	 * @param date the date the Itineraries will start
	 * @param origin the origin of the Itineraries
	 * @param destination the destination of the itineraries
	 * @return String representation of all the Itineraries that go from 
	 * origin to destination on date
	 */
	public List<Itinerary> getItinerariesSortedByTime (String date, 
			String origin, String destination) {
		
		return flightCenter.findItinerariesSortedByTime(date, origin, 
				destination);
	}
	
	/**
	 * Gets the User with email email
	 * @param email the email of the User to search for
	 * @return the User with email email
	 * @throws NoSuchUserException if there is no User with email email
	 */
	public Client getUser (String email) throws NoSuchUserException {
		for (Client user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		throw new NoSuchUserException("No such user with email: " + email);
	}
	
	
	/**
	 * Uploads all the flights that is stored in the csv file at path
	 * @param path the path of the csv file
	 */
	public void uploadFlights (String path) {
		flightCenter.uploadFlights(path);
	}
	
	
	/**
	 * Gets all the Flights that go from origin to destination on date
	 * in this FlightCenter
	 * @param date the date of the Flights to look for
	 * @param origin the origin of the Flights to look for
	 * @param destination the destination of the Flights to look for
	 * @return the Flight with going from origin to destination on date
	 */
	public List<Flight> getFlights (String date, String origin, String 
			destination) {
		return flightCenter.getFlights(date, origin, destination);
	}
	
	
	/**
	 * Gets the user that is currently logged in.
	 * @return the user that is logged in.
	 * @throws NotLogedInException if no user is logged in.
	 */
	public Client getUser () throws NotLogedInException {
		if (isLoggedIn) {
			return user;
		} else {
			throw new NotLogedInException("No User is " +
					"not logged in!");
		}
	}
	
	/**
	 * Adds a flight to the flightCenter with the given information.
	 * @param flightNum
	 *            Number of the flight.
	 * @param departureDateTime
	 *            The date and time when the flight will leave its 
	 *            origin,in the form YYYY-MM-DD HH:MM.
	 * @param arrivalDateTime
	 *            The date and time when the flight will arrive to its
	 *            destination in the form YYYY-MM-DD HH:MM.
	 * @param airline
	 *            The flight's airline.
	 * @param origin
	 *            The origin of the flight.
	 * @param destination
	 *            The destination of the flight.
	 * @param cost
	 *            The cost of the flight.
	 * @throws FlightTooLongException
	 *             If the time taken from departure to arrival is more than 3
	 *             days.
	 * @throws InvalidDepartureOrArrivalDateTime
	 *             If departure date is after arrival date.
	 */
	public void addFlight (int flightNum, String departureDateTime,
			String arrivalDateTime, String airline, String origin,
			String destination, double cost, int numSeats) throws 
			FlightTooLongException, 
			InvalidDepartureOrArrivalDateTimeException {
		
		flightCenter.addFlight(new Flight(flightNum, departureDateTime, 
				arrivalDateTime, airline, origin, destination, 
				cost, numSeats));
	}
	
	/**
	 * 
	 * Gets the list of all the users registered in the system.
	 * @return the list of all the users.
	 */
	public ArrayList<Client> getUsers () {
		return users;
	} 
	
	
	/**
	 * Books the itinerary for the user with the given username.
	 * @param username the username of the user to book the itinerary for
	 * @param itinerary the itinerary to book
	 * @return
	 */
	public boolean bookItinerary (String username, Itinerary itinerary) {
		for (Client user : users) {
			if (user.getUsername().equals(username)) {
				boolean result = user.bookItinerary(itinerary);
				putUsers();
				return result;
			}
		}
		return false;
		
	}
	
	
	/**
	 * Updates the file that stores all the users.
	 */
	public void update () {
		putUsers();
	}
	
	/**
	 * Gets the FLight whit the given flight number.
	 * @param flightNum the flight number of the flight to be searched for
	 * @return the Flight the the flight number flightNum
	 */
	public Flight getFlight (int flightNum) {
		return flightCenter.getFlight(flightNum);
	}
}
	
	
	
	
	
	
	
	
	
	
	


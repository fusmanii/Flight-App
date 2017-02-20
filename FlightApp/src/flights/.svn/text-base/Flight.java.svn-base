/**
 * 
 */
package flights;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import exceptions.FlightTooLongException;
import exceptions.InvalidDepartureOrArrivalDateTimeException;



/**
 * A representation of a flight
 * @author Silina
 */
public class Flight implements Serializable {

	/**
	 * A serial ID used for serialization
	 */
	private static final long serialVersionUID = 2957132382462817148L;
 
	/*** The flight's airline. */
	private String airline;

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

	/** The flights number. */
	private int flightNum;

	/** The cost of the flight. */
	private double cost;
	
	/** the number of seats in this Flight*/
	private int numSeats;

	/**
	 * The time taken to travel from origin to destination, 
	 * in the form DD:HH:MM (days:hours:minutes)
	 */
	private String travelTime;

	/**
	 * Generates new flight.
	 * 
	 * @param flightNum
	 *            Number of the flight.
	 * @param departureDateTime
	 *            The date and time when the flight will leave its origin,in the
	 *            form YYYY-MM-DD HH:MM.
	 * @param arrivalDateTime
	 *            The date and time when the flight will arrive to its
	 *            destinationin the form YYYY-MM-DD HH:MM.
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
	 * 
	 */
	public Flight(int flightNum, String departureDateTime,
			String arrivalDateTime, String airline, String origin,
			String destination, double cost, int numSeats)
			throws FlightTooLongException,
			InvalidDepartureOrArrivalDateTimeException {

		this.flightNum = flightNum;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.numSeats = numSeats;
		// Calculates time taken from origin to destination.
		this.TravelTime(this.departureDateTime, this.arrivalDateTime);
	}

	/**
	 * Returns the airline of the flight.
	 * 
	 * @return The airline of the flight.
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Sets the airline of the flight.
	 * 
	 * @param airline
	 *            The airline of the flight.
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Returns the destination of the flight.
	 * 
	 * @return The flight's destination.
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * Returns the number of seats in this Flight.
	 * @return the number of seats
	 */
	public int getNumSeats () {
		return numSeats;
	}
	
	/**
	 * Sets the number of seats for this Flight
	 * @param numSeats the new number of seats for this Flight.
	 */
	public void setNumSeats (int numSeats) {
		this.numSeats = numSeats;
	}

	/**
	 * Sets the destination of the flight.
	 * 
	 * @param destination
	 *            The flight's destination.
	 */

	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Returns the origin of the flight.
	 * 
	 * @return The flight's origin.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the flight's origin.
	 * 
	 * @param origin
	 *            The flight's origin.
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Returns the flight's departure date.
	 * 
	 * @return The flight's departure date.
	 */
	public String getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * Sets the flight's departure date and time, must be in the form 
	 * YYYY-MM-DD HH:MM.
	 * 
	 * @param departureDateTime
	 *            The flight's departure date and time.
	 * @throws FlightTooLongException
	 *             If the time taken from departure to arrival is more than 3
	 *             days.
	 * @throws InvalidDepartureOrArrivalDateTime
	 *             If departure date is after arrival date.
	 */
	public void setDepartureDateTime(String departureDateTime)
			throws FlightTooLongException,
			InvalidDepartureOrArrivalDateTimeException {

		// Figures out travel time, throws exception if departure time is
		// invalid.
		TravelTime(departureDateTime, this.arrivalDateTime);
		this.departureDateTime = departureDateTime;

	}

	/**
	 * Returns the flight's arrival date and time in the form YYYY-MM-DD HH:MM.
	 * 
	 * @return The flight's arrival date and time in the form YYYY-MM-DD HH:MM.
	 */
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * Sets the flight's arrival date and time in the form YYYY-MM-DD HH:MM.
	 * 
	 * @param arrivalDateTime
	 *            The flight's arrival date and time in the form YYYY-MM-DD
	 *            HH:MM.
	 * @throws FlightTooLongException
	 *             If the time taken from departure to arrival is more than 3
	 *             days.
	 * @throws InvalidDepartureOrArrivalDateTime
	 *             If departure date is after arrival date.
	 */
	public void setArrivalDateTime(String arrivalDateTime)
			throws FlightTooLongException,
			InvalidDepartureOrArrivalDateTimeException {

		// Figures out travel time, throws exception if arrival time is invalid.
		TravelTime(this.departureDateTime, arrivalDateTime);
		this.arrivalDateTime = arrivalDateTime;
	}

	/**
	 * Returns the flight's number.
	 * 
	 * @return The flight's number.
	 */
	public int getFlightNum() {
		return flightNum;
	}

	/**
	 * Sets the flight's number.
	 * 
	 * @param flightNum
	 *            The flight's number.
	 */
	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	/**
	 * Returns the cost of the flight.
	 * 
	 * @return The cost of the flight.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost of the flight.
	 * 
	 * @param cost
	 *            The cost of the flight.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Calculates the amount of time taken from departure to arrival, and sets
	 * the travel time.
	 * 
	 * @param departureDateTime
	 *            departure date and time in the form YYYY-MM-DD HH:MM.
	 * @param arrivalDateTime
	 *            arrival date and time in the form YYYY-MM-DD HH:MM.
	 * @throws FlightTooLongException
	 *             If the time taken from departure to arrival is more than 3
	 *             days.
	 * @throws InvalidDepartureOrArrivalDateTime
	 *             If departure date is after arrival date.
	 */
	@SuppressLint("SimpleDateFormat") 
	public void TravelTime(String departureDateTime, String arrivalDateTime)
			throws FlightTooLongException,
			InvalidDepartureOrArrivalDateTimeException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			Date departure = format.parse(departureDateTime);
			Date arrival = format.parse(arrivalDateTime);

			long diff = arrival.getTime() - departure.getTime();
			
			long minutes = diff / (60 * 1000) % 60;         
			long hours = diff / (60 * 60 * 1000) % 24;
			long days = diff / (24 * 60 * 60 * 1000); 
			/*
			long days = TimeUnit.MILLISECONDS.toDays(diff);
			long hours = TimeUnit.MILLISECONDS.toHours(diff) % 24;
			long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
			*/

			if (days > 3) {
				throw new FlightTooLongException("Flight is over 3 days");
			}

			if (days < 0 || hours < 0 || minutes < 0) {
				throw new InvalidDepartureOrArrivalDateTimeException(
						"Departure date is past arrival date.");
			}

			this.travelTime = "";

			// If days, hours, minutes is less than 10 then add a zero in front
			// of it.
			if (days < 10) {
				this.travelTime += "0" + days + ":";
			} else {
				this.travelTime += days + ":";
			}

			if (hours < 10) {
				this.travelTime += "0" + hours + ":";
			} else {
				this.travelTime += hours + ":";
			}

			if (minutes < 10) {
				this.travelTime += "0" + minutes;
			} else {
				this.travelTime += minutes;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns the time taken to travel from origin to destination, in the form
	 * DD:HH:MM (days:hours:minutes).
	 * 
	 * @return The time taken to travel from origin to destination, in the form
	 *         DD:HH:MM (days:hours:minutes).
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  flightNum + "," + departureDateTime + "," + 
	arrivalDateTime + ","  +  airline +  "," + origin + "," + destination;
	}     

}

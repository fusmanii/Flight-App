/**
 * 
 */
package flights;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An airport representation. 
 * @author Faisal Usmani
 * @param <F> the type of vehicles in this Airport 
 */
public class Airport<F> implements Serializable {

	/**
	 * A serial ID used for serialization
	 */
	private static final long serialVersionUID = -7827199168528615896L;

	// the location of this Airport
	private String location;
	
	// the list of arrival vehicles
	private List<F> arrivals;
	
	// the list of departure vehicles
	private List<F> departures;
	
	// state of this Airport
	private boolean state;
	
	
	/**
	 * Creates a new Airport with the given location.
	 * @param location the location of this Airport
	 */
	public Airport (String location) {
		this.location = location;
		this.arrivals = new ArrayList<F>();
		this.departures = new ArrayList<F>();
		this.state = false;
	}
	
	/**
	 * Returns the state of this Airport, visited/unvisited.
	 * @return the state of this Airport
	 */
	public boolean isState() {
		return state;
	}

	
	/**
	 * Sets the state of this Airport to state.
	 * @param state the state of this Airport to be set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * Gets the List of all arrival vehicles.
	 * @return the List of all arrival vehicles
	 */
	public List<F> getArrivals() {
		return arrivals;
	}
	
	
	/**
	 * Sets the List of all arrival vehicle to arrivals.
	 * @param arrivals the arrivals vehicles to be set
	 */
	public void setArrivals(List<F> arrivals) {
		this.arrivals = arrivals;
	}

	/**
	 * Gets the location of this Airport.
	 * @return the location of this Airport
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * Gets the List of all departure vehicles.
	 * @return the List of all departure vehicles
	 */
	public List<F> getDepartures() {
		return departures;
	}

	/**
	 * Sets the List of all departure vehicle to departures.
	 * @param departures the departures vehicles to be set
	 */
	public void setDeparture(List<F> departures) {
		this.departures = departures;
	}
	
	/**
	 * Adds the vehicle to the list of arrivals at index index.
	 * @param vehicle the vehicle to be added
	 */
	public void addArrival(int index, F vehicle) {
		arrivals.add(index, vehicle);
	}
	
	/**
	 * Removes and returns the given arrival vehicle 
	 * @param vehicle the vehicle to be removed
	 * @return the removed vehicle from the list of arrivals
	 *
	public F getArrival(F vehicle) {
		return arrivals.remove(arrivals.indexOf(vehicle));
	} */
	
	/**
	 * Removes the arrival vehicle vehicle.
	 * @param vehicle the vehicle to be removed
	 */
	public void removeArrival(F vehicle) {
		arrivals.remove(vehicle);
	}
	
	/**
	 * Adds the vehicle to the list of departures at index index.
	 * @param index the index of vehicle to be inserted
	 * @param vehicle the vehicle to be added
	 */
	public void addDeparture(int index, F vehicle) {
		departures.add(index, vehicle);
	}
	
	/**
	 * Returns the given departure vehicle 
	 * @param vehicle the vehicle to be removed
	 * @return the removed vehicle from the list of departures
	 *
	public F getDeparture(F vehicle) {
		return departures.get(departures.indexOf(vehicle));
	} */
	
	/**
	 * Removes the departure vehicle vehicle.
	 * @param vehicle the vehicle to be removed
	 */
	public void removeDeparture(F vehicle) {
		departures.remove(vehicle);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Airport [location=" + location + ", arrivals=" + arrivals
				+ ", departures=" + departures + ", state=" + state + "]";
	}
	
	
	
	
	
}

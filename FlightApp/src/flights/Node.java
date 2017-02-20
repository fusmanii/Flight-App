/**
 * 
 */
package flights;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A node in a directed graph.
 * @author Faisal Usmani
 */
public class Node<F> implements Comparable<Node<F>>, Serializable {
	
	/**
	 * A serial ID used for serialization.
	 */
	private static final long serialVersionUID = -5880655527838345807L;

	
	
	// The list of edges to this Node
	private List<Integer> toEdges;
	
	private Map<Integer, Boolean> isVisitedPath;
	
	// The list of edges from this Node
	private List<Integer> fromEdges;
	
	// The id of this Node
	private int id;
	
	// The value of this Node
	private F value;
	
	// Indicate whether this Node is visited
	private boolean state;
	
	/**
	 * Creates a new node with the given ID id and value.
	 * @param id the ID of the new Node.
	 * @param value the value of the new Node.
	 */
	@SuppressLint("UseSparseArrays")
	public Node (int id, F value) {
		this.id = id;
		this.value = value;
		this.state = false;
		this.isVisitedPath = new HashMap<Integer, Boolean>();
	}

	/**
	 * Gets the list of ids this Node connects to.
	 * @return the ids of nodes that this Node is connected to.
	 */
	public List<Integer> getToEdges() {
		return toEdges;
	}

	/**
	 * Sets the list of ids this Node connects to to toEdge.
	 * @param the ids of all the nodes this Node is connected to
	 */
	public void setToEdges(List<Integer> toEdges) {
		this.toEdges = toEdges;
	}

	/**
	 * Adds the id to the list of edges this Node is connected to.
	 * @param id the id of the new Node that is connected to this Node.
	 */
	public void addToEdge(int id) {
		toEdges.add(id);
		isVisitedPath.put(id, new Boolean(false));
	}
	
	/**
	 * Gets the list of ids that connect to this Node.
	 * @return the ids of nodes that connect to this Node.
	 */
	public List<Integer> getFromEdges() {
		return fromEdges;
	}

	/**
	 * Sets the list of ids that connect to this Node to fromEdge.
	 * @param the ids of nodes connect to this Node.
	 */
	public void setFromEdges(List<Integer> fromEdges) {
		this.fromEdges = fromEdges;
	}

	/**
	 * Returns the ID of this Node.
	 * @return the id of this Node.
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * Returns the value of this Node.
	 * @return the value of this Node.
	 */
	public F getValue() {
		return value;
	}

	/**
	 * Sets the value of this Node to value.
	 * @param value the new value of this Node.
	 */
	public void setValue(F value) {
		this.value = value;
	}

	/**
	 * Returns the state of this Node, visited/unvisited.
	 * @return the state of this Node
	 */
	public boolean isVisited() {
		return state;
	}

	/**
	 * Sets the state of this Node to state.
	 * @param state the new state of this Node.
	 */
	public void setVisited(boolean state) {
		this.state = state;
	}
	
	/**
	 * Checks if the path between this and the node with ID id has been 
	 * visited.
	 * @param id the ID of the other node
	 * @return True if the path between this and node with ID id has been 
	 * visited.
	 */
	public boolean isVisitedPath(int id) {
		return isVisitedPath.get(id).booleanValue();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + id + "," + value.toString() + ")";
	}
	
	
	/**
	   * Compares this Node to Node other. Comparison is based on comparison
	   * of Node IDs.
	   * @return an int < 0, if ID of this Node is less than ID of other
	   *         0, if ID of this Node is equal to ID of other
	   *         an int > 0, otherwise
	   */
	  @Override
	  public int compareTo(Node<F> other) {
	    return id - other.id;
	  }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

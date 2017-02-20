/**
 * 
 */
package flights;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import exceptions.NoSuchNodeException;


/**
 * A directed graph of Node<F>s.
 * @author Faisal Usmani
 * 
 * @param <F> the type of value in this DirectedGraph
 */
public class DirectedGraph<F> implements Serializable {
		
	/**
	* A serial ID used for serialization.
	*/
	private static final long serialVersionUID = -2355993566278047483L;
	private Map<Node<F>, Set<Node<F>>> nodeToNodes;
	
	/**
	 * Creates a new empty DirectedGraph.
	 */
	public DirectedGraph () {
		this.nodeToNodes = new HashMap<Node<F>, Set<Node<F>>>();
	}
	
	/**
	 * Returns a Set of Nodes in this DirectedGraph.
	 * @return a Set of Nodes in this DirectedGraph
	 */
	public Set<Node<F>> getNodes () {
		return nodeToNodes.keySet();
	}
	
	/**
	 * Return the Node from this DirectedGraph with the given ID id.
	 * @param id the ID of the Node to return
	 * @return the Node from this DirectedGraph with the given ID
	 * @throws NoSuchNodeException if there is no Node with
	 * ID id in this DirectedGraph
	 */
	public Node<F> getNode (int id) throws NoSuchNodeException {
		for (Node<F> node : getNodes()) {
			if (node.getId() == id) {
				return node;
			}
		}
		throw new NoSuchNodeException("No node with id" + id);
	}
	
	
	
	
	/**
	  * Returns a Set of neighbours of the given Node.
	  * @param node the Node whose neighbours are returned
	  * @return a Set of neighbours of Node node
	  */
	public Set<Node<F>> getNeighbours (Node<F> node) {
		  return nodeToNodes.get(node);
	  } 
	  
	/**
	  * Returns whether Node with given ID id1 is adjacent to 
	  * Node with ID id2 in this DirectedGraph.
	  * @param id1 ID of the node to test for adjacency
	  * @param id2 ID of the node to test for adjacency
	  * @return true, if Node with ID id1 is adjacent to Node with ID 
	  * id2 in this DirectedGraph, and false otherwise
	  * @throws NoSuchNodeException if node with ID id1 or id2 is 
	  * not in this DirectedGraph
	  */
	public boolean isAdjacent (int id1, int id2) throws NoSuchNodeException {
		for (Node<F> nodeNeighbour : getNeighbours(getNode(id1))) {
			if (nodeNeighbour.getId() == id2) {
				return true;
			}
		}
		getNode(id2);
		return false;
	  }
	

	 /**
	   * Returns whether the given Node node1 is adjacent to Node node2 
	   * in this DirectedGraph.
	   * @param node1 the Node to test for adjacency with node2
	   * @param node2 the Node to test if Adjacent to node1
	   * @return true, if node1 is adjacent to node2 in this DirectedGraph,
	   *  and false otherwise
	   * @throws NoSuchNodeException if node1 or node2 are not 
	   * in this DirectedGraph
	   */
	public boolean isAdjacent (Node<F> node1, Node<F> node2) 
			throws NoSuchNodeException {
		return isAdjacent(node1.getId(), node2.getId());
	}
	
	
	 /**
	   * Returns the number of nodes in this DirectedGraph.
	   * @return The number of nodes in this DirectedGraph.
	   */
	public int getNumNodes() {
		return getNodes().size();	
	}
	
	
	/**
	   * Adds a new Node with the given value to this DirectedGraph. 
	   * @param id the ID of the new Node
	   * @param value the value of the new Node
	   */
	public void addNode (int id, F value) {
		nodeToNodes.put(new Node<F>(id, value), new TreeSet<Node<F>>());
	}
	
	
	 /**
	   * Adds an edge from node1 to node2 given nodes in this DirectedGraph. 
	   * If there is already an edge from node1 to node2, does nothing.
	   * @param node1 the node to add an edge to node2
	   * @param node2 the node to add an edge from node1
	   * @throws NoSuchNodeException if node1 or node2 is not in
	   *  this DirectedGraph
	   */
	public void addEdge (Node<F> node1, Node<F> node2)
			throws NoSuchNodeException {
		if (!node1.equals(node2)) {
			nodeToNodes.get(getNode(node1.getId())).add(getNode(node2.getId()));
			node1.addToEdge(node2.getId());
		}
	}


	 /**
	   * Adds an edge from node1 to node2 with the given IDs 
	   * in this DirectedGraph. 
	   * @param id1 ID of the node to add an edge from
	   * @param id2 ID of the node to add an edge to
	   * @throws NoSuchNodeExceptionf there is no Node with ID 
	   *  id1 or ID id2 in this DirectedGraph.
	   */
	public void addEdge (int id1, int id2) throws NoSuchNodeException {
		addEdge(getNode(id1), getNode(id2));
	}
	
	
	/**
	 * Returns a list of list of IDs that correspond to all the paths 
	 * between nodes with id1 and id2.
	 * @param id1 the ID of the starting Node
	 * @param id2 the ID of the finishing Node
	 * @return List of List of IDs that to all the paths 
	 * between nodes with id1 and id2
	 *
	public List<List<Integer>> getPaths (int id1, int id2) {
		
	}*/
	
	
	 





	
	
}

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains basic functionality for performing a Graph Node's operations.
 * @author David Tang & Ezekiel Calubaquib
 */
public class Node {
    private int value;
    private Node parent; // Used for backtracking when a cycle is found
    private Status status; // Can be: UNVISITED, VISITED, or COMPLETED
    private Map<Integer, Node> adjacencyList; // Mapping of Node values to the Node objects themself
    private int incomingEdges; // The number of times this Node should be visited is incomingEdges
    private int timesVisited; // Count the number of times the node has been revisited

    /**
     * Constructor to initialize instance variables and assign itself a value.
     * @param value The value of the Node
     */
    public Node(int value) {
        this.value = value;
        parent = null;
        status = Status.UNVISITED;
        adjacencyList = new HashMap<Integer, Node>();
        incomingEdges = 0;
        timesVisited = 0;
    }

    /**
     * Add an edge to this Node's adjacency list
     * @param child The child node to be added to this Node's adjacency list
     * @return True if successfully added. False if a Node with the given value is already in the adjacency list.
     */
    public boolean addEdge(Node child) {
        // Add the Node to the adjaency list only if it doesn't already contain the Node
        if (adjacencyList.get(child.value) == null) {
            adjacencyList.put(child.value, child);
            incomingEdges++;
            return true;
        }

        return false;
    }

    /**
     * @return The value of the node
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the Node's value
     * @param value The value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return The parent that was last visited this node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Set the parent node
     * @param parent The node to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * @return The status of the node
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the node
     * @param status The status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Resets the times visited back to 0 and the status back to unvisited
     */
    public void unvisit() {
        timesVisited = 0;
        status = Status.UNVISITED;
    }

    /**
     * Incremented the number of times visited and set the status to Status.VISITED
     */
    public void visit() {
        timesVisited++;
        status = Status.VISITED;
    }

    /**
     * Set the status of the node to Status.COMPLETED
     */
    public void complete() {
        status = Status.COMPLETED;
    }

    /**
     * @return The Node's adjacency list
     */
    public Map<Integer, Node> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Get a Node from the adjacency list
     * @param value The value of the Node to be retrieved
     * @return The Node with the value if it is in the adjacency list. False otherwise.
     */
    public Node getChild(int value) {
        return adjacencyList.get(value);
    }

    /**
     * @return The number of incoming edges
     */
    public int getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * @return The number of times the Node has been visited
     */
    public int getTimesVisited() {
        return timesVisited;
    }

    /**
     * This method is called during the Graph.dfs() method. It will determine if this Node will be revisited. The
     * total number of times this Node should be visited is the number of incoming edges.
     * @return True if the number of visited has not been used up. False otherwise.
     */
    public boolean shouldVisit() {
        return timesVisited < incomingEdges && status == Status.COMPLETED;
    }

    /**
     * A convenience method to print all the Node in the adjaceny list
     */
    public void printAdjacencyList() {
        for (Node node : adjacencyList.values()) {
            System.out.println(node.value);
        }
    }
}

import java.util.*;

/**
 * This class is a container for class Node and provides common helper methods.
 * @author David Tang & Ezekiel Calubaquib
 */
public class Graph {
    private int autoIncrementingValue; // Auto-incrementing integer to create Nodes with unique values
    private Map<Integer, Node> nodes; // Contains mappings of the Node's integer value to the Node object
    private int longestSimpleCycleLength;
    private Stack<Node> longestSimpleCyclePath;

    /**
     * Default constructor to initialize instance variables
     */
    public Graph() {
        autoIncrementingValue = 1;
        nodes = new HashMap<Integer, Node>();
        longestSimpleCycleLength = 0;
        longestSimpleCyclePath = null;
    }

    /**
     * Creates a node with the value determined by autoIncrementingValue
     * @return The value of the Node that was just created
     */
    public int addNode() {
        nodes.put(autoIncrementingValue, new Node(autoIncrementingValue));
        return autoIncrementingValue++;
    }

    /**
     * @param value Value of the node to look for
     * @return The Node object that has a value equal to the one passed. null otherwise.
     */
    public Node getNode(int value) {
        return nodes.get(value);
    }

    /**
     * Add the child Node into the parent Node's adjacencyList
     * @param parentValue The value of the parent Node
     * @param childValue The value of the child Node
     * @return True if adding was successful. False otherwise.
     */
    public boolean addEdge(int parentValue, int childValue) {
        // Error check in case one of the nodes don't exist
        if (parentValue > nodes.size() || childValue > nodes.size()) {
            return false;
        }

        Node parentNode = nodes.get(parentValue);
        Node childNode = nodes.get(childValue);

        return parentNode.addEdge(childNode);
    }

    /**
     * Convenience class to print all nodes in the Graph.
     */
    public void printNodes() {
        for (Node node: nodes.values()) {
            System.out.println(node.getValue());
        }
    }

    /**
     * Search for the longest simple cycle
     * @return Length of the longest simple cycle
     */
    public int findLongestSimpleCycle() {
        longestSimpleCycleLength = 0; // Reinitialize length before calculating longest simple cycle
        longestSimpleCyclePath = new Stack<Node>();

        for (Node node : nodes.values()) {
            node.unvisit(); // Reinitialize statuses before calculating longest simple cycle
        }

        for (Node node : nodes.values()) {
            if (node.getStatus() == Status.UNVISITED) {
                dfs(node);
            }
        }

        return longestSimpleCycleLength;
    }

    /**
     * This is a modified version of the DFS algorithm used to find cycles.
     * u -> parent, v -> child of u
     * @param u The parent node
     */
    public void dfs(Node u) {
        u.visit(); // Set this node to have Status.VISITED

        for (Node v : u.getAdjacencyList().values()) {
            if (v.getStatus() == Status.UNVISITED || v.shouldVisit()) {
                v.setParent(u);
                dfs(v);
            }
            // If it gets into the following statement, a cycle is detected
            else if (v.getStatus() == Status.VISITED) {
                Node temp = u; // A temporary node used for finding the path by backtracking the parent Nodes
                Stack<Node> cyclePath = new Stack<Node>(); // A stack is used because the path will be in reverse order
                int cycleLength = 1;

                cyclePath.push(temp);

                while (temp != v) {
                    cycleLength++;
                    temp = temp.getParent();
                    cyclePath.push(temp);
                }

                cyclePath.push(u);

                /*
                // Used for debugging cycle paths
                // When iterating through a Stack Collection, it does not give the intended order. The values must be
                // popped in order to get the desired order. The clone will make sure we still have a copy after popping.
                System.out.println("--- Cycle Found ---");
                Stack<Node> pathClone = (Stack<Node>) cyclePath.clone();
                while (!pathClone.isEmpty()) {
                    System.out.println(pathClone.pop().getValue()); // Print path to debug
                }
                */

                // If a cycle has been found that is longer than the previous one found, save it!
                if (longestSimpleCycleLength < cycleLength) {
                    longestSimpleCycleLength = cycleLength;
                    longestSimpleCyclePath = cyclePath;
                }
            }
        }

        u.complete(); // Set this node to have Status.COMPLETED
    }

    /**
     * @return The longest simple cycle length
     */
    public int getLongestSimpleCycleLength() {
        return longestSimpleCycleLength;
    }

    /**
     * Set the longestSimpleCycleLength
     * @param longestSimpleCycleLength The value to set
     */
    public void setLongestSimpleCycleLength(int longestSimpleCycleLength) {
        this.longestSimpleCycleLength = longestSimpleCycleLength;
    }

    /**
     * @return The longest simple cycle path
     */
    public Stack<Node> getLongestSimpleCyclePath() {
        return (Stack<Node>) longestSimpleCyclePath.clone();
    }

    /**
     * Set the longestSimpleCyclePath
     * @param longestSimpleCyclePath The path to set
     */
    public void setLongestSimpleCyclePath(Stack<Node> longestSimpleCyclePath) {
        this.longestSimpleCyclePath = longestSimpleCyclePath;
    }
}

import java.util.HashMap;
import java.util.Map;

public class Node {
    public static final int EXTRA_VISITS = 5;

    private int value;
    private Node parent;
    private Status status;
    private Map<Integer, Node> adjacencyList;
    private int incomingEdges;
    private int timesVisited;

    public Node(int value) {
        this.value = value;
        parent = null;
        status = Status.UNVISITED;
        adjacencyList = new HashMap<Integer, Node>();
        incomingEdges = 0;
        timesVisited = 0;
    }

    public boolean addEdge(Node child) {
        if (adjacencyList.get(child.value) == null) {
            adjacencyList.put(child.value, child);
            incomingEdges++;
            return true;
        }

        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void unvisit() {
        status = Status.UNVISITED;
    }

    public void visit() {
        status = Status.VISITED;
    }

    public void complete() {
        status = Status.COMPLETED;
    }

    public Map<Integer, Node> getAdjacencyList() {
        return adjacencyList;
    }

    public Node getChild(int value) {
        return adjacencyList.get(value);
    }

    public int getIncomingEdges() {
        return incomingEdges;
    }

    public void setIncomingEdges(int incomingEdges) {
        this.incomingEdges = incomingEdges;
    }

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }

    public boolean shouldVisit() {
        return timesVisited < incomingEdges + EXTRA_VISITS && status == Status.COMPLETED;
    }

    public void printAdjacencyList() {
        for (Node node : adjacencyList.values()) {
            System.out.println(node.value);
        }
    }
}

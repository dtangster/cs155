import java.util.*;

public class Graph {
    public static int NODE_VALUE = 1;

    private Map<Integer, Node> nodes;
    private int longestSimpleCycleLength;
    private Stack<Node> longestSimpleCyclePath;

    public Graph() {
        nodes = new HashMap<Integer, Node>();
        longestSimpleCycleLength = 0;
        longestSimpleCyclePath = null;
    }

    public int addNode() {
        nodes.put(NODE_VALUE, new Node(NODE_VALUE));
        return NODE_VALUE++;
    }

    public Node getNode(int value) {
        return nodes.get(value);
    }

    public boolean addEdge(int parentValue, int childValue) {
        // Error check in case one of the nodes don't exist
        if (parentValue > nodes.size() || childValue > nodes.size()) {
            return false;
        }

        Node parentNode = nodes.get(parentValue);
        Node childNode = nodes.get(childValue);

        return parentNode.addEdge(childNode);
    }

    public void printNodes() {
        for (Node node: nodes.values()) {
            System.out.println(node.getValue());
        }
    }

    public int findLongestSimpleCycle() {
        longestSimpleCycleLength = 0;
        longestSimpleCyclePath = new Stack<Node>();

        for (Node node : nodes.values()) {
            node.unvisit();
        }

        for (Node node : nodes.values()) {
            if (node.getStatus() == Status.UNVISITED) {
                dfs(node);
            }
        }

        return longestSimpleCycleLength;
    }

    public void dfs(Node u) {
        u.visit();

        for (Node v : u.getAdjacencyList().values()) {
            if (v.getStatus() == Status.UNVISITED || v.shouldVisit()) {
                v.setParent(u);
                dfs(v);
            }
            else if (v.getStatus() == Status.VISITED) {
                Node temp = u;
                Stack<Node> cyclePath = new Stack<Node>();
                int cycleLength = 1;

                System.out.println("--- Cycle Found ---");
                cyclePath.push(temp);

                while (temp != v) {
                    cycleLength++;
                    temp = temp.getParent();
                    cyclePath.push(temp);
                }

                // Print path to debug
                Stack<Node> pathClone = (Stack<Node>) cyclePath.clone();
                while (!pathClone.isEmpty()) {
                    System.out.println(pathClone.pop().getValue());
                }

                if (longestSimpleCycleLength < cycleLength) {
                    longestSimpleCycleLength = cycleLength;
                    longestSimpleCyclePath = cyclePath;
                }
            }
        }

        u.complete();
    }

    public int getLongestSimpleCycleLength() {
        return longestSimpleCycleLength;
    }

    public void setLongestSimpleCycleLength(int longestSimpleCycleLength) {
        this.longestSimpleCycleLength = longestSimpleCycleLength;
    }

    public Stack<Node> getLongestSimpleCyclePath() {
        return (Stack<Node>) longestSimpleCyclePath.clone();
    }

    public void setLongestSimpleCyclePath(Stack<Node> longestSimpleCyclePath) {
        this.longestSimpleCyclePath = longestSimpleCyclePath;
    }
}

import java.util.Stack;

/**
 * Driver class to test the functionality of our implementation for finding the longest simple cycle.
 * @author David Tang & Ezekiel Calubaquib
 */
public class Main {
    public static void main(String [] args) {
        Graph graph = new Graph();

        // Create nodes 1 through 10
        for (int i = 0; i < 10; i++) {
            graph.addNode();
        }

        // Create edges
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 7);
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);
        graph.addEdge(5, 4);
        graph.addEdge(6, 3);
        graph.addEdge(6, 5);
        graph.addEdge(7, 3);
        graph.addEdge(7, 6);

        System.out.println("Printing all nodes in the graph");
        graph.printNodes();

        System.out.println("Calculating longest simple cycle length");
        graph.findLongestSimpleCycle();

        System.out.println("Longest cycle found: " + graph.getLongestSimpleCycleLength());

        // Print the path of the longest simple cycle found
        Stack<Node> pathClone = graph.getLongestSimpleCyclePath();
        while (!pathClone.isEmpty()) {
            System.out.println(pathClone.pop().getValue());
        }
    }
}

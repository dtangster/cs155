import java.util.Stack;

/**
 * Driver class to test the functionality of our implementation for finding the longest simple cycle.
 * @author David Tang & Ezekiel Calubaquib
 */
public class Main {
    public static void main(String [] args) {
        completeGraph();
        smallGraph();
        figureOne();
    }

    public static void figureOne() {
        Graph graph = new Graph();

        // Create nodes 1 through 6
        for (int i = 0; i < 6; i++) {
            graph.addNode();
        }

        // Create edges
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 1);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);

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

    public static void smallGraph() {
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

    public static void completeGraph() {
        int numberOfNodes = 100;
        Graph graph = new Graph();

        for (int i = 0; i < numberOfNodes; i++) {
            graph.addNode();
        }

        // Create complete graph
        for (int i = 1; i <= numberOfNodes; i++) {
            for (int j = i + 1; j <= numberOfNodes; j++) {
                graph.addEdge(i, j);
            }

            for (int j = i - 1; j > 0; j--) {
                graph.addEdge(i, j);
            }
        }

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

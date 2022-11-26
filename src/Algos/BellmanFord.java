package Algos;
import java.util.List; 
import java.util.Arrays;

public class BellmanFord {
    
    public BellmanFord() {}

    public int findShortestPath(int nodes, List<List<Integer>> edges, int src, int dest, int numStops) {

        int[] weights = new int[nodes];
        // Initialise for each node just in case we use a node
        for (int i = 0; i < nodes; i++) {
            weights[i] = Integer.MAX_VALUE;
        }
        // Shortest path from start node is 0   
        weights[src] = 0;

        // We iterate through edges k + 1 times, where k is num of stops
        for (int i = 0; i < numStops + 1; i++) {
            // Create a copy so that we strictly update values based on 
            //   previous iterations values
            int[] weightsCopy = Arrays.copyOf(weights, nodes);
            for (int j = 0; j < edges.size(); j++) {
                int fromNode = edges.get(j).get(0);
                int destNode = edges.get(j).get(1);
                int weight = edges.get(j).get(2);

                // If cost to get to node 'fromNode' is infty, then we cant 
                //   reach the node yet, so skip
                if (weights[fromNode] == Integer.MAX_VALUE) continue;

                // Check if path to fromNode + weight of edge is less than 
                //  the current path to destNod
                weightsCopy[destNode] = Integer.min(weights[fromNode] + weight, weights[destNode]);
            }
            // Replace the current weights list with the copy
            weights = weightsCopy;
        }

        return weights[dest] == Integer.MAX_VALUE ? -1 : weights[dest];
    } 
}

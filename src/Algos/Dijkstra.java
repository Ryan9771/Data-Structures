package Algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Time Complexity: mlog(n) (n-> nodes, m-> edges)
// Structures: Heap, HashSet 
// Purpose: Finds the shortest path between 2 nodes BUT here we are finding the
//      longest path in ALL nodes. Just change the end condition
public class Dijkstra {
    
    public Dijkstra() {}
    
    // Dijkstra's algo for MAX cost path
    // Input of the time [[from, to, weight]]
    public int DijkstraAlgo(List<List<Integer>> edgeWeights, int nodes, int startNode) {

        Map<Integer, List<Pair>> store = new HashMap<>();

        // For each node in the edges in edgeWeights, adds the corresponding other 
        //      node along with the weight as a list
        // Essentially creates an adjacency list for each node A in A->B edge in edgeWeights
        // Stores as {from : (weight, to)}
        for (List<Integer> edge : edgeWeights) {
            int currNode = edge.get(0);
            List<Pair> curr = store.getOrDefault(currNode, new ArrayList<>());
            curr.add(new Pair(edge.get(1), edge.get(2)));
            store.put(currNode, curr);
        }

        PriorityQueue<Pair> heap = new PriorityQueue<>();
        // Adds the starting node
        heap.add(new Pair(0, startNode));
        
        // Keeps track of node visited to prevent a cycle
        Set<Integer> visited = new HashSet<>();

        // The actual max path result
        // Note: although we use a minheap here, we can still get the max path
        int max = 0;

        while (!heap.isEmpty()) {
            // Pop the node with the max current path weight
            Pair curr = heap.poll();
            int currNode = curr.node;

            // We already visited this node
            if (visited.contains(currNode)) {
                continue;
            }

            int weight = curr.weight;
            // Just stores the greater path length of current node when popped
            max = Math.max(max, weight);

            for (Pair edge : store.get(currNode)) {
                if (!visited.contains(edge.node)) {
                    visited.add(edge.node);
                    // The new path length will hence be weight to get to currNode
                    //      + the edge here
                    heap.add(new Pair(weight + edge.weight, edge.node));
                }
            }
        }

        // Returns the max length to reach every node from start node
        //      note: to show its not possible to reach every node, we 
        //      can just check visited.size() == nodes. If its false, then 
        //      we haven't reached all the nodes.
        return max;
    }

    public class Pair implements Comparable {

        private int weight;
        private int node;

        public Pair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }

        @Override
        public int compareTo(Object o) {
            int weight2 = ((Pair) o).weight;

            if (this.weight> weight2) {
                return 1;
            }

            if (this.weight< weight2) {
                return -1;
            }

            return 0;
        }
        
    }
}

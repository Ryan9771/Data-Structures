package Algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Time Complexity: O(n)
// Structures: HashSet
// Purpose: Traverse through a graph
public class BFS {

    public BFS() {};

    // Provided an adjacency list for each node [[x,y], [z, h]] -> node 0 has neighbours x,y. node 1 has neighbors z, h etc..
    public List<Integer> bfs(List<List<Integer>> graph, int startNode, int numNodes) {
        
        List<Integer> res = new ArrayList<>();
        boolean visited[] = new boolean[numNodes];

        // Stores the nodes in FIFO
        LinkedList<Integer> queue = new LinkedList<>();
        // Add the start node to the list
        queue.add(startNode);

        // Polls from the queue and adds each neighbour to the queue
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            visited[currNode] = true;
            res.add(currNode);

            for (int n : graph.get(currNode)) {
                if (!visited[n]) {
                    queue.add(n);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        BFS bfs = new BFS();

        List<List<Integer>> graph = Arrays.asList(
            Arrays.asList(1,2),
            Arrays.asList(2),
            Arrays.asList(0,3),
            Arrays.asList(3)
        );

        System.out.println(bfs.bfs(graph, 2, 4));
    }
}
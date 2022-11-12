package Algos;

import java.util.ArrayList;
import java.util.List;

// Time Complexity: O(n)
// Structures: HashSet
// Purpose: Traverse through nodes of a graph
public class DFS {

    public DFS() {}

    // Provided an adjacency list for each node [[x,y], [z, h]] -> node 0 has neighbours x,y. node 1 has neighbors z, h etc..
    public List<Integer> dfs(List<List<Integer>> graph, int startNode, int numNodes) {

        // Arraylist of the result in order
        List<Integer> res = new ArrayList<>();
        // Keeps track of nodes visited to prevent cycle
        boolean visited[] = new boolean[numNodes];

        dfsHelper(graph, visited, startNode, res);

        return res;
    }

    private void dfsHelper(List<List<Integer>> graph, boolean visited[], int currNode, List<Integer> res) {
        // Marks the visited as true. Guaranteed to be initially false anyway
        visited[currNode] = true;
        // Add the current node to the result, then:
        res.add(currNode);

        // ..then call dfsHelper in a loop for each of its nodes so that it can backtrack once done
        for (int node : graph.get(currNode)) {
            if (!visited[node]) {
                dfsHelper(graph, visited, node, res);
            }
        }
    }
    
}

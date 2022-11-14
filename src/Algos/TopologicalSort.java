package Algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import Data_Structures.StacksAndQueues.Stack;

// Time Complexity: O(n)
// Structures: HashSet
// Purpose: Finds the order of nodes with which all 
//      previous nodes are visited
public class TopologicalSort {

    public TopologicalSort() {}

        // Provided an adjacency list for each node [[x,y], [z, h]] -> node 0 has neighbours x,y. node 1 has neighbors z, h etc..
        public List<Integer> topoSort(List<List<Integer>> graph, int numNodes, int startNode) {
            Stack<Integer> stack = new Stack<>();

            // To keep track of visited nodes
            Map<Integer, Boolean> visited = new HashMap<>();

            topoSortHelper(graph, stack, visited, startNode);

            // Now we reverse the list
            List<Integer> res = new ArrayList<>();
            while (!stack.isEmpty()) {
                res.add(stack.pop());
            }

            return res;
        }

        private void topoSortHelper(List<List<Integer>> graph, Stack<Integer> res, Map<Integer, Boolean> visited, int currNode) {
            visited.put(currNode, true);
            for (int node : graph.get(currNode)) {
                if (!visited.getOrDefault(node, false)) {
                    topoSortHelper(graph, res, visited, node);
                }
            }
            res.push(currNode);
        }
    
        public static void main(String[] args) {
            TopologicalSort topoSort = new TopologicalSort();
    
            List<List<Integer>> graph = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(3),
                Arrays.asList(1),
                Arrays.asList(0, 1),
                Arrays.asList(0, 2)
            );
    
            System.out.println(topoSort.topoSort(graph, 6, 5));
        }
}

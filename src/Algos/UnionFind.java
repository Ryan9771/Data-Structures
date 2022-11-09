package Algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time complexity: O(nlog(n))
// Structures: Forest of trees
// Purpose: Finds the number of connected components in a graph
public class UnionFind {

    public UnionFind(){}

    // Returns the number of components found.
    // @param edges is a list of a pair of nodes representing an edge
    public int unionFind(int numNodes, List<List<Integer>> edges) {
        // Initialise parents array to keep track of index's parent. Rank for 
        //      size of components connected together
        int[] parents = new int[numNodes];
        int[] ranks = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        // Start res as numNodes as we assume all nodes are disconnected 
        // Iterate through edges and decrease number of components if merger occurs
        int res = numNodes;
        for (List<Integer> list : edges) {
            res -= union(parents, ranks, list.get(0), list.get(1));
        }
        
        return res;
    }

    // Returns the parent of the given node
    public int find(int[] parents, int node) {
        int res = node;

        // Goes back on indexes until res matches the parent
        while (res != parents[res]) {
            parents[res] = parents[parents[res]]; // Path comp optimisation
            res = parents[res];
        }

        return res;
    }

    // Checks parent of each node, and joins based on larger rank if different
    // Returns 1 if merge occurs, and 0 if not
    public int union(int[] parents, int[] ranks, int n1, int n2) {
        int par1 = find(parents, n1);
        int par2 = find(parents, n2);

        // If parents are the same
        if (par1 == par2) {
            return 0;
        }

        if (ranks[par2] > ranks[par1]) {
            parents[par1] = par2;
            ranks[par2] += ranks[par1];
        } else {
            parents[par2] = par1;
            ranks[par1] += ranks[par2];
        }

        return 1;
    }

    public void addToList(List<Integer> list, int n) {
        list.add(n);
    }


    public static void main(String[] args) {
        UnionFind uf = new UnionFind();
        List<List<Integer>> edges = Arrays.asList(Arrays.asList(0,1), Arrays.asList(1,2), Arrays.asList(3,4));
        System.out.println(uf.unionFind(5, edges));
    }

}

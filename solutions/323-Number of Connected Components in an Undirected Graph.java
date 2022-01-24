// LeetCode Question URL: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Union Find
 *
 * Time Complexity: O(E).
 *
 * Space Complexity: O(2 * V) = O(V)
 *
 * V = Number of vertices in the graph. E = Number of edges in the graph.
 */
class Solution1 {
    class UnionFind {
        int[] parents;
        int[] ranks;
        int count;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            this.ranks = new int[n + 1];
            this.count = n;
        }

        public int findParent(int a) {
            if (parents[a] == 0) {
                parents[a] = a;
            } else if (parents[a] != a) {
                parents[a] = findParent(parents[a]);
            }
            return parents[a];
        }

        public void union(int x, int y) {
            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) {
                return;
            }

            if (ranks[parentX] >= ranks[parentY]) {
                parents[parentY] = parentX;
                if (ranks[parentX] == ranks[parentY]) {
                    ranks[parentX]++;
                }
            } else {
                parents[parentX] = parentY;
            }
            count--;
        }
    }

    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || edges == null || edges.length == 0) {
            return n;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            uf.union(e[0] + 1, e[1] + 1);
        }

        return uf.count;
    }
}

/**
 * Using DFS
 *
 * Time Complexity: O(2 * (V + E)) = O(V + E)
 *
 * Space Complexity: O(V + E)
 *
 * V = Number of vertices in the graph. E = Number of edges in the graph.
 */
class Solution2 {
    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || edges == null || edges.length == 0) {
            return n;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        buildGraph(graph, edges);

        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
                dfsHelper(graph, i, visited);
            }
        }

        return count;
    }

    private void buildGraph(Map<Integer, Set<Integer>> graph, int[][] edges) {
        for (int[] e : edges) {
            graph.putIfAbsent(e[0], new HashSet<>());
            graph.get(e[0]).add(e[1]);
            graph.putIfAbsent(e[1], new HashSet<>());
            graph.get(e[1]).add(e[0]);
        }
    }

    private void dfsHelper(Map<Integer, Set<Integer>> graph, int i, Set<Integer> visited) {
        visited.add(i);
        Set<Integer> children = graph.get(i);
        if (children != null) {
            for (int c : children) {
                if (!visited.contains(c)) {
                    dfsHelper(graph, c, visited);
                }
            }
        }
    }
}

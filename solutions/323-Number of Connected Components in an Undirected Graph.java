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

    public class UnionFind {
        int[] parent;
        int[] rank;
        int groups;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            groups = n;
        }

        public int findParent(int x) {
            if (parent[x] == 0) {
                parent[x] = x;
            } else if (parent[x] != x) {
                parent[x] = findParent(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int pX = findParent(x);
            int pY = findParent(y);

            if (pX == pY) {
                return;
            }

            if (rank[pX] >= rank[pY]) {
                parent[pY] = pX;
                if (rank[pX] == rank[pY]) {
                    rank[pX]++;
                }
            } else {
                parent[pX] = pY;
            }
            groups--;
        }

        public int groupCount() {
            return groups;
        }
    }

    public int countComponents(int n, int[][] edges) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes is invalid");
        }
        if (n <= 1 || edges == null || edges.length == 0) {
            return n;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0] + 1, edge[1] + 1);
        }

        return uf.groupCount();
    }
}

class Solution2 {

    public class UnionFind {
        int[] parent;
        int[] rank;
        int groups;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            groups = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int findParent(int x) {
            if (parent[x] != x) {
                parent[x] = findParent(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int pX = findParent(x);
            int pY = findParent(y);

            if (pX == pY) {
                return;
            }

            if (rank[pX] >= rank[pY]) {
                parent[pY] = pX;
                if (rank[pX] == rank[pY]) {
                    rank[pX]++;
                }
            } else {
                parent[pX] = pY;
            }
            groups--;
        }

        public int groupCount() {
            return groups;
        }
    }

    public int countComponents(int n, int[][] edges) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes is invalid");
        }
        if (n <= 1 || edges == null || edges.length == 0) {
            return n;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.groupCount();
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
class Solution3 {
    public int countComponents(int n, int[][] edges) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes is invalid");
        }
        if (n <= 1 || edges == null || edges.length == 0) {
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

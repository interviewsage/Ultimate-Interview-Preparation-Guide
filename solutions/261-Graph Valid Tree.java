// LeetCode Question URL: https://leetcode.com/problems/graph-valid-tree/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Union Find to find cycles.
 *
 * Time Complexity: O(E * ⍺). Where ⍺ is a very slow growing function, which
 * is always ≤ 4. Thus the time complexity becomes O(E).
 *
 * Space Complexity: O(V).
 *
 * V = Number of nodes. E = Number of edges. Here E is V-1.
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

        public boolean union(int x, int y) {
            int pX = findParent(x);
            int pY = findParent(y);

            if (pX == pY) {
                return false;
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
            return true;
        }

        public int numConnectedGroups() {
            return groups;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (n < 0) {
            return false;
        }
        if (edges == null || edges.length == 0) {
            return n <= 1;
        }
        if (edges.length != n - 1) {
            // Proof:
            // https://math.stackexchange.com/questions/1198263/proof-verification-prove-that-a-tree-with-n-vertices-has-n-1-edges#comment8913833_3075437
            return false;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            if (!uf.union(e[0] + 1, e[1] + 1)) {
                return false;
            }
        }

        return uf.numConnectedGroups() == 1;
    }
}

/**
 * Using DFS.
 *
 * Time Complexity: O(V + 2*E) = O(V + E)
 *
 * Space Complexity: O(V + E + V + V) = O(3*V + E) = O(V + E)
 *
 * V = Number of nodes. E = Number of edges. Here E is V-1.
 */
class Solution2 {
    public boolean validTree(int n, int[][] edges) {
        if (n < 0) {
            return false;
        }
        if (edges == null || edges.length == 0) {
            return n <= 1;
        }
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        buildGraph(edges, graph);
        if (graph.size() != n) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        return !hasCycle(graph, visited, 0, -1) && visited.size() == n;
    }

    private void buildGraph(int[][] edges, Map<Integer, Set<Integer>> graph) {
        for (int[] e : edges) {
            graph.putIfAbsent(e[0], new HashSet<>());
            graph.putIfAbsent(e[1], new HashSet<>());
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
    }

    private boolean hasCycle(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int cur, int parent) {
        visited.add(cur);

        for (int child : graph.get(cur)) {
            /**
             * visited.contains(child) && child != parent ==> Child node was already visited
             * in this component and not coming from parent. Thus there is a cycle in the
             * given graph.
             */
            if ((visited.contains(child) && child != parent)
                    || (!visited.contains(child) && hasCycle(graph, visited, child, cur))) {
                return true;
            }
        }

        return false;
    }
}

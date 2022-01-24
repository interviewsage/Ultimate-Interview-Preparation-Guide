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

        public boolean union(int x, int y) {
            int parentX = findParent(x);
            int parentY = findParent(y);

            if (parentX == parentY) {
                return false;
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
            return true;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (n < 0) {
            return false;
        }
        if (n <= 1) {
            return true;
        }
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            if (!uf.union(e[0] + 1, e[1] + 1)) {
                return false;
            }
        }

        return uf.count == 1;
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
        if (n <= 1) {
            return true;
        }
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        buildGraph(graph, edges);

        Set<Integer> visited = new HashSet<>();
        if (graph.size() != n) {
            return false;
        }

        return !hasCycle(graph, visited, 0, -1) && visited.size() == n;
    }

    private void buildGraph(Map<Integer, Set<Integer>> graph, int[][] edges) {
        for (int[] e : edges) {
            graph.putIfAbsent(e[0], new HashSet<>());
            graph.get(e[0]).add(e[1]);
            graph.putIfAbsent(e[1], new HashSet<>());
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

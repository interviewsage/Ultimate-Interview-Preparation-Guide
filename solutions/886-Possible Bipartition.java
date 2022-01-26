// LeetCode Question URL: https://leetcode.com/problems/possible-bipartition/
// LeetCode Discuss URL:

import java.util.*;

/**
 * DFS
 *
 * Build the graph using the given edges and the color nodes alternately through
 * graph traversal.
 *
 * Time Complexity: Build Graph -> O(N + D). DFS -> O(V + E) = O(N + D). Total
 * time complexity = O(N + D) = O(N + N^2)
 *
 * Space Complexity: O(N + 2*D + N) = O(2*N + 2*N^2)
 *
 * N = Number of nodes. D = Number of dislikes edges. In worst case there can be
 * N^2 edges (C(N, 2) edges).
 */
class Solution1 {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        /**
         * Graph with zero and one nodes are always bipartite. Graph with 2 nodes is
         * bipartite only if the graph is undirected.
         *
         * Undirected graph with 2 edges is always bipartite.
         */
        if (N <= 2 || dislikes == null || dislikes.length <= 2) {
            return true;
        }

        HashMap<Integer, HashSet<Integer>> graph = buildGraph(N, dislikes);

        int[] nodeColor = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (nodeColor[i] == 0 && !isBipartite(graph, nodeColor, i, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isBipartite(HashMap<Integer, HashSet<Integer>> graph, int[] nodeColor, int node, int col) {
        nodeColor[node] = col;

        for (int neigh : graph.get(node)) {
            if (nodeColor[neigh] == col) {
                return false;
            }
            if (nodeColor[neigh] == 0 && !isBipartite(graph, nodeColor, neigh, -col)) {
                return false;
            }
        }

        return true;
    }

    private HashMap<Integer, HashSet<Integer>> buildGraph(int N, int[][] dislikes) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] d : dislikes) {
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }

        return graph;
    }
}

/**
 * BFS
 *
 * Build the graph using the given edges and the color nodes alternately through
 * graph traversal.
 *
 * Time Complexity: Build Graph -> O(N + D). BFS -> O(V + E) = O(N + D). Total
 * time complexity = O(N + D) = O(N + N^2)
 *
 * Space Complexity: O(N + 2*D + N) = O(2*N + 2*N^2)
 *
 * N = Number of nodes. D = Number of dislikes edges. In worst case there can be
 * N^2 edges (C(N, 2) edges).
 */
class Solution2 {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        /**
         * Graph with zero and one nodes are always bipartite. Graph with 2 nodes is
         * bipartite only if the graph is undirected.
         *
         * Undirected graph with 2 edges is always bipartite.
         */
        if (N <= 2 || dislikes == null || dislikes.length <= 2) {
            return true;
        }

        HashMap<Integer, HashSet<Integer>> graph = buildGraph(N, dislikes);

        int[] nodeColor = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (nodeColor[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                nodeColor[i] = 1;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();

                    for (int neigh : graph.get(cur)) {
                        if (nodeColor[neigh] == nodeColor[cur]) {
                            return false;
                        }
                        if (nodeColor[neigh] == 0) {
                            nodeColor[neigh] = -nodeColor[cur];
                            queue.offer(neigh);
                        }
                    }
                }
            }
        }

        return true;
    }

    private HashMap<Integer, HashSet<Integer>> buildGraph(int N, int[][] dislikes) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] d : dislikes) {
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }

        return graph;
    }
}

/**
 * Using Union-Find by Rank and Path Compression
 *
 * Build the graph using the given edges and the color nodes alternately using
 * UnionFind.
 *
 * Time Complexity: Build Graph -> O(D). UnionFind -> O(N). Adding all edges to
 * UnionFind = O(2 * D). Total
 * time complexity = O(N + D)
 *
 * Space Complexity: O(N + 2*D + N)
 *
 * N = Number of nodes. D = Number of dislikes edges. In worst case there can be
 * N^2 edges (C(N, 2) edges).
 */
class Solution3 {
    public class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int findParent(int n) {
            if (n != parents[n]) {
                parents[n] = findParent(parents[n]);
            }
            return parents[n];
        }

        public void union(int a, int b) {
            int parentA = findParent(a);
            int parentB = findParent(b);

            if (parentA == parentB) {
                return;
            }

            if (ranks[parentA] >= ranks[parentB]) {
                parents[parentB] = parentA;
                ranks[parentA] = ranks[parentA] == ranks[parentB] ? ranks[parentA] + 1 : ranks[parentA];
            } else {
                parents[parentA] = parentB;
            }
        }
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of people is invalid");
        }
        if (n <= 2 || dislikes == null || dislikes.length <= 2) {
            return true;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] d : dislikes) {
            graph.putIfAbsent(d[0], new HashSet<>());
            graph.get(d[0]).add(d[1]);
            graph.putIfAbsent(d[1], new HashSet<>());
            graph.get(d[1]).add(d[0]);
        }

        UnionFind uf = new UnionFind(n + 1);
        for (int person : graph.keySet()) {
            int parentOfPerson = uf.findParent(person);
            Set<Integer> dislikedPersons = graph.get(person);
            int firstPerson = dislikedPersons.iterator().next();

            for (int dp : dislikedPersons) {
                if (parentOfPerson == uf.findParent(dp)) {
                    return false;
                }
                uf.union(firstPerson, dp);
            }
        }

        return true;
    }
}
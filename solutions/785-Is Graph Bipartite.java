// LeetCode Question URL: https://leetcode.com/problems/is-graph-bipartite/
// LeetCode Discuss URL:

import java.util.*;

/**
 * DFS
 *
 * Color each node.
 *
 * https://www.quora.com/Why-is-the-complexity-of-DFS-O-V%2BE/answer/Sumeet-Dash-1?ch=10&oid=68882008&share=317ad6af&target_type=answer
 * Time Complexity: O(V + 2E) = O(N + N^2) in worst case
 *
 * Space Complexity: O(N) - Height of the graph + Colors Array
 *
 * N = Number of nodes. E = Number of edges in the graph. In worst case edges
 * can be N^2.
 */
class Solution1 {
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Input graph is null");
        }

        int numNodes = graph.length;
        if (numNodes <= 2) {
            return true;
        }

        int[] nodeColors = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            if (nodeColors[i] == 0 && !isBipartite(i, graph, nodeColors, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isBipartite(int node, int[][] graph, int[] nodeColors, int curColor) {
        nodeColors[node] = curColor;
        for (int neigh : graph[node]) {
            if (nodeColors[neigh] == curColor) {
                return false;
            }
            if (nodeColors[neigh] == 0 && !isBipartite(neigh, graph, nodeColors, -curColor)) {
                return false;
            }
        }
        return true;
    }
}

/**
 * BFS
 *
 * Color each node.
 *
 * https://www.quora.com/Why-is-the-complexity-of-DFS-O-V%2BE/answer/Sumeet-Dash-1?ch=10&oid=68882008&share=317ad6af&target_type=answer
 * Time Complexity: O(V + 2E) = O(N + N^2) in worst case
 *
 * Space Complexity: O(N) - Width of the graph + Colors Array
 *
 * N = Number of nodes. E = Number of edges in the graph. In worst case edges
 * can be N^2.
 */
class Solution2 {
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            return false;
        }

        int nodes = graph.length;
        if (nodes <= 2) {
            return true;
        }

        int[] nodeColors = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            if (nodeColors[i] != 0) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            nodeColors[i] = 1;
            queue.offer(i);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int curColor = nodeColors[cur];

                for (int neigh : graph[cur]) {
                    if (nodeColors[neigh] == curColor) {
                        return false;
                    }
                    if (nodeColors[neigh] == 0) {
                        nodeColors[neigh] = -curColor;
                        queue.offer(neigh);
                    }
                }
            }
        }

        return true;
    }
}

/**
 * Refer this post for Union-Find Solution:
 * https://leetcode.com/problems/is-graph-bipartite/discuss/176266/Clean-and-easy-unionfind-in-JAVA
 *
 * Time Complexity: O(2E) = O(N^2) in worst case
 *
 * Space Complexity: O(2*N) = Parents + Rank
 *
 * N = Number of nodes. E = Number of edges in the graph. In worst case edges
 * can be N^2.
 */
class Solution3 {
    public class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
        }

        public int findParent(int i) {
            if (ranks[i] == 0) {
                parents[i] = i;
                ranks[i] = 1;
            } else if (parents[i] != i) {
                parents[i] = findParent(parents[i]);
            }
            return parents[i];
        }

        public void union(int x, int y) {
            int pX = findParent(x);
            int pY = findParent(y);

            if (pX == pY) {
                return;
            }

            if (ranks[pX] >= ranks[pY]) {
                parents[pY] = pX;
                if (ranks[pX] == ranks[pY]) {
                    ranks[pX]++;
                }
            } else {
                parents[pX] = pY;
            }
        }
    }

    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Input graph is null");
        }

        int numNodes = graph.length;
        if (numNodes <= 2) {
            return true;
        }

        UnionFind uf = new UnionFind(numNodes);
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (uf.findParent(i) == uf.findParent(graph[i][j])) {
                    return false;
                }
                uf.union(graph[i][0], graph[i][j]);
            }
        }

        return true;
    }
}

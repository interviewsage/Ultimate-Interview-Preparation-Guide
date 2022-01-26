// LeetCode Question URL: https://leetcode.com/problems/is-graph-bipartite/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer this post for Union-Find Solution:
 * https://leetcode.com/problems/is-graph-bipartite/discuss/176266/Clean-and-easy-unionfind-in-JAVA
 */

/**
 * BFS
 *
 * Color each node.
 *
 * Time Complexity: O(V + E) = O(N + N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Number of nodes. E = Number of edges. In worst case edges can be N^2.
 */
class Solution1 {
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
 * DFS
 *
 * Color each node.
 *
 * Time Complexity: O(V + E) = O(N + N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Number of nodes. E = Number of edges. In worst case edges can be N^2.
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
            if (nodeColors[i] == 0 && !isBipartiteHelper(i, graph, nodeColors, 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isBipartiteHelper(int node, int[][] graph, int[] nodeColors, int curColor) {
        nodeColors[node] = curColor;
        for (int neigh : graph[node]) {
            if (nodeColors[neigh] == curColor) {
                return false;
            }
            if (nodeColors[neigh] == 0 && !isBipartiteHelper(neigh, graph, nodeColors, -curColor)) {
                return false;
            }
        }
        return true;
    }
}

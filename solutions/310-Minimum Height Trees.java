// LeetCode Question URL: https://leetcode.com/problems/minimum-height-trees/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
 *
 * Time Complexity: O(2N + 2N) = O(N)
 *
 * Space Complexity: O(2N) = O(N)
 *
 * N = number of vertices.
 */
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes is invalid");
        }
        if (n <= 2) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }

        /**
         * Time taken to populate the graph = O(V + E). Since the graph is a valid tree,
         * there will be no cycle and all nodes will be in the same connected component.
         * Thus there will be N-1 edges. Thus time taken = O(2*N).
         */
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();
        for (int[] e : edges) {
            processEdge(e[0], e[1], graph, leaves);
            processEdge(e[1], e[0], graph, leaves);
        }

        /**
         * Follow https://leetcode.com/problems/minimum-height-trees/solution/ for
         * explanation of the solution.
         */
        // Time taken = O(V + E) = O(2*N). As each node will be processed once.
        while (n > 2) {
            n -= leaves.size();
            Set<Integer> newLeaves = new HashSet<>();
            for (int l : leaves) {
                // Find the parent of the leaf. There will be only one edge connecting to the
                // leaf.
                // Remove this edge from parent node.
                // Delete this leaf node from graph.
                int parent = graph.remove(l).iterator().next();
                Set<Integer> parentsChildNodes = graph.get(parent);
                parentsChildNodes.remove(l);

                // Check if parent node has become a leaf node.
                if (parentsChildNodes.size() == 1) {
                    newLeaves.add(parent);
                }
            }
            leaves = newLeaves;
        }

        return new ArrayList<>(leaves);
    }

    private void processEdge(int a, int b, Map<Integer, Set<Integer>> graph, Set<Integer> leaves) {
        Set<Integer> childNodes = graph.get(a);
        if (childNodes == null) {
            childNodes = new HashSet<>();
            graph.put(a, childNodes);
            leaves.add(a);
        }
        childNodes.add(b);
        if (childNodes.size() == 2) {
            leaves.remove(a);
        }
    }
}

class Solution2 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        if (n <= 2) {
            result.add(0);
            if (n == 2) {
                result.add(1);
            }
            return result;
        }

        /**
         * Time taken to populate the graph = O(V + E). Since the graph is a valid tree,
         * there will be no cycle and all nodes will be in the same connected component.
         * Thus there will be N-1 edges. Thus time taken = O(2*N).
         */
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // Time taken = O(N) to find the initial leaves.
        for (int i = 0; i < n; i++) {
            // Finding Leaves
            if (graph.get(i).size() == 1) {
                result.add(i);
            }
        }

        /**
         * Maximum 2 roots are possible.
         *
         * This merges leaves at same level and then converts the graph into a Path
         * Graph. Thus if there are odd number nodes in the path graph, only one root is
         * possible with minimum tree height. In case of even number of nodes, 2 roots
         * are possible with minimum tree height.
         *
         * Very similar to finding the median.
         */
        // Time taken by this = O(N). As each leaf will be processed once.
        while (n > 2) {
            n -= result.size();
            List<Integer> newLeaves = new ArrayList<>();

            for (int l : result) {
                // Get parent of the leaf.
                int parent = graph.get(l).iterator().next();

                // Remove leaf from parent's graph
                graph.get(parent).remove(l);

                // Remove leaf node from graph
                graph.remove(l);

                // Now check if the parent has become the leaf
                if (graph.get(parent).size() == 1) {
                    newLeaves.add(parent);
                }
            }

            result = newLeaves;
        }

        return result;
    }
}

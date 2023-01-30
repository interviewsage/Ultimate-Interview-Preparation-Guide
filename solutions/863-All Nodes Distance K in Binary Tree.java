// LeetCode Question URL: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Refer first solution in this post:
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation
 *
 * As we know, if the distance from a node to target node is k, the distance
 * from its child to the target node is k + 1 unless the child node is closer to
 * the target node which means the target node is in it's subtree.
 *
 * To avoid this situation, we need to travel the tree first to find the path
 * from root to target, to store the value of distance in HashMap from the all
 * nodes in that path to target
 *
 * Then we can easily use dfs to travel the whole tree. Every time when we meet
 * a TreeNode which has already been stored in map, use the stored value in
 * HashMap instead of plus 1.
 *
 * <pre>
 * Time Complexity:
 * findTargetNode() --> O(N) --> In worst case whole tree can be traversed.
 * distanceKHelper() --> O(N) --> In worst case whole tree can be traversed.
 * Total time complexity: O(N + N) = O(N)
 *
 * Space Complexity:
 * distFromTarget --> O(H)
 * Recursion Stack --> O(H)
 * Total space complexity: O(H + H) = O(H)
 * </pre>
 *
 * N = Number of nodes in the input tree. H = Height of the tree.
 */
class Solution1 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }

        List<Integer> result = new ArrayList<>();
        if (root == null || target == null) {
            return result;
        }

        Map<TreeNode, Integer> distFromTarget = new HashMap<>();

        // Finding the path from root to target and store the distance from target for
        // each node in the path
        findTargetNode(root, target, distFromTarget);

        // Now use DFS to traverse the tree to find the result nodes.
        distanceKHelper(result, root, target, k, distFromTarget.get(root), distFromTarget);

        return result;
    }

    private int findTargetNode(TreeNode node, TreeNode target, Map<TreeNode, Integer> distFromTarget) {
        if (node == null) {
            return -1;
        }
        if (node == target) {
            distFromTarget.put(node, 0);
            return 0;
        }

        int left = findTargetNode(node.left, target, distFromTarget);
        if (left != -1) {
            distFromTarget.put(node, left + 1);
            return left + 1;
        }

        int right = findTargetNode(node.right, target, distFromTarget);
        if (right != -1) {
            distFromTarget.put(node, right + 1);
            return right + 1;
        }

        return -1;
    }

    private void distanceKHelper(List<Integer> result, TreeNode node, TreeNode target, int k, int curDist,
            Map<TreeNode, Integer> distFromTargetMap) {
        if (node == null) {
            return;
        }

        Integer distFromMap = distFromTargetMap.get(node);
        if (distFromMap != null) {
            curDist = distFromMap;
        }
        if (curDist == k) {
            result.add(node.val);
        }
        if (distFromMap == null && curDist >= k) {
            return;
        }

        distanceKHelper(result, node.left, target, k, curDist + 1, distFromTargetMap);
        distanceKHelper(result, node.right, target, k, curDist + 1, distFromTargetMap);
    }
}

/**
 * Build an undirected graph and then perform level order traversal with target
 * as root.
 *
 * Time Complexity: buildGraph() -> O(N). Level Order Traversal O(N). Thus total
 * time complexity: O(N).
 *
 * Space Complexity: O(2 * N) = O(N)
 *
 * N = Number of nodes in the input tree. Number of edges in a tree are N-1.
 */
class Solution2 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (root == null || target == null || K < 0) {
            return result;
        }

        HashMap<TreeNode, HashSet<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        if (!graph.containsKey(target)) {
            return result;
        }
        if (K == 0) {
            result.add(target.val);
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        while (!queue.isEmpty() && K > 0) {
            K--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                for (TreeNode neigh : graph.get(cur)) {
                    if (!visited.contains(neigh)) {
                        if (K == 0) {
                            result.add(neigh.val);
                        } else {
                            queue.offer(neigh);
                            visited.add(neigh);
                        }
                    }
                }
            }
        }

        return result;
    }

    private void buildGraph(TreeNode root, TreeNode parent, HashMap<TreeNode, HashSet<TreeNode>> graph) {
        if (root == null) {
            return;
        }

        if (!graph.containsKey(root)) {
            graph.put(root, new HashSet<>());
        }
        if (parent != null) {
            graph.get(root).add(parent);
            graph.get(parent).add(root);
        }

        buildGraph(root.left, root, graph);
        buildGraph(root.right, root, graph);
    }
}

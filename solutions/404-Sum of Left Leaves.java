// LeetCode Question URL: https://leetcode.com/problems/sum-of-left-leaves/
// LeetCode Discuss URL: https://leetcode.com/problems/sum-of-left-leaves/discuss/1557944/Java-TC:-O(N)-or-SC:-O(Height)-or-2-Simple-DFS-(Recursive-and-Iterative)-+-BFS-Solutions

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
 * DFS Recursive
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Checking if left Node is a leaf node
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }

        // Exploring the tree further.
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}

/**
 * DFS Recursive
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfsHelper(root, false);
    }

    private int dfsHelper(TreeNode node, boolean isLeftNode) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return isLeftNode ? node.val : 0;
        }

        return dfsHelper(node.left, true) + dfsHelper(node.right, false);
    }
}

/**
 * DFS Iterative (Pre-Order Traversal)
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(W) = O(N) in worst case.
 *
 * N = Number of nodes. W = Width of the tree.
 */
class Solution3 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair<TreeNode, Boolean>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, false));

        int result = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> cur = stack.pop();
            TreeNode node = cur.getKey();
            boolean isLeft = cur.getValue();

            if (isLeft && node.left == null && node.right == null) {
                result += node.val;
                continue;
            }

            if (node.right != null) {
                stack.push(new Pair<>(node.right, false));
            }
            if (node.left != null) {
                stack.push(new Pair<>(node.left, true));
            }
        }

        return result;
    }
}

/**
 * BFS Iterative
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * <pre>
 * Space Complexity: O(Width of the tree)
 * In case of a complete tree the width can be N/2. Thus worst case complexity = O(N)
 * </pre>
 *
 * N = Number of nodes.
 */
class Solution4 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Boolean>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, false));

        int result = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Boolean> cur = queue.poll();
            TreeNode node = cur.getKey();
            boolean isLeft = cur.getValue();

            if (isLeft && node.left == null && node.right == null) {
                result += node.val;
                continue;
            }

            if (node.left != null) {
                queue.offer(new Pair<>(node.left, true));
            }
            if (node.right != null) {
                queue.offer(new Pair<>(node.right, false));
            }
        }

        return result;
    }
}
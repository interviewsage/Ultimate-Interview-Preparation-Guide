// LeetCode Question URL: https://leetcode.com/problems/maximum-depth-of-binary-tree/
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
 * Recursive DFS Solution
 *
 * Time Complexity: O(N) -> Each Node is visited once.
 *
 * Space Complexity: O(Height of the tree) = O(N) in worst case -> Recursion
 * stack.
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

/**
 * Iterative DFS Solution
 *
 * Time Complexity: O(N) -> Each Node is visited once.
 *
 * Space Complexity: O(Height of the tree) = Worst case O(N) in skewed tree.
 * Best case O(log N) in balanced tree.
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 1;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curDepth = cur.getValue();

            if (curNode.left == null && curNode.right == null) {
                maxDepth = Math.max(maxDepth, curDepth);
                continue;
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, curDepth + 1));
            }
            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, curDepth + 1));
            }
        }

        return maxDepth;
    }
}

/**
 * Iterative BFS Solution
 *
 * Time Complexity: O(N) -> Each Node is visited once.
 *
 * Space Complexity: O(N/2) = O(N) -> Queue will store each level. Space
 * Complexity is maximum number of nodes at the same level (the number of leaf
 * nodes in a full binary tree)
 *
 * N = Number of nodes in the tree.
 */
class Solution3 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;

        while (!queue.isEmpty()) {
            maxDepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return maxDepth;
    }
}

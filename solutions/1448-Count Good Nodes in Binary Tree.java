// LeetCode Question URL: https://leetcode.com/problems/count-good-nodes-in-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * DFS PreOrder Recursive
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return goodNodesHelper(root, root.val);
    }

    private int goodNodesHelper(TreeNode node, int curMax) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val >= curMax) {
            count = 1;
            curMax = node.val;
        }

        return count + goodNodesHelper(node.left, curMax) + goodNodesHelper(node.right, curMax);
    }
}

/**
 * DFS Iterative
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, root.val));
        int count = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curMax = cur.getValue();

            if (curNode.val >= curMax) {
                count++;
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, Math.max(curMax, curNode.left.val)));
            }
            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, Math.max(curMax, curNode.right.val)));
            }
        }

        return count;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/balanced-binary-tree/
// LeetCode Discuss URL:

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
 * DFS - Bottom Up approach
 *
 * Calculate max depth of both left and right subtree and compare.
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * Space Complexity: O(Height of the tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeight(root) != -2;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.left);
        if (leftHeight == -2) {
            return -2;
        }
        int rightHeight = getHeight(node.right);
        if (rightHeight == -2) {
            return -2;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -2;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

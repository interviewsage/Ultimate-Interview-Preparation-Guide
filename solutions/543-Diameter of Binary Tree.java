// LeetCode Question URL: https://leetcode.com/problems/diameter-of-binary-tree/

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
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] diameter = new int[1];
        diameterOfBinaryTreeHelper(root, diameter);
        return diameter[0];
    }

    /**
     * This function uses correct definition of height. Height is calculated by
     * counting the edges. Height of the leaf node is zero.
     */
    private int diameterOfBinaryTreeHelper(TreeNode node, int[] diameter) {
        if (node == null) {
            return -1;
        }

        int left = diameterOfBinaryTreeHelper(node.left, diameter);
        int right = diameterOfBinaryTreeHelper(node.right, diameter);

        /**
         * In Diameter calculation +2 is for the edges connecting the subtrees to the
         * root node.
         */
        diameter[0] = Math.max(diameter[0], left + right + 2);
        return Math.max(left, right) + 1;
    }
}

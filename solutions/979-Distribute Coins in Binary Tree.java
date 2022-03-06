// LeetCode Question URL: https://leetcode.com/problems/distribute-coins-in-binary-tree/
// LeetCode Discuss URL:

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Post-Order Traversal
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C++-with-picture-post-order-traversal
 * 2. https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221930/JavaC++Python-Recursive-Solution
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height)
 */
class Solution {
    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] totalMoves = { 0 };
        distributeCoinsHelper(root, totalMoves);
        return totalMoves[0];
    }

    private int distributeCoinsHelper(TreeNode node, int[] totalMoves) {
        if (node == null) {
            return 0;
        }

        int extraCoinsLeft = distributeCoinsHelper(node.left, totalMoves);
        int extraCoinsRight = distributeCoinsHelper(node.right, totalMoves);

        totalMoves[0] += Math.abs(extraCoinsLeft) + Math.abs(extraCoinsRight);
        return node.val + extraCoinsLeft + extraCoinsRight - 1;
    }
}

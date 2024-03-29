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
 * Space Complexity: O(H)
 */
class Solution1 {
    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] totalMoves = { 0 };
        distributeCoinsDfsHelper(root, totalMoves);
        return totalMoves[0];
    }

    private int distributeCoinsDfsHelper(TreeNode node, int[] totalMoves) {
        if (node == null) {
            return 0;
        }

        int extraCoinsLeft = distributeCoinsDfsHelper(node.left, totalMoves);
        int extraCoinsRight = distributeCoinsDfsHelper(node.right, totalMoves);

        totalMoves[0] += Math.abs(extraCoinsLeft) + Math.abs(extraCoinsRight);
        return node.val + extraCoinsLeft + extraCoinsRight - 1;
    }
}

class Solution2 {
    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int moves = 0;

        if (root.left != null) {
            moves += distributeCoins(root.left);
            root.val += root.left.val - 1;
            moves += Math.abs(root.left.val - 1);
        }
        if (root.right != null) {
            moves += distributeCoins(root.right);
            root.val += root.right.val - 1;
            moves += Math.abs(root.right.val - 1);
        }

        return moves;
    }
}

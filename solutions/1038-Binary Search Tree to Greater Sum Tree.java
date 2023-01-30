// LeetCode Question URL: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
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
 * Recursive - Reverse In-Order. Sum from right side can be added to current
 * node and passed to left node.
 *
 * No need for 1D array.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        bstToGstHelper(root, 0);

        return root;
    }

    private int bstToGstHelper(TreeNode node, int curSum) {
        if (node == null) {
            return curSum;
        }

        node.val += bstToGstHelper(node.right, curSum);
        return bstToGstHelper(node.left, node.val);
    }
}

/**
 * Iterative - Reverse Pre-Order. Keep sum of all numbers seen till now
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int sum = 0;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }

            cur = stack.pop();

            cur.val += sum;
            sum = cur.val;

            cur = cur.left;
        }

        return root;
    }
}

/**
 * Recursive - Reverse In-Order. Keep sum of all numbers seen till now
 *
 * This solution used 1D array to keep the sum.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution3 {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        bstToGstHelper(root, new int[1]);

        return root;
    }

    private void bstToGstHelper(TreeNode node, int[] sum) {
        if (node == null) {
            return;
        }

        bstToGstHelper(node.right, sum);

        node.val += sum[0];
        sum[0] = node.val;

        bstToGstHelper(node.left, sum);
    }
}

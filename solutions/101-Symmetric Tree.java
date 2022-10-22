// LeetCode Question URL: https://leetcode.com/problems/symmetric-tree/
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
 * Recursive Solution
 *
 * Time Complexity: O(N/2)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSymmetricHelper(p.left, q.right) && isSymmetricHelper(p.right, q.left);
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(N/2)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isSame(root.left, root.right)) {
            return false;
        }
        if (root.left == null) {
            return true;
        }

        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { root.left, root.right });

        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            if (!isSame(cur[0].left, cur[1].right) || !isSame(cur[0].right, cur[1].left)) {
                return false;
            }

            if (cur[0].left != null) {
                stack.push(new TreeNode[] { cur[0].left, cur[1].right });
            }
            if (cur[0].right != null) {
                stack.push(new TreeNode[] { cur[0].right, cur[1].left });
            }
        }

        return true;
    }

    private boolean isSame(TreeNode m, TreeNode n) {
        if (m == null && n == null) {
            return true;
        }
        if (m == null || n == null) {
            return false;
        }
        return m.val == n.val;
    }
}

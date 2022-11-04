// LeetCode Question URL: https://leetcode.com/problems/same-tree/
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
 * Time Complexity: O(min(N1, N2))
 *
 * Space Complexity: O(min(H_N1, H_N2)) -> Height of the tree. In worst case
 * O(min(N1, N2))
 *
 * N1 = Nodes in tree 1. N2 = Nodes in tree 2
 * H_N1 = Height of tree 1. H_N2 = Height of tree 2.
 */
class Solution1 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(min(N1, N2))
 *
 * Space Complexity: O(min(H_N1, H_N2)) -> Height of the tree. In worst case
 * O(min(N1, N2))
 *
 * N1 = Nodes in tree 1. N2 = Nodes in tree 2
 * H_N1 = Height of tree 1. H_N2 = Height of tree 2.
 */
class Solution2 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (!validateNode(p, q)) {
            return false;
        }
        if (p == null) {
            return true;
        }

        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { p, q });
        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();

            if (!validateNode(cur[0].left, cur[1].left) || !validateNode(cur[0].right, cur[1].right)) {
                return false;
            }

            if (cur[0].left != null) {
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            }
            if (cur[0].right != null) {
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            }
        }

        return true;
    }

    private boolean validateNode(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        return n1 != null && n2 != null && n1.val == n2.val;
    }
}

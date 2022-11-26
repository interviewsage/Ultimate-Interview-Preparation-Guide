// LeetCode Question URL: https://leetcode.com/problems/validate-binary-search-tree/
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
 * Iterative Solution
 *
 * Time Complexity: O(N) -> All nodes are pushed once in the stack and popped
 * out of stack once.
 *
 * Space Complexity: O(H) -> Height of the tree. In worst case O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 *
 * Note: If duplicates are allowed see solution 3 in the file.
 */
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (!stack.isEmpty() || cur != null) {
            // Push all left nodes to the stack
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // Get the top node and compare with previous node processed
            cur = stack.pop();
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            // Get ready for next iteration (exploring the right sub tree of current node.)
            pre = cur;
            cur = cur.right;
        }

        return true;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N) -> All nodes are visited once.
 *
 * Space Complexity: O(H) -> Height of the tree. In worst case O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }
        return isValidBSTHelper(node.left, min, node.val) && isValidBSTHelper(node.right, node.val, max);
    }
}

/**
 * Iterative Solution (Duplicates are allowed in this solution)
 *
 * Refer:
 * https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)/122619
 *
 * In the below solution duplicate values must be on the left side of a node. If
 * it needs to be on right side, then just switch the > and >= signs.
 *
 * Time Complexity: O(N) -> All nodes are visited once.
 *
 * Space Complexity: O(H) -> Height of the tree. In worst case O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        boolean onRightSideOfPrevious = false;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if (pre != null && ((!onRightSideOfPrevious && pre.val > cur.val)
                    || (onRightSideOfPrevious && pre.val >= cur.val))) {
                return false;
            }

            pre = cur;
            cur = cur.right;
            onRightSideOfPrevious = cur == null ? false : true;
        }

        return true;
    }
}

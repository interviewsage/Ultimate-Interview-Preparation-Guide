// LeetCode Question URL: https://leetcode.com/problems/increasing-order-search-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(2 * N). Once to find the right most element in the sub
 * tree, and once to check if any left subtree has to be solved.
 *
 * OR
 *
 * Once to place in the correct position and then second to pass through to go
 * to other nodes.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode dummy = new TreeNode();
        dummy.right = root;
        TreeNode pre = dummy;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left != null) {
                TreeNode temp = cur.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = cur;
                pre.right = cur.left;
                cur.left = null;
                cur = pre.right;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }

        return dummy.right;
    }
}

/**
 * Recursive Solution (inorder)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of the tree). In worst case O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode dummy = new TreeNode();
        increasingBSTInorderHelper(root, new TreeNode[] { dummy });
        return dummy.right;
    }

    private void increasingBSTInorderHelper(TreeNode node, TreeNode[] curSolved) {
        if (node == null) {
            return;
        }

        increasingBSTInorderHelper(node.left, curSolved);
        curSolved[0].right = node;
        curSolved[0] = curSolved[0].right;
        node.left = null;
        increasingBSTInorderHelper(node.right, curSolved);
    }
}

/**
 * Iterative Solution (inorder)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of the tree). In worst case O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution3 {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode dummy = new TreeNode();
        TreeNode cur = dummy;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            cur.right = root;
            cur = cur.right;
            root.left = null;

            root = root.right;
        }

        return dummy.right;
    }
}

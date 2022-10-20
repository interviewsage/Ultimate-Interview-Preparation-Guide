// LeetCode Question URL: https://leetcode.com/problems/binary-tree-inorder-traversal/
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
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H). in worst case O(N) space used by stack.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }

        return result;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H). in worst case O(N)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderDfsHelper(root, result);
        return result;
    }

    private void inorderDfsHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorderDfsHelper(node.left, result);
        result.add(node.val);
        inorderDfsHelper(node.right, result);
    }
}

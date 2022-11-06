// LeetCode Question URL: https://leetcode.com/problems/binary-tree-postorder-traversal/
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                // Adding left side nodes.
                stack.push(cur);
                cur = cur.left;
            }

            // Getting top of stack.
            cur = stack.peek();
            // Checking if it has a right subtree and its not already processed.
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            result.add(cur.val);
            pre = stack.pop();
            cur = null;
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        postorderHelper(result, root);

        return result;
    }

    private void postorderHelper(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }

        postorderHelper(result, node.left);
        postorderHelper(result, node.right);
        result.add(node.val);
    }
}

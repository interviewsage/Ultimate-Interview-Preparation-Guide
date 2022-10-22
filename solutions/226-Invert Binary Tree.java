// LeetCode Question URL: https://leetcode.com/problems/invert-binary-tree/
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
 * Recursive Solution - DFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H). In worst case O(N)
 *
 * N = Number nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
    }
}

/**
 * Iterative Solution - DFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H). In worst case O(N)
 *
 * N = Number nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode left = cur.left;
            cur.left = cur.right;
            cur.right = left;

            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        return root;
    }
}

/**
 * Recursive Solution - BFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W). In worst case O(N) -> In a full (proper) binary tree
 * leaf level has N/2 nodes.
 *
 * N = Number nodes in the tree. W = Width of the tree.
 */
class Solution3 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode t = cur.left;
            cur.left = cur.right;
            cur.right = t;
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return root;
    }
}

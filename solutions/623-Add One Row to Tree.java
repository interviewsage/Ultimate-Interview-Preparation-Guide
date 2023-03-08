// LeetCode Question URL: https://leetcode.com/problems/add-one-row-to-tree/
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
 * DFS
 *
 * Use DFS to find nodes at D-1 and then add the new nodes.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of the tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("Input depth is invalid");
        }
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        if (root == null) {
            throw new IllegalArgumentException("Input tree does not have enough depth");
        }

        if (!addOneRowHelper(root, val, depth - 1)) {
            throw new IllegalArgumentException("Input tree does not have enough depth");
        }
        return root;
    }

    private boolean addOneRowHelper(TreeNode node, int val, int depth) {
        if (node == null) {
            return false;
        }

        if (depth == 1) {
            TreeNode l = new TreeNode(val);
            l.left = node.left;
            node.left = l;

            TreeNode r = new TreeNode(val);
            r.right = node.right;
            node.right = r;
            return true;
        }

        boolean leftRes = addOneRowHelper(node.left, val, depth - 1);
        boolean rightRes = addOneRowHelper(node.right, val, depth - 1);
        return leftRes || rightRes;
    }
}

/**
 * BFS
 *
 * Use BFS to find nodes at D-1 and then add the new nodes.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Width of the tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("Input depth is invalid");
        }
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        if (root == null) {
            throw new IllegalArgumentException("Input tree does not have enough depth");
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty() && --depth > 1) {
            int size = queue.size();
            while (--size >= 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        if (queue.isEmpty()) {
            throw new IllegalArgumentException("Input tree does not have enough depth");
        }

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            TreeNode l = new TreeNode(val);
            l.left = cur.left;
            cur.left = l;

            TreeNode r = new TreeNode(val);
            r.right = cur.right;
            cur.right = r;
        }

        return root;
    }
}

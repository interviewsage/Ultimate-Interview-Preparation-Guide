// LeetCode Question URL: https://leetcode.com/problems/path-sum/
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
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}

/**
 * Iterative Solution (Using DFS postorder traversal)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                targetSum -= cur.val;
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            if (cur.left == null && cur.right == null && targetSum == 0) {
                return true;
            }
            targetSum += cur.val;
            pre = stack.pop();
            cur = null;
        }

        return false;
    }
}

/**
 * Iterative Solution (DFS)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, root.val));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curSum = cur.getValue();

            if (curNode.left == null && curNode.right == null) {
                if (curSum == targetSum) {
                    return true;
                } else {
                    continue;
                }
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, curSum + curNode.left.val));
            }
            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, curSum + curNode.right.val));
            }
        }

        return false;
    }
}

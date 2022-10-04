// LeetCode Question URL: https://leetcode.com/problems/closest-binary-search-tree-value/

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
 * Iterative Solution.
 *
 * Time Complexity: O(H). In case of skewed tree it will be O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            throw new NoSuchElementException("Input tree is null, thus no element found");
        }

        double minDiff = Math.abs(target - root.val);
        int closest = root.val;
        TreeNode cur = root;

        while (cur != null) {
            if (root.val == target) {
                return root.val;
            }

            double diff = Math.abs(target - cur.val);
            if (diff < minDiff) {
                minDiff = diff;
                closest = cur.val;
            }

            if (target < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return closest;
    }
}

/**
 * Recursive Solution.
 *
 * Time Complexity: O(H). In case of skewed tree it will be O(N)
 *
 * Space Complexity: O(H). In case of skewed tree it will be O(N)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            throw new NoSuchElementException("Input tree is null, thus no element found");
        }

        int[] closest = { root.val };
        closestValueHelper(root, target, closest);

        return closest[0];
    }

    private void closestValueHelper(TreeNode node, double target, int[] closest) {
        if (node == null) {
            return;
        }
        if (node.val == target) {
            closest[0] = node.val;
            return;
        }

        if (Math.abs(target - node.val) < Math.abs(target - closest[0])) {
            closest[0] = node.val;
        }
        if (target < node.val) {
            closestValueHelper(node.left, target, closest);
        } else {
            closestValueHelper(node.right, target, closest);
        }
    }
}

// LeetCode Question URL: https://leetcode.com/problems/range-sum-of-bst/

import java.util.ArrayDeque;
import java.util.Deque;

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
 * Pre-Order
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H) = O(N) in worst case in case of skewed Tree
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }

        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }

        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }

        return root.val + rangeSumBST(root.left, low, root.val - 1) + rangeSumBST(root.right, root.val + 1, high);
    }
}

/**
 * Iterative Solution
 *
 * Inorder
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H) = O(N) in worst case in case of skewed Tree
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int sum = 0;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                if (cur.val <= low) {
                    break;
                }
                cur = cur.left;
            }

            cur = stack.pop();
            if (cur.val >= low && cur.val <= high) {
                sum += cur.val;
            }
            if (cur.val >= high) {
                break;
            }

            cur = cur.right;
        }

        return sum;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/house-robber-iii/
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
 * Recursive DFS (PostOrder) Greedy Solution.
 *
 * Time Complexity: O(N) - All houses are processed once
 *
 * Space Complexity: O(H) - Height of the tree
 *
 * N = Number of nodes in the tree.
 */
class Solution {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] result = robDfsHelper(root);
        return Math.max(result[0], result[1]);
    }

    // 0 --> Total money robbed if the current node IS NOT robbed
    // 1 --> Total money robbed if the current node IS robbed
    private int[] robDfsHelper(TreeNode node) {
        int[] res = new int[2];
        if (node == null) {
            return res;
        }

        int[] left = robDfsHelper(node.left);
        int[] right = robDfsHelper(node.right);

        // NOT Robbed current node --> Add Maximum of both sides
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // Robbed current node --> NoRobLeft + NoRobRight + Current Money
        res[1] = node.val + left[0] + right[0];
        return res;
    }
}

/**
 * Iterative DFS (PostOrder) Greedy Solution.
 *
 * Time Complexity: O(N) - All houses are processed once
 *
 * Space Complexity: O(N + H) - Map + Stack
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        // 0 --> Total money robbed if the current node IS NOT robbed
        // 1 --> Total money robbed if the current node IS robbed
        Map<TreeNode, int[]> memo = new HashMap<>();
        memo.put(null, new int[2]);

        TreeNode cur = root;
        TreeNode pre = null;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            pre = stack.pop();
            cur = null;

            if (pre.left == null && pre.right == null) {
                memo.put(pre, new int[] { 0, pre.val });
                continue;
            }

            int[] left = memo.get(pre.left);
            int[] right = memo.get(pre.right);
            int[] res = new int[2];

            // NOT Robbed current node --> Add Maximum of both sides
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = pre.val + left[0] + right[0];
            // Robbed current node --> NoRobLeft + NoRobRight + Current Money
            memo.put(pre, res);
        }

        int[] result = memo.get(root);
        return Math.max(result[0], result[1]);
    }
}

// LeetCode Question URL: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * BFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Width of the tree) = O(N/2)
 *
 * N = Number of nodes
 */
class Solution1 {
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int maxSum = Integer.MIN_VALUE;
        int maxSumLevelId = 1;
        int levelId = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            levelId++;
            int sum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxSumLevelId = levelId;
            }
        }

        return maxSumLevelId;
    }
}

/**
 * DFS
 *
 * Time Complexity: O(N + H)
 *
 * Space Complexity: O(2*H)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<Integer> levelSum = new ArrayList<>();
        dfsHelper(root, levelSum, 0);

        int maxSum = levelSum.get(0);
        int maxSumLevelId = 0;

        for (int i = 1; i < levelSum.size(); i++) {
            if (levelSum.get(i) > maxSum) {
                maxSum = levelSum.get(i);
                maxSumLevelId = i;
            }
        }

        return maxSumLevelId + 1;
    }

    private void dfsHelper(TreeNode node, List<Integer> levelSum, int level) {
        if (node == null) {
            return;
        }

        if (levelSum.size() == level) {
            levelSum.add(node.val);
        } else {
            levelSum.set(level, levelSum.get(level) + node.val);
        }

        dfsHelper(node.left, levelSum, level + 1);
        dfsHelper(node.right, levelSum, level + 1);
    }
}

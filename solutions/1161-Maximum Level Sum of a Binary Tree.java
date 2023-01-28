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
 * BFS - Iterative
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
        int maxSum = root.val;
        int resultLevel = 1;
        int curLevel = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            curLevel++;
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
                resultLevel = curLevel;
            }
        }

        return resultLevel;
    }
}

/**
 * DFS - Recursive
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

        List<Integer> depthSum = new ArrayList<>();
        maxLevelSumHelper(root, 0, depthSum);

        int resultLevel = 0;
        int maxSum = depthSum.get(0);

        for (int i = 1; i < depthSum.size(); i++) {
            if (depthSum.get(i) > maxSum) {
                maxSum = depthSum.get(i);
                resultLevel = i;
            }
        }

        return resultLevel + 1;
    }

    private void maxLevelSumHelper(TreeNode node, int depth, List<Integer> depthSum) {
        if (node == null) {
            return;
        }

        if (depth == depthSum.size()) {
            depthSum.add(node.val);
        } else {
            depthSum.set(depth, depthSum.get(depth) + node.val);
        }

        maxLevelSumHelper(node.left, depth + 1, depthSum);
        maxLevelSumHelper(node.right, depth + 1, depthSum);
    }
}

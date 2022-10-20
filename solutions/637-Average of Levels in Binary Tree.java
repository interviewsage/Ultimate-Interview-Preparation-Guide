// LeetCode Question URL: https://leetcode.com/problems/average-of-levels-in-binary-tree/
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
 * BFS Iterative
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W)= O(N/2) = O(N) in worst case.
 *
 * N = Number of nodes in the tree. W = Width of the tree. It can be maximum N/2
 * at last level.
 */
class Solution1 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double sum = 0;

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

            result.add(sum / levelSize);
        }

        return result;
    }
}

class Solution2 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long sum = 0;

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

            result.add((sum * 100000 / levelSize) / 100000.0d);
        }

        return result;
    }
}

/**
 * DFS Recursive
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2 * H) = O(N) in worst case.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        averageOfLevelsDfsHelper(root, 0, result, new ArrayList<>());
        return result;
    }

    private void averageOfLevelsDfsHelper(TreeNode node, int level, List<Double> result, List<Integer> levelSize) {
        if (node == null) {
            return;
        }

        if (level == levelSize.size()) {
            levelSize.add(1);
            result.add((double) node.val);
        } else {
            int newLevelSize = levelSize.get(level) + 1;
            levelSize.set(level, newLevelSize);
            result.set(level, (result.get(level) * (newLevelSize - 1) + node.val) / newLevelSize);
        }

        averageOfLevelsDfsHelper(node.left, level + 1, result, levelSize);
        averageOfLevelsDfsHelper(node.right, level + 1, result, levelSize);
    }
}

class Solution4 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Long> levelSum = new ArrayList<>();
        List<Integer> levelCount = new ArrayList<>();

        dfsHelper(root, 0, levelCount, levelSum);

        for (int i = 0; i < levelSum.size(); i++) {
            result.add((double) levelSum.get(i) / levelCount.get(i));
        }

        return result;
    }

    private void dfsHelper(TreeNode node, int level, List<Integer> levelCount, List<Long> levelSum) {
        if (node == null) {
            return;
        }

        if (level < levelSum.size()) {
            levelSum.set(level, levelSum.get(level) + node.val);
            levelCount.set(level, levelCount.get(level) + 1);
        } else {
            levelSum.add((long) node.val);
            levelCount.add(1);
        }

        dfsHelper(node.left, level + 1, levelCount, levelSum);
        dfsHelper(node.right, level + 1, levelCount, levelSum);
    }
}

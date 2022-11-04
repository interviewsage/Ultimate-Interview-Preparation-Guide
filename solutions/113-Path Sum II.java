// LeetCode Question URL: https://leetcode.com/problems/path-sum-ii/
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
 * Recursive Backtracking Solution
 *
 * Time Complexity:
 * Worst Case in Perfect Binary Tree (Balanced Tree): O(N + N/2 * log N).
 * Best Case in skewed Tree: O(2*N).
 *
 * Space Complexity: O(2 * H) for recursion stack and tempList. For result space
 * = Height of Tree * number of leaf nodes
 *
 * H = N in case of skewed tree. H = log N in case of balanced tree.
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        pathSumHelper(root, targetSum, result, new ArrayList<>());
        return result;
    }

    private void pathSumHelper(TreeNode node, int targetSum, List<List<Integer>> result, List<Integer> curList) {
        if (node == null) {
            return;
        }

        curList.add(node.val);
        if (node.left == null && node.right == null) {
            if (targetSum == node.val) {
                result.add(new ArrayList<>(curList));
            }
        } else {
            targetSum -= node.val;
            pathSumHelper(node.left, targetSum, result, curList);
            pathSumHelper(node.right, targetSum, result, curList);
        }
        curList.remove(curList.size() - 1);
    }
}

/**
 * Iterative Solution (Using DFS postorder traversal)
 *
 * Time Complexity: O(N) All nodes will be visited once
 *
 * Space Complexity: O(H) for recursion stack and tempList. For result space =
 * Height of Tree * number of leaf nodes
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        List<Integer> curList = new ArrayList<>();

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                targetSum -= cur.val;
                curList.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            if (cur.left == null && cur.right == null && targetSum == 0) {
                result.add(new ArrayList<>(curList));
            }
            targetSum += cur.val;
            curList.remove(curList.size() - 1);
            pre = stack.pop();
            cur = null;
        }

        return result;
    }
}

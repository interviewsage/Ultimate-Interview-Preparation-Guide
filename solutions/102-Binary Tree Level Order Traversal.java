// LeetCode Question URL: https://leetcode.com/problems/binary-tree-level-order-traversal/
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
 * Recursive Approach (DFS).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes in the tree.
 * H = Height of the tree.
 */
class Solution1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        helper(result, root, 0);

        return result;
    }

    private void helper(List<List<Integer>> result, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        helper(result, node.left, level + 1);
        helper(result, node.right, level + 1);
    }
}

/**
 * Iterative DFS Approach.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes in the tree.
 * H = Height of the tree.
 */
class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curLevel = cur.getValue();

            if (curLevel == result.size()) {
                result.add(new ArrayList<>());
            }
            result.get(curLevel).add(curNode.val);

            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, curLevel + 1));
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, curLevel + 1));
            }
        }
        return result;
    }
}

/**
 * Iterative Approach (BFS).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W)
 *
 * N = Number of nodes in the tree.
 * W = Width of the tree.
 */
class Solution3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                tempList.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            result.add(tempList);
        }

        return result;
    }
}

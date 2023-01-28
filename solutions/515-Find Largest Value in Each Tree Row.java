// LeetCode Question URL: https://leetcode.com/problems/find-largest-value-in-each-tree-row
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
}

/**
 * Recursive DFS Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        largestValuesHelper(root, 0, result);
        return result;
    }

    private void largestValuesHelper(TreeNode node, int row, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (row == result.size()) {
            result.add(node.val);
        } else {
            result.set(row, Math.max(result.get(row), node.val));
        }

        largestValuesHelper(node.left, row + 1, result);
        largestValuesHelper(node.right, row + 1, result);
    }
}

/**
 * Iterative DFS Solution using a stack.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curRow = cur.getValue();

            if (curRow == result.size()) {
                result.add(curNode.val);
            } else {
                result.set(curRow, Math.max(result.get(curRow), curNode.val));
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, curRow + 1));
            }
            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, curRow + 1));
            }
        }

        return result;
    }
}

/**
 * Iterative BFS Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W) = O(N/2) = O(N)
 *
 * N = Number of nodes in the tree. W = Width of the binary tree.
 */
class Solution3 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int level = queue.size();
            int max = queue.peek().val;

            for (int i = 0; i < level; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            result.add(max);
        }

        return result;
    }
}

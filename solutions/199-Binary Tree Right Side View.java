// LeetCode Question URL: https://leetcode.com/problems/binary-tree-right-side-view/

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
 * Depth First Search
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        dfsHelper(root, result, 0);

        return result;
    }

    private void dfsHelper(TreeNode node, List<Integer> result, int depth) {
        if (node == null) {
            return;
        }

        if (result.size() == depth) {
            result.add(node.val);
        }

        dfsHelper(node.right, result, depth + 1);
        dfsHelper(node.left, result, depth + 1);
    }
}

/**
 * Breadth First Search
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W) -> Excluding the result array. O(W+H) -> Including the
 * result array.
 *
 * W = (N+1)/2 --> For full binary tree.
 *
 * N = Number of nodes. H = Height of the tree. W = Width of the tree.
 */
class Solution2 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            result.add(queue.peek().val);

            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }

        return result;
    }
}

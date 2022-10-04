// LeetCode Question URL: https://leetcode.com/problems/binary-tree-vertical-order-traversal/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 * BFS (Level by Level). Since the requirement is top to bottom, DFS is not the
 * right solution
 *
 * Time Complexity: O(W + N). All nodes are visited once.
 *
 * Space Complexity: O(W + N)
 *
 * N = Number of nodes in the tree.
 * W = Width of the tree. It can be N in worst case.
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        HashMap<Integer, ArrayList<Integer>> colMap = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxCol = 0;
        int minCol = 0;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> cur = queue.poll();
            TreeNode curNode = cur.getKey();
            int curCol = cur.getValue();

            colMap.putIfAbsent(curCol, new ArrayList<>());
            colMap.get(curCol).add(curNode.val);

            if (curNode.left != null) {
                minCol = Math.min(minCol, curCol - 1);
                queue.offer(new Pair<>(curNode.left, curCol - 1));
            }
            if (curNode.right != null) {
                maxCol = Math.max(maxCol, curCol + 1);
                queue.offer(new Pair<>(curNode.right, curCol + 1));
            }
        }

        for (int i = minCol; i <= maxCol; i++) {
            result.add(colMap.get(i));
        }

        return result;
    }
}

/**
 * Refer to "Approach 3: Depth-First Search (DFS)" in Solutions tab for DFS
 * solution. Only code this if asked the interviewer.
 */
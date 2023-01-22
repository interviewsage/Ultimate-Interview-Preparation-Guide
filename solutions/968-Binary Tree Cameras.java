// LeetCode Question URL: https://leetcode.com/problems/binary-tree-cameras
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
 * DFS Recursive
 *
 * Refer:
 * https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC++Python-Greedy-DFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Number of node in the tree. H = Height of tree
 */
class Solution1 {
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = { 0 };
        if (minCameraCoverHelper(root, count) == -1) {
            count[0]++;
        }
        return count[0];
    }

    // -1 --> Un-Monitored
    // 0 --> Monitored But Camera NOT here
    // 1 --> Camera Here
    private int minCameraCoverHelper(TreeNode node, int[] count) {
        if (node == null) {
            return 0;
        }

        int left = minCameraCoverHelper(node.left, count);
        int right = minCameraCoverHelper(node.right, count);

        if (left == -1 || right == -1) {
            count[0]++;
            return 1;
        }
        if (left == 1 || right == 1) {
            return 0;
        }
        return -1;
    }
}

/**
 * DFS Iterative
 *
 * Refer:
 * https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC++Python-Greedy-DFS
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H + N). N to save in HashMap
 *
 * N = Number of node in the tree. H = Height of tree
 */
class Solution {
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;

        Map<TreeNode, Integer> stateMap = new HashMap<>();
        // -1 --> Un-Monitored
        // 0 --> Monitored But Camera NOT here
        // 1 --> Camera Here
        stateMap.put(null, 0);

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

            int left = stateMap.get(cur.left);
            int right = stateMap.get(cur.right);
            if (left == -1 || right == -1) {
                count++;
                stateMap.put(cur, 1);
            } else if (left == 1 || right == 1) {
                stateMap.put(cur, 0);
            } else {
                stateMap.put(cur, -1);
            }

            stack.pop();
            pre = cur;
            cur = null;
        }

        return stateMap.get(root) == -1 ? count + 1 : count;
    }
}

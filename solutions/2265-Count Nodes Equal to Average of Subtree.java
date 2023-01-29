// LeetCode Question URL: https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
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
 * DFS PostOrder Recursive
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = new int[1];
        averageOfSubtreeHelper(root, count);

        return count[0];
    }

    private int[] averageOfSubtreeHelper(TreeNode node, int[] count) {
        if (node == null) {
            return new int[2];
        }

        int[] leftSubTree = averageOfSubtreeHelper(node.left, count);
        int[] rightSubTree = averageOfSubtreeHelper(node.right, count);

        int[] cur = new int[] { leftSubTree[0] + rightSubTree[0] + node.val, leftSubTree[1] + rightSubTree[1] + 1 };

        if (node.val == cur[0] / cur[1]) {
            count[0]++;
        }

        return cur;
    }
}

/**
 * DFS PostOrder Iterative
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * Space Complexity: O(H + N) = O(N)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, int[]> map = new HashMap<>();
        map.put(null, new int[2]);
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

            stack.pop();

            int[] leftSubTree = map.get(cur.left);
            int[] rightSubTree = map.get(cur.right);
            int[] temp = new int[] { leftSubTree[0] + rightSubTree[0] + cur.val, leftSubTree[1] + rightSubTree[1] + 1 };
            if (cur.val == temp[0] / temp[1]) {
                count++;
            }
            map.put(cur, temp);

            pre = cur;
            cur = null;
        }

        return count;
    }
}

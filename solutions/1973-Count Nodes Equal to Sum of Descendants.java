// LeetCode Question URL: https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/
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
    public int equalToDescendants(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = new int[1];
        equalToDescendantsHelper(root, count);

        return count[0];
    }

    private long equalToDescendantsHelper(TreeNode node, int[] count) {
        if (node == null) {
            return 0;
        }

        long sum = equalToDescendantsHelper(node.left, count) + equalToDescendantsHelper(node.right, count);
        if (sum == node.val) {
            count[0]++;
        }

        return sum + node.val;
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
    public int equalToDescendants(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Long> map = new HashMap<>();
        map.put(null, 0L);
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

            long sum = map.get(cur.left) + map.get(cur.right);
            if (sum == cur.val) {
                count++;
            }
            map.put(cur, sum + cur.val);

            pre = cur;
            cur = null;
        }

        return count;
    }
}

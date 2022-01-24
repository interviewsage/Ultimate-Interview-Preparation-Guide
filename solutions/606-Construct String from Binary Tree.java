// LeetCode Question URL: https://leetcode.com/problems/construct-string-from-binary-tree/
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

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * DFS - Recursive Solution
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(Height of the tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        dfsHelper(root, sb);
        return sb.toString();
    }

    private void dfsHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append(root.val);

        if (root.left == null && root.right == null) {
            return;
        }

        sb.append("(");
        dfsHelper(root.left, sb);
        sb.append(")");

        if (root.right != null) {
            sb.append("(");
            dfsHelper(root.right, sb);
            sb.append(")");
        }
    }
}

/**
 * Iterative Preorder Solution
 *
 * Refer:
 * https://leetcode.com/problems/construct-string-from-binary-tree/discuss/314882/Java-Iterative-without-'visited'-set
 *
 * Time Complexity: O(2N + 2*2N) = O(N)
 *
 * Space Complexity: O(2*Height + 2*N) = O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode dummy = new TreeNode();

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur == dummy) {
                sb.append(")");
                continue;
            }
            sb.append("(").append(cur.val);
            stack.push(dummy);

            if (cur.left == null && cur.right != null) {
                sb.append("()");
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }
}

// LeetCode Question URL: https://leetcode.com/problems/binary-tree-paths/
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
 * DFS - Recursive
 *
 * Time Complexity: O(Visiting All Nodes + (Number of leaves * Height) for
 * creating final string of each path) = O(N + (N+1)/2*logN) in Perfect Tree
 * case. OR O(N + 1*N) in Skewed Tree case.
 *
 * Space Complexity: O(2 * H). (Recursion stack + StringBuilder). In worst case
 * it will be O(N)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        binaryTreePathsHelper(result, root, new StringBuilder());
        return result;
    }

    private void binaryTreePathsHelper(List<String> result, TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        int preLen = sb.length();
        sb.append(node.val);

        if (node.left == null && node.right == null) {
            result.add(sb.toString());
        } else {
            sb.append("->");
            binaryTreePathsHelper(result, node.left, sb);
            binaryTreePathsHelper(result, node.right, sb);
        }

        sb.setLength(preLen);
    }
}

/**
 * DFS - Iterative
 *
 * Time Complexity: O(Visiting All Nodes + (Number of leaves * Height) for
 * creating final string of each path) = O(N + (N+1)/2*logN) in Perfect Tree
 * case. OR O(N + 1*N) in Skewed Tree case.
 *
 * Space Complexity: O(2 * H). (Recursion stack + StringBuilder). In worst case
 * it will be O(N)
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> sbLenStack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                if (sb.length() != 0) {
                    sb.append("->");
                }
                stack.push(cur);
                sb.append(cur.val);
                sbLenStack.push(sb.length());
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && pre != cur.right) {
                sb.setLength(sbLenStack.peek());
                cur = cur.right;
                continue;
            }

            sb.setLength(sbLenStack.pop());
            if (cur.left == null && cur.right == null) {
                result.add(sb.toString());
            }
            pre = stack.pop();
            cur = null;
        }

        return result;
    }
}

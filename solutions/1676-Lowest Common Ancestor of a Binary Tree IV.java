// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
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
 * Recursive Solution - This solution will work only if all nodes are present in
 * the tree.
 *
 * Time Complexity: O(N) - All nodes will be visited once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null || nodes == null || nodes.length == 0) {
            return null;
        }
        if (nodes.length == 1) {
            return nodes[0];
        }

        Set<TreeNode> nodeSet = new HashSet<>();
        for (TreeNode n : nodes) {
            nodeSet.add(n);
        }

        return lowestCommonAncestor(root, nodeSet);
    }

    private TreeNode lowestCommonAncestor(TreeNode root, Set<TreeNode> nodeSet) {
        if (root == null || nodeSet.contains(root)) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, nodeSet);
        TreeNode right = lowestCommonAncestor(root.right, nodeSet);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

/**
 * Recursive Solution - This solution can handle the input where all nodes in
 * the input array are not present in the tree. It will return null is some
 * nodes are not found in the tree.
 *
 * Time Complexity: O(N) - All nodes will be visited once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null || nodes == null || nodes.length == 0) {
            return null;
        }

        Set<TreeNode> nodeSet = new HashSet<>();
        for (TreeNode n : nodes) {
            nodeSet.add(n);
        }
        int[] count = { 0 };
        TreeNode lca = lowestCommonAncestor(root, nodeSet, count);
        return count[0] == nodeSet.size() ? lca : null;
    }

    private TreeNode lowestCommonAncestor(TreeNode node, Set<TreeNode> nodeSet, int[] count) {
        if (node == null || count[0] == nodeSet.size()) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(node.left, nodeSet, count);
        if (nodeSet.contains(node)) {
            count[0]++;
            left = node;
        }
        if (count[0] == nodeSet.size()) {
            return left;
        }

        TreeNode right = lowestCommonAncestor(node.right, nodeSet, count);

        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }
}

/**
 * Iterative Solution - PostOrder - This DOES NOT WORK.
 */
class Solution3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null || nodes == null || nodes.length == 0) {
            return null;
        }

        Set<TreeNode> nodeSet = new HashSet<>();
        for (TreeNode n : nodes) {
            nodeSet.add(n);
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode lca = null;
        int count = 0;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                if (nodeSet.contains(cur)) {
                    count++;
                    lca = cur;
                }
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            stack.pop();
            if (count > 0 && lca == cur) {
                if (stack.isEmpty()) {
                    return null;
                }
                lca = stack.peek();
            }
            if (count == nodeSet.size()) {
                return lca;
            }
            pre = cur;
            cur = null;
        }

        return null;
    }
}

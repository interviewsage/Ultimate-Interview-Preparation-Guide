// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
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
 * Recursive - This solution uses answer from LC 236 and builds on that by
 * calling same function.
 *
 * Refer:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/944963/Beat-96-Recursion-without-count-easy-understanding
 *
 * Time Complexity: O(N) - All nodes are visited once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        TreeNode lca = findLCA(root, p, q);
        if (lca == p) {
            return findLCA(p, q, q) != null ? lca : null;
        }
        if (lca == q) {
            return findLCA(q, p, p) != null ? lca : null;
        }

        return lca;
    }

    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

/**
 * Recursive - Inorder. This solution uses a 1D boolean array to save if we
 * found both nodes.
 *
 * Refer:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
 *
 * Time Complexity: O(N) - All nodes are visited once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        boolean[] foundNodes = new boolean[2];
        TreeNode lca = dfsHelper(root, p, q, foundNodes);
        return foundNodes[0] & foundNodes[1] ? lca : null;
    }

    private TreeNode dfsHelper(TreeNode node, TreeNode p, TreeNode q, boolean[] foundNodes) {
        if (node == null || (foundNodes[0] & foundNodes[1])) {
            return null;
        }

        TreeNode left = dfsHelper(node.left, p, q, foundNodes);

        if (node == p) {
            foundNodes[0] = true;
            left = node;
        } else if (node == q) {
            foundNodes[1] = true;
            left = node;
        }
        if (foundNodes[0] & foundNodes[1]) {
            return left;
        }

        TreeNode right = dfsHelper(node.right, p, q, foundNodes);

        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }
}

/**
 * Iterative Solution - PostOrder
 *
 * Time Complexity: O(N) - Every node is inserted into stack once and then
 * popped once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode lca = null;
        boolean oneNodeFound = false;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                if (cur == p || cur == q) {
                    if (oneNodeFound) {
                        return lca;
                    }
                    oneNodeFound = true;
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
            if (oneNodeFound && lca == cur) {
                if (stack.isEmpty()) {
                    return null;
                }
                lca = stack.peek();
            }
            pre = cur;
            cur = null;
        }

        return null;
    }
}

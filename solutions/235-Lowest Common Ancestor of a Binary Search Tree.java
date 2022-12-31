// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// LeetCode Discuss URL:

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
 * Recursive Solution
 *
 * Time Complexity: O(Height of the Tree) = O(N) in worst case.
 *
 * Space Complexity: O(Height of the Tree) = O(N) in worst case.
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(Height of the Tree) = O(N) in worst case.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode cur = root;

        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else {
                break;
            }
        }

        return cur;
    }
}

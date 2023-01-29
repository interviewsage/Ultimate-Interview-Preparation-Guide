// LeetCode Question URL: https://leetcode.com/problems/inorder-successor-in-bst/
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
 * Iterative BST Search
 *
 * Time Complexity: O(H) in Best case. O(N) in worst case.
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        // This assumes if p exists in the tree
        // Remove this block in interview if P's existence is not guaranteed in the
        // input tree.
        if (p.right != null) {
            return getLeftMostNodeInTree(p.right);
        }

        TreeNode cur = root;
        TreeNode successor = null;
        boolean isPFound = false;

        while (cur != null) {
            if (p.val < cur.val) {
                successor = cur;
                cur = cur.left;
            } else {
                if (cur == p) {
                    isPFound = true;
                }
                cur = cur.right;
            }
        }

        return isPFound ? successor : null;
    }

    private TreeNode getLeftMostNodeInTree(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

/**
 * Recursive BST Search. This only works if P's existence is guaranteed in the
 * input tree.
 *
 * Time Complexity: O(H) in Best case. O(N) in worst case.
 *
 * Space Complexity: O(H) in Best case. O(N) in worst case.
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        // This solution assumes if p exists in the tree
        if (p.right != null) {
            return getLeftMostNodeInTree(p.right);
        }

        if (p.val < root.val) {
            TreeNode successor = inorderSuccessor(root.left, p);
            return successor == null ? root : successor;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }

    private TreeNode getLeftMostNodeInTree(TreeNode node) {
        return node.left == null ? node : getLeftMostNodeInTree(node.left);
    }
}

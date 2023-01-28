// LeetCode Question URL: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// LeetCode Discuss URL:

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
}

/**
 * Iterative Solution
 *
 * Refer:
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/37010/Share-my-simple-NON-recursive-solution-O(1)-space-complexity!
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        while (cur != null) {
            // If there is no left node, the current node is at correct place in the tree.
            if (cur.left != null) {
                // We are trying to find left subTree's rightmost child. Since we are doing
                // pre-order, right subtree of cur will be after the right most child of the
                // left sub tree.
                TreeNode leftSubTree = cur.left;
                while (leftSubTree.right != null) {
                    leftSubTree = leftSubTree.right;
                }
                leftSubTree.right = cur.right;

                // Since the right subtree has been place at the right place, Left subtree can
                // move to the right now.
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}

/**
 * Recursive Solution. Here we are solving in reverse.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of the tree). In worst case O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution2 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenHelper(root, null);
    }

    // solvedHead node represents the head of the solved list that can be appended
    // to the right of the current node
    private TreeNode flattenHelper(TreeNode node, TreeNode solvedHead) {
        if (node == null) {
            return solvedHead;
        }

        solvedHead = flattenHelper(node.right, solvedHead);
        solvedHead = flattenHelper(node.left, solvedHead);
        node.right = solvedHead;
        node.left = null;

        return node;
    }
}

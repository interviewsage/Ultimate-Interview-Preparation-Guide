// LeetCode Question URL: https://leetcode.com/problems/maximum-binary-tree-ii/
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
 * Iterative Solution
 *
 * Refer for explanation:
 * https://leetcode.com/problems/maximum-binary-tree-ii/discuss/242936/JavaC++Python-Recursion-and-Iteration
 *
 * Always go right since new element will be inserted at the end of the list.
 *
 * Time Complexity: O(Length of right most path) = O(N) in worst case.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null || root.val <= val) {
            node.left = root;
            return node;
        }

        TreeNode cur = root;

        while (cur.right != null && cur.right.val > val) {
            cur = cur.right;
        }

        node.left = cur.right;
        cur.right = node;
        return root;
    }
}

/**
 * Recursive Solution
 *
 * Refer for explanation:
 * https://leetcode.com/problems/maximum-binary-tree-ii/discuss/242936/JavaC++Python-Recursion-and-Iteration
 *
 * Always go right since new element will be inserted at the end of the list.
 *
 * Time Complexity: O(Length of right most path) = O(N) in worst case.
 *
 * Space Complexity: O(Length of right most path) = O(N) in worst case.
 */
class Solution2 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val <= val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}

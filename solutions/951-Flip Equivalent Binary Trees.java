// LeetCode Question URL: https://leetcode.com/problems/flip-equivalent-binary-trees/
// LeetCode Discuss URL:

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * <pre>
 * Refer
 * 1. https://leetcode.com/problems/flip-equivalent-binary-trees/discuss/200514/JavaPython-3-DFS-3-liners-and-BFS-with-explanation-time-and-space:-O(n).
 * 2. https://leetcode.com/problems/flip-equivalent-binary-trees/solution/
 *
 * For iterative solution see this post: https://leetcode.com/problems/flip-equivalent-binary-trees/discuss/200514/JavaPython-3-DFS-3-liners-and-BFS-with-explanation-time-and-space:-O(n).
 *
 * For Time & Space Complexity refer to above posts.
 * </pre>
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
                || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}

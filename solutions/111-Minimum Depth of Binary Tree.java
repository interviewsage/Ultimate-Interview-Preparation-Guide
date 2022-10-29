// LeetCode Question URL: https://leetcode.com/problems/minimum-depth-of-binary-tree/
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
 * Recursive solution. DFS.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        if (leftMinDepth == 0 || rightMinDepth == 0) {
            return leftMinDepth + rightMinDepth + 1;
        }
        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
}

class Solution2 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

/**
 * Iterative solution. BFS using a queue (Level order traversal)
 *
 * This approach is better for cases where the tree is highly unbalanced. One
 * side of tree has lot more nodes as compared to other side. This will help in
 * early exit without looking at whole tree.
 *
 * Time Complexity: O(N).. In case of balanced tree.. we will skip last level.
 * Thus complexity will become O(N/2)
 *
 * Space Complexity: O(W) = O(N/2) in worst case.
 *
 * N = Total number of nodes in the tree. W = Width of the tree.
 */
class Solution3 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            depth++;
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        return depth;
    }
}

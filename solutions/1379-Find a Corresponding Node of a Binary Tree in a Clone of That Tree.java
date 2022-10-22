// LeetCode Question URL: https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
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
 * DFS Recursive
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H)
 */
class Solution1 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        return left != null ? left : getTargetCopy(original.right, cloned.right, target);
    }
}

/**
 * DFS Iterative
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2 * H) = O(H)
 */
class Solution2 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }

        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { original, cloned });

        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            if (cur[0] == target) {
                return cur[1];
            }

            if (cur[0].right != null) {
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            }
            if (cur[0].left != null) {
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            }
        }

        return null;
    }
}

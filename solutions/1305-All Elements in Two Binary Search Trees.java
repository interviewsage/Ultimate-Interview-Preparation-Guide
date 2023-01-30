// LeetCode Question URL: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Iterative DFS - Inorder.
 *
 * Here we move small node by small node in either tree.
 *
 * Time Complexity: O(N1 + N2)
 *
 * Space Complexity: O(H1 + H2)
 *
 * N1 = Number of nodes in Tree 1
 * H1 = Height of Tree 1
 * N2 = Number of nodes in Tree 2
 * H2 = Height of Tree 2
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        if (root1 == null && root2 == null) {
            return result;
        }

        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        TreeNode cur1 = root1;
        TreeNode cur2 = root2;

        while (!stack1.isEmpty() || !stack2.isEmpty() || cur1 != null || cur2 != null) {
            while (cur1 != null) {
                stack1.push(cur1);
                cur1 = cur1.left;
            }
            while (cur2 != null) {
                stack2.push(cur2);
                cur2 = cur2.left;
            }

            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val <= stack2.peek().val)) {
                cur1 = stack1.pop();
                result.add(cur1.val);
                cur1 = cur1.right;
            } else {
                cur2 = stack2.pop();
                result.add(cur2.val);
                cur2 = cur2.right;
            }
        }

        return result;
    }
}

/**
 * Refer to 2nd solution for Generic K Trees solution
 *
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/discuss/464073/C++-One-Pass-Traversal
 */

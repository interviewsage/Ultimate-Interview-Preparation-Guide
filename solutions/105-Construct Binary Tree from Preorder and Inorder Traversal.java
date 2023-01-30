// LeetCode Question URL: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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
 * Recursive DFS Solution
 *
 * Time Complexity: O(N + N) = O(N)
 *
 * Space Complexity: O(N + H)
 *
 * N = Length of input array. H = height of the tree
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = inorder.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return new TreeNode(inorder[0]);
        }

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, inorder, 0, len - 1, inorderMap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int pStart, int[] inorder, int iStart, int iEnd,
            Map<Integer, Integer> inorderMap) {
        if (iStart > iEnd) {
            return null;
        }
        if (iStart == iEnd) {
            return new TreeNode(inorder[iStart]);
        }

        int nodeVal = preorder[pStart];
        int inorderIdx = inorderMap.get(nodeVal);
        int leftNodes = inorderIdx - iStart;

        TreeNode node = new TreeNode(nodeVal);
        node.left = buildTreeHelper(preorder, pStart + 1, inorder, iStart, inorderIdx - 1, inorderMap);
        node.right = buildTreeHelper(preorder, pStart + leftNodes + 1, inorder, inorderIdx + 1, iEnd, inorderMap);
        return node;
    }
}

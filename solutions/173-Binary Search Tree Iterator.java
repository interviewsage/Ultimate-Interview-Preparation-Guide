// LeetCode Question URL: https://leetcode.com/problems/binary-search-tree-iterator/

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

/*
 * Time Complexity:
 *
 * hasNext() -> O(1)
 *
 * Each node is pushed once and popped once. Thus they are accessed maximum
 * twice. We call next() N times.. thus average complexity is O(1)
 *
 * Space Complexity -> O(height of the tree)
 */

class BSTIterator implements Iterator<Integer> {

    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        exploreLeftTree(root);
    }

    private void exploreLeftTree(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element not found");
        }

        TreeNode node = stack.pop();
        exploreLeftTree(node.right);
        return node.val;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

// Your BSTIterator object will be instantiated and called as such:
// BSTIterator obj = new BSTIterator(root);
// int param_1 = obj.next();
// boolean param_2 = obj.hasNext();

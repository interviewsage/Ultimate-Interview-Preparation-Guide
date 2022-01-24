// LeetCode Question URL: https://leetcode.com/problems/maximum-width-of-binary-tree/
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
 * DFS
 *
 * We know that a binary tree can be represented by an array (assume the root
 * begins from the position with index 1 in the array). If the index of a node
 * is i, the indices of its two children are 2*i and 2*i + 1. The idea is to use
 * two arrays (start[] and end[]) to record the the indices of the leftmost node
 * and rightmost node in each level, respectively. For each level of the tree,
 * the width is end[level] - start[level] + 1. Then, we just need to find the
 * maximum width.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Height of the tree) = O(N) in worst case (Recursion
 * Stack)
 *
 * N = NUmber of nodes in the tree.
 */
class Solution1 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] maxWidth = { 1 };
        widthOfBinaryTreeHelper(root, 0, 0, new ArrayList<>(), maxWidth);
        return maxWidth[0];
    }

    private void widthOfBinaryTreeHelper(TreeNode node, int depth, int idx, List<Integer> startOfEachLevel,
            int[] maxWidth) {
        if (node == null) {
            return;
        }

        if (startOfEachLevel.size() == depth) {
            startOfEachLevel.add(idx);
        } else {
            maxWidth[0] = Math.max(maxWidth[0], idx - startOfEachLevel.get(depth) + 1);
        }

        widthOfBinaryTreeHelper(node.left, depth + 1, 2 * idx, startOfEachLevel, maxWidth);
        widthOfBinaryTreeHelper(node.right, depth + 1, 2 * idx + 1, startOfEachLevel, maxWidth);
    }
}

/**
 * BFS
 *
 * Refer above solution for explanation.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Width of the tree) = O(N/2) in worst case (Queue size)
 *
 * N = NUmber of nodes in the tree.
 */
class Solution2 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int start = -1;
            int end = -1;
            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> cur = queue.poll();
                TreeNode curNode = cur.getKey();
                end = cur.getValue();
                if (i == 0) {
                    start = end;
                }
                if (curNode.left != null) {
                    queue.offer(new Pair<>(curNode.left, 2 * end));
                }
                if (curNode.right != null) {
                    queue.offer(new Pair<>(curNode.right, 2 * end + 1));
                }
            }

            maxWidth = Math.max(maxWidth, end - start + 1);
        }

        return maxWidth;
    }
}

/**
 * BFS. THis one handles some cases of Integer Overflow.
 *
 * Refer above solution for explanation.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Width of the tree) = O(N/2) in worst case (Queue size)
 *
 * N = NUmber of nodes in the tree.
 */
class Solution3 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int maxWidth = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int start = -1;
            int end = -1;
            int diff = queue.peek().getValue();

            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> cur = queue.poll();
                TreeNode curNode = cur.getKey();
                end = cur.getValue() - diff;
                if (i == 0) {
                    start = end;
                }
                if (curNode.left != null) {
                    queue.offer(new Pair<>(curNode.left, 2 * end));
                }
                if (curNode.right != null) {
                    queue.offer(new Pair<>(curNode.right, 2 * end + 1));
                }
            }

            maxWidth = Math.max(maxWidth, end - start + 1);
        }

        return maxWidth;
    }
}

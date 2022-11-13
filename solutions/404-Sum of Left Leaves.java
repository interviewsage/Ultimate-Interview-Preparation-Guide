// LeetCode Question URL: https://leetcode.com/problems/sum-of-left-leaves/
// LeetCode Discuss URL: https://leetcode.com/problems/sum-of-left-leaves/discuss/1557944/Java-TC:-O(N)-or-SC:-O(Height)-or-2-Simple-DFS-(Recursive-and-Iterative)-+-BFS-Solutions

import java.util.*;

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

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * DFS Recursive
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = 0;
        if (root.left != null) {
            // Checking if left Node is a leaf node
            if (root.left.left == null && root.left.right == null) {
                leftSum = root.left.val;
            } else {
                leftSum = sumOfLeftLeaves(root.left);
            }
        }

        return leftSum + sumOfLeftLeaves(root.right);
    }
}

/**
 * DFS Iterative
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. W = Width of the tree.
 */
class Solution2 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int result = 0;

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                if (cur.left.left == null && cur.left.right == null) {
                    result += cur.left.val;
                } else {
                    stack.push(cur.left);
                }
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        return result;
    }
}

/**
 * DFS Recursive
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution3 {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesHelper(root, false);
    }

    private int sumOfLeftLeavesHelper(TreeNode node, boolean isLeftSideOfParent) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return isLeftSideOfParent ? node.val : 0;
        }

        return sumOfLeftLeavesHelper(node.left, true) + sumOfLeftLeavesHelper(node.right, false);
    }
}

/**
 * DFS Iterative (Pre-Order Traversal)
 *
 * Time Complexity: O(N). All nodes will be visited.
 *
 * Space Complexity: O(H) = O(N) in worst case.
 *
 * N = Number of nodes. W = Width of the tree.
 */
class Solution4 {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair<TreeNode, Boolean>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, false));

        int result = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> cur = stack.pop();
            TreeNode node = cur.getKey();
            boolean isLeft = cur.getValue();

            if (isLeft && node.left == null && node.right == null) {
                result += node.val;
                continue;
            }

            if (node.right != null) {
                stack.push(new Pair<>(node.right, false));
            }
            if (node.left != null) {
                stack.push(new Pair<>(node.left, true));
            }
        }

        return result;
    }
}

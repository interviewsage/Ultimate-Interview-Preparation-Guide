// LeetCode Question URL: https://leetcode.com/problems/sum-root-to-leaf-numbers/
// LeetCode Discuss URL: https://leetcode.com/problems/sum-root-to-leaf-numbers/discuss/1555847/Java-TC:-O(N)-or-SC:-O(TreeHeight)-or-3-Simple-DFS-solutions-(Recursive-and-Iterative)

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
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(H). Stack space. In case of balanced tree (best case) it
 * will be O(log N) and in case of Skewed Tree (worst case) it will be O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return sumNumbersHelper(root, 0);
    }

    private int sumNumbersHelper(TreeNode node, int curVal) {
        if (node == null) {
            return 0;
        }

        curVal = curVal * 10 + node.val;
        if (node.left == null && node.right == null) {
            return curVal;
        }

        return sumNumbersHelper(node.left, curVal) + sumNumbersHelper(node.right, curVal);
    }
}

/**
 * Iterative Post-Order Traversal
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(H). Stack space. In case of balanced tree (best case) it
 * will be O(log N) and in case of Skewed Tree (worst case) it will be O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int curVal = 0;
        int sum = 0;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                curVal = curVal * 10 + cur.val;
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            if (cur.right == null && cur.left == null) {
                sum += curVal;
            }

            pre = stack.pop();
            curVal /= 10;
            cur = null;
        }

        return sum;
    }
}

/**
 * Iterative DFS
 *
 * Time Complexity: O(N). Each node is visited once.
 *
 * Space Complexity: O(H). Stack space. In case of balanced tree (best case) it
 * will be O(log N) and in case of Skewed Tree (worst case) it will be O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, root.val));

        int sum = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode node = cur.getKey();
            int num = cur.getValue();

            if (node.left == null && node.right == null) {
                sum += num;
                continue;
            }

            if (node.left != null) {
                stack.push(new Pair<>(node.left, num * 10 + node.left.val));
            }
            if (node.right != null) {
                stack.push(new Pair<>(node.right, num * 10 + node.right.val));
            }
        }

        return sum;
    }
}

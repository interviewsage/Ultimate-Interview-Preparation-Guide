// LeetCode Question URL: https://leetcode.com/problems/sum-root-to-leaf-numbers/
// LeetCode Discuss URL: https://leetcode.com/problems/sum-root-to-leaf-numbers/discuss/1555847/Java-TC:-O(N)-or-SC:-O(TreeHeight)-or-3-Simple-DFS-solutions-(Recursive-and-Iterative)

/**
 * Refer Solutions tab for space optimized O(1) Pre-Order Traversal using Morris Preorder Traversal.
 * "Approach 3: Morris Preorder Traversal."
 *
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/solution/
 */

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

        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode node, int curNum) {
        if (node == null) {
            return 0;
        }

        curNum = curNum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return curNum;
        }
        return sumNumbers(node.left, curNum) + sumNumbers(node.right, curNum);
    }
}

/**
 * Iterative Post-Order Traversal
 *
 * <pre>
 * Why post order?
 * We can remove the node from stack only after processing left & right child nodes.
 * </pre>
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

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int curNum = 0;
        int sum = 0;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                curNum = curNum * 10 + cur.val;
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            if (cur.left == null && cur.right == null) {
                sum += curNum;
            }

            pre = stack.pop();
            curNum /= 10;
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
 * Space Complexity: O2 * H). Stack space. In case of balanced tree (best case)
 * it will be O(log N) and in case of Skewed Tree (worst case) it will be O(N)
 *
 * N = Total number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, root.val));
        int sum = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> cur = stack.pop();
            TreeNode curNode = cur.getKey();
            int curNum = cur.getValue();

            if (curNode.left == null && curNode.right == null) {
                sum += curNum;
                continue;
            }

            if (curNode.left != null) {
                stack.push(new Pair<>(curNode.left, curNum * 10 + curNode.left.val));
            }
            if (curNode.right != null) {
                stack.push(new Pair<>(curNode.right, curNum * 10 + curNode.right.val));
            }
        }

        return sum;
    }
}

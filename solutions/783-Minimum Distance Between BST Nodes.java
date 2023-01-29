// LeetCode Question URL: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
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
 * DFS In-Order Iterative
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution1 {
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Less than 2 nodes in the tree");
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        int minDiff = Integer.MAX_VALUE;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();

            if (pre != null) {
                minDiff = Math.min(minDiff, cur.val - pre.val);
            }
            pre = cur;

            cur = cur.right;
        }

        return minDiff;
    }
}

/**
 * DFS In-Order Recursive
 *
 * Time Complexity: O(N). All nodes will be visited once.
 *
 * <pre>
 * Space Complexity: O(H) = O(N) in worst case (Skewed tree)
 *                          or O(log N) in best case (Balanced tree)
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution2 {
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Less than 2 nodes in the tree");
        }

        TreeNode[] pre = new TreeNode[] { null };
        int[] minDiff = new int[] { Integer.MAX_VALUE };

        minDiffInBSTHelper(root, pre, minDiff);

        return minDiff[0];
    }

    private void minDiffInBSTHelper(TreeNode node, TreeNode[] pre, int[] minDiff) {
        if (node == null) {
            return;
        }

        minDiffInBSTHelper(node.left, pre, minDiff);

        if (pre[0] != null) {
            minDiff[0] = Math.min(minDiff[0], node.val - pre[0].val);
        }
        pre[0] = node;

        minDiffInBSTHelper(node.right, pre, minDiff);
    }
}

/**
 * If the input tree is not BST, then we used this Iterative solution.
 * Here we are creating a {@code TreeSet} of all nodes and then iterating over
 * the set to find the min diff.
 *
 * <pre>
 * Time Complexity: O(N*logN + N). Add all nodes in TreeSet + Iterate over the set
 *
 * Space Complexity: O(H + N) = O(N) = Stack + TreeSet
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution3 {
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Less than 2 nodes in the tree");
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        Set<TreeNode> sortedSet = new TreeSet<>((a, b) -> (a.val - b.val));
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            sortedSet.add(cur);
            cur = cur.right;
        }

        int minDiff = Integer.MAX_VALUE;
        Iterator<TreeNode> iterator = sortedSet.iterator();
        TreeNode pre = iterator.next();
        while (iterator.hasNext()) {
            cur = iterator.next();
            minDiff = Math.min(minDiff, cur.val - pre.val);
            pre = cur;
        }

        return minDiff;
    }
}

/**
 * If the input tree is not BST, then we used this Iterative solution.
 * Here we are creating a {@code TreeSet} of all nodes and then iterating over
 * the set to find the min diff.
 *
 * <pre>
 * Time Complexity: O(N*logN + N). Add all nodes in TreeSet + Iterate over the set
 *
 * Space Complexity: O(H + N) = O(N) = Stack + TreeSet
 * </pre>
 *
 * N = Number of nodes. H = Height of the tree.
 */
class Solution4 {
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            throw new IllegalArgumentException("Invalid Input");
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeSet<TreeNode> set = new TreeSet<>((a, b) -> (a.val - b.val));

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            set.add(cur);
            cur = cur.right;
        }

        int res = Integer.MAX_VALUE;
        TreeNode prev = null;
        for (TreeNode node : set) {
            if (prev != null) {
                res = Math.min(res, node.val - prev.val);
            }
            prev = node;
        }

        return res;
    }
}

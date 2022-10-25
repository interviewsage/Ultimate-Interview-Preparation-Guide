// LeetCode Question URL: https://leetcode.com/problems/merge-two-binary-trees/
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
 * DFS (Recursive). Modifying the input tree.
 *
 * Time Complexity: O(min(M, N))
 *
 * Space Complexity: O(min(dM, dN)) = O(min (M, N)) in worst case.
 *
 * M = Number nodes in t1. N = Number of nodes in t2. dM = Max Height of t1. dN
 * = Max Height of t2.
 */
class Solution1 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}

/**
 * DFS (Iterative). Modifying the input tree.
 *
 * Time Complexity: O(min(M, N))
 *
 * Space Complexity: O(min(dM, dN)) = O(min (M, N)) in worst case.
 *
 * M = Number nodes in t1. N = Number of nodes in t2. dM = Max Height of t1. dN
 * = Max Height of t2.
 */
class Solution2 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { root1, root2 });

        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            cur[0].val += cur[1].val;

            if (cur[0].left != null && cur[1].left != null) {
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            } else if (cur[1].left != null) {
                cur[0].left = cur[1].left;
            }

            if (cur[0].right != null && cur[1].right != null) {
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            } else if (cur[1].right != null) {
                cur[0].right = cur[1].right;
            }
        }

        return root1;
    }
}

/**
 * DFS (Recursive). NOT Modifying the input tree.
 *
 * Time Complexity: O(M + N)
 *
 * Space Complexity: O(max(dM, dN)) = O(max (M, N)) in worst case.
 *
 * M = Number nodes in t1. N = Number of nodes in t2. dM = Max Height of t1. dN
 * = Max Height of t2.
 */
class Solution3 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }

        TreeNode node = new TreeNode((root1 == null ? 0 : root1.val) + (root2 == null ? 0 : root2.val));
        node.left = mergeTrees((root1 == null ? null : root1.left), (root2 == null ? null : root2.left));
        node.right = mergeTrees((root1 == null ? null : root1.right), (root2 == null ? null : root2.right));

        return node;
    }
}

/**
 * DFS (Iterative). NOT Modifying the input tree.
 *
 * Time Complexity: O(M + N)
 *
 * Space Complexity: O(max(dM, dN)) = O(max (M, N)) in worst case.
 *
 * M = Number nodes in t1. N = Number of nodes in t2. dM = Max Height of t1. dN
 * = Max Height of t2.
 */
class Solution4 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return treeDeepCopy(root2);
        }
        if (root2 == null) {
            return treeDeepCopy(root1);
        }

        TreeNode newRoot = new TreeNode(root1.val + root2.val);
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { newRoot, root1, root2 });

        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();

            if (cur[1].left == null) {
                cur[0].left = treeDeepCopy(cur[2].left);
            } else if (cur[2].left == null) {
                cur[0].left = treeDeepCopy(cur[1].left);
            } else {
                cur[0].left = new TreeNode(cur[1].left.val + cur[2].left.val);
                stack.push(new TreeNode[] { cur[0].left, cur[1].left, cur[2].left });
            }

            if (cur[1].right == null) {
                cur[0].right = treeDeepCopy(cur[2].right);
            } else if (cur[2].right == null) {
                cur[0].right = treeDeepCopy(cur[1].right);
            } else {
                cur[0].right = new TreeNode(cur[1].right.val + cur[2].right.val);
                stack.push(new TreeNode[] { cur[0].right, cur[1].right, cur[2].right });
            }
        }

        return newRoot;
    }

    private TreeNode treeDeepCopy(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        Deque<TreeNode[]> stack = new ArrayDeque<>();
        stack.push(new TreeNode[] { root, newRoot });

        while (!stack.isEmpty()) {
            TreeNode[] cur = stack.pop();
            if (cur[0].left != null) {
                cur[1].left = new TreeNode(cur[0].left.val);
                stack.push(new TreeNode[] { cur[0].left, cur[1].left });
            }
            if (cur[0].right != null) {
                cur[1].right = new TreeNode(cur[0].right.val);
                stack.push(new TreeNode[] { cur[0].right, cur[1].right });
            }
        }
        return newRoot;
    }

    private TreeNode treeDeepCopyRec(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeDeepCopyRec(root.left);
        newRoot.right = treeDeepCopyRec(root.right);
        return newRoot;
    }
}

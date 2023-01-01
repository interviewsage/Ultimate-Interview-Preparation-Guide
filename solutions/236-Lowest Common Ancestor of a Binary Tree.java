// LeetCode Question URL: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
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

/*
 * Recursive Solution.
 *
 * This solution assumes that both nodes are present in the tree.
 *
 * Time Complexity : O(N) - Worst case all nodes will be visited once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

/**
 * Iterative Solution - PostOrder
 *
 * This solution assumes that both nodes are present in the tree.
 *
 * Time Complexity: O(N) - Every node is inserted into stack once and then
 * popped once.
 *
 * Space Complexity: O(H) - In worst case of skewed tree, the recursion stack
 * can grow up to O(N).
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode pre = null;
        TreeNode lca = null;
        boolean oneNodeFound = false;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                if (cur == p || cur == q) {
                    if (oneNodeFound) {
                        return lca;
                    }
                    oneNodeFound = true;
                    lca = cur;
                }
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }

            stack.pop();
            if (oneNodeFound && lca == cur) {
                if (stack.isEmpty()) {
                    return null;
                }
                lca = stack.peek();
            }
            pre = cur;
            cur = null;
        }

        return null;
    }
}

/**
 * Iterative Solution
 *
 * Find all parent child relationships and save them in a HashMap.
 *
 * Find all ancestors of P and save them in a set. Search for ancestors of q in
 * this set. First matching ancestor is the LCA.
 *
 * This solution assumes that both nodes are present in the tree.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty() && !(parentMap.containsKey(p) && parentMap.containsKey(q))) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                parentMap.put(cur.left, cur);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                parentMap.put(cur.right, cur);
                queue.offer(cur.right);
            }
        }

        Set<TreeNode> pAncestors = new HashSet<>();
        while (p != null) {
            pAncestors.add(p);
            p = parentMap.get(p);
        }

        while (q != null) {
            if (pAncestors.contains(q)) {
                return q;
            }
            q = parentMap.get(q);
        }

        return null;
    }
}

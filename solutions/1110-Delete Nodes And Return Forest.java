// LeetCode Question URL: https://leetcode.com/problems/delete-nodes-and-return-forest/
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
 * Recursive DFS
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/328853/JavaC++Python-Recursion-Solution
 * 2. If asked to solve without using an extra flag: https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/328853/JavaC++Python-Recursion-Solution/301984
 *
 * Time Complexity: O(N + D)
 *
 * Space Complexity: O(H + D)
 *
 * N = Number of nodes in the tree
 * H = Height of the tree
 * D = Length of to_delete array.
 * </pre>
 */
class Solution1 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int n : to_delete) {
            toDeleteSet.add(n);
        }

        delNodesHelper(root, true, result, toDeleteSet);
        return result;
    }

    private TreeNode delNodesHelper(TreeNode node, boolean isRoot, List<TreeNode> result, Set<Integer> toDeleteSet) {
        if (node == null) {
            return null;
        }

        boolean toBeDeleted = toDeleteSet.contains(node.val);

        if (isRoot && !toBeDeleted) {
            result.add(node);
        }

        node.left = delNodesHelper(node.left, toBeDeleted, result, toDeleteSet);
        node.right = delNodesHelper(node.right, toBeDeleted, result, toDeleteSet);

        return toBeDeleted ? null : node;
    }
}

/**
 * Iterative DFS
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/345009/Python-BFS-Solution
 * 2. https://leetcode.com/problems/delete-nodes-and-return-forest/discuss/328853/JavaC++Python-Recursion-Solution
 *
 * Time Complexity: O(N + D)
 *
 * Space Complexity: O(H + D)
 *
 * N = Number of nodes in the tree
 * H = Height of the tree
 * D = Length of to_delete array.
 * </pre>
 */
class Solution2 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Pair -> TreeNode, isRoot
        Deque<Pair<TreeNode, Boolean>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, true));

        Set<Integer> toDeleteSet = new HashSet<>();
        for (int n : to_delete) {
            toDeleteSet.add(n);
        }

        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> cur = stack.pop();
            TreeNode node = cur.getKey();
            boolean isRoot = cur.getValue();
            boolean toBeDeleted = toDeleteSet.contains(node.val);

            if (isRoot && !toBeDeleted) {
                result.add(node);
            }

            if (node.left != null) {
                stack.push(new Pair<>(node.left, toBeDeleted));
                if (toDeleteSet.contains(node.left.val)) {
                    node.left = null;
                }
            }
            if (node.right != null) {
                stack.push(new Pair<>(node.right, toBeDeleted));
                if (toDeleteSet.contains(node.right.val)) {
                    node.right = null;
                }
            }
        }

        return result;
    }
}

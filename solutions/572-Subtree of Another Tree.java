// LeetCode Question URL: https://leetcode.com/problems/subtree-of-another-tree/
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
 * Assign an ID to each subtree in S and then, check if all subtrees of T are
 * present in this HashMap.
 *
 * <pre>
 * Time Complexity:
 * 1. O(S) --> To find all subtrees of S
 * 2. O(T) --> To check if T is a subtree of S.
 * 3. Total time complexity: O(S + T)
 *
 * Space Complexity:
 * 1. O(Height of Tree S) --> Recursion Stack required for findAllSubTrees().
 * 2. O(min(Height of Tree S, Height of Tree T)) --> Recursion Stack required for checkSubTree().
 * 3. O(4 * S) = O(S) --> for allSubTrees memoization map.
 * </pre>
 *
 * S = Number of nodes in tree S. T = Number of nodes in tree t.
 */
class Solution1 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return root == subRoot;
        }

        Map<Pair<Integer, Pair<Integer, Integer>>, Integer> allSubTrees = new HashMap<>();
        int[] maxDepth = { 0 };
        findAllSubTrees(root, allSubTrees, 0, maxDepth);

        boolean[] result = { true };
        checkSubTree(subRoot, allSubTrees, result, maxDepth[0]);
        return result[0];
    }

    private Integer findAllSubTrees(TreeNode root, Map<Pair<Integer, Pair<Integer, Integer>>, Integer> allSubTrees,
            int depth, int[] maxDepth) {
        if (root == null) {
            return null;
        }

        maxDepth[0] = Math.max(maxDepth[0], depth);

        Integer left = findAllSubTrees(root.left, allSubTrees, depth + 1, maxDepth);
        Integer right = findAllSubTrees(root.right, allSubTrees, depth + 1, maxDepth);
        Pair<Integer, Pair<Integer, Integer>> subTreeKey = new Pair<>(root.val, new Pair<>(left, right));
        allSubTrees.putIfAbsent(subTreeKey, allSubTrees.size());
        return allSubTrees.get(subTreeKey);
    }

    private Integer checkSubTree(TreeNode root, Map<Pair<Integer, Pair<Integer, Integer>>, Integer> allSubTrees,
            boolean[] result, int height) {
        if (root == null) {
            return null;
        }
        if (height < 0) {
            result[0] = false;
            return null;
        }

        Integer left = checkSubTree(root.left, allSubTrees, result, height - 1);
        if (!result[0]) {
            return null;
        }
        Integer right = checkSubTree(root.right, allSubTrees, result, height - 1);
        if (!result[0]) {
            return null;
        }

        Integer foundSubTree = allSubTrees.get(new Pair<>(root.val, new Pair<>(left, right)));
        if (foundSubTree == null) {
            result[0] = false;
        }
        return foundSubTree;
    }
}

/**
 * <pre>
 * Refer following links for other solutions:
 *
 * 1. Iterative --> https://leetcode.com/problems/subtree-of-another-tree/discuss/102724/Java-Solution-tree-traversal/106046
 * 2. KMP --> https://leetcode.com/problems/subtree-of-another-tree/discuss/474425/JavaPython-2-solutions:-Naive-Serialize-in-Preorder-then-KMP-O(M+N)-Clean-and-Concise
 * 3. Merkle hashing --> https://leetcode.com/problems/subtree-of-another-tree/discuss/102741/Python-Straightforward-with-Explanation-(O(ST)-and-O(S+T)-approaches)
 * </pre>
 */

/**
 * DFS - Compare each node.
 *
 * Time Complexity: O(S * T)
 *
 * Space Complexity : Depth of the S tree. O(S) in Worst Case off skewed tree.
 *
 * S = Number of nodes in tree S. T = Number of nodes in tree t.
 */
class Solution2 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return root == subRoot;
        }

        return isSame(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSame(TreeNode rootA, TreeNode rootB) {
        if (rootA == null || rootB == null) {
            return rootA == rootB;
        }

        return rootA.val == rootB.val && isSame(rootA.left, rootB.left) && isSame(rootA.right, rootB.right);
    }
}

/**
 * Using Preorder Traversal
 *
 * Time Complexity: genPreorderString takes O(Nodes). indexOf takes (S * T).
 * Thus, total time complexity = O(S + T + S*T)
 *
 * Space Complexity: O(S + T)
 *
 * S = Number of nodes in tree s. T = Number of nodes in tree t.
 */
class Solution3 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }

        StringBuilder sbS = new StringBuilder();
        StringBuilder sbT = new StringBuilder();
        genPreorderString(s, sbS);
        genPreorderString(t, sbT);

        if (sbS.indexOf(sbT.toString()) >= 0) {
            return true;
        }
        return false;
    }

    private void genPreorderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null");
            return;
        }
        sb.append("#" + node.val);
        genPreorderString(node.left, sb);
        genPreorderString(node.right, sb);
    }
}

// LeetCode Question URL: https://leetcode.com/problems/unique-binary-search-trees-ii/
// LeetCode Discuss URL:

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
 * Recursive Solution + Using Memoization
 *
 * In this solution we generate results for each valid range. And then save the
 * result in a memoMpa cache.
 *
 * Time Complexity:
 *
 * Space Complexity:
 *
 * N = Input number of nodes.
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes in invalid");
        }

        return generateTreesHelper(1, n, new HashMap<>());
    }

    private List<TreeNode> generateTreesHelper(int start, int end, HashMap<String, List<TreeNode>> memoMap) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        String key = new StringBuilder().append(start).append('_').append(end).toString();
        List<TreeNode> cachedResult = memoMap.get(key);
        if (cachedResult != null) {
            return cachedResult;
        }

        if (start == end) {
            result.add(new TreeNode(start));
            memoMap.put(key, result);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = generateTreesHelper(start, i - 1, memoMap);
            List<TreeNode> rightSubtrees = generateTreesHelper(i + 1, end, memoMap);
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    result.add(new TreeNode(i, left, right));
                }
            }
        }

        memoMap.put(key, result);
        return result;
    }
}

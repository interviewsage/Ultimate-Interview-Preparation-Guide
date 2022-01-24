// LeetCode Question URL: https://leetcode.com/problems/nested-list-weight-sum-ii/
// LeetCode Discuss URL:

import java.util.*;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // Constructor initializes an empty nested list.
    // public NestedInteger();

    // Constructor initializes a single integer.
    // public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

/**
 * Iterative Solution (BFS)
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83641/No-depth-variable-no-multiplication
 * 2. https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83641/No-depth-variable-no-multiplication/87997
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Number of integers in the nested list.
 */
class Solution1 {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int weightedSum = 0;
        int unweightedSum = 0;

        while (!queue.isEmpty()) {
            int curLevel = queue.size();
            for (int i = 0; i < curLevel; i++) {
                NestedInteger ni = queue.poll();
                Integer num = ni.getInteger();
                if (num != null) {
                    unweightedSum += num;
                } else {
                    queue.addAll(ni.getList());
                }
            }
            weightedSum += unweightedSum;
        }

        return weightedSum;
    }
}

/**
 * Recursive Solution (DFS)
 *
 * Find max depth and multiply it with unweighted sum. Subtract sum calculated
 * with weight definition in problem 1.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Max Depth of the nested list) = O(N) in worst case.
 *
 * N = Number of integers in the nested list.
 */
class Solution2 {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int[] maxDepth = { 0 };
        int[] unweightedSum = { 0 };

        /**
         * Find max depth and multiply it with unweighted sum. Subtract sum calculated
         * with weight definition in problem 1.
         */
        int compSum = depthSumInverseHelper(nestedList, 1, maxDepth, unweightedSum);

        return (maxDepth[0] + 1) * unweightedSum[0] - compSum;
    }

    private int depthSumInverseHelper(List<NestedInteger> list, int depth, int[] maxDepth, int[] unweightedSum) {
        maxDepth[0] = Math.max(maxDepth[0], depth);

        int sum = 0;
        for (NestedInteger ni : list) {
            Integer num = ni.getInteger();
            if (num != null) {
                sum += num * depth;
                unweightedSum[0] += num;
            } else {
                sum += depthSumInverseHelper(ni.getList(), depth + 1, maxDepth, unweightedSum);
            }
        }

        return sum;
    }
}

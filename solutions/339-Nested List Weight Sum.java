// LeetCode Question URL: https://leetcode.com/problems/nested-list-weight-sum/
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
 * Iterative Solution (Level by level)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2 * W) = We are maintaining 2 levels.
 *
 * N = Number of NestedIntegers encountered.
 * W = Maximum number of NestedIntegers encountered at a particular depth.
 */
class Solution1 {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new IllegalArgumentException("Input list in invalid");
        }

        List<NestedInteger> curLevel = nestedList;
        int depth = 0;
        int result = 0;

        while (!curLevel.isEmpty()) {
            depth++;
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : curLevel) {
                Integer num = ni.getInteger();
                if (num != null) {
                    result += depth * num;
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            curLevel = nextLevel;
        }

        return result;
    }
}

/**
 * Recursive Solution (DFS)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(Max Depth of the nested list) = O(N) in worst case.
 *
 * N = Number of NestedIntegers encountered.
 */
class Solution2 {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new IllegalArgumentException("Input list in invalid");
        }

        return depthSumHelper(nestedList, 1);
    }

    private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            Integer num = ni.getInteger();
            if (num != null) {
                sum += depth * num;
            } else {
                sum += depthSumHelper(ni.getList(), depth + 1);
            }
        }
        return sum;
    }
}

/**
 * Iterative Solution (Level by level) using a queue
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Number of NestedIntegers encountered.
 */
class Solution3 {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int sum = 0;
        int depth = 1;

        Queue<NestedInteger> queue = new LinkedList<>(nestedList);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                NestedInteger n = queue.poll();
                if (n.isInteger()) {
                    sum += n.getInteger() * depth;
                } else {
                    queue.addAll(n.getList());
                }
            }

            depth++;
        }

        return sum;
    }
}

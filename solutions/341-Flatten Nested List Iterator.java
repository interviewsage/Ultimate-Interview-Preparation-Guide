// LeetCode Question URL: https://leetcode.com/problems/flatten-nested-list-iterator/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

/**
 * Using stack.
 *
 * Time Complexity: Amortized O(1). It will take average O(1) time to perform
 * each operation
 *
 * Space Complexity: O(N)
 *
 * N = Total length of the nested list.
 */
public class NestedIterator implements Iterator<Integer> {

    private Deque<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new IllegalArgumentException("Input is null");
        }

        stack = new ArrayDeque<>();
        flattenNestedList(nestedList);
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No next element");
        }

        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) {
                return true;
            }
            flattenNestedList(stack.pop().getList());
        }

        return false;
    }

    private void flattenNestedList(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
}

// Your NestedIterator object will be instantiated and called as such:
// NestedIterator i = new NestedIterator(nestedList);
// while (i.hasNext()) v[f()] = i.next();

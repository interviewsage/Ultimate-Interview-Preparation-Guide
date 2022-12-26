// LeetCode Question URL: https://leetcode.com/problems/peeking-iterator/
// LeetCode Discuss URL:

import java.util.*;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

/**
 * Time and Space Complexity: O(1)
 */
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> itr;
    Integer curValue;
    boolean endOfItr;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        if (iterator == null) {
            throw new IllegalArgumentException("Input is null");
        }

        itr = iterator;
        endOfItr = false;
        advanceInternalIterator();
    }

    private void advanceInternalIterator() {
        if (!itr.hasNext()) {
            endOfItr = true;
            curValue = null;
        } else {
            curValue = itr.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element not found");
        }
        return curValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("Next element not found");
        }
        Integer val = curValue;
        advanceInternalIterator();
        return val;
    }

    @Override
    public boolean hasNext() {
        return !endOfItr;
    }
}

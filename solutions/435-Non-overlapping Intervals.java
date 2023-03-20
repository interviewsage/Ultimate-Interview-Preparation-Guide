// LeetCode Question URL: https://leetcode.com/problems/non-overlapping-intervals/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Greedy polynomial solution
 *
 * Refer: 1)
 * https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
 * 2)
 * https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java:-Least-is-Most/96283
 *
 * (1) Select the interval, x, with the earliest finishing time. (2) Remove x,
 * and all intervals intersecting x, from the set of candidate intervals. (3)
 * Repeat until the set of candidate intervals is empty.
 *
 * Whenever we select an interval at step 1, we may have to remove many
 * intervals in step 2. However, all these intervals necessarily cross the
 * finishing time of x, and thus they all cross each other (see figure). Hence,
 * at most 1 of these intervals can be in the optimal solution. Hence, for every
 * interval in the optimal solution, there is an interval in the greedy
 * solution. This proves that the greedy algorithm indeed finds an optimal
 * solution.
 *
 * The interval with early start might be very long and incompatible with many
 * intervals. But if we choose the interval that ends early, we'll have more
 * space left to accommodate more intervals.
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N) for sorting.
 */
class Solution1 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input intervals array is null");
        }

        int numIntervals = intervals.length;
        if (numIntervals <= 1) {
            return 0;
        }

        // Sorting by end.
        Arrays.sort(intervals, (a, b) -> (Integer.compare(a[1], b[1])));

        int priorEnd = intervals[0][1];
        int result = 0;

        for (int i = 1; i < numIntervals; i++) {
            if (intervals[i][0] >= priorEnd) {
                priorEnd = intervals[i][1];
            } else {
                result++;
            }
        }

        return result;
    }
}

/**
 * This solution can be used when the range of end is small or finite.
 *
 * Time Complexity: O(2*N + Range) = O(N + Range)
 *
 * Space Complexity: O(Range)
 *
 * N = Number of intervals
 * Range = Range of end = maxEnd - minEnd + 1
 */
class Solution2 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input intervals array is null");
        }

        int numIntervals = intervals.length;
        if (numIntervals <= 1) {
            return 0;
        }

        int minEnd = intervals[0][1];
        int maxEnd = intervals[0][1];
        for (int i = 1; i < numIntervals; i++) {
            minEnd = Math.min(minEnd, intervals[i][1]);
            maxEnd = Math.max(maxEnd, intervals[i][1]);
        }

        int range = maxEnd - minEnd + 1;
        // Saving the max start at the end or the start of shortest interval at each
        // end.
        Integer[] maxStart = new Integer[range];

        for (int[] interval : intervals) {
            // Subtracting by minEnd .. transforms the minEnd to zero.
            int s = interval[0] - minEnd;
            int e = interval[1] - minEnd;

            if (maxStart[e] == null || s > maxStart[e]) {
                maxStart[e] = s;
            }
        }

        int numNonOverlap = 1;
        int priorEnd = 0;
        for (int e = 1; e < range; e++) {
            if (maxStart[e] != null && maxStart[e] >= priorEnd) {
                priorEnd = e;
                numNonOverlap++;
            }
        }

        return numIntervals - numNonOverlap;
    }
}

// -----------------------------------------------------------------------------

// Definition for an interval.
class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

/**
 * Same explanation and complexity as above solution.
 */
class Solution3 {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> (a.end - b.end));
        int count = 1;
        int end = intervals[0].end;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }

        return intervals.length - count;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/insert-interval/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Binary Search.
 *
 * Time Complexity: O(X + Z + log(N) + log(Y+Z)) = O(ResultLen + 2*log(N)) = O(N
 * + log(N))
 *
 * Space Complexity: O(1) -> excluding the result space. O(N) -> including the
 * result space.
 *
 * N = Number of intervals in the input array.
 *
 * X = Intervals before the merge.
 *
 * Y = Intervals Merged.
 *
 * Z = Intervals after the merge.
 */
class Solution1 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            if (newInterval != null) {
                return new int[][] { newInterval };
            }
            return new int[0][0];
        }
        if (newInterval == null) {
            return intervals;
        }

        int len = intervals.length;

        // Thus will return the leftmost index at which end is greater than or equal to
        // the start in newInterval
        int leftIdx = Arrays.binarySearch(intervals, 0, len, new int[] { -1, newInterval[0] }, (a, b) -> (a[1] - b[1]));
        if (leftIdx < 0) {
            leftIdx = -leftIdx - 1;
        }

        // Thus will return the rightmost index at which start is greater than or equal
        // to the end in newInterval
        int rightIdx = Arrays.binarySearch(intervals, leftIdx, len, new int[] { newInterval[1], -1 },
                (a, b) -> (a[0] - b[0]));
        if (rightIdx < 0) {
            rightIdx = -rightIdx - 1;
            rightIdx--; // If exact match is not found, binarySearch will return the next greater
                        // element. Thus we need to reduce the rightIdx by one ot get the interval that
                        // will be merged.
        }

        int resultLen = leftIdx + 1 + (len - 1 - rightIdx);
        int[][] result = new int[resultLen][2];
        int i = 0;

        while (i < leftIdx) {
            result[i] = intervals[i++];
        }

        int mergedStart = leftIdx < len ? Math.min(intervals[leftIdx][0], newInterval[0]) : newInterval[0];
        int mergedEnd = rightIdx >= 0 ? Math.max(intervals[rightIdx][1], newInterval[1]) : newInterval[1];
        result[i++] = new int[] { mergedStart, mergedEnd };

        for (; i < resultLen; i++) {
            result[i] = intervals[rightIdx - leftIdx + i];
        }

        return result;
    }
}

/**
 * With new function signature. Check comments in code for explanation.
 *
 * Time Complexity: O(N) -> Each interval is visited twice.
 *
 * Space Complexity: O(1) -> excluding the result space. O(N) -> including the
 * result space.
 *
 * N = Number of intervals in the input array.
 */
class Solution2 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            if (newInterval != null) {
                return new int[][] { newInterval };
            }
            return new int[0][0];
        }
        if (newInterval == null) {
            return intervals;
        }

        int len = intervals.length;
        int i = 0;
        // Find count of intervals ending before the new interval starts.
        while (i < len && newInterval[0] > intervals[i][1]) {
            i++;
        }
        int beforeCount = i;

        // Find the intervals to be merged with new interval, that start before the end
        // of new interval.
        if (i < len) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        }
        while (i < len && newInterval[1] >= intervals[i][0]) {
            i++;
        }
        if (i > 0) {
            newInterval[1] = Math.max(newInterval[1], intervals[i - 1][1]);
        }

        int resultLen = beforeCount + 1 + (len - i);
        int[][] result = new int[resultLen][2];
        int j = 0;

        while (j < beforeCount) {
            result[j] = intervals[j++];
        }

        result[j++] = newInterval;

        while (i < len) {
            result[j++] = intervals[i++];
        }

        return result;
    }
}

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
 * Check comments in code for explanation.
 *
 * Time Complexity: O(N) -> Each interval is visited once.
 *
 * Space Complexity: O(1) -> excluding the result space. O(N) -> including the
 * result space.
 *
 * N = Number of intervals in the input array.
 */
class Solution3 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            if (newInterval != null) {
                result.add(newInterval);
            }
            return result;
        }
        if (newInterval == null) {
            return intervals;
        }

        int n = intervals.size();
        int i = 0;

        // Find and add all intervals ending before the new interval starts.
        while (i < n && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }

        // Find and merge the intervals with the new interval, that start before the end
        // of new interval.
        while (i < n && intervals.get(i).start <= newInterval.end) {
            int newStart = Math.min(intervals.get(i).start, newInterval.start);
            int newEnd = Math.max(intervals.get(i).end, newInterval.end);
            newInterval = new Interval(newStart, newEnd);
            i++;
        }

        // Add the new interval.
        result.add(newInterval);

        // Add all intervals starting after the end of updated new interval.
        while (i < n) {
            result.add(intervals.get(i));
            i++;
        }

        return result;
    }
}

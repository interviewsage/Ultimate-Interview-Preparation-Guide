// LeetCode Question URL: https://leetcode.com/problems/meeting-rooms/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity O(N log N + N)
 *
 * Space Complexity: O(N) Required for sorting (Tim Sort)
 *
 * N = Length of input array.
 */
class Solution1 {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input intervals array is null");
        }

        int len = intervals.length;
        if (len <= 1) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        for (int i = 1; i < len; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
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
 * Time Complexity O(N log N)
 *
 * Space Complexity: O(N) Required for sorting.
 *
 * N = Length of input list.
 */
class Solution2 {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null) {
            return false;
        }
        int len = intervals.length;
        if (len < 2) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        for (int i = 1; i < len; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }
}
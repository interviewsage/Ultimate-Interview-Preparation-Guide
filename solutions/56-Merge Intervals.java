// LeetCode Question URL: https://leetcode.com/problems/merge-intervals/

import java.util.*;

/**
 * <pre>
 * Refer these post for Facebook Follow-up:
 * 1. https://leetcode.com/problems/merge-intervals/solution/321556
 * 2. https://leetcode.com/problems/merge-intervals/discuss/21452/Share-my-interval-tree-solution-no-sorting
 * 3. https://leetcode.com/problems/merge-intervals/discuss/21451/Share-my-BST-interval-tree-solution-C++-No-sorting!
 * </pre>
 */

/**
 * Refer:
 * https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
 *
 * Time Complexity O(N log N)
 *
 * Space Complexity: O(N) Including the result.
 *
 * N = Length of input array.
 */
class Solution1 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int numIntervals = intervals.length;
        if (numIntervals <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<int[]> resultList = new ArrayList<>();
        int[] newInterval = intervals[0];

        for (int i = 1; i < numIntervals; i++) {
            if (intervals[i][0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            } else {
                resultList.add(newInterval);
                newInterval = intervals[i];
            }
        }

        resultList.add(newInterval);

        return resultList.toArray(new int[resultList.size()][]);
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
 * Refer:
 * https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution
 *
 * Time Complexity O(N log N)
 *
 * Space Complexity: O(N) Including the result.
 *
 * N = Length of input list.
 */
class Solution2 {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> result = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> (a.start - b.start));

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= end) {
                end = Math.max(end, intervals.get(i).end);
            } else {
                result.add(new Interval(start, end));
                start = intervals.get(i).start;
                end = intervals.get(i).end;
            }
        }

        result.add(new Interval(start, end));

        return result;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/meeting-rooms-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/meeting-rooms-ii/discuss/67857/AC-Java-solution-using-min-heap/69761
 *
 * Time Complexity: O(N logN + 2N logN) = O(N logN)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = intervals.length;
        if (len <= 1) {
            return len;
        }

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);

        for (int i = 1; i < len; i++) {
            int curEnd = pq.peek();
            if (intervals[i][0] >= curEnd) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }

        return pq.size();
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-"Super-Easy-Java-Solution-Beats-98.8"-from-@pinkfloyda
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = intervals.length;
        if (len <= 1) {
            return len;
        }

        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 1;
        int endIdx = 0;

        for (int i = 1; i < len; i++) {
            if (starts[i] < ends[endIdx]) {
                rooms++;
            } else {
                endIdx++;
            }
        }

        return rooms;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/meeting-rooms-ii/discuss/67857/AC-Java-solution-using-min-heap/69761
 *
 * Time Complexity: O(N logN + 2N logN) = O(N logN)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution3 {
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

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null) {
            return 0;
        }
        int len = intervals.length;
        if (len < 2) {
            return len;
        }

        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        PriorityQueue<Interval> queue = new PriorityQueue<Interval>((a, b) -> (a.end - b.end));
        queue.offer(intervals[0]);

        for (int i = 1; i < len; i++) {
            Interval cur = queue.poll();

            if (intervals[i].start >= cur.end) {
                cur.end = intervals[i].end;
            } else {
                queue.offer(intervals[i]);
            }
            queue.offer(cur);
        }

        return queue.size();
    }
}

// LeetCode Question URL: https://leetcode.com/problems/employee-free-time/
// LeetCode Discuss URL:

import java.util.*;

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
 * Using Priority Queue.
 *
 * Refer:
 * https://leetcode.com/articles/employee-free-time/#approach-2-priority-queue-accepted
 *
 * Time Complexity: O(N log K)
 *
 * Space Complexity: O(K)
 *
 * N = Count of all intervals in the input array. K = Number of employees.
 */
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return result;
        }

        int numEmployees = schedule.size();
        PriorityQueue<int[]> pq = new PriorityQueue<>(numEmployees, (a, b) -> (schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start));
        int end = Integer.MAX_VALUE;

        for (int i = 0; i < numEmployees; i++) {
            List<Interval> e = schedule.get(i);
            if (e.size() > 0) {
                pq.offer(new int[] {i, 0});
                end = Math.min(end, e.get(0).end);
            }
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            List<Interval> curEmp = schedule.get(cur[0]);
            Interval curInt = curEmp.get(cur[1]);

            if (curInt.start > end) {
                result.add(new Interval(end, curInt.start));
                end = curInt.end;
            } else {
                end = Math.max(end, curInt.end);
            }

            if (cur[1] < curEmp.size()-1) {
                cur[1]++;
                pq.offer(cur);
            }
        }

        return result;
    }
}

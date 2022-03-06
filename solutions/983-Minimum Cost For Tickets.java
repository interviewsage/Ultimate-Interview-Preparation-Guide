// LeetCode Question URL: https://leetcode.com/problems/minimum-cost-for-tickets/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226659/Two-DP-solutions-with-pictures
 * 2. https://leetcode.com/problems/minimum-cost-for-tickets/discuss/630868/explanation-from-someone-who-took-2-hours-to-solve
 * </pre>
 *
 * Using Queue to track 7-Day and 30-Day Pass.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(7 + 30) = O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        if (days == null || days.length == 0 || costs == null) {
            return 0;
        }

        Queue<int[]> cur7DayPass = new LinkedList<>();
        Queue<int[]> cur30DayPass = new LinkedList<>();
        int minCost = 0;

        for (int d : days) {
            while (!cur7DayPass.isEmpty() && cur7DayPass.peek()[0] + 7 <= d) {
                cur7DayPass.poll();
            }
            while (!cur30DayPass.isEmpty() && cur30DayPass.peek()[0] + 30 <= d) {
                cur30DayPass.poll();
            }

            cur7DayPass.offer(new int[] { d, minCost + costs[1] });
            cur30DayPass.offer(new int[] { d, minCost + costs[2] });

            minCost = Math.min(minCost + costs[0], Math.min(cur7DayPass.peek()[1], cur30DayPass.peek()[1]));
        }

        return minCost;
    }
}

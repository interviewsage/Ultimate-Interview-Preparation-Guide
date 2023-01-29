// LeetCode Question URL: https://leetcode.com/problems/minimum-time-visiting-all-points/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }

        int result = 0;
        for (int i = 1; i < points.length; i++) {
            int[] p1 = points[i - 1];
            int[] p2 = points[i];

            result += Math.max(Math.abs(p1[0] - p2[0]), Math.abs(p1[1] - p2[1]));
        }

        return result;
    }
}

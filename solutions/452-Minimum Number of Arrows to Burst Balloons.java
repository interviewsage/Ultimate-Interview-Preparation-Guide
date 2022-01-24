// LeetCode Question URL: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Greedy Solution
 *
 * Refer:
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/93703/Share-my-explained-Greedy-solution
 *
 * We know that eventually we have to shoot down every balloon, so for each
 * ballon there must be an arrow whose position is between balloon[0] and
 * balloon[1] inclusively. Given that, we can sort the array of balloons by
 * their ending position. Then we make sure that while we take care of each
 * balloon in order, we can shoot as many following balloons as possible.
 *
 * So what position should we pick each time? We should shoot as to the right as
 * possible, because since balloons are sorted, this gives you the best chance
 * to take down more balloons. Therefore the position should always be
 * balloon[i][1] for the ith balloon.
 *
 * This is exactly what I do in the for loop: check how many balloons I can
 * shoot down with one shot aiming at the ending position of the current
 * balloon. Then I skip all these balloons and start again from the next one (or
 * the leftmost remaining one) that needs another arrow.
 *
 * Time Complexity: O(N log N + N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of points.
 */
class Solution1 {
    public int findMinArrowShots(int[][] points) {
        if (points == null) {
            return 0;
        }

        int numPoints = points.length;
        if (numPoints <= 1) {
            return numPoints;
        }

        Arrays.sort(points, (a, b) -> (Integer.compare(a[1], b[1])));
        int curEnd = points[0][1];
        int arrowCount = 1;

        for (int i = 1; i < numPoints; i++) {
            if (points[i][0] > curEnd) {
                curEnd = points[i][1];
                arrowCount++;
            }
        }

        return arrowCount;
    }
}

/**
 * Sorting the points array on Start value in descending order
 *
 * Time Complexity: O(N log N + N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of points.
 */
class Solution2 {
    public int findMinArrowShots(int[][] points) {
        if (points == null) {
            return 0;
        }

        int numPoints = points.length;
        if (numPoints <= 1) {
            return numPoints;
        }

        Arrays.sort(points, (a, b) -> (Integer.compare(b[0], a[0])));
        int curStart = points[0][0];
        int arrowCount = 1;

        for (int i = 1; i < numPoints; i++) {
            if (points[i][1] < curStart) {
                curStart = points[i][0];
                arrowCount++;
            }
        }

        return arrowCount;
    }
}
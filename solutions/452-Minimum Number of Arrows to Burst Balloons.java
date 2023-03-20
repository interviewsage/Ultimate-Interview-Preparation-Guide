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
            throw new IllegalArgumentException("Input points array is null");
        }

        int numPoints = points.length;
        if (numPoints <= 1) {
            return numPoints;
        }

        Arrays.sort(points, (a, b) -> (Integer.compare(a[1], b[1])));
        int priorEnd = points[0][1];
        int numArrows = 1;

        for (int i = 1; i < numPoints; i++) {
            if (points[i][0] > priorEnd) {
                priorEnd = points[i][1];
                numArrows++;
            }
        }

        return numArrows;
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
class Solution3 {
    public int findMinArrowShots(int[][] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input points array is null");
        }

        int numPoints = points.length;
        if (numPoints <= 1) {
            return numPoints;
        }

        int minEnd = points[0][1];
        int maxEnd = points[0][1];
        for (int i = 1; i < numPoints; i++) {
            minEnd = Math.min(minEnd, points[i][1]);
            maxEnd = Math.max(maxEnd, points[i][1]);
        }

        int range = maxEnd - minEnd + 1;
        Integer[] maxStart = new Integer[range];
        for (int[] point : points) {
            int s = point[0] - minEnd;
            int e = point[1] - minEnd;

            if (maxStart[e] == null || s > maxStart[e]) {
                maxStart[e] = s;
            }
        }

        int numArrows = 1;
        int priorEnd = 0;
        for (int end = 1; end < range; end++) {
            if (maxStart[end] != null && maxStart[end] > priorEnd) {
                priorEnd = end;
                numArrows++;
            }
        }

        return numArrows;
    }
}

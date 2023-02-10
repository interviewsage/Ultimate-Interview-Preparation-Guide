// LeetCode Question URL: https://leetcode.com/problems/minimum-area-rectangle/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find Diagonals and then check if other points to complete the rectangle are
 * present.
 *
 * Time Complexity: O(N + N^2)
 *
 * Space Complexity: O(N + 2N)
 *
 * N = Number of points in the input array.
 *
 * See another way of converting the input array to HashSet. See submission at
 * 48 ms. (Also copied below. see Solution3)
 */
class Solution {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length < 4) {
            return 0;
        }

        int numPoints = points.length;
        Set<Integer> uniqueY = new HashSet<>();
        Map<Integer, Set<Integer>> pointsMap = new HashMap<>();
        for (int[] p : points) {
            pointsMap.putIfAbsent(p[0], new HashSet<>());
            pointsMap.get(p[0]).add(p[1]);
            uniqueY.add(p[1]);
        }

        // No rectangle is possible
        if (pointsMap.size() == 1 || pointsMap.size() == numPoints || uniqueY.size() == 1
                || uniqueY.size() == numPoints) {
            return 0;
        }

        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < numPoints - 1; i++) {
            for (int j = i + 1; j < numPoints; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];

                if (p1[0] == p2[0] || p1[1] == p2[1] || !pointsMap.get(p1[0]).contains(p2[1])
                        || !pointsMap.get(p2[0]).contains(p1[1])) {
                    continue;
                }

                minArea = Math.min(minArea, Math.abs((p1[0] - p2[0]) * (p1[1] - p2[1])));
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}

// Alternate to above solution.
class Solution2 {
    public int minAreaRect(int[][] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input points array is null");
        }

        int numPoints = points.length;
        if (numPoints < 4) {
            return 0;
        }

        Set<Integer> uniqueY = new HashSet<>();
        Map<Integer, Set<Integer>> pointsMap = new HashMap<>();

        for (int[] p : points) {
            pointsMap.putIfAbsent(p[0], new HashSet<>());
            pointsMap.get(p[0]).add(p[1]);
            uniqueY.add(p[1]);
        }

        if (pointsMap.size() == 1 || pointsMap.size() == numPoints || uniqueY.size() == 1
                || uniqueY.size() == numPoints) {
            return 0;
        }

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < numPoints - 1; i++) {
            for (int j = i + 1; j < numPoints; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];

                int area = Math.abs((p1[0] - p2[0]) * (p1[1] - p2[1]));
                if (area == 0 || area >= minArea || !pointsMap.get(p1[0]).contains(p2[1])
                        || !pointsMap.get(p2[0]).contains(p1[1])) {
                    continue;
                }
                minArea = area;
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}

class Solution3 {
    public int minAreaRect(int[][] points) {
        Set<Pair<Integer, Integer>> pointSet = new HashSet<>();

        for (int[] point : points) {
            pointSet.add(new Pair<>(point[0], point[1]));
        }

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];

                int area = Math.abs((p1[0] - p2[0]) * (p1[1] - p2[1]));

                if (area > minArea || area == 0) {
                    continue;
                }

                if (pointSet.contains(new Pair<>(p1[0], p2[1]))
                        && pointSet.contains(new Pair<>(p2[0], p1[1]))) {
                    minArea = area;
                }
            }
        }
        return minArea != Integer.MAX_VALUE ? minArea : 0;
    }
}

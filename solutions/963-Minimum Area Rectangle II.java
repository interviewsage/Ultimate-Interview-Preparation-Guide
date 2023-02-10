// LeetCode Question URL: https://leetcode.com/problems/minimum-area-rectangle-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * In this solution we are finding 3 corners and then find the 4th corner.
 *
 * <pre>
 * Once we have chosen 3 corners of rectangle we will find the 4th corner only
 * if the corners create a right angle triangle.
 *
 * https://www.quora.com/Why-is-the-product-of-slopes-of-two-perpendicular-lines-equal-to-1
 * https://qr.ae/prwDjF
 * Product of slopes of two perpendicular lines equal to -1.
 *
 * To find the 4th point:
 * dx1 & dy1 represent the shift from point i to get point j.
 * Thus, we can use same shit to get 4th corner from point k.
 * Since both lines (i, j) & (k, 4th) will be parallel.
 * </pre>
 *
 * Time Complexity: O(N + N^3) = O(N^3)
 *
 * Space Complexity: O(N) - Each point is saved in the hashmap.
 *
 * N = Number of points in the input array.
 */
class Solution1 {
    public double minAreaFreeRect(int[][] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input points array is null");
        }

        int numPoints = points.length;
        if (numPoints < 4) {
            return 0;
        }

        int minX = points[0][0];
        int maxX = points[0][0];
        int minY = points[0][1];
        int maxY = points[0][1];
        Map<Integer, Set<Integer>> pointsMap = new HashMap<>();

        for (int[] p : points) {
            minX = Math.min(minX, p[0]);
            maxX = Math.max(maxX, p[0]);
            minY = Math.min(minY, p[1]);
            maxY = Math.max(maxY, p[1]);
            pointsMap.putIfAbsent(p[0], new HashSet<>());
            pointsMap.get(p[0]).add(p[1]);
        }

        if (minX == maxX || minY == maxY) {
            return 0;
        }

        long minArea = Long.MAX_VALUE;

        // We are choosing 3 corners of rectangle and finding the 4th corner if the 3
        // corners create a right angle triangle.
        for (int i = 0; i < numPoints - 2; i++) {
            for (int j = i + 1; j < numPoints - 1; j++) {
                // Slope of line1 = dy1/dx1
                int dx1 = points[j][0] - points[i][0];
                int dy1 = points[j][1] - points[i][1];

                for (int k = j + 1; k < numPoints; k++) {
                    // Slope of line2 = dy2/dx2
                    int dx2 = points[k][0] - points[i][0];
                    int dy2 = points[k][1] - points[i][1];

                    // https://www.quora.com/Why-is-the-product-of-slopes-of-two-perpendicular-lines-equal-to-1
                    // https://qr.ae/prwDjF
                    // Product of slopes of two perpendicular lines equal to -1
                    // Its done this way to avoid division by zero in case of vertical & horizontal
                    // lines.
                    if (dx1 * dx2 + dy1 * dy2 != 0) {
                        continue;
                    }

                    // Now finding 4th corner of the rectangle
                    // dx1 & dy1 represent the shift from point i to get point j.
                    // Thus, we can use same shit to get 4th corner from point k.
                    // Since both lines (i, j) & (k, 4th) will be parallel.
                    int x = dx1 + points[k][0];
                    int y = dy1 + points[k][1];

                    Set<Integer> ys = pointsMap.get(x);
                    if (ys != null && ys.contains(y)) {
                        minArea = Math.min(minArea,
                                ((long) dx1 * dx1 + (long) dy1 * dy1) * ((long) dx2 * dx2 + (long) dy2 * dy2));
                    }
                }
            }
        }

        return minArea == Long.MAX_VALUE ? 0 : Math.sqrt(minArea);
    }
}

/**
 * In this solution we are grouping all pair of points by their midpoint &
 * length. In a rectangle both diagonals have same length and their intersection
 * is at the mid point.
 *
 * Now we find pairs in each group and calculate their area.
 *
 * Time Complexity: O(N^2 + N^4) = O(N^4)
 *
 * Space Complexity: O(N^2) - All pairs are saved in the map.
 *
 * N = Number of points in the input array.
 */
class Solution2 {
    public double minAreaFreeRect(int[][] points) {
        if (points == null) {
            throw new IllegalArgumentException("Input points array is null");
        }

        int numPoints = points.length;
        if (numPoints < 4) {
            return 0.0;
        }

        int minX = points[numPoints - 1][0];
        int maxX = points[numPoints - 1][0];
        int minY = points[numPoints - 1][1];
        int maxY = points[numPoints - 1][1];
        Map<String, List<int[]>> pointsMap = new HashMap<>();

        for (int i = 0; i < numPoints - 1; i++) {
            int[] p1 = points[i];
            minX = Math.min(minX, p1[0]);
            maxX = Math.max(maxX, p1[0]);
            minY = Math.min(minY, p1[1]);
            maxY = Math.max(maxY, p1[1]);

            for (int j = i + 1; j < numPoints; j++) {
                int[] p2 = points[j];
                long dist = getDistanceSquare(p1, p2);
                if (dist == 0) {
                    continue;
                }

                String midPoint = new StringBuilder()
                        .append(p1[0] + p2[0]).append(',')
                        .append(p1[1] + p2[1]).append(',')
                        .append(dist)
                        .toString();

                pointsMap.putIfAbsent(midPoint, new ArrayList<>());
                pointsMap.get(midPoint).add(new int[] { i, j });
            }
        }

        if (minX == maxX || minY == maxY) {
            return 0.0;
        }

        long minArea = Long.MAX_VALUE;

        for (List<int[]> list : pointsMap.values()) {
            int size = list.size();
            if (size == 1) {
                continue;
            }

            for (int i = 0; i < size - 1; i++) {
                int[] pair1 = list.get(i);
                int[] diagPoint1 = points[pair1[0]];
                int[] diagPoint2 = points[pair1[1]];

                for (int j = i + 1; j < size; j++) {
                    int[] pair2 = list.get(j);
                    int[] point = points[pair2[0]];

                    minArea = Math.min(minArea,
                            getDistanceSquare(diagPoint1, point) * getDistanceSquare(diagPoint2, point));
                }
            }
        }

        return minArea == Long.MAX_VALUE ? 0.0 : Math.sqrt(minArea);
    }

    private long getDistanceSquare(int[] p1, int[] p2) {
        return ((long) p1[0] - p2[0]) * ((long) p1[0] - p2[0]) + ((long) p1[1] - p2[1]) * ((long) p1[1] - p2[1]);
    }
}

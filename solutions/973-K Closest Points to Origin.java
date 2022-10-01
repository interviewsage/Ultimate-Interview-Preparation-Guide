// LeetCode Question URL: https://leetcode.com/problems/k-closest-points-to-origin/

import java.util.*;

/**
 * Using Kth smallest element algorithm (Quick Select)
 *
 * Time Complexity: Best Case -> O(N). Worst Case -> O(N^2).
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    private static final Random RANDOM = new Random();

    public int[][] kClosest(int[][] points, int k) {
        if (points == null || k <= 0) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int numPoints = points.length;
        if (k >= numPoints) {
            return points;
        }

        int start = 0;
        int end = numPoints - 1;

        while (start < end) {
            int pivot = partition(points, start, end);
            if (pivot == k - 1) {
                break;
            }
            if (pivot < k - 1) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }

        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int start, int end) {
        swap(points, start, start + RANDOM.nextInt(end - start + 1));
        int insertPos = start;
        int startDist = distance(points[start]);

        for (int i = start + 1; i <= end; i++) {
            if (distance(points[i]) <= startDist) {
                insertPos++;
                swap(points, i, insertPos);
            }
        }

        swap(points, start, insertPos);
        return insertPos;
    }

    private void swap(int[][] points, int i, int j) {
        if (i != j) {
            int[] t = points[i];
            points[i] = points[j];
            points[j] = t;
        }
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

/**
 * ∑
 * Using Priority Queue
 *
 * Priority Queue will maintain k closest points.
 *
 * Time Complexity: O(N log K)
 *
 * Space Complexity: O(K)
 *
 * N = Length of the input array. K = input K.
 */
class Solution2 {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || K <= 0) {
            return new int[0][0];
        }
        if (K >= points.length) {
            return points;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (distance(b) - distance(a)));

        for (int[] p : points) {
            queue.offer(p);

            if (queue.size() > K) {
                queue.poll();
            }
        }

        int[][] result = new int[K][2];

        int i = 0;
        while (!queue.isEmpty()) {
            result[i++] = queue.poll();
        }

        return result;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}

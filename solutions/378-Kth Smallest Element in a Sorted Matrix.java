// LeetCode Question URL: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1524783/Java-or-Two-Solutions-or-Binary-Search-+-PriorityQueue

import java.util.*;

/**
 * Using PriorityQueue
 *
 * <pre>
 * Time Complexity:
 *      O(min(N,K)*log(min(N,K))) -> To add initial min(N,K) elements, as we are adding the elements individually.
 *                                   If we were adding all elements in one go, then the complexity would be O(min(N,K))
 *                                   Refer: https://stackoverflow.com/a/34697891
 *      O(2*(K-1)*log(min(N,K)) -> To poll K-1 elements and add next K-1 elements.
 * Total Time Complexity: O((min(N,K) + 2*(K-1)) * log(min(N,K)) = O(K * log(min(N,K))
 * </pre>
 *
 * Space Complexity: O(min(N, K))
 *
 * N = size of matrix. K = input value k.
 */
class Solution1 {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int n = matrix.length;
        if (k > n * n) {
            throw new NoSuchElementException("k is greater than number of elements in matrix");
        }
        if (k == 1) {
            return matrix[0][0];
        }
        if (k == n * n) {
            return matrix[n - 1][n - 1];
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));

        for (int i = 0; i < Math.min(n, k); i++) {
            queue.offer(new int[] { i, 0 });
        }
        while (k > 1) {
            int[] cur = queue.poll();
            if (cur[1] < n - 1) {
                cur[1]++;
                queue.offer(cur);
            }
            k--;
        }

        return matrix[queue.peek()[0]][queue.peek()[1]];
    }
}

/**
 * Using Binary Search
 *
 * Refer: Approach 2 in Solutions Tab.
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/
 *
 * Time Complexity: O(2*N * log(Max-Min))
 *
 * Space Complexity: O(1)
 *
 * N = Size of matrix (NOT total number of cells in the matrix). Max = Maximum
 * value in Matrix. Min = Minimum value in matrix.
 */
class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int n = matrix.length;
        if (k > n * n) {
            throw new NoSuchElementException("k is greater than number of elements in matrix");
        }
        if (k == 1) {
            return matrix[0][0];
        }
        if (k == n * n) {
            return matrix[n - 1][n - 1];
        }

        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1];

        while (start < end) {
            int mid = start + (end - start) / 2;
            int[] smallLargePair = { start, end };
            int count = countLessThanAndEqual(matrix, mid, smallLargePair);
            if (count == k) {
                return smallLargePair[0];
            }
            if (count < k) {
                start = smallLargePair[1]; // search higher
            } else {
                end = smallLargePair[0]; // search lower
            }
        }

        return start;
    }

    private int countLessThanAndEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length;
        int row = 0;
        int col = n - 1;
        while (row < n && col >= 0) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                col--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                row++;
                count += col + 1;
            }
        }
        return count;
    }
}

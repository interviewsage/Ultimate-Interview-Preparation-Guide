// LeetCode Question URL: https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/discuss/1527238/Java-or-TC:-O(M*log(NK))-or-BinarySearch+PriorityQueue-and-Constant-Space-Solutions

import java.util.*;

/**
 * Using Binary Search + PriorityQueue (Max Heap)
 *
 * Find the left most zero in each row (using binary search). Save the row index
 * and count combination in max heap of size k.
 *
 * <pre>
 * Time Complexity:
 * O(M * logN) --> To find the left most zero in each row
 * O(M * logK) --> To add and poll from PriorityQueue. K elements are maintained in the queue.
 * Total Time Complexity: O(M * log(NK))
 * </pre>
 *
 * Space Complexity: O(K) --> Used by MaxHeap
 *
 * M = Number of rows. N = Number of Columns.
 */
class Solution1 {
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || k < 0 || mat.length < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        // Max Heap
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]));

        for (int i = 0; i < mat.length; i++) {
            queue.offer(new int[] { i, getSoldierCount(mat[i]) });
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] result = new int[k];
        while (!queue.isEmpty()) {
            result[--k] = queue.poll()[0];
        }
        return result;
    }

    private int getSoldierCount(int[] row) {
        if (row[0] == 0) {
            return 0;
        }
        if (row[row.length - 1] == 1) {
            return row.length;
        }
        int start = 0;
        int end = row.length; // Number of solder can be maximum row.length. Thus end needs to be row.length

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (row[mid] == 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end;
    }
}

/**
 * Vertical (Column) Iteration
 *
 * In this solution iterate column by column. Add the row index if first zero in
 * that row is found.
 *
 * Time Complexity: O(M*N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of Columns.
 */
class Solution2 {
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || k < 0 || mat.length < k) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (k == 0) {
            return new int[0];
        }

        int rows = mat.length;
        int cols = mat[0].length;
        int[] result = new int[k];
        int idx = 0;

        for (int j = 0; j <= cols; j++) {
            for (int i = 0; i < rows; i++) {
                if ((j == cols || mat[i][j] == 0) && (j == 0 || mat[i][j - 1] == 1)) {
                    result[idx++] = i;
                    if (idx == k) {
                        return result;
                    }
                }
            }
        }

        return result;
    }
}

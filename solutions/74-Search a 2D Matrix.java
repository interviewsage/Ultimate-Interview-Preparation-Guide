// LeetCode Question URL: https://leetcode.com/problems/search-a-2d-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/search-a-2d-matrix/discuss/1511703/Java-or-TC:-O(log(R*C))-or-SC:-O(1)-or-Optimized-binary-search-solution

/**
 * Optimized binary search solution
 *
 * Time Complexity: O(log(R * C))
 *
 * Space Complexity: O(1)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }

        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (n == 0 || target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        if (m == 1 && n == 1) {
            return true;
        }

        int start = 0;
        int end = m * n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) {
                return true;
            }
            if (val < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }
}

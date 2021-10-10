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
            throw new IllegalArgumentException("Input is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int cols = matrix[0].length;
        int start = 0;
        int end = matrix.length * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int r = mid / cols;
            int c = mid % cols;
            if (target == matrix[r][c]) {
                return true;
            }
            if (target < matrix[r][c]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }
}

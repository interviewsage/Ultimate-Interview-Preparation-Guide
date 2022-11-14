// LeetCode Question URL: https://leetcode.com/problems/search-a-2d-matrix-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/1513112/Java-or-TC:-O(R+C)-or-SC:-O(1)-or-Optimal-solution-using-Elimination-Strategy

/**
 * Search from Top left corner.
 *
 * If target is less than top left corner, then we can safely reduce the column
 * index as all the below values in column will be greater than the target.
 *
 * Similarly if the target is more than the top left corner, then we can safely
 * increase the row index as all the left values in the row will be smaller than
 * the target.
 *
 * Time Complexity: O(R + C)
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
        if (n == 0) {
            return false;
        }

        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (target < matrix[i][j]) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }
}

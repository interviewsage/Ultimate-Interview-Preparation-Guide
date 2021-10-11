// LeetCode Question URL: https://leetcode.com/problems/set-matrix-zeroes/
// LeetCode Discuss URL: https://leetcode.com/problems/set-matrix-zeroes/discuss/1515437/Java-or-TC:-O(R*C)-or-SC:-O(1)-or-Optimized-In-Place-solution

/**
 * Optimized In-Place solution
 *
 * We can use the first cell of every row and column as a flag. This flag will
 * determine whether a row or a column has to be set to zero.
 *
 * Time Complexity: O(2 * R * C)
 *
 * Space Complexity: O(1)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 1 && cols == 1) {
            return;
        }

        boolean isFirstColZero = false;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                isFirstColZero = true;
            }
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 | matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (isFirstColZero) {
                matrix[i][0] = 0;
            }
        }
    }
}

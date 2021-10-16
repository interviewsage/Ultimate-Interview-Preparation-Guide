// LeetCode Question URL: https://leetcode.com/problems/01-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/01-matrix/discuss/1524749/Java-or-TC:-O(MN)-or-SC:-O(1)-or-Optimized-DP-Solution

/**
 * Space Optimized Dynamic Programming
 *
 * Refer: 1)
 * https://leetcode.com/problems/01-matrix/discuss/101051/Simple-Java-solution-beat-99-(use-DP)
 * 2)
 * https://leetcode.com/problems/01-matrix/discuss/101051/Simple-Java-solution-beat-99-(use-DP)/113437
 *
 * The first iteration is from top left corner to bottom right. In this
 * iteration we are checking Top and Left neighbors as they have been already
 * solved.
 *
 * The second iteration is from bottom right corner to top left corner. In this
 * iteration we are checking Bottom and Right neighbors and also compare them
 * with the result of the current cell from the previous iteration.
 *
 * Time Complexity: O(2 * M * N)
 *
 * Space Complexity: O(1) - Excluding the result space.
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int rows = mat.length;
        int cols = mat[0].length;
        if (rows == 1 && cols == 1) {
            return mat;
        }

        int[][] result = new int[rows][cols];
        // Confirm with interviewer: Can the sum of rows+cols be greater than
        // Integer.MAX_VALUE. Also check if at least once zero exists in the input
        // matrix. If it does not exist what should we return.

        // (rows + cols - 1) is the maximum possible distance in the matrix. Its the
        // distance been two diagonally opposite corners.
        int maxDistance = rows + cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                result[i][j] = maxDistance;
                if (i > 0) {
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if (j > 0) {
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (mat[i][j] == 0) {
                    continue;
                }
                if (i < rows - 1) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }

        return result;
    }
}

class Solution2 {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    // If the cell in original matrix is not 0, then set this result cell to
                    // Integer.MAX_VALUE. This will help to find the minimum in the following
                    // conditions.
                    result[i][j] = Integer.MAX_VALUE;
                    if (i > 0 && result[i - 1][j] != Integer.MAX_VALUE) {
                        result[i][j] = result[i - 1][j] + 1;
                    }
                    if (j > 0 && result[i][j - 1] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // Integer.MAX_VALUE check can be removed from below conditions if there is at
                // least one 0 in the given matrix
                if (i < rows - 1 && result[i + 1][j] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < cols - 1 && result[i][j + 1] != Integer.MAX_VALUE) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }

        return result;
    }
}
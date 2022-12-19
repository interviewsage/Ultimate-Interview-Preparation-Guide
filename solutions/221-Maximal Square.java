// LeetCode Question URL: https://leetcode.com/problems/maximal-square/
// LeetCode Discuss URL: https://leetcode.com/problems/maximal-square/discuss/1519235/Java-or-TC:-O(MN)-or-SC:-O(min(MN))-or-Space-optimized-DP-solution

/**
 * Similar Questions:
 *
 * 72. Edit Distance: https://leetcode.com/problems/edit-distance/
 *
 * 712. Minimum ASCII Delete Sum for Two Strings:
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * 583. Delete Operation for Two Strings:
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * 221. Maximal Square: https://leetcode.com/problems/maximal-square/
 */

/**
 * Dynamic Programming
 *
 * Refer: 1)
 * https://leetcode.com/problems/maximal-square/discuss/61803/C++-space-optimized-DP
 * 2)
 * https://leetcode.com/problems/maximal-square/discuss/61803/C++-space-optimized-DP/63371
 *
 * DP[i][j] = Maximal size (square = size*size) of the square that can be formed
 * ending at point (i,j).
 *
 * Time Complexity: O(R * C)
 *
 * Space Complexity: O(C)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution1 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }

        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }

        int[] dp = new int[cols + 1];
        int maxSquareSide = 0;

        for (int i = 1; i <= rows; i++) {
            int pre = 0;
            for (int j = 1; j <= cols; j++) {
                int cur = dp[j];
                if (matrix[i - 1][j - 1] == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] = Math.min(pre, Math.min(cur, dp[j - 1])) + 1;
                    maxSquareSide = Math.max(maxSquareSide, dp[j]);
                }
                pre = cur;
            }
        }

        return maxSquareSide * maxSquareSide;
    }
}

/**
 * Dynamic Programming
 *
 * Refer: 1)
 * https://leetcode.com/problems/maximal-square/discuss/61803/C++-space-optimized-DP
 * 2)
 * https://leetcode.com/problems/maximal-square/discuss/61803/C++-space-optimized-DP/63371
 *
 * DP[i][j] = Maximal size (square = size*size) of the square that can be formed
 * ending at point (i,j).
 *
 * Time Complexity: O(R * C)
 *
 * Space Complexity: O(min(R, C))
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution2 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }

        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }

        if (rows < cols) {
            return maximalSquareHelperWithReverse(matrix, cols, rows, false);
        } else {
            return maximalSquareHelperWithReverse(matrix, rows, cols, true);
        }
    }

    private int maximalSquareHelperWithReverse(char[][] matrix, int bigSide, int smallSide, boolean isColsSmall) {
        int[] dp = new int[smallSide + 1];
        int maxSquareSide = 0;

        for (int i = 1; i <= bigSide; i++) {
            int pre = 0;
            for (int j = 1; j <= smallSide; j++) {
                int cur = dp[j];
                char cellVal = isColsSmall ? matrix[i - 1][j - 1] : matrix[j - 1][i - 1];
                if (cellVal == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] = Math.min(pre, Math.min(cur, dp[j - 1])) + 1;
                    maxSquareSide = Math.max(maxSquareSide, dp[j]);
                }
                pre = cur;
            }
        }

        return maxSquareSide * maxSquareSide;
    }
}

class SolutionIgnore {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows < cols) {
            // return solveIfRowsAreLess(matrix, rows, cols);
            return maximalSquareHelper(matrix, cols, rows, false);
        } else {
            // return solveIfColsAreLess(matrix, rows, cols);
            return maximalSquareHelper(matrix, rows, cols, true);
        }
    }

    private int maximalSquareHelper(char[][] matrix, int big, int small, boolean isColsSmall) {
        int[] dp = new int[small + 1];
        int maxSide = 0;
        for (int j = 1; j <= big; j++) {
            int prev = dp[0]; // Since we have added a padding in-front, dp[0] will always be zero
            for (int i = 1; i <= small; i++) {
                int temp = dp[i];
                if ((isColsSmall && matrix[j - 1][i - 1] == '0') || (!isColsSmall && matrix[i - 1][j - 1] == '0')) {
                    dp[i] = 0;
                } else {
                    dp[i] = Math.min(prev, Math.min(dp[i], dp[i - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i]);
                }
                prev = temp;
            }
        }
        return maxSide * maxSide;
    }

    private int solveIfRowsAreLess(char[][] matrix, int rows, int cols) {
        int[] dp = new int[rows + 1];
        int maxSide = 0;
        for (int j = 1; j <= cols; j++) {
            int prev = dp[0]; // Since we have added a padding in-front, dp[0] will always be zero
            for (int i = 1; i <= rows; i++) {
                int temp = dp[i];
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i] = 0;
                } else {
                    dp[i] = Math.min(prev, Math.min(dp[i], dp[i - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i]);
                }
                prev = temp;
            }
        }
        return maxSide * maxSide;
    }

    private int solveIfColsAreLess(char[][] matrix, int rows, int cols) {
        int[] dp = new int[cols + 1];
        int maxSide = 0;
        for (int j = 1; j <= rows; j++) {
            int prev = dp[0]; // Since we have added a padding in-front, dp[0] will always be zero
            for (int i = 1; i <= cols; i++) {
                int temp = dp[i];
                if (matrix[j - 1][i - 1] == '0') {
                    dp[i] = 0;
                } else {
                    dp[i] = Math.min(prev, Math.min(dp[i], dp[i - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i]);
                }
                prev = temp;
            }
        }
        return maxSide * maxSide;
    }
}

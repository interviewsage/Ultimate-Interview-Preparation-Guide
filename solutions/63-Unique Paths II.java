// LeetCode Question URL: https://leetcode.com/problems/unique-paths-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/unique-paths-ii/discuss/1513891/Java-or-TC:-O(R*C)-or-SC:-O(min(RC))-or-Space-optimized-Dynamic-Programming-solution

/**
 * Dynamic Programming (Modifying the obstacleGrid array)
 *
 * Refer:
 * https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution/22632
 *
 * current cell = top cell + left cell. If there is an obstacle at current cell,
 * then current cell = 0;
 *
 * Time Complexity: O(R * C).
 *
 * Space Complexity = O(C).
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution1 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = obstacleGrid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        if (cols == 0 || obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }

        int[] dp = new int[cols];
        dp[0] = 1;
        for (int i = 0; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[0] = 0;
            }
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[cols - 1];
    }
}

/**
 * Dynamic Programming (Modifying the obstacleGrid array)
 *
 * Refer:
 * https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution/22632
 *
 * current cell = top cell + left cell. If there is an obstacle at current cell,
 * then current cell = 0;
 *
 * Time Complexity: O(R * C).
 *
 * Space Complexity = O(1). Modified the obstacleGrid.
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }
        if (rows == 1 && cols == 1) {
            return 1;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    if (i > 0 && j > 0) {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    } else if (i > 0) {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                    } else if (j > 0) {
                        obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                    } else {
                        obstacleGrid[i][j] = 1; // 0,0 case
                    }
                }
            }
        }

        return obstacleGrid[rows - 1][cols - 1];
    }
}

/**
 * Space optimized Dynamic Programming solution (Input grid not modified)
 *
 * Refer:
 * https://leetcode.com/problems/unique-paths-ii/discuss/23250/Short-JAVA-solution/22620
 *
 * current cell = top cell + left cell. If there is an obstacle at current cell,
 * then current cell = 0;
 *
 * Time Complexity: O(R * C).
 *
 * Space Complexity = O(min(R, C)). Either one column or one row is used for dp.
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution3 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }
        if (rows == 1 && cols == 1) {
            return 1;
        }

        if (rows >= cols) {
            return uniquePathHelper(obstacleGrid, rows, cols, true);
            // return solveIfColsAreLess(obstacleGrid, rows, cols);
        } else {
            return uniquePathHelper(obstacleGrid, cols, rows, false);
            // return solveIfRowsAreLess(obstacleGrid, rows, cols);
        }
    }

    private int uniquePathHelper(int[][] obstacleGrid, int big, int small, boolean isColsSmall) {
        int[] dp = new int[small];
        dp[0] = 1;

        for (int i = 0; i < big; i++) {
            if ((isColsSmall && obstacleGrid[i][0] == 1) || (!isColsSmall && obstacleGrid[0][i] == 1)) {
                dp[0] = 0;
            }
            for (int j = 1; j < small; j++) {
                if ((isColsSmall && obstacleGrid[i][j] == 1) || (!isColsSmall && obstacleGrid[j][i] == 1)) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] += dp[j - 1];
            }
        }

        return dp[small - 1];
    }

    private int solveIfColsAreLess(int[][] obstacleGrid, int rows, int cols) {
        int[] dp = new int[cols];
        dp[0] = 1;

        for (int i = 0; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[0] = 0;
            }
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                dp[j] += dp[j - 1];
            }
        }

        return dp[cols - 1];
    }

    private int solveIfRowsAreLess(int[][] obstacleGrid, int rows, int cols) {
        int[] dp = new int[rows];
        dp[0] = 1;

        for (int j = 0; j < cols; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0] = 0;
            }
            for (int i = 1; i < rows; i++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i] = 0;
                    continue;
                }
                dp[i] += dp[i - 1];
            }
        }

        return dp[rows - 1];
    }
}

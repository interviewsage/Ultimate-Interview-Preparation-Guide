// LeetCode Question URL: https://leetcode.com/problems/minimum-path-sum/
// LeetCode Discuss URL: https://leetcode.com/problems/minimum-path-sum/discuss/1513899/Java-or-TC:-O(R*C)-or-SC:-O(min(RC))-or-Space-optimized-Dynamic-Programming-solution

/**
 * Space optimized Dynamic Programming solution (Input grid not modified)
 *
 * <pre>
 * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j].
 * If j == 0, then dp[i][0] = dp[i-1][0] + grid[i][0].
 * If i == 0, then dp[0][j] = dp[0][j-1] + grid[0][j].
 * </pre>
 *
 * We can just use 1D array of size rows or columns.
 *
 * Time Complexity: O(R * C)
 *
 * Space Complexity : O(min(R, C))
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution1 {
    public int minPathSum(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 1 && cols == 1) {
            return grid[0][0];
        }

        if (cols <= rows) {
            return minPathSumHelper(grid, rows, cols, true);
            // return solveIfColsAreLess(grid, rows, cols);
        } else {
            return minPathSumHelper(grid, cols, rows, false);
            // return solveIfRowsAreLess(grid, rows, cols);
        }
    }

    private int minPathSumHelper(int[][] grid, int big, int small, boolean isColsSmall) {
        int[] dp = new int[small];

        // Process 1st small column/row
        dp[0] = grid[0][0];
        for (int j = 1; j < small; j++) {
            dp[j] = dp[j - 1] + (isColsSmall ? grid[0][j] : grid[j][0]);
        }
        // Process remaining columns/rows
        for (int i = 1; i < big; i++) {
            dp[0] += isColsSmall ? grid[i][0] : grid[0][i];
            for (int j = 1; j < small; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + (isColsSmall ? grid[i][j] : grid[j][i]);
            }
        }

        return dp[small - 1];
    }

    private int solveIfColsAreLess(int[][] grid, int rows, int cols) {
        int[] dp = new int[cols];

        // Process 1st row
        dp[0] = grid[0][0];
        for (int j = 1; j < cols; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        // Process remaining rows
        for (int i = 1; i < rows; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < cols; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        return dp[cols - 1];
    }

    private int solveIfRowsAreLess(int[][] grid, int rows, int cols) {
        int[] dp = new int[rows];

        // Process 1st column
        dp[0] = grid[0][0];
        for (int j = 1; j < rows; j++) {
            dp[j] = dp[j - 1] + grid[j][0];
        }
        // Process remaining columns
        for (int i = 1; i < cols; i++) {
            dp[0] += grid[0][i];
            for (int j = 1; j < rows; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[j][i];
            }
        }

        return dp[rows - 1];
    }
}

/**
 * Dynamic Programming (Input grid MODIFIED)
 *
 * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]. If j == 0, then
 * dp[i][0] = dp[i-1][0] + grid[i][0]. If i == 0, then dp[0][j] = dp[0][j-1] +
 * grid[0][j]
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity : O(1)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if (rows == 0 || cols == 0) {
            return 0;
        }

        // j == 0 (First Column)
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // i == 0 (First Row)
        for (int j = 1; j < cols; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[rows - 1][cols - 1];
    }
}
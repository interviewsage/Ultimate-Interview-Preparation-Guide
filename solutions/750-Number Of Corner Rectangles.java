// LeetCode Question URL: https://leetcode.com/problems/number-of-corner-rectangles/
// LeetCode Discuss URL: https://leetcode.com/problems/number-of-corner-rectangles/discuss/1527239/Java-or-TC:-O(max(MN)*(min(MN))2)-or-Space-Optimized-and-Sparse-Matrix-FollowUp

/**
 * To find a corner rectangle, the idea is to fix two rows (or two columns)
 * first, then check column by column (or row by row) to find "1" on both rows.
 * Say you find n pairs, then just pick any 2 of those to form a corner
 * rectangle. Total Number of corner rectangles for these 2 rows will be C(N,2)
 * (Combination)
 *
 * Initially code R^2 * C solution and explain the optimized solution. If
 * required then code the optimized solution.
 *
 * Time Complexity: O(R^2 * C)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int countCornerRectangles(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows <= 1) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols <= 1) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                int count = 0;
                for (int k = 0; k < cols; k++) {
                    count += grid[i][k] & grid[j][k];
                }
                result += count * (count - 1) / 2;
            }
        }

        return result;
    }
}

/**
 * To find a corner rectangle, the idea is to fix two rows (or two columns)
 * first, then check column by column (or row by row) to find "1" on both rows.
 * Say you find n pairs, then just pick any 2 of those to form a corner
 * rectangle. Total Number of corner rectangles for these 2 rows will be C(N,2)
 * (Combination)
 *
 * Initially code R^2 * C solution and explain the optimized solution. If
 * required then code the optimized solution.
 *
 * Time Complexity: O(max(M,N) * (min(M,N))^2)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of cols.
 */
class Solution2 {
    public int countCornerRectangles(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows <= 1) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols <= 1) {
            return 0;
        }

        if (rows < cols) {
            return countCornerRectanglesHelper(grid, rows, cols, true);
        } else {
            return countCornerRectanglesHelper(grid, cols, rows, false);
        }
    }

    private int countCornerRectanglesHelper(int[][] grid, int small, int big, boolean isRowsSmall) {
        int result = 0;

        for (int i = 0; i < small - 1; i++) {
            for (int j = i + 1; j < small; j++) {
                int count = 0;
                for (int k = 0; k < big; k++) {
                    if (isRowsSmall) {
                        count += grid[i][k] & grid[j][k];
                    } else {
                        count += grid[k][i] & grid[k][j];
                    }
                }
                result += count * (count - 1) / 2;
            }
        }

        return result;
    }
}

/**
 * This solution is optimized for Sparse Matrix
 *
 * Refer to Idea III in this link
 * https://leetcode.com/problems/number-of-corner-rectangles/discuss/110200/Summary-of-three-solutions-based-on-three-different-ideas
 *
 * Time Complexity: O(M * N^2) --> Here we can also run the other loop on cols
 * and inner loops on rows. In that case, the time complexity will be O(M^2 * N)
 *
 * Space Complexity: O(N^2) --> This can be optimized by using a hashmap
 *
 * M = Number of rows. N = Number of cols.
 */
class Solution3 {
    public int countCornerRectangles(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows <= 1) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols <= 1) {
            return 0;
        }

        int[][] dp = new int[cols][cols];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int k = j + 1; k < cols; k++) {
                    if (grid[i][k] == 0) {
                        continue;
                    }
                    result += dp[j][k];
                    dp[j][k]++;
                }
            }
        }

        return result;
    }
}

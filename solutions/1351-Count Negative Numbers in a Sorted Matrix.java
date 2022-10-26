// LeetCode Question URL: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
// LeetCode Discuss URL:

/**
 * Eliminate either one row or one column in each iteration.
 *
 * Time Complexity: O(M + N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution {
    public int countNegatives(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        if (grid[0][0] < 0) {
            return rows * cols;
        }
        if (grid[rows - 1][cols - 1] >= 0) {
            return 0;
        }

        int negativeCount = 0;
        int r = 0;
        int c = cols - 1;
        while (r < rows && c >= 0) {
            if (grid[r][c] < 0) {
                negativeCount += rows - r;
                c--;
            } else {
                r++;
            }
        }

        return negativeCount;
    }
}

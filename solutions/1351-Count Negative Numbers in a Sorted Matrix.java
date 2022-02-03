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
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] < 0) {
            return rows * cols;
        }
        if (grid[rows - 1][cols - 1] >= 0) {
            return 0;
        }

        int i = 0;
        int j = cols - 1;
        int count = 0;

        while (i < rows && j >= 0) {
            if (grid[i][j] < 0) {
                count += rows - i;
                j--;
            } else {
                i++;
            }
        }

        return count;
    }
}

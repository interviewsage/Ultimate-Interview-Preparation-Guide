// LeetCode Question URL: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
// LeetCode Discuss URL:

/**
 * DFS + Memoization
 *
 * Time Complexity: O(2*R*C). Due to memoization, every cell in DP will be
 * calculated once.
 *
 * Space Complexity: O(2*R*C)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = Math.max(result, dfsHelper(matrix, dp, i, j));
            }
        }

        return result;
    }

    private int dfsHelper(int[][] matrix, int[][] dp, int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        int len = 0;
        for (int[] d : DIRS) {
            int i = x + d[0];
            int j = y + d[1];
            if (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length && matrix[i][j] > matrix[x][y]) {
                len = Math.max(len, dfsHelper(matrix, dp, i, j));
            }
        }
        dp[x][y] = len + 1;
        return dp[x][y];
    }
}
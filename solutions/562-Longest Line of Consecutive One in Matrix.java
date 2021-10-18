// LeetCode Question URL: https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/discuss/1527240/Java-or-TC:-O(MN)-or-SC:-O(min(M-N))-OR-O(1)-or-Two-Space-Optimized-Solutions

/**
 * Space Optimized Dynamic Programming solution
 *
 * For each cell we need 4 counts. Horizontal, Vertical, Diagonal and
 * Anti-diagonal.
 *
 * <pre>
 * DP equations:
 * if mat[i][j] != 1 ==> Then DP[i][j][*] = 0
 * if mat[i][j] == 1 ==> Then:
 * DP[i][j][Horizontal] = DP[i][j-1][Horizontal]+1
 * DP[i][j][Vertical] = DP[i-1][j][Vertical]+1
 * DP[i][j][Diagonal] = DP[i-1][j-1][Diagonal]+1
 * DP[i][j][Anti-diagonal] = DP[i-1][j+1][Anti-diagonal]+1
 * </pre>
 *
 * To save auxiliary space, we just need one row or column and previous diagonal
 * value
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(4 * (min(M, N) + 2)) = O(min(M, N))
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    public int longestLine(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int rows = mat.length;
        int cols = mat[0].length;

        if (cols < rows) {
            return helper(mat, rows, cols, true);
            // return solveIfColsAreLess(mat, rows, cols);
        } else {
            return helper(mat, cols, rows, false);
            // return solveIfRowsAreLess(mat, rows, cols);
        }
    }

    private int helper(int[][] mat, int big, int small, boolean isColsSmall) {
        // 0 -> Horizontal
        // 1 -> Vertical
        // 2 -> Diagonal
        // 3 -> Anti-diagonal
        int[][] lines = new int[small + 2][4];

        int result = 0;

        for (int i = 0; i < big; i++) {
            int preD = 0;

            for (int j = 1; j <= small; j++) {
                int tempD = lines[j][2];

                if ((isColsSmall && mat[i][j - 1] != 1) || (!isColsSmall && mat[j - 1][i] != 1)) {
                    lines[j] = new int[4];
                } else {
                    // 0 -> Horizontal
                    lines[j][0] = isColsSmall ? lines[j - 1][0] + 1 : lines[j][0] + 1;
                    // 1 -> Vertical
                    lines[j][1] = isColsSmall ? lines[j][1] + 1 : lines[j - 1][1] + 1;
                    // 2 -> Diagonal
                    lines[j][2] = preD + 1;
                    // 3 -> Anti-diagonal
                    lines[j][3] = lines[j + 1][3] + 1;

                    for (int n : lines[j]) {
                        result = Math.max(result, n);
                    }
                }

                preD = tempD;
            }
        }

        return result;
    }

    private int solveIfColsAreLess(int[][] mat, int rows, int cols) {
        // 0 -> Horizontal
        // 1 -> Vertical
        // 2 -> Diagonal
        // 3 -> Anti-diagonal
        int[][] lines = new int[cols + 2][4];

        int result = 0;

        for (int i = 0; i < rows; i++) {
            int preD = 0;

            for (int j = 1; j <= cols; j++) {
                int tempD = lines[j][2];

                if (mat[i][j - 1] != 1) {
                    lines[j] = new int[4];
                } else {
                    // 0 -> Horizontal
                    lines[j][0] = lines[j - 1][0] + 1;
                    // 1 -> Vertical
                    lines[j][1] = lines[j][1] + 1;
                    // 2 -> Diagonal
                    lines[j][2] = preD + 1;
                    // 3 -> Anti-diagonal
                    lines[j][3] = lines[j + 1][3] + 1;

                    for (int n : lines[j]) {
                        result = Math.max(result, n);
                    }
                }

                preD = tempD;
            }
        }

        return result;
    }

    private int solveIfRowsAreLess(int[][] mat, int rows, int cols) {
        // 0 -> Horizontal
        // 1 -> Vertical
        // 2 -> Diagonal
        // 3 -> Anti-diagonal
        int[][] lines = new int[rows + 2][4];

        int result = 0;

        for (int i = 0; i < cols; i++) {
            int preD = 0;

            for (int j = 1; j <= rows; j++) {
                int tempD = lines[j][2];

                if (mat[j - 1][i] != 1) {
                    lines[j] = new int[4];
                } else {
                    // 0 -> Horizontal
                    lines[j][0] = lines[j][0] + 1;
                    // 1 -> Vertical
                    lines[j][1] = lines[j - 1][1] + 1;
                    // 2 -> Diagonal
                    lines[j][2] = preD + 1;
                    // 3 -> Anti-diagonal
                    lines[j][3] = lines[j + 1][3] + 1;

                    for (int n : lines[j]) {
                        result = Math.max(result, n);
                    }
                }

                preD = tempD;
            }
        }

        return result;
    }
}

/**
 * Traverse every horizontal, vertical, diagonal and anti-diagonal lines once.
 *
 * Time Complexity: O(4 * M * N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    public int longestLine(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int rows = mat.length;
        int cols = mat[0].length;

        int result = 0;

        // Horizontal
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
            }
        }

        // Vertical
        for (int j = 0; j < cols; j++) {
            int count = 0;
            for (int i = 0; i < rows; i++) {
                if (mat[i][j] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
            }
        }

        // Diagonal
        for (int i = rows - 1; i >= 0; i--) {
            int r = i;
            int c = 0;
            int count = 0;
            while (r < rows && c < cols) {
                if (mat[r][c] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
                r++;
                c++;
            }
        }
        for (int i = 1; i < cols; i++) {
            int r = 0;
            int c = i;
            int count = 0;
            while (r < rows && c < cols) {
                if (mat[r][c] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
                r++;
                c++;
            }
        }

        // Anti-Diagonal
        for (int i = 0; i < cols; i++) {
            int r = 0;
            int c = i;
            int count = 0;
            while (r < rows && c >= 0) {
                if (mat[r][c] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
                r++;
                c--;
            }
        }
        for (int i = 1; i < rows; i++) {
            int r = i;
            int c = cols - 1;
            int count = 0;
            while (r < rows && c >= 0) {
                if (mat[r][c] == 1) {
                    count++;
                    result = Math.max(result, count);
                } else {
                    count = 0;
                }
                r++;
                c--;
            }
        }

        return result;
    }
}

/**
 * Extra Solutions
 *
 * <pre>
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/discuss/102296/Simple-and-Concise-Java-Solution-(Easy-to-Understand-O(m%2Bn)-space)
 * Time: O(M*N)
 * Space: O(M+N)
 * Unique identifier for each horizontal, vertical, diagonal and anti-diagonal line.
 * </pre>
 *
 * <pre>
 * https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/discuss/178335/O(1)-space-O(mn)-time-solution-Nice-invariant-solution-no-DP-required
 * Time: O(5*M*N)
 * Space: O(1)
 * Finding the start of each line and then exploring it.
 * </pre>
 */

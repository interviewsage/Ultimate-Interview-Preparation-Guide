// LeetCode Question URL: https://leetcode.com/problems/spiral-matrix-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/spiral-matrix-ii/discuss/1511479/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Multiple-optimized-ways-to-solve-this-question

/**
 * Traverse Right -> Down -> Left -> Up
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1) excluding the result space.
 */
class Solution1 {
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }

        int rowTop = 0;
        int rowBottom = n - 1;
        int colLeft = 0;
        int colRight = n - 1;
        int num = 1;

        while (rowTop <= rowBottom && colLeft <= colRight) {
            for (int i = colLeft; i <= colRight; i++) {
                result[rowTop][i] = num++;
            }
            rowTop++;

            for (int i = rowTop; i <= rowBottom; i++) {
                result[i][colRight] = num++;
            }
            colRight--;

            if (rowTop <= rowBottom) {
                for (int i = colRight; i >= colLeft; i--) {
                    result[rowBottom][i] = num++;
                }
                rowBottom--;
            }

            if (colLeft <= colRight) {
                for (int i = rowBottom; i >= rowTop; i--) {
                    result[i][colLeft] = num++;
                }
                colLeft++;
            }
        }

        return result;
    }
}

/**
 * Using Switch-Case: Traverse Right -> Down -> Left -> Up
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1) excluding the result space.
 */
class Solution2 {
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int num = 1;
        int dir = 0;

        while (top <= bottom && left <= right) {
            switch (dir) {
                case 0: // Left
                    for (int i = left; i <= right; i++) {
                        result[top][i] = num++;
                    }
                    top++;
                    break;
                case 1: // Down
                    for (int i = top; i <= bottom; i++) {
                        result[i][right] = num++;
                    }
                    right--;
                    break;
                case 2: // Right
                    for (int i = right; i >= left; i--) {
                        result[bottom][i] = num++;
                    }
                    bottom--;
                    break;
                case 3: // Up
                    for (int i = bottom; i >= top; i--) {
                        result[i][left] = num++;
                    }
                    left++;
            }
            dir = (dir + 1) % 4;
        }

        return result;
    }
}

/**
 * Using 2D Directions array to calculate the next valid position. Traverse
 * Right -> Down -> Left -> Up
 *
 * <pre>
 * Refer Approach 2 in Solutions: https://leetcode.com/problems/spiral-matrix-ii/solution/#approach-2-optimized-spiral-traversal
 * </pre>
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1) excluding the result space.
 */
class Solution3 {
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }

        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int row = 0;
        int col = 0;
        int dirIdx = 0;
        int num = 1;

        while (num <= n * n) {
            result[row][col] = num++;
            row += dirs[dirIdx][0];
            col += dirs[dirIdx][1];

            if (row < 0 || row >= n || col < 0 || col >= n || result[row][col] != 0) {
                // Moving back from invalid position
                row -= dirs[dirIdx][0];
                col -= dirs[dirIdx][1];
                // Going to next dir
                dirIdx = (dirIdx + 1) % 4;
                // Updating row and col to valid next position
                row += dirs[dirIdx][0];
                col += dirs[dirIdx][1];
            }
        }

        return result;
    }
}

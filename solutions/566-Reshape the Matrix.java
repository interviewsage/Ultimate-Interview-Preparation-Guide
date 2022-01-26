// LeetCode Question URL: https://leetcode.com/problems/reshape-the-matrix/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(M*N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat == null || mat.length == 0 || mat.length * mat[0].length != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];
        int row = 0;
        int col = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                result[row][col++] = mat[i][j];
                if (col == c) {
                    row++;
                    col = 0;
                }
            }
        }

        return result;
    }
}

/**
 * Time Complexity: O(M*N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat == null || mat.length == 0 || mat.length * mat[0].length != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];
        int idx = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                result[idx / c][idx % c] = mat[i][j];
                idx++;
            }
        }

        return result;
    }
}

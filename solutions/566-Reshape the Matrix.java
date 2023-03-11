// LeetCode Question URL: https://leetcode.com/problems/reshape-the-matrix/
// LeetCode Discuss URL:

/**
 * Refer to learn about Matlab reshape function: https://www.mathworks.com/help/matlab/ref/reshape.html
 */

/**
 * Time Complexity: O(M*N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat == null || r < 0 || c < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int origRows = mat.length;
        int numElementsInOrig = origRows == 0 ? 0 : origRows * mat[0].length;
        if (numElementsInOrig != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];
        if (numElementsInOrig == 0) {
            return result;
        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < origRows; i++) {
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
        if (mat == null || r < 0 || c < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int origRows = mat.length;
        int numElementsInOrig = origRows == 0 ? 0 : origRows * mat[0].length;
        if (numElementsInOrig != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];
        if (numElementsInOrig == 0) {
            return result;
        }

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

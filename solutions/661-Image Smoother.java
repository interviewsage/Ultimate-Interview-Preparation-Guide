// LeetCode Question URL: https://leetcode.com/problems/image-smoother/
// LeetCode Discuss URL: https://leetcode.com/problems/image-smoother/discuss/1520730/Java-or-TC:-O(MN)-or-SC:-O(1)-or-Optimal-in-place-solution-without-creating-a-new-matrix

/**
 * Similar to "289. Game of Life" (https://leetcode.com/problems/game-of-life/)
 *
 * Constant Space Solution. Using input array to store the average
 *
 * This solution can be modified to work if numbers are upto 2^16 - 1 (65,535).
 *
 * Time Complexity: O(8*M*N + M*N) = O(M*N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public int[][] imageSmoother(int[][] img) {
        if (img == null) {
            throw new IllegalArgumentException("Input image is null");
        }

        int rows = img.length;
        if (rows == 0) {
            return img;
        }
        int cols = img[0].length;
        if (cols == 0 || (rows == 1 && cols == 1)) {
            return img;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = img[i][j];
                int count = 1;
                for (int[] d : DIRS) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x >= 0 && y >= 0 && x < rows && y < cols) {
                        sum += img[x][y] & 0xFF;
                        count++;
                    }
                }
                img[i][j] |= (sum / count) << 8;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                img[i][j] >>>= 8;
            }
        }

        return img;
    }
}

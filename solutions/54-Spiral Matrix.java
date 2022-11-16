// LeetCode Question URL: https://leetcode.com/problems/spiral-matrix/
// LeetCode Discuss URL: https://leetcode.com/problems/spiral-matrix/discuss/1511476/Java-or-TC:-O(M*N)-or-SC:-O(1)-or-Optimized-solution-using-Switch-Case

import java.util.*;

/**
 * Traverse Right -> Down -> Left -> Up
 *
 * Refer:
 * https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution
 *
 * Time Complexity: O(M*N). Every element is visited once.
 *
 * Space Complexity: O(1) excluding the result space.
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }

        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) {
            return result;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return result;
        }

        int rowTop = 0;
        int rowBottom = rows - 1;
        int colLeft = 0;
        int colRight = cols - 1;

        while (rowTop <= rowBottom && colLeft <= colRight) {
            for (int i = colLeft; i <= colRight; i++) {
                result.add(matrix[rowTop][i]);
            }
            rowTop++;

            for (int i = rowTop; i <= rowBottom; i++) {
                result.add(matrix[i][colRight]);
            }
            colRight--;

            if (rowTop <= rowBottom) {
                for (int i = colRight; i >= colLeft; i--) {
                    result.add(matrix[rowBottom][i]);
                }
                rowBottom--;
            }

            if (colLeft <= colRight) {
                for (int i = rowBottom; i >= rowTop; i--) {
                    result.add(matrix[i][colLeft]);
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
 * <pre>
 * Refer: https://leetcode.com/problems/spiral-matrix/discuss/20599/Super-Simple-and-Easy-to-Understand-Solution/213614
 * </pre>
 *
 * Time Complexity: O(M*N). Every element is visited once.
 *
 * Space Complexity: O(1) excluding the result space.
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int dir = 0;

        while (top <= bottom && left <= right) {
            switch (dir) {
                case 0: // Right
                    for (int i = left; i <= right; i++) {
                        result.add(matrix[top][i]);
                    }
                    top++;
                    break;
                case 1: // Down
                    for (int i = top; i <= bottom; i++) {
                        result.add(matrix[i][right]);
                    }
                    right--;
                    break;
                case 2: // Left
                    for (int i = right; i >= left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    bottom--;
                    break;
                case 3: // Top
                    for (int i = bottom; i >= top; i--) {
                        result.add(matrix[i][left]);
                    }
                    left++;
            }
            dir = (dir + 1) % 4;
        }

        return result;
    }
}

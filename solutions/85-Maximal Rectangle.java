// LeetCode Question URL: https://leetcode.com/problems/maximal-rectangle/
// LeetCode Discuss URL: https://leetcode.com/problems/maximal-rectangle/discuss/1519263/Java-or-TC:-O(RC)-or-SC:-O(min(RC))-or-Optimal-Stack-solution

import java.util.*;

/**
 * This solution is converting the input matrix row by row (OR column by column)
 * to Largest Rectangle in a Histogram.
 *
 * For each row (OR column) cumulative height is calculated. Then use stack to
 * save the increasing height index.
 *
 * Time Complexity: O(R * C). Each element is added to stack once and popped
 * from stack once.
 *
 * Space Complexity: O(min(R,C)). We will either store a row or a column
 *
 * R = Number of rows in the matrix. C = Number of columns in the matrix.
 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (cols < rows) {
            // return solveIfColsAreLess(matrix, rows, cols);
            return maximalRectangleHelper(matrix, rows, cols, true);
        } else {
            // return solveIfRowsAreLess(matrix, rows, cols);
            return maximalRectangleHelper(matrix, cols, rows, false);
        }
    }

    private int maximalRectangleHelper(char[][] matrix, int big, int small, boolean isColsSmall) {
        int[] heights = new int[small];
        int largestRectangle = 0;
        for (int i = 0; i < big; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= small; j++) {
                if (j < small) {
                    if (isColsSmall) {
                        heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
                    } else {
                        heights[j] = matrix[j][i] == '0' ? 0 : heights[j] + 1;
                    }

                }
                while (!stack.isEmpty() && (j == small || heights[stack.peek()] >= heights[j])) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    largestRectangle = Math.max(largestRectangle, (j - 1 - left) * h);
                }
                stack.push(j);
            }
        }
        return largestRectangle;
    }

    private int solveIfColsAreLess(char[][] matrix, int rows, int cols) {
        int[] heights = new int[cols];
        int largestRectangle = 0;
        for (int i = 0; i < rows; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= cols; j++) {
                if (j < cols) {
                    heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
                }
                while (!stack.isEmpty() && (j == cols || heights[stack.peek()] >= heights[j])) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    largestRectangle = Math.max(largestRectangle, (j - 1 - left) * h);
                }
                stack.push(j);
            }
        }
        return largestRectangle;
    }

    private int solveIfRowsAreLess(char[][] matrix, int rows, int cols) {
        int[] heights = new int[rows];
        int largestRectangle = 0;
        for (int i = 0; i < cols; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= rows; j++) {
                if (j < rows) {
                    heights[j] = matrix[j][i] == '0' ? 0 : heights[j] + 1;
                }
                while (!stack.isEmpty() && (j == rows || heights[stack.peek()] >= heights[j])) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    largestRectangle = Math.max(largestRectangle, (j - 1 - left) * h);
                }
                stack.push(j);
            }
        }
        return largestRectangle;
    }
}

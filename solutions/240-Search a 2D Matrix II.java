// LeetCode Question URL: https://leetcode.com/problems/search-a-2d-matrix-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/1513112/Java-or-TC:-O(R+C)-or-SC:-O(1)-or-Optimal-solution-using-Elimination-Strategy

/**
 * Search from Top left corner.
 *
 * If target is less than top left corner, then we can safely reduce the column
 * index as all the below values in column will be greater than the target.
 *
 * Similarly if the target is more than the top left corner, then we can safely
 * increase the row index as all the left values in the row will be smaller than
 * the target.
 *
 * Time Complexity: O(R + C)
 *
 * Space Complexity: O(1)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }
}

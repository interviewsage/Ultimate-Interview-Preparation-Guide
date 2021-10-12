// LeetCode Question URL: https://leetcode.com/problems/battleships-in-a-board/
// LeetCode Discuss URL: https://leetcode.com/problems/battleships-in-a-board/discuss/1517529/Java-or-TC:-O(MN)-or-SC:-O(1)-or-One-Pass-Constant-Space-(Input-board-is-NOT-modified)

/**
 * One-Pass Constant-Space solution. Input board is not modified
 *
 * Going over all cells, we can count only those that are the "last" cell of the
 * battleship. Last cell will be defined as the most bottom-right cell. We can
 * check for last cells by only counting cells that do not have an 'X' to the
 * right and do not have an 'X' below them.
 *
 * Refer:
 * https://leetcode.com/problems/battleships-in-a-board/discuss/90902/Simple-Java-Solution
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows in the board. N = Number of columns in the board.
 */
class Solution {
    public int countBattleships(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int rows = board.length;
        int cols = board[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X' && (j == cols - 1 || board[i][j + 1] == '.')
                        && (i == rows - 1 || board[i + 1][j] == '.')) {
                    count++;
                }
            }
        }

        return count;
    }
}

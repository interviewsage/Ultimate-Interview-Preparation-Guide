// LeetCode Question URL: https://leetcode.com/problems/word-search/
// LeetCode Discuss URL: https://leetcode.com/problems/word-search/discuss/1520705/Java-or-TC%3A-O(RC*(3L))-or-SC%3A-O(L)-or-Optimal-DFS-solution-without-visited-matrix

/**
 * For each char, perform Depth-First Search in all four directions.
 *
 * Refer:
 * https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space.
 *
 * <pre>
 * Time Complexity:
 * 1. If L > R*C ==> TC = O(1)
 * 2. If L <= R*C ==> TC = O(R*C * 4*(3^L))
 *      3^L ==> For the dfsHelper function, first time we have at most 4 directions
 *              to explore, but the choices are reduced to 3 (since no need to go back to the
 *              cell from where we came). Therefore, in the worst case, the total number of
 *              calls to dfsHelper will be 3^L
 *
 * Space Complexity:
 * 1. If L > R*C ==> SC = O(1)
 * 2. If L <= R*C ==> SC = O(L)
 * </pre>
 *
 * R = Number of rows. C = Number of columns. L = Length of word.
 */
class Solution {
    private static final int[][] DIRS = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;
        if (rows * cols < word.length()) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfsHelper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfsHelper(char[][] board, String word, int x, int y, int wIdx) {
        if (wIdx == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(wIdx)) {
            return false;
        }

        board[x][y] = '#';

        for (int[] d : DIRS) {
            if (dfsHelper(board, word, x + d[0], y + d[1], wIdx + 1)) {
                board[x][y] = word.charAt(wIdx);
                return true;
            }
        }

        board[x][y] = word.charAt(wIdx);
        return false;
    }
}

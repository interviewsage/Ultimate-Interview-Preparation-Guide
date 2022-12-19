// LeetCode Question URL: https://leetcode.com/problems/word-search/
// LeetCode Discuss URL: https://leetcode.com/problems/word-search/discuss/1520705/Java-or-TC%3A-O(RC*(3L))-or-SC%3A-O(L)-or-Optimal-DFS-solution-without-visited-matrix

import java.util.*;

/**
 * For each char, perform Depth-First Search in all four directions.
 *
 * Refer:
 * https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space.
 *
 * Refer why memoization does not work for this solution:
 * https://leetcode.com/problems/word-search/discuss/1653308/Some-Thoughts-about-the-Follow-Up:-Pruning
 *
 * <pre>
 * Time Complexity:
 * 1. If L > R*C ==> TC = O(1)
 * 2. If L <= R*C ==> TC = O(R*C * 4*(3^(L-1)))
 *      3^L ==> For the dfsHelper function, first time we have at most 4 directions
 *              to explore, but the choices are reduced to 3 (since no need to go back to the
 *              cell from where we came). Therefore, in the worst case, the total number of
 *              calls to dfsHelper will be 4 * 3^(L-1)
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
        if (board == null || word == null || word.length() == 0) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int rows = board.length;
        if (rows == 0) {
            return false;
        }
        int cols = board[0].length;
        int wordLen = word.length();
        // Not enough chars in board
        if (rows * cols < word.length()) {
            return false;
        }

        Map<Character, Integer> boardCharCountMap = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = board[i][j];
                boardCharCountMap.put(c, boardCharCountMap.getOrDefault(c, 0) + 1);
            }
        }

        Map<Character, Integer> wordCharCountMap = new HashMap<>();
        for (int i = 0; i < wordLen; i++) {
            char c = word.charAt(i);
            int countInWord = wordCharCountMap.getOrDefault(c, 0) + 1;
            if (boardCharCountMap.getOrDefault(c, 0) < countInWord) {
                // Not enough specific character in board
                return false;
            }
            wordCharCountMap.put(c, countInWord);
        }

        // Reducing the number of initial calls to existDfsHelper by searching in
        // reverse
        boolean searchInReverse = false;
        if (boardCharCountMap.get(word.charAt(0)) > boardCharCountMap.get(word.charAt(wordLen - 1))) {
            searchInReverse = true;
        }

        char firstChar = word.charAt(searchInReverse ? wordLen - 1 : 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == firstChar && existDfsHelper(board, i, j, word, 0, searchInReverse)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existDfsHelper(char[][] board, int r, int c, String word, int wIdx, boolean searchInReverse) {
        int wordLen = word.length();
        if (wIdx == wordLen - 1) {
            return true;
        }

        char curChar = board[r][c];
        board[r][c] = '#';
        char nextChar = word.charAt(searchInReverse ? (wordLen - 1 - wIdx - 1) : (wIdx + 1));
        boolean result = false;

        for (int[] d : DIRS) {
            int x = r + d[0];
            int y = c + d[1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == nextChar
                    && existDfsHelper(board, x, y, word, wIdx + 1, searchInReverse)) {
                result = true;
                break;
            }
        }

        board[r][c] = curChar;
        return result;
    }
}

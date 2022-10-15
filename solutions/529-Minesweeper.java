// LeetCode Question URL: https://leetcode.com/problems/minesweeper/
// LeetCode Discuss URL: https://leetcode.com/problems/minesweeper/discuss/1524772/Java-or-TC:-O(MN)-or-SC:-O(M+N)-or-BFS-+-DFS-Solutions

import java.util.*;

/**
 * BFS
 *
 * Time Complexity: O(8 * M * N) = O(M * N)
 *
 * Space Complexity: O(M + N) --> Last level contains 2M+2N cells. Thus it can
 * grow maximum to 2M+2N.
 *
 * M = Number of rows. N = Number columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || click == null) {
            throw new IllegalArgumentException("Input is null");
        }

        if (board[click[0]][click[1]] != 'M' && board[click[0]][click[1]] != 'E') {
            return board;
        }
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int mines = getMinesCount(board, click[0], click[1]);
        if (mines != 0) {
            board[click[0]][click[1]] = (char) (mines + '0');
            return board;
        }

        board[click[0]][click[1]] = 'B';
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(click);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int r = cur[0] + d[0];
                int c = cur[1] + d[1];
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'E') {
                    continue;
                }

                mines = getMinesCount(board, r, c);
                if (mines != 0) {
                    board[r][c] = (char) (mines + '0');
                    continue;
                }

                board[r][c] = 'B';
                queue.offer(new int[] { r, c });
            }
        }

        return board;
    }

    private int getMinesCount(char[][] board, int x, int y) {
        int mines = 0;
        for (int[] d : DIRS) {
            int r = x + d[0];
            int c = y + d[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}

/**
 * DFS
 *
 * Time Complexity: O(8 * M * N) = O(M * N)
 *
 * Space Complexity: O(M * N)
 *
 * M = Number of rows. N = Number columns.
 */
class Solution2 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || click == null) {
            throw new IllegalArgumentException("Input is null");
        }

        if (board[click[0]][click[1]] != 'M' && board[click[0]][click[1]] != 'E') {
            return board;
        }
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        revealBoard(board, click[0], click[1]);
        return board;
    }

    private void revealBoard(char[][] board, int x, int y) {
        int mines = getMinesCount(board, x, y);
        if (mines != 0) {
            board[x][y] = (char) (mines + '0');
            return;
        }

        board[x][y] = 'B';

        for (int[] d : DIRS) {
            int r = x + d[0];
            int c = y + d[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'E') {
                revealBoard(board, x + d[0], y + d[1]);
            }
        }
    }

    private int getMinesCount(char[][] board, int x, int y) {
        int mines = 0;
        for (int[] d : DIRS) {
            int r = x + d[0];
            int c = y + d[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}

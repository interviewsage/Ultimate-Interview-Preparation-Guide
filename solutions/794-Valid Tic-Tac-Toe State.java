// LeetCode Question URL: https://leetcode.com/problems/valid-tic-tac-toe-state/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-tic-tac-toe-state/discuss/1517556/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Space-Optimized-(NO-extra-arrays-used-to-store-the-scores)

/**
 * Space Optimized solution (True constant space solution)
 *
 * Calculate net score of each row, column and diagonal. Here the score of each
 * row, column and diagonal is stored in an int. For Player1 add 1 and for
 * Player2 add -1.
 *
 * <pre>
 * Time Complexity:
 * Every non-diagonal element is visited 2 times. --> N^2-(2N-1)
 * Center element is visited 4 times --> 1
 * Remaining diagonal elements are visited 3 times. --> 2N-1-1 = 2N-2
 * Total Time Complexity = O( 2*(N^2-(2N-1)) + 3*(2N-2) + 4*(1) )
 *                       = O(2*N^2 + 2*N)
 *                       = O(N^2)
 * </pre>
 *
 * Space Complexity: O(1) --> True constant space solution. No arrays are used
 * to store the scores of each row and column.
 *
 * N = Board Size (Number of Rows or Number of Columns)
 */
class Solution1 {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0) {
            return false;
        }

        int n = board.length;
        int diag1 = 0;
        int diag2 = 0;
        int turnsDiff = 0;
        boolean xWin = false;
        boolean oWin = false;
        char c;

        for (int i = 0; i < n; i++) { // ith row OR ith column
            int row = 0;
            int col = 0;
            for (int j = 0; j < n; j++) {
                c = board[i].charAt(j);
                if (c == 'X') {
                    row++;
                    turnsDiff++;
                } else if (c == 'O') {
                    row--;
                    turnsDiff--;
                }
                c = board[j].charAt(i);
                if (c == 'X') {
                    col++;
                } else if (c == 'O') {
                    col--;
                }
            }

            c = board[i].charAt(i);
            if (c == 'X') {
                diag1++;
            } else if (c == 'O') {
                diag1--;
            }
            c = board[i].charAt(n - 1 - i);
            if (c == 'X') {
                diag2++;
            } else if (c == 'O') {
                diag2--;
            }

            if (row == n || col == n || diag1 == n || diag2 == n) {
                if (oWin) {
                    return false;
                }
                xWin = true;
            }
            if (row == -n || col == -n || diag1 == -n || diag2 == -n) {
                if (xWin) {
                    return false;
                }
                oWin = true;
            }
        }

        if (turnsDiff < 0 || turnsDiff > 1) {
            return false;
        }
        /**
         * X Wins only after odd number of turns, thus the diff will be 1.
         *
         * O wins only after even number of turns, thus the diff will be 0.
         */
        if ((turnsDiff == 0 && xWin) || (turnsDiff == 1 && oWin)) {
            return false;
        }
        return true;
    }
}

/**
 * Calculate net score of each row, column and diagonal.
 *
 * Not Space Optimized. Here the score of each row, column and diagonal is
 * stored in an int array. For Player1 add 1 and for Player2 add -1.
 *
 * Time Complexity: O(N^2) or O(1)
 *
 * Space Complexity: O(N) or O(1)
 *
 * N = Board Size (Number of Rows or Number of Columns)
 */
class Solution2 {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0) {
            return false;
        }

        int n = board.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int diag1 = 0;
        int diag2 = 0;
        int turnsDiff = 0;
        boolean xWin = false;
        boolean oWin = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    continue;
                }
                int val = c == 'X' ? 1 : -1;
                turnsDiff += val;
                rows[i] += val;
                cols[j] += val;
                if (i == j) {
                    diag1 += val;
                }
                if (i + j == n - 1) {
                    diag2 += val;
                }
                if (rows[i] == n || cols[j] == n || diag1 == n || diag2 == n) {
                    if (oWin) {
                        return false;
                    }
                    xWin = true;
                }
                if (rows[i] == -n || cols[j] == -n || diag1 == -n || diag2 == -n) {
                    if (xWin) {
                        return false;
                    }
                    oWin = true;
                }
            }
        }

        if (turnsDiff < 0 || turnsDiff > 1) {
            return false;
        }
        /**
         * X Wins only after odd number of turns, thus the diff will be 1.
         *
         * O wins only after even number of turns, thus the diff will be 0.
         */
        if ((turnsDiff == 0 && xWin) || (turnsDiff == 1 && oWin)) {
            return false;
        }
        return true;
    }
}

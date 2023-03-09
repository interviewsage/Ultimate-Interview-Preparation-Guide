// LeetCode Question URL: https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
// LeetCode Discuss URL: https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/discuss/1517563/Java-or-TC:-O(M)-or-SC:-O(1)-or-Space-Optimized-solution

/**
 * Space Optimized solution. Here the score of each row, column and diagonal is
 * stored in an int. For Player1 add 1 and for Player2 add -1.
 *
 * Time Complexity: O(M) or O(N^2) or O(1)
 *
 * Space Complexity: O(N) = O(1)
 *
 * M = Number of Moves. N = Board Size (Number of Rows or Number of Columns)
 */
class Solution {
    public String tictactoe(int[][] moves) {
        if (moves == null || moves.length > 9) {
            throw new IllegalArgumentException("Input move list is invalid");
        }

        int numMoves = moves.length;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < numMoves; i++) {
            int r = moves[i][0];
            int c = moves[i][1];
            int player = (i & 1) == 0 ? 1 : -1;
            rows[r] += player;
            cols[c] += player;
            if (r == c) {
                diag1 += player;
            }
            if (r + c == 2) {
                diag2 += player;
            }

            if (Math.abs(rows[r]) == 3 || Math.abs(cols[c]) == 3 || Math.abs(diag1) == 3 || Math.abs(diag2) == 3) {
                if (i != numMoves - 1) {
                    throw new IllegalArgumentException("Input move list is invalid");
                }
                return player == 1 ? "A" : "B";
            }
        }

        return numMoves == 9 ? "Draw" : "Pending";
    }
}

// LeetCode Question URL: https://leetcode.com/problems/design-tic-tac-toe/
// LeetCode Discuss URL: https://leetcode.com/problems/design-tic-tac-toe/discuss/1517545/Java-or-TC:-O(1)-or-SC:-O(N)-or-Space-Optimized-solution

/**
 * Space Optimized solution. Here the score of each row, column and diagonal is
 * stored in an int. For Player1 add 1 and for Player2 add -1.
 *
 * Time Complexity: Initialization -> O(1). Move -> O(1).
 *
 * Space Complexity: O(N). This is required to store scores at each row and each
 * column.
 */
class TicTacToe {

    int[] rows;
    int[] cols;
    int diag1;
    int diag2;

    public TicTacToe(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid size");
        }

        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int n = rows.length;
        int val = player == 1 ? 1 : -1;
        rows[row] += val;
        cols[col] += val;
        if (row == col) {
            diag1 += val;
        }
        if (row + col == n - 1) {
            diag2 += val;
        }

        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diag1) == n || Math.abs(diag2) == n) {
            return player;
        }
        return 0;
    }
}

// Your TicTacToe object will be instantiated and called as such:
// TicTacToe obj = new TicTacToe(n);
// int param_1 = obj.move(row,col,player);

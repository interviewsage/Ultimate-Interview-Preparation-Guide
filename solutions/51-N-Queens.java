// LeetCode Question URL: https://leetcode.com/problems/n-queens/
// LeetCode Discuss URL: https://leetcode.com/problems/n-queens/discuss/1551695/Java-or-TC:-O(N*N!)-or-SC:-O(N)-or-Space-Optimized-Backtracking-using-BitSet

import java.util.*;

/**
 * Space Optimized Backtracking
 *
 * <pre>
 * Total number of permutations can be found by this equation
 * T(N) = N * T(N-1) + O(N)
 * T(N-1) = (N-1) * T(N-2) + O(N)
 * T(N-2) = (N-2) * T(N-3) + O(N)
 * T(N-3) = (N-3) * T(N-4) + O(N)
 * ...
 * T(2) = 2 * T(1) + O(N)
 * T(1) = O(N)
 * Thus total number of permutations
 *      = N * (P(N,0) + P(N,1) + ... + P(N, N-2) + P(N,N-1))
 *      = N * (e * N! - P(N,N))
 *      = (e-1) * N * N! = 1.718 * N * N!
 *
 * Also, if there are S(N) solutions, then time taken to generate these solution will be N^2 * S(N).
 * Here number of solutions will be much less than the total number of permutations.
 * Thus we can ignore the time taken for generating and adding the board in the result list.
 * Refer: https://en.wikipedia.org/wiki/Eight_queens_puzzle#Counting_solutions
 *
 * Total Time Complexity = O(N * N! + S(N) * N^2)
 *
 * Space Complexity:
 * -> O(N) for queensPos arr
 * -> O(N) for recursion depth
 * -> O(5*N-2) bits for boolean arrays
 *
 * Total Space Complexity = O(N)
 * </pre>
 *
 * N = Input board size.
 */
class Solution1 {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input borad size is invalid");
        }

        List<List<String>> result = new ArrayList<>();
        solveNQueensHelper(result, new int[n], new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], 0);
        return result;
    }

    private void solveNQueensHelper(List<List<String>> result, int[] queensPos, boolean[] cols, boolean[] diag45,
            boolean[] diag135, int row) {
        int n = queensPos.length;
        if (row == n) {
            result.add(generateResultBoard(queensPos));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d45 = row + col;
            int d135 = n - 1 + row - col;
            if (cols[col] || diag45[d45] || diag135[d135]) {
                continue;
            }

            cols[col] = true;
            diag45[d45] = true;
            diag135[d135] = true;
            queensPos[row] = col;

            solveNQueensHelper(result, queensPos, cols, diag45, diag135, row + 1);

            cols[col] = false;
            diag45[d45] = false;
            diag135[d135] = false;
        }
    }

    private List<String> generateResultBoard(int[] queensPos) {
        List<String> board = new ArrayList<>();
        char[] r = new char[queensPos.length];
        Arrays.fill(r, '.');
        for (int c : queensPos) {
            r[c] = 'Q';
            board.add(new String(r));
            r[c] = '.';
        }
        return board;
    }
}

class Solution2 {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid board");
        }

        List<List<String>> result = new ArrayList<>();
        solveNQueensHelper(result, new int[n], new BitSet(5 * n), 0);
        return result;
    }

    private void solveNQueensHelper(List<List<String>> result, int[] queensPos, BitSet occupied, int row) {
        int n = queensPos.length;
        if (row == n) {
            result.add(generateResultBoard(queensPos));
            return;
        }

        for (int col = 0; col < n; col++) {
            // First N bits are for columns
            // Then 2*N bits are for diagonal at 45 degrees
            // Then 2*N bits are for diagonal at 135 degrees
            int diag45 = n + (row + col);
            int diag135 = 3 * n + (n + row - col);
            if (occupied.get(col) || occupied.get(diag45) || occupied.get(diag135)) {
                continue;
            }

            occupied.set(col);
            occupied.set(diag45);
            occupied.set(diag135);
            queensPos[row] = col;

            solveNQueensHelper(result, queensPos, occupied, row + 1);

            occupied.clear(col);
            occupied.clear(diag45);
            occupied.clear(diag135);
        }
    }

    private List<String> generateResultBoard(int[] queensPos) {
        List<String> temp = new ArrayList<>();
        char[] b = new char[queensPos.length];
        Arrays.fill(b, '.');
        for (int q : queensPos) {
            b[q] = 'Q';
            temp.add(new String(b));
            b[q] = '.';
        }
        return temp;
    }
}

class Solution3 {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid board");
        }

        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] b : board) {
            Arrays.fill(b, '.');
        }

        solveNQueensHelper(result, board, new BitSet(n), new BitSet(n << 1), new BitSet(n << 1), 0);
        return result;
    }

    private void solveNQueensHelper(List<List<String>> result, char[][] board, BitSet cols, BitSet diags45,
            BitSet diags135, int row) {
        int n = board.length;
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (char[] b : board) {
                temp.add(new String(b));
            }
            result.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {
            int diag45 = row + col;
            int diag135 = n + row - col;
            if (cols.get(col) || diags45.get(diag45) || diags135.get(diag135)) {
                continue;
            }

            cols.set(col);
            diags45.set(diag45);
            diags135.set(diag135);
            board[row][col] = 'Q';

            solveNQueensHelper(result, board, cols, diags45, diags135, row + 1);

            cols.clear(col);
            diags45.clear(diag45);
            diags135.clear(diag135);
            board[row][col] = '.';
        }
    }
}

class Solution4 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }

        char[][] board = new char[n][n];
        for (char[] b : board) {
            Arrays.fill(b, '.');
        }

        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals45 = new HashSet<>();
        Set<Integer> diagonals135 = new HashSet<>();

        helper(result, board, columns, diagonals45, diagonals135, 0, n);

        return result;
    }

    private void helper(List<List<String>> result, char[][] board, Set<Integer> columns, Set<Integer> diagonals45,
            Set<Integer> diagonals135, int row, int n) {

        if (row == n) {
            result.add(constructResult(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int diag45 = row + col;
            int diag135 = row - col;
            if (columns.contains(col) || diagonals45.contains(diag45) || diagonals135.contains(diag135)) {
                continue;
            }

            columns.add(col);
            diagonals45.add(diag45);
            diagonals135.add(diag135);
            board[row][col] = 'Q';

            helper(result, board, columns, diagonals45, diagonals135, row + 1, n);

            columns.remove(col);
            diagonals45.remove(diag45);
            diagonals135.remove(diag135);
            board[row][col] = '.';
        }
    }

    private List<String> constructResult(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] b : board) {
            list.add(new String(b));
        }
        return list;
    }
}

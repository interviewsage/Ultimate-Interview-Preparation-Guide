// LeetCode Question URL: https://leetcode.com/problems/n-queens-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/n-queens-ii/discuss/1551698/Java-or-TC:-O(N*N!)-or-SC:-O(N)-or-Space-Optimized-Backtracking-using-BitSet

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
 * T(1) = O(1)
 * Thus total number of permutations
 *      = N * (P(N,0) + P(N,1) + ... + P(N, N-2)) + P(N,N-1)
 *      = N * (e * N! - P(N,N-1) - P(N,N)) + N!
 *      = ((e-2)*N + 1) * N! = (0.718 * N + 1) * N!
 *
 * Total Time Complexity = O(N * N!)
 *
 * Space Complexity:
 * -> O(N) for recursion depth
 * -> O(1) for occupied BitSet
 *
 * Total Space Complexity = O(N)
 * </pre>
 *
 * N = Input board size.
 */
class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return totalNQueensHelper(new BitSet(5 * n), 0, n);
    }

    private int totalNQueensHelper(BitSet occupied, int row, int n) {
        if (row == n) {
            return 1;
        }

        int count = 0;
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

            count += totalNQueensHelper(occupied, row + 1, n);

            occupied.clear(col);
            occupied.clear(diag45);
            occupied.clear(diag135);
        }

        return count;
    }
}

class Solution1 {
    public int totalNQueens(int n) {
        if (n < 2) {
            return n;
        }

        int[] count = new int[1];
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals45 = new HashSet<>();
        Set<Integer> diagonals135 = new HashSet<>();

        helper(count, columns, diagonals45, diagonals135, 0, n);

        return count[0];
    }

    private void helper(int[] count, Set<Integer> columns, Set<Integer> diagonals45, Set<Integer> diagonals135, int row,
            int n) {

        if (row == n) {
            count[0]++;
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

            helper(count, columns, diagonals45, diagonals135, row + 1, n);

            columns.remove(col);
            diagonals45.remove(diag45);
            diagonals135.remove(diag135);
        }
    }
}

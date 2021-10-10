// LeetCode Question URL: https://leetcode.com/problems/sudoku-solver/
// LeetCode Discuss URL: https://leetcode.com/problems/sudoku-solver/discuss/1513192/Java-or-TC:-O(9N)-or-SC:-O(N)-or-Most-Optimized-Backtracking-solution-using-Bit-Manipulation

import java.util.*;

/**
 * Most Optimized Backtracking solution using Bit Manipulation
 *
 * Time Complexity: T(N) = 9 * T(N-1) + O(9) ==> TC = (9^N). Also, O(9*9) is
 * required for checking validity and finding blanks.
 *
 * Space Complexity: O(3*9 + 2*N). 3*9 for rows, cols and boxes int array. N for
 * blanks list. N will be the recursion depth.
 *
 * N = Number of blank spaces. In worst case it can be 9*9 = 81.
 */
class Solution1 {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        List<int[]> blanks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                // To Blanks List
                if (c == '.') {
                    blanks.add(new int[] { i, j });
                    continue;
                }

                // Check for Invalid Chars
                if (c < '1' || c > '9') {
                    throw new IllegalArgumentException("Invalid sudoku board");
                }

                int b = 3 * (i / 3) + (j / 3);
                int mask = 1 << (c - '1');

                // Check for unsolvable board
                if (((rows[i] & mask) != 0) || ((cols[j] & mask) != 0) || ((boxes[b] & mask) != 0)) {
                    throw new IllegalArgumentException("Invalid sudoku board");
                }

                // Add the cell to rows, cols and boxes.
                rows[i] |= mask;
                cols[j] |= mask;
                boxes[b] |= mask;
            }
        }

        if (!solveSudokuHelper(board, rows, cols, boxes, blanks, 0)) {
            throw new RuntimeException("Input sudoku does not have a valid solution");
        }
    }

    private boolean solveSudokuHelper(char[][] board, int[] rows, int[] cols, int[] boxes, List<int[]> blanks,
            int idx) {
        if (idx == blanks.size()) {
            return true;
        }

        int[] cell = blanks.get(idx);
        int i = cell[0];
        int j = cell[1];
        int b = 3 * (i / 3) + (j / 3);

        for (char c = '1'; c <= '9'; c++) {
            int mask = 1 << (c - '1');

            // Check if the number already used in row, column and sub-box.
            if (((rows[i] & mask) != 0) || ((cols[j] & mask) != 0) || ((boxes[b] & mask) != 0)) {
                continue;
            }

            // Add the cell to rows, cols and boxes.
            rows[i] |= mask;
            cols[j] |= mask;
            boxes[b] |= mask;
            board[i][j] = c;

            if (solveSudokuHelper(board, rows, cols, boxes, blanks, idx + 1)) {
                return true;
            }

            // Backtrack
            // Remove the cell to rows, cols and boxes.
            rows[i] &= ~mask;
            cols[j] &= ~mask;
            boxes[b] &= ~mask;
        }

        return false;
    }
}

/**
 * Using Backtracking
 *
 * Time Complexity: T(N) = 9 * T(N-1) + O(9) ==> TC = (9^N)
 *
 * Space Complexity: O(9*9 + N). 9*9 for rows, cols, boxes and blanks list. N
 * will be the recursion depth.
 *
 * N = Number of blank spaces. In worst case it can be 9*9 = 81.
 */
class Solution2 {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }

        List<Set<Character>> rows = new ArrayList<>();
        List<Set<Character>> cols = new ArrayList<>();
        List<Set<Character>> boxes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }

        List<int[]> blanks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    blanks.add(new int[] { i, j });
                } else {
                    char c = board[i][j];
                    int box = 3 * (i / 3) + j / 3;
                    if (rows.get(i).contains(c) || cols.get(j).contains(c) || boxes.get(box).contains(c)) {
                        return;
                    }
                    rows.get(i).add(c);
                    cols.get(j).add(c);
                    boxes.get(box).add(c);
                }
            }
        }

        solveSudoku(board, rows, cols, boxes, blanks, 0);
    }

    private boolean solveSudoku(char[][] board, List<Set<Character>> rows, List<Set<Character>> cols,
            List<Set<Character>> boxes, List<int[]> blanks, int idx) {

        if (idx == blanks.size()) {
            return true;
        }

        int r = blanks.get(idx)[0];
        int c = blanks.get(idx)[1];
        int box = 3 * (r / 3) + c / 3;

        for (char i = '1'; i <= '9'; i++) {
            if (rows.get(r).contains(i) || cols.get(c).contains(i) || boxes.get(box).contains(i)) {
                continue;
            }

            rows.get(r).add(i);
            cols.get(c).add(i);
            boxes.get(box).add(i);
            board[r][c] = i;

            if (solveSudoku(board, rows, cols, boxes, blanks, idx + 1)) {
                return true;
            }

            rows.get(r).remove(i);
            cols.get(c).remove(i);
            boxes.get(box).remove(i);
            board[r][c] = '.';
        }

        return false;
    }
}
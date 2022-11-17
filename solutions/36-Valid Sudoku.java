// LeetCode Question URL: https://leetcode.com/problems/valid-sudoku/
// LeetCode Discuss URL: https://leetcode.com/problems/valid-sudoku/discuss/1513191/Java-or-TC:-O(9*9)-or-SC:-O(1)-or-Most-Optimized-solution-using-Bit-Manipulation

import java.util.*;

/**
 * Most Optimized solution using Bit Manipulation
 *
 * Using only one int to store the bits for Row, Column and Box.
 *
 * This Solution uses True O(1) space and requires True O(N^2) time.
 *
 * <pre>
 * Bit 0 -> 8 will represent ith Row
 * Bit 9 -> 17 will represent ith Column
 * Bit 18 -> 26 will represent ith Box
 * </pre>
 *
 * Time Complexity: O(9*9)
 *
 * Space Complexity: O(1). Using only one integer.
 */
class Solution1 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Input board is invalid");
        }

        for (int i = 0; i < 9; i++) { // Index of row, column and box
            int rowColBox = 0;
            for (int j = 0; j < 9; j++) {
                // Check ith row
                rowColBox = validateAndAddCell(board[i][j], 0, rowColBox);
                if (rowColBox == -1) {
                    return false;
                }

                // Check ith column
                rowColBox = validateAndAddCell(board[j][i], 1, rowColBox);
                if (rowColBox == -1) {
                    return false;
                }

                // Check ith box
                int boxRow = 3 * (i / 3) + (j / 3);
                int boxCol = 3 * (i % 3) + (j % 3);
                rowColBox = validateAndAddCell(board[boxRow][boxCol], 2, rowColBox);
                if (rowColBox == -1) {
                    return false;
                }
            }
        }

        return true;
    }

    private int validateAndAddCell(char cell, int type, int rowColBox) {
        if (cell == '.') {
            return rowColBox;
        }
        if (cell < '1' || cell > '9') {
            return -1;
        }

        int bitIdx = 9 * type + (cell - '1');
        if (((rowColBox >> bitIdx) & 1) == 1) {
            return -1;
        }

        return rowColBox | (1 << bitIdx);
    }
}

/**
 * Time Complexity: O(9 + 9 * 9) or O(N^2)
 *
 * Space Complexity: O(3 * 9 * 9) or O(N^2)
 *
 * N = 9.
 */
class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Input is invalid");
        }

        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if (c < '1' || c > '9') {
                    return false;
                }
                int boxIdx = 3 * (i / 3) + (j / 3);

                if (rows[i].contains(c) || cols[j].contains(c) || boxes[boxIdx].contains(c)) {
                    return false;
                }

                rows[i].add(c);
                cols[j].add(c);
                boxes[boxIdx].add(c);
            }
        }

        return true;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/valid-sudoku/discuss/15450/Shared-my-concise-Java-code/15493
 *
 * Time Complexity: O(9 * 4*9) or O(4 * N^2)
 *
 * Space Complexity: O(27 Bits) = O(1)
 *
 * N = 9.
 */
class Solution3 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Input is invalid");
        }

        HashSet<Character> row = new HashSet<>(9);
        HashSet<Character> col = new HashSet<>(9);
        HashSet<Character> box = new HashSet<>(9);

        for (int i = 0; i < 9; i++) { // Index of row, column and box
            for (int j = 0; j < 9; j++) {
                // Check ith row
                if (!isValidCell(board[i][j], row)) {
                    return false;
                }

                // Check ith column
                if (!isValidCell(board[j][i], col)) {
                    return false;
                }

                // Check ith box
                int boxRow = 3 * (i / 3) + (j / 3);
                int colRow = 3 * (i % 3) + (j % 3);
                if (!isValidCell(board[boxRow][colRow], box)) {
                    return false;
                }
            }
            row.clear();
            col.clear();
            box.clear();
        }

        return true;
    }

    private boolean isValidCell(char c, HashSet<Character> set) {
        if (c == '.') {
            return true;
        }
        if (c < '1' || c > '9' || !set.add(c)) {
            return false;
        }
        return true;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/valid-sudoku/discuss/15450/Shared-my-concise-Java-code/15493
 *
 * Time Complexity: O(9 * 4*9) or O(4 * N^2)
 *
 * Space Complexity: O(3 * long)
 *
 * N = 9.
 */
class Solution4 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            throw new IllegalArgumentException("Input is invalid");
        }

        BitSet row = new BitSet(9);
        BitSet col = new BitSet(9);
        BitSet box = new BitSet(9);

        for (int i = 0; i < 9; i++) { // Index of row, column and box
            for (int j = 0; j < 9; j++) {
                // Check ith row
                if (!isValidCell(board[i][j], row)) {
                    return false;
                }

                // Check ith column
                if (!isValidCell(board[j][i], col)) {
                    return false;
                }

                // Check ith box
                int boxRow = 3 * (i / 3) + (j / 3);
                int colRow = 3 * (i % 3) + (j % 3);
                if (!isValidCell(board[boxRow][colRow], box)) {
                    return false;
                }
            }
            row.clear();
            col.clear();
            box.clear();
        }

        return true;
    }

    private boolean isValidCell(char c, BitSet bitSet) {
        if (c == '.') {
            return true;
        }
        if (c < '1' || c > '9' || bitSet.get(c - '1')) {
            return false;
        }
        bitSet.set(c - '1');
        return true;
    }
}

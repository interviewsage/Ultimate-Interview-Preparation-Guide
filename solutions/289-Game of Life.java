// LeetCode Question URL: https://leetcode.com/problems/game-of-life/
// LeetCode Discuss URL: https://leetcode.com/problems/game-of-life/discuss/1520732/Java-or-TC:-O(MN)-or-SC:-O(1)-or-Optimal-in-place-and-infinite-board-solutions

import java.util.*;

/**
 * Refer: Awesome explanation
 * https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
 *
 * To solve it in place, we use 2 bits to store 2 states: [2nd bit, 1st bit] =
 * [next state, current state]
 *
 * For each cell, check the current state of 8 neighbors, and set the cell's 2nd
 * bit. Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
 * Transition 00 -> 10: when board == 0 and lives == 3.
 *
 * To get the current state, simply do board[i][j] & 1
 *
 * To get the next state, simply do board[i][j] >>> 1
 *
 * Time Complexity: O(8*M*N +M*N) = O(M * N)
 *
 * Space Complexity: O(1)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public void gameOfLife(int[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input board is null");
        }

        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int cols = board[0].length;
        if (cols == 0) {
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveCount = 0;
                for (int[] d : DIRS) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols) {
                        liveCount += board[x][y] & 1;
                    }
                }
                if (liveCount == 3 || (board[i][j] == 1 && liveCount == 2)) {
                    board[i][j] |= 2; // 1 << 1
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] >>>= 1;
            }
        }
    }
}

/**
 * Infinite Grid
 *
 * Refer: Awesome explanation
 * https://leetcode.com/problems/game-of-life/discuss/73217/Infinite-board-solution/76083
 *
 * Time Complexity: O(8*N + 8*N) = O(N)
 *
 * Space Complexity: O(8*N + N) = O(N)
 *
 * N = Number of live cells
 */
class Solution2 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Cell) {
                Cell other = (Cell) o;
                return this.x == other.x && this.y == other.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int h = 1;
            h = 31 * h + this.x;
            h = 31 * h + this.y;
            return h;
        }
    }

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        HashSet<Cell> currentState = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 1) {
                    currentState.add(new Cell(i, j));
                }
            }
        }

        HashSet<Cell> nextState = gameOfLifeInfinite(currentState);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = nextState.contains(new Cell(i, j)) ? 1 : 0;
            }
        }
    }

    public HashSet<Cell> gameOfLifeInfinite(HashSet<Cell> currentState) {
        HashSet<Cell> nextState = new HashSet<>();
        if (currentState == null || currentState.size() == 0) {
            return nextState;
        }

        HashMap<Cell, Integer> map = new HashMap<>();
        for (Cell c : currentState) {
            for (int[] d : DIRS) {
                Cell neighbor = new Cell(c.x + d[0], c.y + d[1]);
                map.put(neighbor, map.getOrDefault(neighbor, 0) + 1);
            }
        }
        for (Cell c : map.keySet()) {
            int count = map.get(c);
            if (count == 3 || (count == 2 && currentState.contains(c))) {
                nextState.add(c);
            }
        }
        return nextState;
    }
}

/**
 * Infinite Grid
 *
 * Refer: Awesome explanation
 * https://leetcode.com/problems/game-of-life/discuss/73217/Infinite-board-solution/76083
 *
 * Time Complexity: O(8*N + 8*N) = O(N)
 *
 * Space Complexity: O(8*N + N) = O(N)
 *
 * N = Number of live cells
 */
class Solution3 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
            { -1, 1 } };

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        HashSet<Pair<Integer, Integer>> currentState = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 1) {
                    currentState.add(new Pair<>(i, j));
                }
            }
        }
        Pair<Integer, Integer> pair = new MutablePair<>(1, 2);

        HashSet<Pair<Integer, Integer>> nextState = gameOfLifeInfinite(currentState);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = nextState.contains(new Pair<>(i, j)) ? 1 : 0;
            }
        }
    }

    public HashSet<Pair<Integer, Integer>> gameOfLifeInfinite(HashSet<Pair<Integer, Integer>> currentState) {
        HashSet<Pair<Integer, Integer>> nextState = new HashSet<>();
        if (currentState == null || currentState.size() == 0) {
            return nextState;
        }

        HashMap<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        for (Pair<Integer, Integer> c : currentState) {
            for (int[] d : DIRS) {
                Pair<Integer, Integer> neighbor = new Pair<>(c.getKey() + d[0], c.getValue() + d[1]);
                map.put(neighbor, map.getOrDefault(neighbor, 0) + 1);
            }
        }
        for (Pair<Integer, Integer> c : map.keySet()) {
            int count = map.get(c);
            if (count == 3 || (count == 2 && currentState.contains(c))) {
                nextState.add(c);
            }
        }
        return nextState;
    }
}

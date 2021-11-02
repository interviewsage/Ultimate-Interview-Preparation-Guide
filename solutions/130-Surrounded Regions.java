// LeetCode Question URL: https://leetcode.com/problems/surrounded-regions/
// LeetCode Discuss URL: https://leetcode.com/problems/surrounded-regions/discuss/1552685/Java-or-TC:-O(M*N)-or-SC:-O(min(MN)-or-Linear-Space-BFS-and-simple-DFS-solutions

import java.util.*;

/**
 * DFS - Recursive Solution
 *
 * <pre>
 * Start from edges and then mark all 'O' cells that connect to 'O' cells at edge.
 *
 * Time Complexity: O(2*M*N + M + N) = O(M * N)
 * Each cell is visited twice. To set '#' and then to set them back to 'O'.
 *
 * Space Complexity: O(M * N). Recursion Depth.
 * In worst case if all cells are 'O' then it will take O(M*N) space.
 * </pre>
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    private static final char NON_CAPTURED = '#';

    public void solve(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input board is null");
        }
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfsHelper(board, i, 0);
            }

            if (board[i][n - 1] == 'O') {
                dfsHelper(board, i, n - 1);
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                dfsHelper(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfsHelper(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] == NON_CAPTURED) ? 'O' : 'X';
            }
        }
    }

    private void dfsHelper(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'O') {
            return;
        }

        board[x][y] = NON_CAPTURED;

        for (int[] d : DIRS) {
            dfsHelper(board, x + d[0], y + d[1]);
        }
    }
}

/**
 * BFS - Iterative Solution (This is a space optimized solution)
 *
 * <pre>
 * Start from edges and then mark all 'O' cells that connect to 'O' cells at edge.
 *
 * Time Complexity: O(2*M*N + M + N) = O(M * N)
 * Each cell is visited twice. To set '#' and then to set them back to 'O'.
 *
 * Space Complexity: O(min(M, N)). Space taken by the queue.
 * In worst case, it will be equal to the diagonal of the board which is min(M, N)
 * </pre>
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    private static final char NON_CAPTURED = '#';

    public void solve(char[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input board is null");
        }
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                bfsHelper(board, i, 0);
            }

            if (board[i][n - 1] == 'O') {
                bfsHelper(board, i, n - 1);
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O') {
                bfsHelper(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                bfsHelper(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] == NON_CAPTURED) ? 'O' : 'X';
            }
        }
    }

    private void bfsHelper(char[][] board, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { i, j });
        board[i][j] = NON_CAPTURED;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'O') {
                    continue;
                }

                queue.offer(new int[] { x, y });
                board[x][y] = NON_CAPTURED;
            }

        }
    }
}

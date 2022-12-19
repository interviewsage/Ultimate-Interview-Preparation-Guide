// LeetCode Question URL: https://leetcode.com/problems/number-of-islands/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS Solution (Modifying the input grid) (Better Solution)
 *
 * Time Complexity: O(M * N). Visiting all cells of the grid twice.
 *
 * Space Complexity: O(min(M,N)). Queue length can grow upto minimum of rows and
 * cols. (Refer for explanation: https://imgur.com/gallery/M58OKvB)
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int numIslands(char[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }

        int numOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }

                numOfIslands++;
                Deque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { i, j });
                grid[i][j] = '0';

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int[] d : DIRS) {
                        int x = cur[0] + d[0];
                        int y = cur[1] + d[1];
                        if (x >= 0 & x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                            queue.offer(new int[] { x, y });
                            grid[x][y] = '0';
                        }
                    }
                }
            }
        }

        return numOfIslands;
    }
}

/**
 * DFS Solution
 *
 * Time Complexity: O(M * N). Visiting all cells of the grid twice.
 *
 * Space Complexity: O(M * N). In worst case if all the cells of the grid are
 * '1', then the recursion stack will be M*N.
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution2 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int numIslands(char[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }

        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }

        int numOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    dfsExtendIsland(grid, i, j);
                }
            }
        }

        return numOfIslands;
    }

    private void dfsExtendIsland(char[][] grid, int x, int y) {
        grid[x][y] = '0';

        for (int[] d : DIRS) {
            int i = x + d[0];
            int j = y + d[1];

            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
                dfsExtendIsland(grid, i, j);
            }
        }
    }
}

/**
 * BFS Solution (Not modifying the input grid)
 *
 * Time Complexity: O(M * N). Visiting all cells of the grid twice.
 *
 * Space Complexity: O(M * N). Visited array will take M*N space
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution3 {
    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '1' || visited[i][j]) {
                    continue;
                }

                count++;

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] { i, j });
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    for (int[] d : dirs) {
                        int x = cell[0] + d[0];
                        int y = cell[1] + d[1];
                        if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != '1' || visited[x][y]) {
                            continue;
                        }
                        queue.offer(new int[] { x, y });
                        visited[x][y] = true;
                    }
                }
            }
        }
        return count;
    }
}

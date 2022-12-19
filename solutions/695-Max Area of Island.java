// LeetCode Question URL: https://leetcode.com/problems/max-area-of-island
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS Iterative Solution.
 *
 * Time Complexity: O(M * N) - All cells in the grids ar visited twice.
 *
 * Space Complexity: O(min(M,N)). Queue length can grow upto minimum of rows and
 * cols. (Refer for explanation: https://imgur.com/gallery/M58OKvB)
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution1 {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public int maxAreaOfIsland(int[][] grid) {
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

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                int area = 1;
                Deque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { i, j });
                grid[i][j] = 0;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int[] d : DIRS) {
                        int x = cur[0] + d[0];
                        int y = cur[1] + d[1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                            area++;
                            grid[x][y] = 0;
                            queue.offer(new int[] { x, y });
                        }
                    }
                }

                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}

/**
 * DFS Recursive Solution.
 *
 * Time Complexity: O(M * N) - All cells in the grids ar visited twice.
 *
 * Space Complexity: O(M * N) - This is for recursion stack and visited boolean
 * array
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution2 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, dfsHelper(grid, visited, i, j));
                }
            }
        }

        return maxArea;
    }

    private int dfsHelper(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1 || visited[x][y]) {
            return 0;
        }

        int area = 1;
        visited[x][y] = true;

        for (int[] d : DIRS) {
            area += dfsHelper(grid, visited, x + d[0], y + d[1]);
        }

        return area;
    }
}

/**
 * BFS Iterative Solution.
 *
 * Time Complexity: O(M * N) - All cells in the grids ar visited twice.
 *
 * Space Complexity: O(M * N) - Visited boolean array. Max size taken by the
 * queue is O(min(M,N)).
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution3 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1 || visited[i][j]) {
                    continue;
                }

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] { i, j });
                visited[i][j] = true;
                int area = 1;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();

                    for (int[] d : DIRS) {
                        int x = cur[0] + d[0];
                        int y = cur[1] + d[1];

                        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1 || visited[x][y]) {
                            continue;
                        }

                        queue.offer(new int[] { x, y });
                        visited[x][y] = true;
                        area++;
                    }
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}
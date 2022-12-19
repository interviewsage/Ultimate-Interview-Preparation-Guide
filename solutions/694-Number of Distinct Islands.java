// LeetCode Question URL: https://leetcode.com/problems/number-of-distinct-islands
// LeetCode Discuss URL:

import java.util.*;

/**
 * DFS Recursive. This solution create a path signature of each island and save
 * it in a HashSet. HashSet keeps one copy of each distinct signature.
 *
 * Time Complexity: O(M * N) - All cells in the grids ar visited twice.
 *
 * Space Complexity: O(3 * M * N) - This is for recursion stack, visited boolean
 * array and signatures
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final char[] DIRS_CODE = { 'u', 'd', 'l', 'r' };

    public int numDistinctIslands(int[][] grid) {
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

        Set<String> signatures = new HashSet<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder path = new StringBuilder();
                    numDistinctIslandsDfsHelper(grid, visited, i, j, 'o', path);
                    signatures.add(path.toString());
                }
            }
        }

        return signatures.size();
    }

    private void numDistinctIslandsDfsHelper(int[][] grid, boolean[][] visited, int i, int j, char dir,
            StringBuilder path) {
        visited[i][j] = true;
        path.append(dir);

        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d][0];
            int y = j + DIRS[d][1];

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {
                numDistinctIslandsDfsHelper(grid, visited, x, y, DIRS_CODE[d], path);
            }
        }

        path.append('b');
    }
}

/**
 * BFS Iterative. This solution create a path signature of each island and save
 * it in a HashSet. HashSet keeps one copy of each distinct signature.
 *
 * Time Complexity: O(M * N) - All cells in the grids ar visited twice.
 *
 * Space Complexity: O(2 * M * N + min(M,N)) - M*N -> visited, Signature Set
 * bounded by M*N, Queue size -> min(M,N) as you will be exploring level by
 * level.
 *
 * M = Number of rows in the grid. N = Number of columns in the grid.
 *
 * <pre>
 * [[1,1,1,1,1,1,1,1,1,1,1,1],
 *  [1,1,1,1,1,1,1,1,1,1,1,1],
 *  [1,1,1,1,1,1,1,1,1,1,0,0],
 *  [1,1,1,1,1,1,1,1,1,0,0,0],
 *  [1,1,1,1,1,1,1,1,0,0,0,0],
 *  [1,1,1,1,1,1,1,0,0,0,0,0],
 *  [1,1,1,1,1,1,0,0,0,0,0,0],
 *  [1,1,1,1,1,0,0,0,0,0,0,0],
 *  [1,1,1,1,0,0,0,0,0,0,0,0],
 *  [1,1,1,0,0,0,0,0,0,0,0,0],
 *  [1,1,0,0,0,0,0,0,0,0,0,0],
 *  [1,0,0,0,0,0,0,0,0,0,0,0]]
 *
 * Here ^^ 1,11 is set
 *
 * [[1,1,1,1,1,1,1,1,1,1,1,1],
 *  [1,1,1,1,1,1,1,1,1,1,1,0],
 *  [1,1,1,1,1,1,1,1,1,1,0,0],
 *  [1,1,1,1,1,1,1,1,1,0,0,0],
 *  [1,1,1,1,1,1,1,1,0,0,0,0],
 *  [1,1,1,1,1,1,1,0,0,0,0,0],
 *  [1,1,1,1,1,1,0,0,0,0,0,0],
 *  [1,1,1,1,1,0,0,0,0,0,0,0],
 *  [1,1,1,1,0,0,0,0,0,0,0,0],
 *  [1,1,1,0,0,0,0,0,0,0,0,0],
 *  [1,1,0,0,0,0,0,0,0,0,0,0],
 *  [1,1,0,0,0,0,0,0,0,0,0,0]]
 *
 * Here ^^ 11,1 is set
 * </pre>
 *
 * Signature for both will be same. Thus we need to add comma in signatures
 */
class Solution2 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int numDistinctIslands(int[][] grid) {
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

        Set<String> signatures = new HashSet<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 1 || visited[i][j]) {
                    continue;
                }

                StringBuilder sb = new StringBuilder("0,0");
                Deque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { i, j });
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int[] d : DIRS) {
                        int x = cur[0] + d[0];
                        int y = cur[1] + d[1];

                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1 && !visited[x][y]) {
                            visited[x][y] = true;
                            sb.append(';').append(x - i).append(',').append(y - j);
                            queue.offer(new int[] { x, y });
                        }
                    }
                }

                signatures.add(sb.toString());
            }
        }

        return signatures.size();
    }
}

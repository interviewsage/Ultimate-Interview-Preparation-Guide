// LeetCode Question URL: https://leetcode.com/problems/shortest-path-in-binary-matrix/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Simple BFS
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N + N-1) = O(N). In worst case, we can store current
 * level and next level.
 *
 * N = Size of grid.
 */
class Solution1 {
    private static final int[][] DIRS = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] != 0) {
            return -1;
        }

        // If rows & cols are always equal. Then just use one variable.
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[rows - 1][cols - 1] != 0) {
            return -1;
        }

        if (rows == cols && rows <= 2) {
            return rows;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 });
        int pathLen = 1;
        grid[0][0] = -1;

        while (!queue.isEmpty()) {
            pathLen++;
            int levelSize = queue.size();

            while (levelSize-- > 0) {
                int[] cur = queue.poll();

                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];

                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 0) {
                        continue;
                    }

                    if (x == rows - 1 && y == cols - 1) {
                        return pathLen;
                    }

                    queue.offer(new int[] { x, y });
                    grid[x][y] = -1;
                }
            }
        }

        return -1;
    }
}

/**
 * Modified Dijkstra's
 *
 * Time Complexity: O(N^2 * log(2N-1)) = O(N^2 * log(N))
 *
 * Space Complexity: O(N + N-1) = O(N). In worst case, we can store current
 * level and next level.
 *
 * N = Size of grid.
 */
class Solution2 {
    private static final int[][] DIRS = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] != 0) {
            return -1;
        }

        // If rows & cols are always equal. Then just use one variable.
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[rows - 1][cols - 1] != 0) {
            return -1;
        }

        if (rows == cols && rows <= 2) {
            return rows;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) {
                return a[2] - b[2];
            } else {
                return getEstimateDistance(a, rows - 1, cols - 1) - getEstimateDistance(b, rows - 1, cols - 1);
            }
        });
        queue.offer(new int[] { 0, 0, 1 });
        grid[0][0] = -1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 0) {
                    continue;
                }

                if (x == rows - 1 && y == cols - 1) {
                    return cur[2] + 1;
                }

                grid[x][y] = -1;
                queue.offer(new int[] { x, y, cur[2] + 1 });
            }
        }

        return -1;
    }

    private static int getEstimateDistance(int[] cell, int x, int y) {
        return Math.max(x - cell[0], y - cell[1]);
    }
}

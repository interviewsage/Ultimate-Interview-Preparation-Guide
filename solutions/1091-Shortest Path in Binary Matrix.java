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
class Solution {
    private static final int[][] DIRS = { { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 },
            { 1, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 || grid[0][0] != 0) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[rows - 1][cols - 1] != 0) {
            return -1;
        }
        // if ((rows == 1 && cols == 1) || (rows == 2 && cols == 2)) {
        // return rows;
        // }
        if (rows == cols && rows <= 2) {
            return rows;
        }
        // If rows & cols are always equal. Then just use one variable.

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        int dist = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;

            while (size-- > 0) {
                int[] cur = queue.poll();

                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];

                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 0) {
                        continue;
                    }
                    if (x == rows - 1 && y == cols - 1) {
                        return dist;
                    }
                    grid[x][y] = -1;
                    queue.offer(new int[] { x, y });
                }
            }
        }

        return -1;
    }
}

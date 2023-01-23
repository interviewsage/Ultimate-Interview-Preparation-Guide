// LeetCode Question URL: https://leetcode.com/problems/rotting-oranges/
// LeetCode Discuss URL: https://leetcode.com/problems/rotting-oranges/discuss/1517522/Java-or-TC:-O(M*N)-or-SC:-O(M*N)-or-Optimized-BFS-Solution

import java.util.*;

/**
 * Optimized BFS Solution. Find all rotten oranges and add them to a queue.
 * Start the BFS from this level and rot all the neighboring oranges. Continue
 * till all levels are exhausted or all oranges have become rotten.
 *
 * Very similar to "286. Walls and Gates" question
 * (https://leetcode.com/problems/walls-and-gates/)
 *
 * Time Complexity: O(M*N + V + E) = O(M*N + M*N + 4*M*N) = O(M * N)
 *
 * Space Complexity: O(M * N) --> Queue Size
 *
 * M = Number of rows. N = Number of columns
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public int orangesRotting(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input grid is null");
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 1 && cols == 1) {
            return grid[0][0] == 1 ? -1 : 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        int time = 0;
        while (!queue.isEmpty() && freshOranges > 0) {
            time++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                int[] cur = queue.poll();
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        if (--freshOranges == 0) {
                            return time;
                        }
                        queue.offer(new int[] { x, y });
                    }
                }
            }
        }

        return freshOranges == 0 ? time : -1;
    }
}

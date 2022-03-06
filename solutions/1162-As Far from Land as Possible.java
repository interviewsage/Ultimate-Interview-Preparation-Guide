// LeetCode Question URL: https://leetcode.com/problems/as-far-from-land-as-possible/
// LeetCode Discuss URL:

import java.util.*;

/**
 * DP Solution
 *
 * Refer:
 * https://leetcode.com/problems/as-far-from-land-as-possible/discuss/422691/7ms-DP-solution-with-example-beats-100
 *
 * Time Complexity: O(2 * N * N) = O(N^2)
 *
 * Space Complexity: O(1). Grid can be reset to original by setting non zero
 * values to water and zeros to land.
 *
 * N = Grid size.
 */
class Solution1 {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length <= 1) {
            return -1;
        }

        int n = grid.length;
        int maxDist = n + n; // Maximum Distance possible if all are water.
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    continue;
                }
                grid[i][j] = maxDist;
                if (i > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
                }
                if (j > 0) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i < n - 1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                }
                if (j < n - 1) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
                }

                result = Math.max(result, grid[i][j]);
            }
        }

        return (result == 0 || result == maxDist) ? -1 : result;
    }
}

/**
 * BFS Solution
 *
 * Time Complexity: O(2 * N * N) = O(N^2)
 *
 * Time Complexity: O(N^2)
 *
 * N = Grid size.
 */
class Solution2 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length <= 1) {
            return -1;
        }

        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] d : DIRS) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0) {
                            grid[x][y] = -1;
                            queue.offer(new int[] { x, y });
                        }
                    }
                }
            }
        }

        if (queue.size() == 0) {
            return -1;
        }

        int farDist = 0;
        while (!queue.isEmpty()) {
            farDist++;
            int len = queue.size();
            while (len-- > 0) {
                int[] cur = queue.poll();
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0) {
                        grid[x][y] = -1;
                        queue.offer(new int[] { x, y });
                    }
                }
            }
        }

        return farDist;
    }
}

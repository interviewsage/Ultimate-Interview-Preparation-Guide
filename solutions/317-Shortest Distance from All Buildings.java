// LeetCode Question URL: https://leetcode.com/problems/shortest-distance-from-all-buildings/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Run BFS for each building
 *
 * <pre>
 * Refer:
 * 1. Approach 3: BFS from Houses to Empty Land (Optimized) - https://leetcode.com/problems/shortest-distance-from-all-buildings/solution/
 * 2. https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76891/Java-solution-with-explanation-and-time-complexity-analysis
 *
 * Time Complexity:
 * 1. To find all buildings = O(M*N)
 * 2. To populate distance grid = O(Numbers of 1s * Number of 0s). This in worst case can be O(M^2 * N^2)
 * Total time complexity = O(M*N + M^2*N^2) = O(M^2 * N^2)
 *
 * Space Complexity:
 * 1. distance grid = O(M*N)
 * 2. Queue can grow upto max O(M + N)
 * Total space complexity = O(M*N + M + N) = O(M*N)
 *
 * M = Number of rows
 * N = Number of cols
 * </pre>
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if (rows == 1 && cols == 1) {
            return -1;
        }

        int[][] distance = new int[rows][cols];
        int minDistance = Integer.MAX_VALUE;
        int emptySpaceVal = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                minDistance = Integer.MAX_VALUE;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] { i, j });
                int dist = 0;

                while (!queue.isEmpty()) {
                    dist++;
                    int levelSize = queue.size();

                    for (int k = 0; k < levelSize; k++) {
                        int[] cur = queue.poll();

                        for (int[] d : DIRS) {
                            int x = cur[0] + d[0];
                            int y = cur[1] + d[1];

                            if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == emptySpaceVal) {
                                grid[x][y]--;
                                distance[x][y] += dist;
                                queue.offer(new int[] { x, y });
                                minDistance = Math.min(minDistance, distance[x][y]);
                            }
                        }
                    }
                }

                if (minDistance == Integer.MAX_VALUE) {
                    return -1;
                }
                emptySpaceVal--;
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/walls-and-gates/
// LeetCode Discuss URL: https://leetcode.com/problems/walls-and-gates/discuss/1515440/Java-or-TC:-O(R*C)-or-SC:-O(R*C)-or-Optimized-BFS-Solution

import java.util.*;

/**
 * The algorithm of 542. 01 Matrix (https://leetcode.com/problems/01-matrix/)
 * does not work in this question because of the walls (blockers)
 */

/**
 * Optimized BFS Solution. Find all gates and add them to a queue. Start the BFS
 * from gates level.
 *
 * Refer for details complexity analysis:
 * https://leetcode.com/problems/walls-and-gates/discuss/72748/Benchmarks-of-DFS-and-BFS
 *
 * Time Complexity: O(2 * R * C) --> Each cell in the rooms matrix is visited
 * maximum twice.
 *
 * Space Complexity: O(R * C) --> Queue Size
 *
 * R = Number of rows. C = Number of columns
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) {
            throw new IllegalArgumentException("Input rooms grid is null");
        }

        int rows = rooms.length;
        if (rows == 0) {
            return;
        }
        int cols = rooms[0].length;
        if (cols == 0) {
            return;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int emptyRooms = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                } else if (rooms[i][j] == Integer.MAX_VALUE) {
                    emptyRooms++;
                }
            }
        }

        if (emptyRooms == 0) {
            return;
        }

        int dist = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            dist++;
            for (int i = 0; i < levelSize; i++) {
                int[] cur = queue.poll();
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];

                    if (x >= 0 && x < rows && y >= 0 && y < cols && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = dist;
                        queue.offer(new int[] { x, y });
                    }
                }
            }
        }
    }
}

/**
 * DO NOT SOLVE THIS SOLUTION as complexity calculation is very complex.
 *
 * DFS
 *
 * Time Complexity: ???? Is it O(Number of Gates * R * C)??
 *
 * Space Complexity: O(R * C) --> Recursion stack size.
 *
 * R = Number of rows. C = Number of columns
 */
class Solution2 {
    private static final int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfsHelper(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfsHelper(int[][] rooms, int x, int y, int dist) {
        if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length || rooms[x][y] < dist) {
            return;
        }

        rooms[x][y] = dist;

        for (int[] d : dirs) {
            dfsHelper(rooms, x + d[0], y + d[1], dist + 1);
        }
    }
}

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
 * Time Complexity: O(2 * M * N) --> Each cell in the grid is visited maximum
 * twice.
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

        Queue<Integer> queue = new LinkedList<>();
        int freshOrangesCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i * cols + j);
                } else if (grid[i][j] == 1) {
                    freshOrangesCount++;
                }
            }
        }

        int time = 0;
        // freshOrangesCount != 0 ==> This check ensure we are not continuing once all
        // fresh oranges have become rotten.
        while (freshOrangesCount != 0 && !queue.isEmpty()) {
            int levelSize = queue.size();
            time++; // Time elapsed after neighbors of this level have become rotten.
            for (int i = 0; i < levelSize && freshOrangesCount != 0; i++) {
                int cur = queue.poll();
                int row = cur / cols;
                int col = cur % cols;
                for (int[] d : DIRS) {
                    int x = row + d[0];
                    int y = col + d[1];
                    if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] != 1) {
                        continue;
                    }
                    grid[x][y] = 2;
                    freshOrangesCount--;
                    if (freshOrangesCount == 0) {
                        break;
                    }
                    queue.offer(x * cols + y);
                }
            }
        }

        return freshOrangesCount == 0 ? time : -1;
    }
}

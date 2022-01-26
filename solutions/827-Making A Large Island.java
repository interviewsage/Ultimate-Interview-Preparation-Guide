// LeetCode Question URL: https://leetcode.com/problems/making-a-large-island/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/making-a-large-island/discuss/127015/C++-with-picture-O(n*m)
 *
 * <pre>
 * Time Complexity:
 * 1. Each cell will be visited twice while search for islands = O(2 * M * N)
 * 2. Iterate over each zero cell.. which in worst case can be all cells. = O(M * N)
 * Total Time Complexity: O(M * N)
 *
 * Space Complexity:
 * 1. Visited = O(M * N)
 * 2. colorSizeMap in worst case = O(M * N)
 * 3. zeros in worst case = O(M * N)
 * Total Space Complexity: O(M * N)
 * </pre>
 *
 * M = Number of rows. N = Number of Cols.
 */
class Solution {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];
        int color = 1;
        Map<Integer, Integer> colorSizeMap = new HashMap<>();
        List<int[]> zeros = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    zeros.add(new int[] { i, j });
                } else if (grid[i][j] == 1 && visited[i][j] == 0) {
                    int area = largestIslandHelper(grid, visited, i, j, color);
                    result = Math.max(result, area);
                    colorSizeMap.put(color++, area);
                }
            }
        }

        if (colorSizeMap.size() <= 1) {
            return Math.min(rows * cols, result + 1);
        }

        for (int[] zeroCell : zeros) {
            Set<Integer> colorSet = new HashSet<>();
            int area = 1;
            for (int[] d : DIRS) {
                int x = zeroCell[0] + d[0];
                int y = zeroCell[1] + d[1];
                if (x >= 0 && y >= 0 && x < rows && y < cols && visited[x][y] != 0 && colorSet.add(visited[x][y])) {
                    area += colorSizeMap.get(visited[x][y]);
                }
            }
            result = Math.max(result, area);
        }

        return result;
    }

    private int largestIslandHelper(int[][] grid, int[][] visited, int i, int j, int color) {
        visited[i][j] = color;
        int area = 1;
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1 && visited[x][y] == 0) {
                area += largestIslandHelper(grid, visited, x, y, color);
            }
        }
        return area;
    }
}

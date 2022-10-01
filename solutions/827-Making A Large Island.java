// LeetCode Question URL: https://leetcode.com/problems/making-a-large-island/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/making-a-large-island/discuss/127015/C++-with-picture-O(n*m)
 *
 * <pre>
 * Time Complexity:
 * 1. Each cell will be visited twice = O(2 * M * N)
 * Total Time Complexity: O(M * N)
 *
 * Space Complexity:
 * 1. Visited = O(M * N)
 * 2. colorSizeMap in worst case = O(M * N / 2) --> Checkered grid
 * Total Space Complexity: O(M * N)
 * </pre>
 *
 * M = Number of rows. N = Number of Cols.
 */
class Solution {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int largestIsland(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];
        Map<Integer, Integer> colorSizeMap = new HashMap<>();
        int color = 1;
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    int area = exploreIsland(grid, i, j, visited, color);
                    result = Math.max(result, area);
                    colorSizeMap.put(color++, area);
                } else if (grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> colorsSeen = new HashSet<>();
                    for (int[] d : DIRS) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0
                                || colorsSeen.contains(visited[x][y])) {
                            continue;
                        }
                        if (visited[x][y] == 0) {
                            int tempArea = exploreIsland(grid, x, y, visited, color);
                            colorSizeMap.put(color++, tempArea);
                            area += tempArea;
                        } else {
                            area += colorSizeMap.get(visited[x][y]);
                        }
                        colorsSeen.add(visited[x][y]);
                    }
                    result = Math.max(result, area);
                }
            }
        }

        return result;
    }

    private int exploreIsland(int[][] grid, int row, int col, int[][] visited, int color) {
        visited[row][col] = color;
        int area = 1;
        for (int[] d : DIRS) {
            int x = row + d[0];
            int y = col + d[1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1 && visited[x][y] == 0) {
                area += exploreIsland(grid, x, y, visited, color);
            }
        }

        return area;
    }
}

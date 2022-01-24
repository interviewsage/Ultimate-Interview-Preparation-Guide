// LeetCode Question URL: https://leetcode.com/problems/number-of-closed-islands/
// LeetCode Discuss URL:

/**
 * Refer for other method:
 * https://leetcode.com/problems/number-of-closed-islands/discuss/425150/JavaC++-with-picture-Number-of-Enclaves
 *
 * <pre>
 * Time Complexity: Each cell is visited twice. = O(2 * M * N) = O(M * N)
 *
 * Space Complexity: If all cells are land, then recursion stack will take O(M * N)
 * </pre>
 *
 * M = Number of rows. N = Number of Cols.
 */
class Solution {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length <= 2 || grid[0].length <= 2) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int closedIslands = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0 && isClosedIsland(grid, i, j)) {
                    closedIslands++;
                }
            }
        }

        return closedIslands;
    }

    // private boolean isClosedIsland(int[][] grid, int i, int j) {
    // grid[i][j] = 1;
    // int rows = grid.length;
    // int cols = grid[0].length;
    // boolean isClosed = i != 0 && j != 0 && i != rows-1 && j != cols-1;

    // for (int[] d : DIRS) {
    // int x = i + d[0];
    // int y = j + d[1];
    // if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 0) {
    // isClosed &= isClosedIsland(grid, x, y);
    // }
    // }

    // return isClosed;
    // }

    private boolean isClosedIsland(int[][] grid, int i, int j) {
        grid[i][j] = 1;
        boolean isClosed = true;

        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                isClosed = false;
                continue;
            }
            if (grid[x][y] == 0) {
                isClosed &= isClosedIsland(grid, x, y);
            }
        }

        return isClosed;
    }
}

class Solution2 {

    int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0 && !touchBoundary(grid, i, j)) {
                    res++;
                }
            }
        }

        return res;
    }

    public boolean touchBoundary(int[][] grid, int i, int j) {
        boolean res = false;
        grid[i][j] = 1;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                res = true;
                continue;
            }

            if (grid[x][y] == 0) {
                res |= touchBoundary(grid, x, y);
            }
        }

        return res;
    }

    // public boolean touchBoundary(int[][] grid, int i, int j) {
    // int rows = grid.length;
    // int cols = grid[0].length;

    // boolean res = i == 0 || j == 0 || i == rows-1 || j == cols-1;
    // grid[i][j] = 1;

    // for (int[] dir : dirs) {
    // int x = i + dir[0];
    // int y = j + dir[1];

    // if (x>=0 && y>=0 && x<rows && y<cols && grid[x][y] == 0) {
    // res |= touchBoundary(grid, x, y);
    // }
    // }

    // return res;
    // }
}
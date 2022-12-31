// LeetCode Question URL: https://leetcode.com/problems/unique-paths-iii/
// LeetCode Discuss URL: https://leetcode.com/problems/unique-paths-iii/discuss/1513906/Java-or-TC:-O(3(R*C))-or-SC:-O(R*C)-or-DFS-solution-with-Backtracking

/**
 * DFS solution with Backtracking
 *
 * Here we are using the input grid array to keep track of visited squares and
 * reset them back while backtracking.
 *
 * <pre>
 * Time Complexity:
 * -> O(R*C) ==> To find the start, end, & number of non-obstacle squares.
 * -> O(4 * 3^(N-2)) ==> DFS Helper will explore all possible paths. At each square
 * (except start & end) we will explore at most 3 directions. At start we will
 * explore 4 directions and at end we will stop the further exploration of that
 * path. Here N is bounded by R*C.
 *
 * Total time complexity: O(R*C + 4 * 3^(N-2)) = O(3^(R*C))
 * </pre>
 *
 * Space Complexity: O(N) --> For recursion stack. Here N is bounded by R*C.
 *
 * R = Number of rows. C = Number of columns. N = Number of non-obstacle
 * squares.
 */
class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid.length * grid[0].length < 2) {
            throw new IllegalArgumentException("Input grid is invalid");
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[] start = null;
        int[] end = null;
        int nonObstacleSquares = 0;

        // Finding the coordinates of Start and End cell.
        // Also, finding the number of Non-Obstacle Squares.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                switch (grid[i][j]) {
                    case 1:
                        // If the input grid has two cells marked as start, then throw exception
                        if (start != null) {
                            throw new IllegalArgumentException("Multiple starting squares");
                        }
                        start = new int[] { i, j };
                        nonObstacleSquares++;
                        break;
                    case 2:
                        // If the input grid has two cells marked as end, then throw exception
                        if (end != null) {
                            throw new IllegalArgumentException("Multiple ending squares");
                        }
                        end = new int[] { i, j };
                        nonObstacleSquares++;
                        break;
                    case 0:
                        nonObstacleSquares++;
                }
            }
        }

        if (start == null || end == null) {
            throw new IllegalArgumentException("Starting / Ending squares not found");
        }
        if (nonObstacleSquares < (Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]) + 1)) {
            return 0;
        }

        return dfsHelper(grid, start[0], start[1], nonObstacleSquares);
    }

    private int dfsHelper(int[][] grid, int row, int col, int nonObstacleSquares) {
        // Found the end square. If number of cells to be visited is 1, then count this
        // path.
        if (grid[row][col] == 2) {
            return nonObstacleSquares == 1 ? 1 : 0;
        }

        int preVal = grid[row][col];
        grid[row][col] = -2;
        int pathCount = 0;

        for (int[] d : DIRS) {
            int x = d[0] + row;
            int y = d[1] + col;

            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] >= 0) {
                pathCount += dfsHelper(grid, x, y, nonObstacleSquares - 1);
            }
        }

        grid[row][col] = preVal;
        return pathCount;
    }
}

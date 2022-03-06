// LeetCode Question URL: https://leetcode.com/problems/best-meeting-point/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find all the row and column indexes that have one in sorted order. Use 1D
 * version to find the |x1 - x2| and |y1 - y2| separately.
 *
 * <pre>
 * Refer:
 * 1. Approach #3 (Sorting) [Accepted] - https://leetcode.com/problems/best-meeting-point/solution/
 * 2. Approach #4 (Collect Coordinates in Sorted Order) [Accepted] - https://leetcode.com/problems/best-meeting-point/solution/
 *
 * Time Complexity: O(2*M*N + M*N/2 + M*N/2) = O(M * N)
 *
 * Space Complexity: O(2 * M*N) = O(M * N)
 * </pre>
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        if (grid.length == 1 && grid[0].length == 1) {
            return 0;
        }

        List<Integer> rows = collectPoints(grid, true);
        if (rows.size() <= 1) {
            return 0;
        }
        List<Integer> cols = collectPoints(grid, false);

        return minDistance1D(rows) + minDistance1D(cols);
    }

    private List<Integer> collectPoints(int[][] grid, boolean rows) {
        List<Integer> points = new ArrayList<>();
        int rowNum = grid.length;
        int colNum = grid[0].length;

        for (int i = 0; i < (rows ? rowNum : colNum); i++) {
            for (int j = 0; j < (rows ? colNum : rowNum); j++) {
                if ((rows && grid[i][j] == 1) || (!rows && grid[j][i] == 1)) {
                    points.add(i);
                }
            }
        }

        return points;
    }

    private int minDistance1D(List<Integer> points) {
        int numPoints = points.size();
        if (points.get(0) == points.get(numPoints - 1)) {
            return 0;
        }

        int dist = 0;
        int start = 0;
        int end = numPoints - 1;
        while (start < end) {
            dist += points.get(end--) - points.get(start++);
        }

        return dist;
    }
}

/**
 * NOT OPTIMIZED SOLUTION
 *
 * Find all the row and column indexes that have one in sorted order. Use 1D
 * version to find the |x1 - x2| and |y1 - y2| separately.
 *
 * <pre>
 * Refer:
 * 1. Approach #3 (Sorting) [Accepted] - https://leetcode.com/problems/best-meeting-point/solution/
 * 2. Approach #4 (Collect Coordinates in Sorted Order) [Accepted] - https://leetcode.com/problems/best-meeting-point/solution/
 *
 * Time Complexity: O(2*M*N + M*N/2 + M*N/2) = O(M * N)
 *
 * Space Complexity: O(2 * M*N) = O(M * N)
 * </pre>
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rowNum = grid.length;
        int colNum = grid[0].length;

        if (rowNum == 1 && colNum == 1) {
            return 0;
        }

        boolean rowsAreLessOrEqual = rowNum <= colNum;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (int i = 0; i < (rowsAreLessOrEqual ? colNum : rowNum); i++) {
            for (int j = 0; j < (rowsAreLessOrEqual ? rowNum : colNum); j++) {
                if (rowsAreLessOrEqual) {
                    if (grid[j][i] == 1) {
                        cols.add(i);
                        rows.add(j);
                    }
                } else {
                    if (grid[i][j] == 1) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }
        }

        if (rows.size() <= 1) {
            return 0;
        }

        if (rowNum > 1 && colNum > 1) {
            Collections.sort(rowsAreLessOrEqual ? rows : cols);
        }

        return minDistance1D(rows) + minDistance1D(cols);
    }

    private int minDistance1D(List<Integer> points) {
        int numPoints = points.size();
        if (points.get(0) == points.get(numPoints - 1)) {
            return 0;
        }

        int dist = 0;
        int start = 0;
        int end = numPoints - 1;
        while (start < end) {
            dist += points.get(end--) - points.get(start++);
        }

        return dist;
    }
}

class Solution3 {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);

        return minDistance1D(rows) + minDistance1D(cols);
    }

    private int minDistance1D(List<Integer> points) {
        int dist = 0;
        int start = 0;
        int end = points.size() - 1;

        while (start < end) {
            dist += points.get(end) - points.get(start);
            start++;
            end--;
        }

        return dist;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }
        return cols;
    }
}
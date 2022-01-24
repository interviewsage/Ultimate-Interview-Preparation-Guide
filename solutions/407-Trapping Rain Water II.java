// LeetCode Question URL: https://leetcode.com/problems/trapping-rain-water-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Priority Queue
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/trapping-rain-water-ii/discuss/89461/Java-solution-using-PriorityQueue
 * 2. https://leetcode.com/problems/trapping-rain-water-ii/discuss/89461/Java-solution-using-PriorityQueue/202680
 * 3. https://leetcode.com/problems/trapping-rain-water-ii/discuss/89495/How-to-get-the-solution-to-2-D-%22Trapping-Rain-Water%22-problem-from-1-D-case#:~:text=The%20time%20complexity,O(mnlog(mn)).
 * </pre>
 *
 * Time Complexity: O(R*C * log(R*C))
 *
 * Space Complexity: O(R*C)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }

        int rows = heightMap.length;
        int cols = heightMap[0].length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] visited = new boolean[rows][cols];
        int waterTrapped = 0;

        for (int i = 0; i < rows; i++) {
            visited[i][0] = true;
            queue.offer(new int[] { i, 0, heightMap[i][0] });
            visited[i][cols - 1] = true;
            queue.offer(new int[] { i, cols - 1, heightMap[i][cols - 1] });
        }
        for (int j = 1; j < cols - 1; j++) {
            visited[0][j] = true;
            queue.offer(new int[] { 0, j, heightMap[0][j] });
            visited[rows - 1][j] = true;
            queue.offer(new int[] { rows - 1, j, heightMap[rows - 1][j] });
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                if (x < 0 || y < 0 || x >= rows || y >= cols || visited[x][y]) {
                    continue;
                }

                int h = heightMap[x][y];
                if (h < cur[2]) {
                    waterTrapped += cur[2] - h;
                    h = cur[2];
                }
                visited[x][y] = true;
                queue.offer(new int[] { x, y, h });
            }
        }

        return waterTrapped;
    }
}

/**
 * Priority Queue
 *
 * Refer:
 * https://leetcode.com/problems/trapping-rain-water-ii/discuss/89461/Java-solution-using-PriorityQueue
 *
 * Time Complexity: O(R*C * log(R*C))
 *
 * Space Complexity: O(R*C)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution2 {
    class Cell implements Comparable<Cell> {
        int r;
        int c;
        int h;

        Cell(int _r, int _c, int _h) {
            r = _r;
            c = _c;
            h = _h;
        }

        @Override
        public int compareTo(Cell c) {
            return this.h - c.h;
        }
    }

    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }

        int rows = heightMap.length;
        int cols = heightMap[0].length;

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            pq.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
            visited[i][cols - 1] = true;
        }
        for (int i = 0; i < cols; i++) {
            pq.offer(new Cell(0, i, heightMap[0][i]));
            visited[0][i] = true;
            pq.offer(new Cell(rows - 1, i, heightMap[rows - 1][i]));
            visited[rows - 1][i] = true;
        }

        int water = 0;

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();

            for (int[] d : dirs) {
                int x = cell.r + d[0];
                int y = cell.c + d[1];
                if (x < 0 || y < 0 || x >= rows || y >= cols || visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                water += Math.max(0, cell.h - heightMap[x][y]);
                pq.offer(new Cell(x, y, Math.max(heightMap[x][y], cell.h)));
            }
        }

        return water;
    }
}
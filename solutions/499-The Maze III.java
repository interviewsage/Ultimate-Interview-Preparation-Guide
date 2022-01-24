// LeetCode Question URL: https://leetcode.com/problems/the-maze-iii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = TN
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(TN * max(M,N))
 * TN = Total nodes bounded by O(M*N). (Need to check & confirm)
 *
 * Also, need to check the time complexity of compareTo
 * </pre>
 *
 * Space Complexity: O(TN + M*N + M*N*Size of path strings) -> Queue, Visited,
 * All Path Strings
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {

    private static final int[][] DIRS = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final char[] CH_DIRS = new char[] { 'u', 'd', 'l', 'r' };

    class Point implements Comparable<Point> {
        int x;
        int y;
        int l;
        String path;

        public Point(int x, int y, int l, String path) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.path = path;
        }

        @Override
        public int compareTo(Point p) {
            return this.l != p.l ? this.l - p.l : this.path.compareTo(p.path);
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || ball == null || hole == null || maze.length == 0 || maze[0].length == 0
                || maze[ball[0]][ball[1]] == 1 || maze[hole[0]][hole[1]] == 1) {
            return "impossible";
        }
        if (ball[0] == hole[0] && ball[1] == hole[1]) {
            return "";
        }

        int rows = maze.length;
        int cols = maze[0].length;
        Queue<Point> queue = new LinkedList<>();
        Point[][] visited = new Point[rows][cols];
        visited[ball[0]][ball[1]] = new Point(ball[0], ball[1], 0, "");
        queue.offer(visited[ball[0]][ball[1]]);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = cur.x + DIRS[i][0];
                int y = cur.y + DIRS[i][1];
                int l = cur.l + 1;

                while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1 && !(x == hole[0] && y == hole[1])) {
                    x += DIRS[i][0];
                    y += DIRS[i][1];
                    l++;
                }

                if (x == hole[0] && y == hole[1]) {
                    Point newPoint = new Point(x, y, l, cur.path + CH_DIRS[i]);
                    if (visited[x][y] == null || newPoint.compareTo(visited[x][y]) < 0) {
                        visited[x][y] = newPoint;
                    }
                    continue;
                }

                x -= DIRS[i][0];
                y -= DIRS[i][1];
                l--;
                Point newPoint = new Point(x, y, l, cur.path + CH_DIRS[i]);
                if (visited[x][y] == null || newPoint.compareTo(visited[x][y]) < 0) {
                    visited[x][y] = newPoint;
                    queue.offer(newPoint);
                }
            }
        }

        return visited[hole[0]][hole[1]] == null ? "impossible" : visited[hole[0]][hole[1]].path;
    }
}

/**
 * DFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = TN
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(TN * max(M,N))
 * TN = Total nodes bounded by O(M*N). (Need to check & confirm)
 *
 * Also, need to check the time complexity of compareTo
 * </pre>
 *
 * Space Complexity: O(TN + M*N + M*N*Size of path strings) -> Recursion Stack,
 * Visited, All Path Strings
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {

    private static final int[][] DIRS = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final char[] CH_DIRS = new char[] { 'u', 'd', 'l', 'r' };

    class Point implements Comparable<Point> {
        int x;
        int y;
        int l;
        String path;

        public Point(int x, int y, int l, String path) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.path = path;
        }

        @Override
        public int compareTo(Point p) {
            return this.l != p.l ? this.l - p.l : this.path.compareTo(p.path);
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || ball == null || hole == null || maze.length == 0 || maze[0].length == 0
                || maze[ball[0]][ball[1]] == 1 || maze[hole[0]][hole[1]] == 1) {
            return "impossible";
        }
        if (ball[0] == hole[0] && ball[1] == hole[1]) {
            return "";
        }

        Point[][] visited = new Point[maze.length][maze[0].length];
        dfsHelper(maze, new Point(ball[0], ball[1], 0, ""), hole, visited);
        return visited[hole[0]][hole[1]] == null ? "impossible" : visited[hole[0]][hole[1]].path;
    }

    private void dfsHelper(int[][] maze, Point cur, int[] hole, Point[][] visited) {
        visited[cur.x][cur.y] = cur;
        for (int i = 0; i < 4; i++) {
            int x = cur.x + DIRS[i][0];
            int y = cur.y + DIRS[i][1];
            int l = cur.l + 1;

            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] != 1
                    && !(x == hole[0] && y == hole[1])) {
                x += DIRS[i][0];
                y += DIRS[i][1];
                l++;
            }

            if (x == hole[0] && y == hole[1]) {
                Point newPoint = new Point(x, y, l, cur.path + CH_DIRS[i]);
                if (visited[x][y] == null || newPoint.compareTo(visited[x][y]) < 0) {
                    visited[x][y] = newPoint;
                }
                continue;
            }

            x -= DIRS[i][0];
            y -= DIRS[i][1];
            l--;
            Point newPoint = new Point(x, y, l, cur.path + CH_DIRS[i]);
            if (visited[x][y] == null || newPoint.compareTo(visited[x][y]) < 0) {
                dfsHelper(maze, newPoint, hole, visited);
            }
        }
    }
}

/**
 * BFS Graph Traversal + Using Dijkstra Algorithm. Skip this solution.
 *
 * Time Complexity: O(M * N * log(M*N) * max(M,N))
 *
 * Space Complexity: O(M * N)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution3 {

    class Point implements Comparable<Point> {
        int x;
        int y;
        int l;
        String path;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.l = Integer.MAX_VALUE;
            this.path = "";
        }

        public Point(int x, int y, int l, String path) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.path = path;
        }

        public int compareTo(Point p) {
            if (this.l != p.l) {
                return this.l - p.l;
            } else {
                return this.path.compareTo(p.path);
            }
        }
    }

    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final char[] chDirs = new char[] { 'u', 'd', 'l', 'r' };

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 || ball == null || hole == null
                || maze[ball[0]][ball[1]] == 1 || maze[hole[0]][hole[1]] == 1) {
            return "impossible";
        }

        if (ball[0] == hole[0] && ball[1] == hole[1]) {
            return "";
        }

        int rows = maze.length;
        int cols = maze[0].length;

        PriorityQueue<Point> pq = new PriorityQueue<>();

        Point[][] visited = new Point[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = new Point(i, j);
            }
        }

        visited[ball[0]][ball[1]].l = 0;
        pq.offer(visited[ball[0]][ball[1]]);

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int x = cur.x;
                int y = cur.y;
                int l = cur.l;

                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0 && !(x == hole[0] && y == hole[1])) {
                    x += dirs[i][0];
                    y += dirs[i][1];
                    l++;
                }

                Point newPoint = new Point(x, y, l, cur.path + chDirs[i]);

                if (x == hole[0] && y == hole[1]) {
                    if (newPoint.compareTo(visited[x][y]) < 0) {
                        visited[x][y] = newPoint;
                    }
                    continue;
                }

                newPoint.x -= dirs[i][0];
                newPoint.y -= dirs[i][1];
                newPoint.l--;

                if (newPoint.compareTo(visited[newPoint.x][newPoint.y]) >= 0) {
                    continue;
                }

                visited[newPoint.x][newPoint.y] = newPoint;
                pq.offer(newPoint);
            }
        }

        return visited[hole[0]][hole[1]].l == Integer.MAX_VALUE ? "impossible" : visited[hole[0]][hole[1]].path;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/the-maze/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = M * N
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(M * N * max(M,N))
 * </pre>
 *
 * Space Complexity: O(2 * M * N) -> Visited, Queue
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || maze.length == 0 || maze[0].length == 0
                || maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        int rows = maze.length;
        int cols = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(new StringBuilder().append(start[0]).append('.').append(start[1]).toString());

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];

                while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
                    x += d[0];
                    y += d[1];
                }

                // Move back to valid cell.
                x -= d[0];
                y -= d[1];

                if (x == destination[0] && y == destination[1]) {
                    return true;
                }
                if (visited.add(new StringBuilder().append(x).append('.').append(y).toString())) {
                    queue.offer(new int[] { x, y });
                }
            }
        }

        return false;
    }
}

/**
 * BFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = M * N
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(M * N * max(M,N))
 * </pre>
 *
 * Space Complexity: O(M * N)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 || start == null || dest == null
                || maze[start[0]][start[1]] == 1 || maze[dest[0]][dest[1]] == 1) {
            return false;
        }
        if (start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }

        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : dirs) {
                int x = cur[0];
                int y = cur[1];

                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                    x += d[0];
                    y += d[1];
                }

                // Move back to valid cell.
                x -= d[0];
                y -= d[1];

                if (visited[x][y]) {
                    continue;
                }

                if (x == dest[0] && y == dest[1]) {
                    return true;
                }

                visited[x][y] = true;
                queue.offer(new int[] { x, y });
            }
        }

        return false;
    }
}

/**
 * DFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = M * N
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(M * N * max(M,N))
 * </pre>
 *
 * Space Complexity: O(2 * M * N) -> Recursion, Visited
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution3 {
    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || maze.length == 0 || maze[0].length == 0
                || maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        return dfsHelper(maze, start, destination, new HashSet<>());
    }

    private boolean dfsHelper(int[][] maze, int[] cur, int[] destination, Set<String> visited) {
        if (cur[0] == destination[0] && cur[1] == destination[1]) {
            return true;
        }
        String key = new StringBuilder().append(cur[0]).append('.').append(cur[1]).toString();
        if (!visited.add(key)) {
            return false;
        }

        for (int[] d : DIRS) {
            int x = cur[0] + d[0];
            int y = cur[1] + d[1];
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] != 1) {
                x += d[0];
                y += d[1];
            }
            // Move back to valid cell.
            x -= d[0];
            y -= d[1];

            if (dfsHelper(maze, new int[] { x, y }, destination, visited)) {
                return true;
            }
        }

        return false;
    }
}

/**
 * DFS Solution
 *
 * <pre>
 * Time Complexity: O(V + E)
 * V = M * N
 * E = 4 * M*N. And it take max(M, N) to process each edge.
 *
 * So Total Time Complexity = O(M * N * max(M,N))
 * </pre>
 *
 * Space Complexity: O(2 * M * N) -> Recursion, Visited
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution4 {
    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 || start == null || dest == null
                || maze[start[0]][start[1]] == 1 || maze[dest[0]][dest[1]] == 1) {
            return false;
        }
        if (start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return dfsHelper(maze, start, dest, visited);
    }

    private boolean dfsHelper(int[][] maze, int[] start, int[] dest, boolean[][] visited) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }

        visited[start[0]][start[1]] = true;

        for (int[] d : dirs) {
            int x = start[0];
            int y = start[1];

            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += d[0];
                y += d[1];
            }

            // Move back to valid cell.
            x -= d[0];
            y -= d[1];

            if (dfsHelper(maze, new int[] { x, y }, dest, visited)) {
                return true;
            }
        }

        return false;
    }
}

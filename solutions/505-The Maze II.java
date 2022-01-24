// LeetCode Question URL: https://leetcode.com/problems/the-maze-ii/
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
 * </pre>
 *
 * Space Complexity: O(TN + M*N) -> Queue, Visited
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution1 {
    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || maze.length == 0 || maze[0].length == 0
                || maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) {
            return -1;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }

        int rows = maze.length;
        int cols = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        Integer[][] visited = new Integer[rows][cols];
        queue.offer(start);
        visited[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                int l = 1;

                while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
                    x += d[0];
                    y += d[1];
                    l++;
                }

                // Move back to valid cell.
                x -= d[0];
                y -= d[1];
                l--;

                if (visited[x][y] == null || visited[x][y] > visited[cur[0]][cur[1]] + l) {
                    visited[x][y] = visited[cur[0]][cur[1]] + l;
                    if (x != destination[0] || y != destination[1]) {
                        queue.offer(new int[] { x, y });
                    }
                }
            }
        }

        return visited[destination[0]][destination[1]] == null ? -1 : visited[destination[0]][destination[1]];
    }
}

/**
 * BFS Graph Traversal + Using Dijkstra Algorithm
 *
 * <pre>
 * Time Complexity:
 *
 * Total Node (TN) = O(M * N) (Need to check & confirm)
 *
 * Inside While loop: O(log(M*N) + 4 * (max(M,N) + log(M*N)))
 * While loop runs for all nodes (TN)
 *
 * Thus Total Time Complexity: O(TN * (max(M,N) + log(M*N)))
 * </pre>
 *
 * Space Complexity: O(TN)
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution2 {
    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || maze.length == 0 || maze[0].length == 0
                || maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) {
            return -1;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }

        int rows = maze.length;
        int cols = maze[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] visited = new boolean[rows][cols];
        pq.offer(new int[] { start[0], start[1], 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }
            visited[cur[0]][cur[1]] = true;

            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                int l = 1;
                while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
                    x += d[0];
                    y += d[1];
                    l++;
                }
                x -= d[0];
                y -= d[1];
                l--;
                if (!visited[x][y]) {
                    pq.offer(new int[] { x, y, l + cur[2] });
                }
            }
        }

        return -1;
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
 * </pre>
 *
 * Space Complexity: O(TN + M*N) -> Recursion, Visited
 *
 * M = Number of rows. N = Number of columns.
 */
class Solution3 {
    private static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || maze.length == 0 || maze[0].length == 0
                || maze[start[0]][start[1]] == 1 || maze[destination[0]][destination[1]] == 1) {
            return -1;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }

        Integer[][] visited = new Integer[maze.length][maze[0].length];
        visited[start[0]][start[1]] = 0;
        dfsHelper(maze, start, destination, visited);
        return visited[destination[0]][destination[1]] == null ? -1 : visited[destination[0]][destination[1]];
    }

    private void dfsHelper(int[][] maze, int[] cur, int[] destination, Integer[][] visited) {
        for (int[] d : DIRS) {
            int x = cur[0] + d[0];
            int y = cur[1] + d[1];
            int l = 1;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] != 1) {
                x += d[0];
                y += d[1];
                l++;
            }
            // Move back to valid cell.
            x -= d[0];
            y -= d[1];
            l--;

            if (visited[x][y] == null || visited[x][y] > visited[cur[0]][cur[1]] + l) {
                visited[x][y] = visited[cur[0]][cur[1]] + l;
                dfsHelper(maze, new int[] { x, y }, destination, visited);
            }
        }
    }
}
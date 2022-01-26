// LeetCode Question URL: https://leetcode.com/problems/minimum-knight-moves/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-knight-moves/discuss/401580/Clean-Java-BFS-solution
 * 2. https://leetcode.com/problems/minimum-knight-moves/discuss/401580/Clean-Java-BFS-solution/1164522
 * 3. https://leetcode.com/problems/minimum-knight-moves/discuss/401580/Clean-Java-BFS-solution/464187
 * 4. Approach 3: DFS (Depth-First Search) with Memoization: https://leetcode.com/problems/minimum-knight-moves/solution/
 * </pre>
 */

class Solution1 {
    private static final int[][] DIRS = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 },
            { -1, 2 } };

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[2]);
        Set<String> visited = new HashSet<>();
        visited.add("0_0");
        int jumps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            jumps++;

            while (levelSize-- > 0) {
                int[] cur = queue.poll();

                for (int[] d : DIRS) {
                    int i = cur[0] + d[0];
                    int j = cur[1] + d[1];

                    if (i == x && j == y) {
                        return jumps;
                    }
                    if (i >= -1 && j >= -1 && visited.add(i + "_" + j)) {
                        queue.offer(new int[] { i, j });
                    }
                }
            }
        }

        return -1;
    }
}

class Solution2 {
    private static final int[][] DIRS = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 },
            { -1, 2 } };

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[2]);
        Set<String> visited = new HashSet<>();
        visited.add("0_0");
        int jumps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            jumps++;

            while (levelSize-- > 0) {
                int[] cur = queue.poll();

                for (int[] d : DIRS) {
                    int i = Math.abs(cur[0] + d[0]);
                    int j = Math.abs(cur[1] + d[1]);

                    if (i == x && j == y) {
                        return jumps;
                    }
                    if (visited.add(i + "_" + j)) {
                        queue.offer(new int[] { i, j });
                    }
                }
            }
        }

        return -1;
    }
}

class Solution3 {
    private static final int[][] DIRS = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 },
            { -1, 2 } };

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[2]);
        Set<String> visited = new HashSet<>();
        visited.add("0_0");
        int jumps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            jumps++;

            while (levelSize-- > 0) {
                int[] cur = queue.poll();

                for (int[] d : DIRS) {
                    int i = Math.abs(cur[0] + d[0]);
                    int j = Math.abs(cur[1] + d[1]);
                    if (i < j) {
                        int temp = i;
                        i = j;
                        j = temp;
                    }

                    if (i == x && j == y) {
                        return jumps;
                    }
                    if (visited.add(i + "_" + j)) {
                        queue.offer(new int[] { i, j });
                    }
                }
            }
        }

        return -1;
    }
}

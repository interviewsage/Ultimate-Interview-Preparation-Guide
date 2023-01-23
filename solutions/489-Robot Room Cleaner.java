// LeetCode Question URL: https://leetcode.com/problems/robot-room-cleaner/
// LeetCode Discuss URL:

import java.util.*;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current
    // cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

/**
 * <pre>
 * Refer:
 * 1. Optimal Solution: https://leetcode.com/problems/robot-room-cleaner/discuss/153530/DFS-Logical-Thinking
 * 2. TC & SC Explanation: https://leetcode.com/problems/robot-room-cleaner/solution/
 * </pre>
 */

/**
 * Backtracking DFS
 *
 * Time Complexity: O(4 * Total Number of reachable cells)
 * In worst case, Total Number of reachable cells = N - M;
 *
 * Space Complexity: O(Total Number of reachable cells) = O(HashSet + Recursion
 * Depth)
 *
 * N = Number of cells in the grid. M = Number of bricks
 */
class Solution1 {
    private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public void cleanRoom(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException("Input is null");
        }

        dfsHelper(robot, new HashSet<>(), 0, 0, 0, "0,0");
    }

    private void dfsHelper(Robot robot, Set<String> visited, int r, int c, int dir, String pos) {
        visited.add(pos);
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int x = r + DIRS[dir][0];
            int y = c + DIRS[dir][1];
            String newPos = new StringBuilder().append(x).append(',').append(y).toString();

            if (!visited.contains(newPos) && robot.move()) {
                dfsHelper(robot, visited, x, y, dir, newPos);

                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }

            dir = (dir + 1) % 4;
            robot.turnRight();
        }
    }
}

class Solution2 {
    public void cleanRoom(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException("Input is null");
        }

        dfsHelper(robot, new HashSet<>(), 0, 0, 0, "0,0");
    }

    private void dfsHelper(Robot robot, Set<String> visited, int i, int j, int dir, String pos) {
        visited.add(pos);
        robot.clean();

        for (int k = 0; k < 4; k++) {
            int x = i;
            int y = j;

            switch (dir) {
                case 0:
                    x--;
                    break; // UP
                case 90:
                    y++;
                    break; // RIGHT
                case 180:
                    x++;
                    break; // DOWN
                case 270:
                    y--; // LEFT
            }

            String newPos = x + "," + y;
            if (!visited.contains(newPos) && robot.move()) {
                dfsHelper(robot, visited, x, y, dir, newPos);
            }

            // Rotating by 90 Degree
            robot.turnRight();
            dir = (dir + 90) % 360;
        }

        // Backtracking to original position
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}

class Solution3 {
    public void cleanRoom(Robot robot) {
        if (robot == null) {
            return;
        }

        HashSet<String> visited = new HashSet<>();

        dfsHelper(robot, visited, 0, 0, 0);
    }

    private void dfsHelper(Robot robot, HashSet<String> visited, int i, int j, int dir) {
        String pos = i + "," + j;

        if (visited.contains(pos)) {
            return;
        }

        robot.clean();
        visited.add(pos);

        for (int k = 0; k < 4; k++) {
            if (robot.move()) {
                int x = i;
                int y = j;

                if (dir == 0) {
                    // UP
                    x--;
                } else if (dir == 90) {
                    // RIGHT
                    y++;
                } else if (dir == 180) {
                    // DOWN
                    x++;
                } else {
                    // LEFT
                    y--;
                }

                dfsHelper(robot, visited, x, y, dir);

                // Moving to the original position.
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }

            // Rotating by 90 degree.
            robot.turnRight();
            dir += 90;
            dir %= 360;
        }
    }
}

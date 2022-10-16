// LeetCode Question URL: https://leetcode.com/problems/island-perimeter
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/island-perimeter/discuss/95001/clear-and-easy-java-solution
 *
 * Loop over the matrix and count the number of islands. If the current dot is
 * an island, count if it has any left neighbor or up neighbor. The result is
 * islands*4 - neighbors*2.
 *
 * Time Complexity: O(R * C)
 *
 * Space Complexity: O(1)
 *
 * R = Number of rows. C = Number of columns.
 */
class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int land = 0;
        int overlapEdges = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        overlapEdges++;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        overlapEdges++;
                    }
                }
            }
        }

        return 4 * land - 2 * overlapEdges;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/paint-house/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of houses.
 */
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int[] minCosts = costs[0];

        for (int i = 1; i < costs.length; i++) {
            int[] newMinCosts = new int[3];
            newMinCosts[0] = costs[i][0] + Math.min(minCosts[1], minCosts[2]);
            newMinCosts[1] = costs[i][1] + Math.min(minCosts[0], minCosts[2]);
            newMinCosts[2] = costs[i][2] + Math.min(minCosts[0], minCosts[1]);
            minCosts = newMinCosts;
        }

        return Math.min(minCosts[0], Math.min(minCosts[1], minCosts[2]));
    }
}

class Solution2 {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int[] minCosts = costs[0];

        for (int i = 1; i < costs.length; i++) {
            int[] nextMinCosts = new int[3];
            for (int j = 0; j < 3; j++) {
                nextMinCosts[j] = costs[i][j] + Math.min(minCosts[(j + 1) % 3], minCosts[(j + 2) % 3]);
            }
            minCosts = nextMinCosts;
        }

        return Math.min(minCosts[0], Math.min(minCosts[1], minCosts[2]));
    }
}

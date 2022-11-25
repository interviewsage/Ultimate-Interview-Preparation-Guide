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
        if (costs == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = costs.length;
        if (len == 0) {
            return 0;
        }

        int minCostRed = costs[0][0];
        int minCostBlue = costs[0][1];
        int minCostGreen = costs[0][2];

        for (int i = 1; i < len; i++) {
            int curCostRed = costs[i][0] + Math.min(minCostBlue, minCostGreen);
            int curCostBlue = costs[i][1] + Math.min(minCostRed, minCostGreen);
            int curCostGreen = costs[i][2] + Math.min(minCostRed, minCostBlue);
            minCostRed = curCostRed;
            minCostBlue = curCostBlue;
            minCostGreen = curCostGreen;
        }

        return Math.min(minCostRed, Math.min(minCostBlue, minCostGreen));
    }
}

class Solution2 {
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

class Solution3 {
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

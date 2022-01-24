// LeetCode Question URL: https://leetcode.com/problems/paint-house-ii/
// LeetCode Discuss URL:

/**
 * Dynamic Programming
 *
 * Here we are looking for previous min, secMin and minIdx.
 *
 * Time Complexity: O(N * K)
 *
 * Space Complexity: O(1)
 *
 * N = Number of houses. K = Number of colors.
 */
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int minCost = 0;
        int minCostColor = -1;
        int secondMinCost = 0;

        for (int i = 0; i < costs.length; i++) {
            int newMinCost = Integer.MAX_VALUE;
            int newMinCostColor = -1;
            int newSecondMinCost = Integer.MAX_VALUE;

            for (int j = 0; j < costs[i].length; j++) {
                int curCost = costs[i][j] + (minCostColor == j ? secondMinCost : minCost);
                if (curCost < newMinCost) {
                    newSecondMinCost = newMinCost;
                    newMinCost = curCost;
                    newMinCostColor = j;
                } else if (curCost < newSecondMinCost) {
                    newSecondMinCost = curCost;
                }
            }

            minCost = newMinCost;
            minCostColor = newMinCostColor;
            secondMinCost = newSecondMinCost;
        }

        return minCost;
    }
}

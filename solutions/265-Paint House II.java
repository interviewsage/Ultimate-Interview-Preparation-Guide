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
        if (costs == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = costs.length;
        if (len == 0) {
            return 0;
        }
        int k = costs[0].length;
        if (k <= 1) {
            throw new IllegalArgumentException("Invalid Input");
        }

        int minCost = 0;
        int minCostColor = -1;
        int secondMinCost = 0;

        for (int[] cost : costs) {
            Integer curMinCost = null;
            int curMinCostColor = -1;
            Integer curSecondMinCost = null;

            for (int i = 0; i < k; i++) {
                int c = cost[i] + (i != minCostColor ? minCost : secondMinCost);
                if (curMinCost == null || c < curMinCost) {
                    curSecondMinCost = curMinCost;
                    curMinCostColor = i;
                    curMinCost = c;
                } else if (curSecondMinCost == null || c < curSecondMinCost) {
                    curSecondMinCost = c;
                }
            }

            minCost = curMinCost;
            minCostColor = curMinCostColor;
            secondMinCost = curSecondMinCost;
        }

        return minCost;
    }
}

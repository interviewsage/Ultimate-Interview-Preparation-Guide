// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// LeetCode Discuss URL:

/**
 * One Pass
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of prices array.
 */
class Solution1 {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < len; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }
}

/**
 * Kadane's Algorithm. This is algorithm can be used if difference array of
 * prices is given.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of prices array.
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfitHere = 0;
        int maxProfitSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfitHere = Math.max(0, maxProfitHere + prices[i] - prices[i - 1]);
            maxProfitSoFar = Math.max(maxProfitSoFar, maxProfitHere);
        }

        return maxProfitSoFar;
    }
}

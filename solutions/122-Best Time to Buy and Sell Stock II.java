// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/discuss/1569081/Java-Simple-and-Clean-DP-solutions-for-all-6-Buy-and-Sell-Stock-questions-on-LeetCode

/**
 * One Pass.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of prices array.
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // Checking if we can profit with previous day's price.
            // If yes, then we buy on previous day and sell on current day.
            // Add all such profits to get the total profit.
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }

        return maxProfit;
    }
}

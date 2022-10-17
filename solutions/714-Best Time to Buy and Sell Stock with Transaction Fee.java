// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// LeetCode Discuss URL:

/**
 * Use Wealth instead of profit
 *
 * Refer: 1)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!
 * 2)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
 *
 * At day i, we may buy stock (from previous sell status) or do nothing (from
 * previous buy status): buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
 *
 * OR
 *
 * At day i, we may sell stock (from previous buy status) or do nothing (from
 * previous sell status): sell[i] = Math.max(sell[i - 1], buy[i] + prices[i] -
 * fee);
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of input prices array.
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < prices.length; i++) {

            // buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i])
            buy = Math.max(buy, sell - prices[i]);

            // sell[i] = Math.max(sell[i - 1], buy[i] + prices[i] - fee)
            sell = Math.max(sell, buy + prices[i] - fee);
        }

        return sell;
    }
}

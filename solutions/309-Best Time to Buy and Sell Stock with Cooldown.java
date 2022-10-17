// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// LeetCode Discuss URL:

/**
 * Use Wealth instead of profit
 *
 * Refer: 1)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process/173646
 * 2)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
 *
 * On any i-th day, we can buy, sell or cooldown
 *
 * To calculate sell[i]: If we sell on the i-th day, the maximum profit is
 * buy[i] + prices[i], because we have to buy before we can sell. If we cooldown
 * on the i-th day, the maximum profit is same as sell[i - 1] since we did
 * nothing on the i-th day. So sell[i] is the larger one of (buy[i] + prices[i],
 * sell[i - 1])
 *
 * To calculate buy[i]: If we buy on the i-th day, the maximum profit is sell[i
 * - 2] - prices[i], because on the (i-1)th day we can only cooldown. If we
 * cooldown on the i-th day, the maximum profit is same as buy[i - 1] since we
 * did nothing on the i-th day. So buy[i] is the larger one of (sell[i - 2] -
 * prices[i], buy[i - 1])
 *
 * Time Complexity: O(N).
 *
 * Space Complexity: O(1)
 *
 * N = Length of input prices array.
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int buy = -prices[0];
        int sell = 0;
        int prevSell = 0;

        for (int i = 1; i < prices.length; i++) {
            // buy[i] = Math.max(sell[i-2]-prices[i], buy[i-1])
            // sell[i-2]-prices[i] -> Buy after a 1 day cooldown
            // buy[i-1] -> cooldown
            buy = Math.max(buy, prevSell - prices[i]);

            // sell[i-1];
            prevSell = sell;

            // sell[i] = Math.max(buy[i]+prices, sell[i-1])
            // buy[i]+prices -> sell the stock.
            // sell[i-1] -> cooldown
            sell = Math.max(sell, buy + prices[i]);
        }

        return sell;
    }
}

// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1).
 * Also check :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1)./37429
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of the prices array.
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

        int buy1 = -prices[0];
        int sell1 = buy1 + prices[0];
        int buy2 = sell1 - prices[0];
        int sell2 = buy2 + prices[0];

        for (int i = 1; i < len; i++) {
            // The maximum money left after buying 1st stock.
            buy1 = Math.max(buy1, -prices[i]);

            // The maximum money left after selling 1st stock.
            sell1 = Math.max(sell1, buy1 + prices[i]);

            // The maximum money left after buying 2nd stock.
            buy2 = Math.max(buy2, sell1 - prices[i]);

            // The maximum money left after selling 2nd stock.
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return sell2;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1).
 * Also check :
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1)./37429
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of the prices array.
 */
class Solution2 {
    public int maxProfit(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = prices.length;
        if (len <= 1) {
            return 0;
        }

        int buy1 = -prices[0];
        int sell1 = buy1 + prices[0];
        int buy2 = sell1 - prices[0];
        int sell2 = buy2 + prices[0];

        for (int i = 1; i < len; i++) {
            int prevBuy1 = buy1;
            int prevSell1 = sell1;
            int prevBuy2 = buy2;

            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prevBuy1 + prices[i]);
            buy2 = Math.max(buy2, prevSell1 - prices[i]);
            sell2 = Math.max(sell2, prevBuy2 + prices[i]);
        }

        return Math.max(sell1, sell2);
    }
}

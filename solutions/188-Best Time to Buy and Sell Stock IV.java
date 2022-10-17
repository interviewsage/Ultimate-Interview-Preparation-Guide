// LeetCode Question URL: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using same concept discussed in "123. Best Time to Buy and Sell Stock III".
 *
 * For k transactions create two arrays of size k. One for buy and one for sell.
 * The goal is to maximize the wealth at each buy and sell.
 *
 * IMPORTANT.. you cannot start the buy before completing the previous
 * transaction. so for jth buy, use (j-1)th sell.
 *
 * <pre>
 * Time & Space Complexity:
 * - If k >= len/2 ... TC = O(N) & SC = O(1).. this is using quickSolve. Refer "122. Best Time to Buy and Sell Stock II"
 * - If k < len/2 ... TC = O(N*K) & SC = O(2*K) = O(K)
 * </pre>
 *
 * N = Length of input array. k = Number of transactions.
 */
class Solution1 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = prices.length;
        if (len <= 1 || k == 0) {
            return 0;
        }

        if (k >= len / 2) {
            return quickSolve(prices);
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // This loop is for i == 0. It has been merged in below loop
        // for (int j = 1 ; j <= k; j++) {
        // buy[j] = -prices[0];
        // }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 1) {
                    buy[j] = -prices[0];
                }
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }

        return sell[k];
    }

    private int quickSolve(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }
}

/**
 * Replace profit with wealth
 *
 * Refer:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54113/A-Concise-DP-Solution-in-Java/55579
 *
 * dp[i][j] = maximum profit from at most i transactions using prices[0..j]
 *
 * A transaction is defined as one buy + sell.
 *
 * Now on day j, we have two options
 *
 * 1) Do nothing (or buy) which doesn't change the acquired profit : dp[i][j] =
 * dp[i][j-1]
 *
 * 2) Sell the stock: In order to sell the stock, you must've bought it on a day
 * t=[0..j-1]. Maximum profit that can be attained is t:0->j-1
 * max(prices[j]-prices[t]+dp[i-1][t-1]) where prices[j]-prices[t] is the profit
 * from buying on day t and selling on day j. dp[i-1][t] is the maximum profit
 * that can be made with at most i-1 transactions with prices prices[0..t].
 *
 * Time complexity of this approach is O(n^2*k).
 *
 * In order to reduce it to O(nk), we must find t:0->j-1
 * max(prices[j]-prices[t]+dp[i-1][t]) this expression in constant time. If you
 * see carefully,
 *
 * t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t]) is same as
 *
 * prices[j] + t:0->j-1 max(dp[i-1][t]-prices[t])
 *
 * Second part of the above expression maxTemp = t:0->j-1
 * max(dp[i-1][t]-prices[t]) can be included in the dp loop by keeping track of
 * the maximum value till j-1.
 *
 * Time Complexity : O(N*k)
 *
 * Space Complexity: O(N)
 *
 * N = length of the prices array
 */
class Solution2 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k <= 0) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return quickSolve(prices);
        }

        int len = prices.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        for (int i = 0; i < k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp2[j] = Math.max(dp2[j - 1], prices[j] + tempMax);
                tempMax = Math.max(tempMax, dp1[j] - prices[j]);
            }
            dp1 = Arrays.copyOf(dp2, len);
        }

        return dp2[len - 1];
    }

    private int quickSolve(int[] prices) {
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }
}

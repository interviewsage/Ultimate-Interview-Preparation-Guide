// LeetCode Question URL: https://leetcode.com/problems/coin-change-2/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Knapsack problem
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 * 2) Good Follow-Up if we cannot use the same coin again https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space/103319
 *
 * DP[i][j] - Number combinations that make the amount j using upto ith coins.
 * DP[i][j] = DP[i-1][j] + DP[i][ j-coins[i] ]
 *            DP[i-1][j] ==> Not using ith coin.
 *            DP[i][ j-coins[i] ] ==> Using ith coin. Here j-coins[i] >= 0
 *            DP[0] = 1 (Base Case). There is one way to achieve zero amount by using no coins.
 * </pre>
 *
 * Time Complexity: O(A * C)
 *
 * Space Complexity: O(A)
 *
 * A = Input amount. C = Number of coins.
 */
class Solution1 {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                dp[i] += dp[i - c];
            }
        }

        return dp[amount];
    }
}

/**
 * Knapsack problem. Recursive Top Down
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 * 2) Good Follow-Up if we cannot use the same coin again https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space/103319
 *
 * DP[i][j] - Number combinations that make the amount j using upto ith coins.
 * DP[i][j] = DP[i-1][j] + DP[i][ j-coins[i] ]
 *            DP[i-1][j] ==> Not using ith coin.
 *            DP[i][ j-coins[i] ] ==> Using ith coin. Here j-coins[i] >= 0
 *            DP[0] = 1 (Base Case). There is one way to achieve zero amount by using no coins.
 * </pre>
 *
 * Time Complexity: O(A * C)
 *
 * Space Complexity: O(A * C) --> Taken by the HashMap and Recursion Depth
 *
 * A = Input amount. C = Number of coins.
 */
class Solution2 {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }

        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        return changeHelper(amount, coins, coins.length - 1, map);
    }

    private int changeHelper(int amount, int[] coins, int idx, HashMap<Integer, HashMap<Integer, Integer>> map) {
        if (amount < 0 || idx < 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        if (map.containsKey(idx) && map.get(idx).containsKey(amount)) {
            return map.get(idx).get(amount);
        }

        int count = changeHelper(amount - coins[idx], coins, idx, map) + changeHelper(amount, coins, idx - 1, map);
        map.putIfAbsent(idx, new HashMap<>());
        map.get(idx).put(amount, count);
        return count;
    }
}

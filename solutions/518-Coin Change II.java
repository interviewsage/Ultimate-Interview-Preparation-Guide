// LeetCode Question URL: https://leetcode.com/problems/coin-change-2/
// LeetCode Discuss URL: https://leetcode.com/problems/coin-change-2/discuss/1549630/Java-or-TC:-O(C*A)-or-SC:-O(A)-or-DP-(Botton-Up-and-Top-Down-approaches)

import java.util.*;

/**
 * Knapsack problem - Bottom up (Iterative Approach)
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 * 2) Good Follow-Up if we cannot use the same coin again https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space/103319
 * This follow-up's logic is same as 416-Partition Equal Subset Sum question.
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
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return 0;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? 1 : 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins) {
            for (int s = c; s <= amount; s++) {
                dp[s] += dp[s - c];
            }
        }

        return dp[amount];
    }
}

/**
 * Knapsack problem - Bottom up (Iterative Approach)
 * Added an early exit while calculating DP by sorting the coins array.
 *
 * <pre>
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
class Solution2 {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return 0;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? 1 : 0;
        }

        Arrays.sort(coins);
        if (amount < coins[0]) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins) {
            if (c > amount) {
                break;
            }
            for (int s = c; s <= amount; s++) {
                dp[s] += dp[s - c];
            }
        }

        return dp[amount];
    }
}

/**
 * Knapsack problem - Top Down (Recursive Approach)
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
class Solution3 {
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

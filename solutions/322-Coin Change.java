// LeetCode Question URL: https://leetcode.com/problems/coin-change/
// LeetCode Discuss URL: https://leetcode.com/problems/coin-change/discuss/1549625/Java-or-TC:-O(C*A)-or-SC:-O(A)-or-DP-(Botton-Up-and-Top-Down-approaches)

import java.util.*;

/**
 * Dynamic programming - Bottom up (Iterative Approach)
 *
 * <pre>
 * DP[i][j] - minimum number of coins needed to make amount j using upto ith coins.
 * DP[i][j] = Math.min(DP[i-1][j], DP[i][ j-coins[i] ] + 1)
 *            DP[i-1][j]==> Not using ith coin.
 *            DP[i][ j-coins[i] ] + 1 ==> Using ith coin. j-coins[i] >= 0
 * </pre>
 *
 * Time Complexity: O(C * A)
 *
 * Space Complexity: O(A). DP array.
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution1 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return -1;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? amount / coins[0] : -1;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int c : coins) {
            for (int s = c; s <= amount; s++) {
                if (dp[s - c] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/**
 * Dynamic programming - Bottom up (Iterative Approach)
 *
 * <pre>
 * DP[i][j] - minimum number of coins needed to make amount j using upto ith coins.
 * DP[i][j] = Math.min(DP[i-1][j], DP[i][ j-coins[i] ] + 1)
 *            DP[i-1][j]==> Not using ith coin.
 *            DP[i][ j-coins[i] ] + 1 ==> Using ith coin. j-coins[i] >= 0
 * </pre>
 *
 * Time Complexity: O(C * A)
 *
 * Space Complexity: O(A). DP array.
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution2 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return -1;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? amount / coins[0] : -1;
        }

        int[] dp = new int[amount + 1];

        for (int s = 1; s <= amount; s++) {
            dp[s] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (s >= c && dp[s - c] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/**
 * Dynamic programming - Bottom up (Iterative Approach)
 * Added an early exit while calculating DP by sorting the coins array.
 *
 * <pre>
 * DP[i][j] - minimum number of coins needed to make amount j using upto ith coins.
 * DP[i][j] = Math.min(DP[i-1][j], DP[i][ j-coins[i] ] + 1)
 *            DP[i-1][j]==> Not using ith coin.
 *            DP[i][ j-coins[i] ] + 1 ==> Using ith coin. j-coins[i] >= 0
 * </pre>
 *
 * Time Complexity: O(C*logC + C * A)
 *
 * Space Complexity: O(A). DP array.
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution3 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return -1;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? amount / coins[0] : -1;
        }

        Arrays.sort(coins);
        if (amount < coins[0]) {
            return -1;
        }
        if (amount % coins[numCoins - 1] == 0) {
            return amount / coins[numCoins - 1];
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int c : coins) {
            if (c > amount) {
                break;
            }
            for (int s = c; s <= amount; s++) {
                if (dp[s - c] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/**
 * Dynamic programming - Bottom up (Iterative Approach)
 * Added an early exit while calculating DP by sorting the coins array.
 *
 * <pre>
 * DP[i][j] - minimum number of coins needed to make amount j using upto ith coins.
 * DP[i][j] = Math.min(DP[i-1][j], DP[i][ j-coins[i] ] + 1)
 *            DP[i-1][j]==> Not using ith coin.
 *            DP[i][ j-coins[i] ] + 1 ==> Using ith coin. j-coins[i] >= 0
 * </pre>
 *
 * Time Complexity: O(C*logC + C * A)
 *
 * Space Complexity: O(A). DP array.
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution4 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int numCoins = coins.length;
        if (numCoins == 0) {
            return -1;
        }
        if (numCoins == 1) {
            return amount % coins[0] == 0 ? amount / coins[0] : -1;
        }

        Arrays.sort(coins);
        if (amount < coins[0]) {
            return -1;
        }
        if (amount % coins[numCoins - 1] == 0) {
            return amount / coins[numCoins - 1];
        }

        int[] dp = new int[amount + 1];

        for (int s = 1; s <= amount; s++) {
            dp[s] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (c > s) {
                    break;
                }
                if (dp[s - c] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/**
 * Dynamic programming - Top Down (Recursive Approach)
 *
 * <pre>
 * DP[i][j] - minimum number of coins needed to make amount j using upto ith coins.
 * DP[i][j] = Math.min(DP[i-1][j], DP[i][ j-coins[i] ] + 1)
 *            DP[i-1][j]==> Not using ith coin.
 *            DP[i][ j-coins[i] ] + 1 ==> Using ith coin. j-coins[i] >= 0
 * </pre>
 *
 * Time Complexity: O(C * A)
 *
 * Space Complexity: O(C * A) --> Taken by the HashMap and Recursion Depth
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution5 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        int result = coinChangeHelper(coins, amount, coins.length - 1, map);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * @return Integer.MAX_VALUE if no combination is possible
     */
    private int coinChangeHelper(int[] coins, int amount, int idx, HashMap<Integer, HashMap<Integer, Integer>> map) {
        if (amount < 0 || idx < 0) {
            return Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        if (map.containsKey(idx) && map.get(idx).containsKey(amount)) {
            return map.get(idx).get(amount);
        }

        int usingIthCoin = coinChangeHelper(coins, amount - coins[idx], idx, map);
        int notUsingIthCoin = coinChangeHelper(coins, amount, idx - 1, map);

        // To avoid Integer overflow, check for Integer.MAX_VALUE before adding 1.
        int minCount = Math.min(usingIthCoin == Integer.MAX_VALUE ? Integer.MAX_VALUE : usingIthCoin + 1,
                notUsingIthCoin);

        map.putIfAbsent(idx, new HashMap<>());
        map.get(idx).put(amount, minCount);
        return minCount;
    }
}

/**
 * Dynamic programming - Top Down (Recursive Approach)
 *
 * change[i] - minimum number of coins needed to make change for amount i using
 * given coin denominations. Each value in change array is calculated only once.
 *
 * Time Complexity: O(C * A). In the worst case the recursive tree has height A
 * and the algorithm solves only A sub problems because it caches precalculated
 * solutions in a table. Each sub problem is computed with C iterations, one by
 * each coin denomination. Thus, TC = O(C * A).
 *
 * Space Complexity: O(A). --> Taken by HashMap and Recursion Depth.
 *
 * C = Number of coin denominations. A = Input amount.
 */
class Solution6 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int result = coinChangeHelper(coins, amount, map);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * @return Integer.MAX_VALUE if no combination is possible
     */
    private int coinChangeHelper(int[] coins, int amount, HashMap<Integer, Integer> map) {
        if (amount == 0) {
            return 0;
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        int minCount = Integer.MAX_VALUE;
        for (int c : coins) {
            if (amount >= c) {
                int count = coinChangeHelper(coins, amount - c, map);
                if (count != Integer.MAX_VALUE) {
                    minCount = Math.min(minCount, count + 1);
                }
            }
        }

        map.put(amount, minCount);
        return map.get(amount);
    }
}

/**
 * Found this solution in Submissions Section of LeetCode.
 *
 * Review this.
 */
class SolutionAlternate {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        boolean allMultiply = true;

        for (int i = 1; i < coins.length; ++i) {

            if (coins[i] % coins[0] != 0) {
                allMultiply = false;
                break;
            }
        }

        if (allMultiply && (amount % coins[0]) != 0) {
            return -1;
        }

        int minCount = Integer.MAX_VALUE;
        int[] counts = new int[coins.length];
        int i = coins.length - 1;
        int count = amount / coins[i];
        int amount1 = amount - count * coins[i];

        if (amount1 == 0) {
            return count;
        }

        if (coins[0] * (count + 1) == amount) {
            return count + 1;
        }

        if (count == amount / coins[0]) {
            return -1;
        }

        counts[i] = count;
        amount = amount1;

        while (true) {
            if (count >= minCount || i <= 0) {
                count -= counts[i];
                amount += counts[i] * coins[i];

                do {
                    ++i;

                    if (i >= coins.length) {
                        return minCount < Integer.MAX_VALUE ? minCount : -1;
                    }

                } while (counts[i] == 0);
                --counts[i];
                --count;
                amount += coins[i];
            }

            --i;
            counts[i] = 0;

            while (amount >= coins[i]) {
                amount -= coins[i];
                ++counts[i];
            }

            count += counts[i];

            if (amount == 0 && count < minCount) {
                minCount = count;
            }
        }
    }
}

// LeetCode Question URL: https://leetcode.com/problems/partition-equal-subset-sum/
// LeetCode Discuss URL:

/**
 * Dynamic Programming Using 1D array.
 *
 * Let us assume dp[i][s] means whether the specific sum s can be gotten from
 * the first i numbers. If we can pick such a series of numbers from 0-i whose
 * sum is S, dp[i][s] is true, otherwise it is false.
 *
 * DP[i][s] = DP[i-1][s] || DP[i-1][s - nums[i]]
 *
 * Transition function: For each number, if we don't pick it, dp[i][s] =
 * dp[i-1][s], which means if the first i-1 elements has made it to s, dp[i][s]
 * would also make it to s (we can just ignore nums[i]). If we pick nums[i].
 * dp[i][s] = dp[i-1][s-nums[i]], which represents that s is composed of the
 * current value nums[i] and the remaining composed of other previous numbers.
 * Thus, the transition function is dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i]]
 *
 * In 1D this can be reduced to DP[s] = DP[s] || DP[s - nums[i]] in backward
 * direction.
 *
 * Explanation of the logic refer to comments of previous solution.
 *
 * Time Complexity: O(N + N * S/2) = O(N * S)
 *
 * Space Complexity: O(S/2+1) = O(S)
 *
 * N = Length of the input array. S = Sum of all elements of the input array.
 */
class Solution1 {
    public boolean canPartition(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int numsLen = nums.length;
        // Empty array can be divided in two empty subsets.
        if (numsLen == 0) {
            return true;
        }
        if (numsLen == 1) {
            return nums[0] == 0;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        // if (sum % 2 != 0) {
        if ((sum & 1) != 0) {
            return false;
        }

        // sum /= 2;
        sum >>= 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int n : nums) {
            if (sum >= n && dp[sum - n]) {
                return true;
            }
            for (int s = sum; s >= n; s--) {
                dp[s] |= dp[s - n];
            }
        }

        return false;
    }
}

/**
 * Dynamic Programming (2D Array).
 *
 * Actually, this is a 0/1 knapsack problem, for each number, we can pick it or
 * not.
 *
 * Let us assume dp[i][s] means whether the specific sum s can be gotten from
 * the first i numbers. If we can pick such a series of numbers from 0-i whose
 * sum is S, dp[i][s] is true, otherwise it is false.
 *
 * Base case: dp[0][0] is true; (zero number consists of sum 0 is true)
 *
 * Transition function: For each number, if we don't pick it, dp[i][s] =
 * dp[i-1][s], which means if the first i-1 elements has made it to s, dp[i][s]
 * would also make it to s (we can just ignore nums[i]). If we pick nums[i].
 * dp[i][s] = dp[i-1][s-nums[i]], which represents that s is composed of the
 * current value nums[i] and the remaining composed of other previous numbers.
 * Thus, the transition function is dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i]]
 *
 * Time Complexity: O(N * S)
 *
 * Space Complexity: O(N * S)
 *
 * N = Length of the input array. S = Sum of all elements of the input array.
 */
class Solution2 {
    public boolean canPartition(int[] nums) {
        if (nums == null) {
            return false;
        }
        int len = nums.length;
        // Empty array can be divided in two empty subsets.
        if (len == 0) {
            return true;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;

        boolean[][] dp = new boolean[len + 1][sum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= len; i++) {
            dp[i][0] = true;
            for (int s = 1; s <= sum; s++) {
                if (s >= nums[i - 1]) {
                    dp[i][s] = dp[i - 1][s] || dp[i - 1][s - nums[i - 1]];
                } else {
                    dp[i][s] = dp[i - 1][s];
                }
            }
        }
        return dp[len][sum];
    }
}

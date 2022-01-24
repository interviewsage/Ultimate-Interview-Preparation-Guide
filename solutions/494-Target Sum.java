// LeetCode Question URL: https://leetcode.com/problems/target-sum/
// LeetCode Discuss URL:

/**
 * Dynamic Programming.
 *
 * As per the question, Sum(P) + Sum(N) = Sum(nums). Also, Sum(P) - Sum(N) =
 * target. Thus if we add these two equations, we will get:
 *
 * 2 * Sum(P) = target + Sum(nums)
 *
 * OR
 *
 * Sum(P) = (target + Sum(nums)) / 2
 *
 * One we have the target sum of positive numbers, the problem reduces to
 * 416-Partition Equal Subset Sum question. Refer to this question's solution
 * for explanation on how to find the subset.
 * (https://leetcode.com/problems/partition-equal-subset-sum)
 *
 * Only change here, we have to return the number of ways we can create these
 * subsets. Thus DP transformation will modify to:
 *
 * DP[i][s] = DP[i-1][s] + DP[i-1][s - nums[i]]
 *
 * This problem has the same explanation for finding the subset.
 *
 * Time Complexity: O(N * T)
 *
 * Space Complexity: O(T)
 *
 * N = Length of the input array. T = (Sum of numbers in array + Given Sum) / 2
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        // If T is negative, then we can just swap P & N in the equation to make target
        // positive.
        // Before: P - N = T. After Multiplying by -1 ==> -P + N == -T
        target = Math.abs(target);

        if (target > sum || (sum - target) % 2 != 0) {
            return 0;
        }

        int sumP = (sum + target) / 2;
        int[] dp = new int[sumP + 1];
        dp[0] = 1;

        for (int n : nums) {
            for (int s = sumP; s >= n; s--) {
                dp[s] += dp[s - n];
            }
        }

        return dp[sumP];
    }
}

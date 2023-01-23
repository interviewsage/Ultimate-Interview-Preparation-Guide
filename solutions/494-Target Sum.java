// LeetCode Question URL: https://leetcode.com/problems/target-sum/
// LeetCode Discuss URL:

/**
 * Dynamic Programming.
 *
 * As per the question, Sum(P) + Sum(N) = Sum(nums). Also, Sum(P) - Sum(N) =
 * target. Thus if we subtract these two equations, we will get:
 *
 * 2 * Sum(N) = Sum(nums) - target
 *
 * OR
 *
 * Sum(N) = (Sum(nums) - target) / 2
 *
 * One we have the target sum of negative numbers, the problem reduces to
 * 416-Partition Equal Subset Sum question. Refer to this question's solution
 * for explanation on how to find the subset.
 * (https://leetcode.com/problems/partition-equal-subset-sum)
 *
 * Only change here, we have to return the number of ways we can create these
 * subsets. Thus DP transformation will modify to:
 *
 * <pre>
 * DP[i][s] = Total Number of subsets with sum 's' using numbers from '0..i'
 *          = If we do not pick current number at 'i', then number of subsets from '0..i-1' which sum to 's'
 *                                  +++++
 *            If we pick 'i', then no. of subsets from '0..i-1' which sum to 's - nums[i]'
 *
 * DP[i][s] = DP[i-1][s] + DP[i-1][s - nums[i]]
 * </pre>
 *
 * This problem has the same explanation for finding the subset.
 *
 * Time Complexity: O(N + N * Sum_Of_Negative)
 *
 * Space Complexity: O(Sum_Of_Negative)
 *
 * N = Length of the input array.
 * Sum_Of_Negative = (Sum of numbers in array - Target) / 2
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int numsLen = nums.length;
        if (numsLen == 0) {
            return target == 0 ? 1 : 0;
        }
        if (numsLen == 1) {
            return nums[0] == Math.abs(target) ? 1 : 0;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        // if (sum < Math.abs(target) || (sum - target) % 2 != 0) {
        if (sum < Math.abs(target) || ((sum - target) & 1) != 0) {
            return 0;
        }

        // int sumN = (sum - target) / 2;
        int sumN = (sum - target) >> 1;
        int[] dp = new int[sumN + 1];
        dp[0] = 1;

        for (int n : nums) {
            for (int s = sumN; s >= n; s--) {
                dp[s] += dp[s - n];
            }
        }

        return dp[sumN];
    }
}

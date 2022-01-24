// LeetCode Question URL: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Backtracking Solution
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/108730/JavaC++Straightforward-dfs-solution
 * 2. https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/108730/JavaC++Straightforward-dfs-solution/140989
 * 3. https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solution/
 * 4. https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/180014/Backtracking-x-2
 * 5. https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/180014/Backtracking-x-2/253356
 * </pre>
 *
 * Time Complexity: O(K * 2^N) -- We will find all possible subsets for each K.
 *
 * Space Complexity: O(2*N + K) -> Visited (N) + Recursion Depth (N + K)
 *
 * N = Length of the input nums array.
 */
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length < k) {
            return false;
        }
        if (k == 1) {
            return true;
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }

        int len = nums.length;
        sum /= k;

        // The runtime can be further improved by initially sorting the given array in
        // decreasing order.
        // Refer Approach 2 in
        // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/solution/
        Arrays.sort(nums);
        reverse(nums);

        // curSize & remaining can be ignored if the numbers in the input array are
        // guaranteed to be greater than 0.

        return canPartition(nums, new boolean[len], 0, k, 0, len, 0, sum);
    }

    private boolean canPartition(int[] nums, boolean[] visited, int start, int k, int curSize, int remaining,
            int curSum, int targetSum) {
        if (k == 1) {
            // This can be replaced with true if all numbers in the input array are
            // guaranteed to be greater than 0.
            return remaining > 0;
        }

        if (curSum == targetSum && curSize > 0) {
            return canPartition(nums, visited, 0, k - 1, 0, remaining, 0, targetSum);
        }

        for (int i = start; i < nums.length; i++) {
            // Remove `curSum + nums[i] > targetSum` if negative numbers and zeros are
            // allowed.
            if (visited[i] || curSum + nums[i] > targetSum) {
                continue;
            }

            visited[i] = true;
            if (canPartition(nums, visited, i + 1, k, curSize + 1, remaining - 1, curSum + nums[i], targetSum)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }

    private void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

class Solution2 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length < k) {
            return false;
        }
        if (k == 1) {
            return true;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        sum /= k;
        boolean[] visited = new boolean[nums.length];

        // curSize can be ignored if the numbers in the input array are guaranteed to be
        // greater than 0.

        return canPartition(nums, visited, 0, k, 0, 0, sum);
    }

    private boolean canPartition(int[] nums, boolean[] visited, int start, int k, int curSize, int curSum,
            int targetSum) {
        if (k == 1) {
            // return true;
            return curSize
        }
        // Below if condition is only for positive numbers. Please remove if negative
        // numbers and zeros are allowed.
        if (curSum > targetSum) {
            return false;
        }
        if (curSum == targetSum && curSize > 0) {
            return canPartition(nums, visited, 0, k - 1, 0, 0, targetSum);
        }

        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (canPartition(nums, visited, i + 1, k, curSize + 1, curSum + nums[i], targetSum)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
}
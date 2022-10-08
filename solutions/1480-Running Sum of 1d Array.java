// LeetCode Question URL: https://leetcode.com/problems/running-sum-of-1d-array/
// LeetCode Discuss URL:

/**
 * One Pass solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int[] runningSum(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        return nums;
    }
}

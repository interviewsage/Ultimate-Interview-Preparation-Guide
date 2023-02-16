// LeetCode Question URL: https://leetcode.com/problems/longest-continuous-increasing-subsequence/
// LeetCode Discuss URL:

/**
 * Sliding Window
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input nums array.
 */
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int maxLen = 1;
        int curLen = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i]) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
            }
        }

        return Math.max(maxLen, curLen);
    }
}
